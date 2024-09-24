package com.trabajogrupal.vibe_movile



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trabajogrupal.vibe_movile.databinding.ActivityRegister2Binding

class RegisterActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.registrar.setOnClickListener{
            val signupUsername = binding.editTextText.text.toString()
            val signupEmail= binding.editTextTextEmailAddress3.text.toString()
            val signupPassword = binding.editTextTextPassword.text.toString()
            signupDatabase(signupUsername,signupEmail,signupPassword)
        }
        binding.logingredirect.setOnClickListener{
            val intent =Intent(this,LoginActivity2::class.java)
            startActivity(intent)
            finish()
        }


        val cancelar = findViewById<Button>(R.id.Cancel)
        cancelar.setOnClickListener {
            cancelar()

        }

    }

    private fun cancelar() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun signupDatabase(username: String, email: String, password: String){
        val insertRowId = databaseHelper.insertUser(username,email, password)
        if(insertRowId != -1L){
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
            val intent =Intent(this,LoginActivity2::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Registro Falló",Toast.LENGTH_SHORT).show()
        }
    }
}