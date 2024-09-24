package com.trabajogrupal.vibe_movile

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class imagenActivity : AppCompatActivity() {

    private val imagenesMusicales = arrayOf(
        R.drawable.rock_genre,
        R.drawable.jazz_genre,
        R.drawable.pop_genre,
        R.drawable.latino_genre,
        R.drawable.hiphop_genre,
        R.drawable.salsa_genre
    )

    private val nombresMusicales = arrayOf(
        "Rock",
        "Jazz",
        "Pop",
        "Latino",
        "Hip-Hop",
        "Salsa"
    )

    // Canciones famosas por género
    private val cancionesPorGenero = mapOf(
        "Rock" to listOf(
            "Bohemian Rhapsody - Queen",
            "Stairway to Heaven - Led Zeppelin",
            "Hotel California - Eagles",
            "Sweet Child O' Mine - Guns N' Roses",
            "Smoke on the Water - Deep Purple",
            "Back in Black - AC/DC",
            "Imagine - John Lennon",
            "Smells Like Teen Spirit - Nirvana",
            "Hey Jude - The Beatles",
            "Comfortably Numb - Pink Floyd"
        ),
        "Jazz" to listOf(
            "So What - Miles Davis",
            "Take Five - Dave Brubeck",
            "My Favorite Things - John Coltrane",
            "Feeling Good - Nina Simone",
            "What a Wonderful World - Louis Armstrong",
            "A Night in Tunisia - Dizzy Gillespie",
            "Round Midnight - Thelonious Monk",
            "Misty - Erroll Garner",
            "Strange Fruit - Billie Holiday",
            "Blue in Green - Miles Davis"
        ),
        "Pop" to listOf(
            "Thriller - Michael Jackson",
            "Like a Prayer - Madonna",
            "Billie Jean - Michael Jackson",
            "Rolling in the Deep - Adele",
            "Baby One More Time - Britney Spears",
            "Shape of You - Ed Sheeran",
            "Firework - Katy Perry",
            "Blinding Lights - The Weeknd",
            "Uptown Funk - Mark Ronson ft. Bruno Mars",
            "Poker Face - Lady Gaga"
        ),
        "Latino" to listOf(
            "Despacito - Luis Fonsi ft. Daddy Yankee",
            "Bailando - Enrique Iglesias",
            "Vivir Mi Vida - Marc Anthony",
            "La Bicicleta - Shakira ft. Carlos Vives",
            "Felices los 4 - Maluma",
            "Mi Gente - J Balvin",
            "Dákiti - Bad Bunny & Jhay Cortez",
            "Propuesta Indecente - Romeo Santos",
            "La Gozadera - Gente de Zona ft. Marc Anthony",
            "Gasolina - Daddy Yankee"
        ),
        "Hip-Hop" to listOf(
            "Lose Yourself - Eminem",
            "Juicy - The Notorious B.I.G.",
            "N.Y. State of Mind - Nas",
            "HUMBLE. - Kendrick Lamar",
            "Sicko Mode - Travis Scott",
            "In Da Club - 50 Cent",
            "Gold Digger - Kanye West ft. Jamie Foxx",
            "God's Plan - Drake",
            "Big Pimpin' - Jay-Z",
            "California Love - 2Pac ft. Dr. Dre"
        ),
        "Salsa" to listOf(
            "Vivir Lo Nuestro - Marc Anthony & La India",
            "A Puro Dolor - Son by Four",
            "La Rebelión - Joe Arroyo",
            "El Gran Varón - Willie Colón",
            "Llorarás - Oscar D'León",
            "Que Manera de Quererte - Gilberto Santa Rosa",
            "Pedro Navaja - Rubén Blades",
            "Periódico de Ayer - Héctor Lavoe",
            "Cali Pachanguero - Grupo Niche",
            "La Vida Es Un Carnaval - Celia Cruz"
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imagen)

        // Creamos un LinearLayout principal
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundResource(R.drawable.fondo1) // Aplicamos el fondo que usabas
        }

        // Creamos el GridView para mostrar las imágenes
        val gridView = GridView(this).apply {
            numColumns = 2
            horizontalSpacing = 30  // Ajustamos el espacio horizontal
            verticalSpacing = 50    // Ajustamos el espacio vertical
            stretchMode = GridView.STRETCH_COLUMN_WIDTH
            gravity = Gravity.CENTER
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(0, 250, 0, 0) // Añadimos margen superior para desplazar hacia abajo
        }

        gridView.adapter = object : BaseAdapter() {
            override fun getCount(): Int = imagenesMusicales.size

            override fun getItem(position: Int): Any = imagenesMusicales[position]

            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val layout: LinearLayout
                if (convertView == null) {
                    layout = LinearLayout(this@imagenActivity).apply {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        setPadding(0, 50, 0, 50)  // Agregamos más espacio vertical
                    }

                    val imageView = ImageView(this@imagenActivity).apply {
                        layoutParams = ViewGroup.LayoutParams(300, 300)  // Ajustamos el tamaño
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }

                    val textView = TextView(this@imagenActivity).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        gravity = Gravity.CENTER
                        text = nombresMusicales[position]
                        setTextColor(getColor(R.color.white))
                    }

                    layout.addView(imageView)
                    layout.addView(textView)
                } else {
                    layout = convertView as LinearLayout
                }

                val imageView = layout.getChildAt(0) as ImageView
                imageView.setImageResource(imagenesMusicales[position])

                val textView = layout.getChildAt(1) as TextView
                textView.text = nombresMusicales[position]

                // Añadir el listener para mostrar las canciones
                layout.setOnClickListener {
                    mostrarCanciones(nombresMusicales[position], gridView, rootLayout)
                }

                return layout
            }
        }

        // Añadimos GridView al layout principal
        rootLayout.addView(gridView)
        setContentView(rootLayout)
    }

    // Función para mostrar las canciones en lugar del GridView
    private fun mostrarCanciones(genero: String, gridView: GridView, rootLayout: LinearLayout) {
        // Eliminamos el GridView de las imágenes
        rootLayout.removeView(gridView)

        // Creamos el ListView para mostrar las canciones
        val listView = ListView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(0, 200, 0, 0)  // Añadimos margen superior para desplazar hacia abajo
        }

        // Obtenemos las canciones del género seleccionado
        val canciones = cancionesPorGenero[genero] ?: emptyList()

        // Creamos un adaptador para las canciones con color blanco en el texto
        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, canciones) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.setTextColor(getColor(R.color.white))  // Color blanco para las canciones
                return view
            }
        }

        listView.adapter = adapter

        // Creamos un botón para regresar al GridView
        val botonRegresar = Button(this).apply {
            text = "Volver a géneros"
            setOnClickListener {
                rootLayout.removeView(listView)
                rootLayout.removeView(this)
                rootLayout.addView(gridView)
            }
        }

        // Añadimos el ListView y el botón de regresar al layout principal
        rootLayout.addView(listView)
        rootLayout.addView(botonRegresar)
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