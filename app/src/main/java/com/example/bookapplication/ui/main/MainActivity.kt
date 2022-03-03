package com.example.bookapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.bookapplication.R
import com.example.bookapplication.databinding.ActivityMainBinding
import com.example.bookapplication.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private var cal = Calendar.getInstance()
    private var publicationDate = ""
    private var emailReceived : String? = ""
    private var passwordReceived : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        auth = Firebase.auth

      /*val credential = intent.extras
        if (credential != null){
            emailReceived = credential.getString("email")
            passwordReceived = credential.getString("password")
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_YEAR, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            mainBinding.publicationDateButton.text = publicationDate
        }

        with(mainBinding) {

            publicationDateButton.setOnClickListener{
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener {
                if (nameBookEditText.text?.isEmpty() == true ||
                    nameAuthorEditText.text?.isEmpty() == true ||
                    pagesEditText.text?.isEmpty() == true
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Debe digitar nombre, autor y número de páginas",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val nameBook = nameBookEditText.text.toString()
                    val author = nameAuthorEditText.text.toString()
                    val pages = pagesEditText.text.toString().toInt()
                    val abstract = abstractEditText.text.toString()

                    var genre = ""
                    if (suspenseCheckBox.isChecked) genre = "Suspenso"
                    if (terrorCheckBox.isChecked) genre += "Terror"
                    if (infantileCheckBox.isChecked) genre += "Infantil"
                    if (fictionCheckBox.isChecked) genre += "Ficción"

                   // var score = if (oneRadioButton.isChecked) 1 else 2
                    val score = when {
                        oneRadioButton.isChecked -> 1
                        twoRadioButton.isChecked -> 2
                        threeRadioButton.isChecked -> 3
                        fourRadioButton.isChecked -> 4
                        else -> 5
                    }

                    infoTextView.text =
                        getString(
                            R.string.info,
                            nameBook,
                            author,
                            pages,
                            abstract,
                            genre,
                            score,
                            publicationDate)
                    }
            }
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sing_out -> {
                auth.signOut()
                goToLoginActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email", emailReceived)
        intent.putExtra("password", passwordReceived)
        intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}