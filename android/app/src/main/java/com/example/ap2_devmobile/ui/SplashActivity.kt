package com.example.ap2_devmobile.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ap2_devmobile.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val title = findViewById<TextView>(R.id.tvTitulo)
        val subtitle = findViewById<TextView>(R.id.tvSubtitulo)
        val progress = findViewById<ProgressBar>(R.id.progressBar)

        title.alpha = 0f
        subtitle.alpha = 0f
        progress.alpha = 0f

        title.animate().alpha(1f).setDuration(800).start()
        subtitle.animate().alpha(1f).setDuration(800).setStartDelay(400).start()
        progress.animate().alpha(1f).setDuration(800).setStartDelay(800).start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, CadastroActivity::class.java))
            finish()
        }, 3000)
    }
}