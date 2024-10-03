package com.trabajogrupal.vibe_movile


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.despleg_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as
                SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    loadUrlInWebView(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return false
    }
    private fun loadUrlInWebView(url: String) {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.userAgentString = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36"
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
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