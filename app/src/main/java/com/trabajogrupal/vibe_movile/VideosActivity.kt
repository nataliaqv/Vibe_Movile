package com.trabajogrupal.vibe_movile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.trabajogrupal.vibe_movile.databinding.ActivityVideosBinding

class VideosActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val url =
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
            goToPlayerPaget(url)
        }

        binding.btn2.setOnClickListener {

            val url =
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
            goToPlayerPaget(url)
        }

        binding.btn3.setOnClickListener {
            val url =
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"
            goToPlayerPaget(url)

        }

    }

    fun goToPlayerPaget(url: String) {
        var intent = Intent(this, MediaPlayerActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.despleg_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Perf -> {

                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)

                true
            }

            R.id.vid -> {
                val intent = Intent(this, VideosActivity::class.java)
                startActivity(intent)

                true
            }

            R.id.web -> {
                val intent = Intent(this, WebActivity::class.java)
                startActivity(intent)

                true
            }

            R.id.img -> {
                val intent = Intent(this, imagenActivity::class.java)
                startActivity(intent)

                true
            }

            R.id.mp3 -> {
                val intent = Intent(this, Mp3Activity::class.java)
                startActivity(intent)

                true
            }

            R.id.rep -> {
                val intent = Intent(this, ReproductorActivity::class.java)
                startActivity(intent)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}