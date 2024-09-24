package com.trabajogrupal.vibe_movile


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.widget.SeekBar


class ReproductorActivity : AppCompatActivity() {

    private lateinit var  elapsedTimeTextView: TextView
    private lateinit var totalTimeTextView: TextView
    private lateinit var mediaPlayer: MediaPlayer
    private var currentSongIndex = 0
    private lateinit var songTitleTextView: TextView
    private lateinit var songImageView: ImageView
    private lateinit var seekBar: SeekBar
    private val handler = Handler()

    // Agregamos el nombre de la cancion
    private val songNames = arrayOf("OHNANA - Kapo","Sabrina Carpenter - Taste ","LISA - NEW WOMAN feat. Rosalía ","Myke Towers & Peso Pluma - SE TE NOTA ","Ana Mena, Emilia - CARITA TRISTE ","The Weeknd - Dancing In The Flames","Dani Fernández - Dile a los demás","KAROL G - Si Antes Te Hubiera Conocido")
    private val songs = arrayOf(R.raw.ohnana, R.raw.taste, R.raw.newwoman, R.raw.setenota, R.raw.caritatriste, R.raw.dancingintheflames, R.raw.dilealosdemas, R.raw.siantestehubieraconocido)

    private val songImages = arrayOf(
        R.drawable.ohnana,
        R.drawable.taste,
        R.drawable.woman,
        R.drawable.nota,
        R.drawable.caritatriste,
        R.drawable.flames,
        R.drawable.dile,
        R.drawable.karolg
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reproductor)


        //Inicializar ImageView
        songImageView = findViewById(R.id.imagencancion)

        // Inicializar TextView
        songTitleTextView = findViewById(R.id.nombreCancion)
        updateSongTitle()
        elapsedTimeTextView = findViewById(R.id.elapsedTime)
        totalTimeTextView = findViewById(R.id.totalTime)

        //Inicializar SeekBar
        seekBar = findViewById(R.id.seekBar)
        seekBar.max = 100


        //Inicializar con la primera canción
        mediaPlayer = MediaPlayer.create(this, songs[currentSongIndex])

        val btnPlay = findViewById<ImageButton>(R.id.play)
        val btnPause = findViewById<ImageButton>(R.id.pause)
        val btnNext = findViewById<ImageButton>(R.id.next)
        val btnPrevious = findViewById<ImageButton>(R.id.prev)

        updateSongImage()


        btnPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                startUpdatingSeekBar()
            }
        }
        btnPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                stopUpdatingSeekBar()
            }
        }
        btnNext.setOnClickListener {
            playNextSong()
        }
        btnPrevious.setOnClickListener {
            playPreviousSong()
        }
        // configuramos el SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser && mediaPlayer.isPlaying){
                    mediaPlayer.seekTo(progress * mediaPlayer.duration / 100)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
    private fun startUpdatingSeekBar(){
        handler.postDelayed(object  : Runnable {
            override fun run(){
                if(::mediaPlayer.isInitialized && mediaPlayer.isPlaying){
                    val currentPosition = mediaPlayer.currentPosition
                    val progress = currentPosition * 100 / mediaPlayer.duration
                    val duration = mediaPlayer.duration
                    seekBar.progress = progress

                    //Actualizar TextView
                    elapsedTimeTextView.text=formatTime(currentPosition)
                    totalTimeTextView.text = formatTime(duration)

                    handler.postDelayed(this, 1000)
                }
            }
        }, 0)
    }
    private fun formatTime(millis: Int): String{
        val minutes =millis / 1000 / 60
        val seconds = millis / 1000 % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
    private fun updateTotalTime(){
        totalTimeTextView.text = formatTime(mediaPlayer.duration)
    }
    private fun stopUpdatingSeekBar(){
        handler.removeCallbacksAndMessages(null)
    }
    private fun updateSongTitle(){
        songTitleTextView.text = songNames[currentSongIndex]
    }
    private fun playNextSong(){
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        currentSongIndex =(currentSongIndex + 1) % songs.size
        mediaPlayer.reset()
        mediaPlayer =MediaPlayer.create(this,songs[currentSongIndex])
        updateSongTitle()
        updateSongImage()
        updateTotalTime()
        mediaPlayer.start()
        startUpdatingSeekBar()
    }
    private fun playPreviousSong(){
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        currentSongIndex = if(currentSongIndex > 0)currentSongIndex - 1 else songs.size - 1
        mediaPlayer.reset()
        mediaPlayer = MediaPlayer.create(this,songs[currentSongIndex])
        updateSongTitle()
        updateSongImage()
        updateTotalTime()
        mediaPlayer.start()
        startUpdatingSeekBar()
    }
    private fun updateSongImage(){
        songImageView.setImageResource(songImages[currentSongIndex])
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        stopUpdatingSeekBar()
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
