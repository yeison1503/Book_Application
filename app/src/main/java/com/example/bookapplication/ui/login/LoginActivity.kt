package com.example.bookapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookapplication.ui.register.RegisterActivity
import com.example.bookapplication.databinding.ActivityLoginBinding
import com.example.bookapplication.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        var emailReceived : String? = ""
        var passwordReceived : String? = ""

        val credential = intent.extras
        if (credential != null){
            emailReceived = credential.getString("email")
            passwordReceived = credential.getString("password")
        }

        loginBinding.registerTextView.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(loginBinding){
            singInButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if ( email == emailReceived && password == passwordReceived && email.isNotEmpty() && password.isNotEmpty() ){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}