package com.example.videocallingapp

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.videocallingapp.databinding.ActivityLoginBinding
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {
    private lateinit var myuserID:EditText
    private lateinit var loginButton:Button

//    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

    myuserID=findViewById(R.id.myIdUser)
    myuserID.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.white))
    loginButton=findViewById(R.id.loginButton)
     loginButton.setOnClickListener {
            val userId=myuserID.text.toString()
           if (userId.isNotEmpty()){
               val intent= Intent(this@LoginActivity,MainActivity::class.java)
               intent.putExtra("userId",userId)
               startActivity(intent)
               userIdSetUp(userId)
           }
        }
    }
    private fun userIdSetUp(userId: String) {
        val application: Application = application
        val appID: Long =1795563446
        val appSign: String = "c50a8875af5c1f389cbfcb63768702d86c479f2cb62472a5cd001a3b80a9e4c1"
        val userName: String = userId
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()

        // تحقق من صحة appID و appSign قبل التهيئة
        if (appID != 0L && appSign.isNotEmpty()) {
            ZegoUIKitPrebuiltCallService.init(application, appID, appSign, userId, userName, callInvitationConfig)
        } else {
            Log.e("LoginActivity", "Invalid appID or appSign")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}