// MainActivity

package com.example.videocallingapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {

    private lateinit var targetUserId: EditText
    private lateinit var videoCall: ZegoSendCallInvitationButton
    private lateinit var myUserIdText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetUserId = findViewById(R.id.targetUserId)
        videoCall = findViewById(R.id.voiceCallButton)
        myUserIdText = findViewById(R.id.myUserIdText)

        val userId = intent.getStringExtra("userId")
        myUserIdText.text = "Hi $userId \n User ID Welcome, do you want to join a video call!"

        // إضافة TextWatcher لتحديث المستخدم الهدف
        targetUserId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val targetId = targetUserId.text.toString()
                targetUserId.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                if (targetId.isNotEmpty()) {
                    startVideoCall(targetId)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun startVideoCall(targetUserId: String) {
        if (::videoCall.isInitialized) {
            videoCall.setIsVideoCall(true) // تفعيل خاصية الفيديو
            videoCall.resourceID = "zego_uikit_call"
            videoCall.setInvitees(listOf(ZegoUIKitUser(targetUserId, targetUserId)))
        } else {
            Log.e("MainActivity", "videoCall button is not initialized")
        }
    }
}
