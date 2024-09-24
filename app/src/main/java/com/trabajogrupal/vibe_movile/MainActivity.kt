package com.trabajogrupal.vibe_movile


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val register = findViewById<Button>(R.id.register)
        register.setOnClickListener {
            register()
        }

        val seccionstart = findViewById<Button>(R.id.seccion_start)
        seccionstart.setOnClickListener {
            seccionstart()
        }
    }

    private fun register() {
        val i = Intent(this, RegisterActivity2::class.java)
        startActivity(i)
    }

    private fun seccionstart() {
        val i = Intent(this, LoginActivity2::class.java)
        startActivity(i)

    }
}