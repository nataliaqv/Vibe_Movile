package com.trabajogrupal.vibe_movile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.despleg_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.Perf ->{

                val intent = Intent(this,PerfilActivity::class.java)
                startActivity(intent)

                true
            }
            R.id.vid -> {
                val intent = Intent(this,VideosActivity::class.java)
                startActivity(intent)

                true
            }
            R.id.web -> {
                val intent = Intent(this,WebActivity::class.java)
                startActivity(intent)

                true
            }
            R.id.img -> {
                val intent = Intent(this,imagenActivity::class.java)
                startActivity(intent)

                true
            }
            R.id.mp3 -> {
                val intent = Intent(this,Mp3Activity::class.java)
                startActivity(intent)

                true
            }
            R.id.rep -> {
                val intent = Intent(this, ReproductorActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super. onOptionsItemSelected(item)
        }
    }
}