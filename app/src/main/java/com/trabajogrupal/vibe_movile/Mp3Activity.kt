package com.trabajogrupal.vibe_movile



import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class Mp3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mp3)

        val songListLayout = findViewById<LinearLayout>(R.id.songList)

        val songData= listOf(
            Pair("OHNANA - Kapo",R.drawable.ohnana),
            Pair("Sabrina Carpenter - Taste ",R.drawable.taste),
            Pair("LISA - NEW WOMAN feat. Rosalía ",R.drawable.woman),
            Pair("Myke Towers & Peso Pluma - SE TE NOTA ", R.drawable.nota),
            Pair("Ana Mena, Emilia - CARITA TRISTE ",R.drawable.caritatriste),
            Pair("The Weeknd - Dancing In The Flames", R.drawable.flames),
            Pair("Dani Fernández - Dile a los demás", R.drawable.dile),
            Pair("KAROL G - Si Antes Te Hubiera Conocido",R.drawable.karolg)

        )


        for (song in songData) {
            val songLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(16, 10, 16, 30) } // 30dp de margen inferior entre canciones
                setPadding(16, 10, 16, 16)
            }

            val songImageView = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(120, 120) // Ajusta el tamaño de la imagen
                setImageResource(song.second)
            }

            val songTextView = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(16, 0, 0, 0) } // Margen entre imagen y texto
                text = song.first
                textSize = 16f
                setTextColor(ContextCompat.getColor(this@Mp3Activity, R.color.white))

                setOnClickListener {
                    navigateToReproductorActivity(song.first)
                }
            }
            songLayout.addView(songImageView)
            songLayout.addView(songTextView)

            songListLayout.addView(songLayout)
        }

        val play = findViewById<Button>(R.id.button)
        play.setOnClickListener {
            play()
        }

        val top = findViewById<ImageButton>(R.id.play)
        top.setOnClickListener {
            top()
        }
    }



    private fun play() {
        val i = Intent(this, ReproductorActivity::class.java)
        startActivity(i)
    }

    private fun top() {
        val i = Intent(this, ReproductorActivity::class.java)
        startActivity(i)
    }
    private fun navigateToReproductorActivity(selectedSong: String){
        val songList = arrayListOf(
            "OHNANA - Kapo",
            "Sabrina Carpenter - Taste",
            "LISA - NEW WOMAN feat. Rosalía",
            "Myke Towers & Peso Pluma - SE TE NOTA",
            "Ana Mena, Emilia - CARITA TRISTE",
            "The Weeknd - Dancing In The Flames",
            "Dani Fernández - Dile a los demás",
            "KAROL G - Si Antes Te Hubiera Conocido"
        )

        val songIndex = songList.indexOf(selectedSong) // Obtener el índice de la canción seleccionada
        val intent = Intent(this, ReproductorActivity::class.java)
        intent.putStringArrayListExtra("SONG_LIST", songList)
        intent.putExtra("SONG_INDEX", songIndex) // Pasar el índice
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.despleg_menu,menu)
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
            R.id.img-> {
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


