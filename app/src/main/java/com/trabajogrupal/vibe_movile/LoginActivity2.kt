package com.trabajogrupal.vibe_movile


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trabajogrupal.vibe_movile.databinding.ActivityLogin2Binding
import android.content.Context
import android.view.Menu

class LoginActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate((layoutInflater))
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.Login.setOnClickListener {
            val loginUsername = binding.editTextUsuarioL.text.toString()
            val logingEmail = binding.emaill.text.toString()
            val loginPasswor= binding.editTextTextPassword2.text.toString()
            logingDatabase(loginUsername,logingEmail,loginPasswor)
        }

        binding.register.setOnClickListener{
            val intent = Intent(this, RegisterActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun logingDatabase(username: String,email: String, password: String){
        val userExists = databaseHelper.readUser(username,email,password)
        if (userExists){

            val sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE)
            with(sharedPref.edit()){
                putString("username", username)
                putString("email",email)
                apply()
            }
            Toast.makeText(this, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Mp3Activity::class.java)
            startActivity(intent)
            finish()

        }else{
            Toast.makeText(this, "El Ingreso Falló ", Toast.LENGTH_SHORT).show()

         }
        }
}