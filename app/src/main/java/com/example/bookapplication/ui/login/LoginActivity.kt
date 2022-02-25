package com.example.bookapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bookapplication.ui.register.RegisterActivity
import com.example.bookapplication.databinding.ActivityLoginBinding
import com.example.bookapplication.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        auth = Firebase.auth

        var emailReceived : String? = ""
        var passwordReceived : String? = ""

       /* val credential = intent.extras
        if (credential != null){
            emailReceived = credential.getString("email")
            passwordReceived = credential.getString("password")
        }*/

        loginBinding.registerTextView.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(loginBinding){
            singInButton.setOnClickListener{
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty() ){
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success")
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                                /* intent.putExtra("email", email)
                                   intent.putExtra("password", password)*/
                                startActivity(intent)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}