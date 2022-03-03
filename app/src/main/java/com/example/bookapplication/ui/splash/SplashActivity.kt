package com.example.bookapplication.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookapplication.databinding.ActivitySplashBinding
import com.example.bookapplication.ui.Bottom.BottomActivity
import com.example.bookapplication.ui.login.LoginActivity
import com.example.bookapplication.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        auth = Firebase.auth

        val timer = Timer()
        timer.schedule(timerTask {
            if(auth.currentUser == null){
                goToLoginActivity()
            }else{
                goToMainActivity()
            }
        },  2000
        )
        /*
        splashBinding.imageView.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            //finish() //Destreye completamente la actividad
        }*/
    }

    private fun goToMainActivity() {
        val intent = Intent(this, BottomActivity::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
    }
}