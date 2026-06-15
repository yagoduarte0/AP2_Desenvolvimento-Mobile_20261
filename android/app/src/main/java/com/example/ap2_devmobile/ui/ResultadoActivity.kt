package com.example.ap2_devmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ap2_devmobile.R

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val perfil = intent.getStringExtra("perfil") ?: ""
        val pontuacao = intent.getIntExtra("pontuacao", 0)
        val usuarioId = intent.getIntExtra("usuario_id", 0)

        findViewById<TextView>(R.id.tvPerfil).text = perfil
        findViewById<TextView>(R.id.tvPontuacao).text = "$pontuacao% de compatibilidade"
        findViewById<ProgressBar>(R.id.progressPontuacao).progress = pontuacao

        setIconePerfil(perfil)

        findViewById<Button>(R.id.btnCompartilhar).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Fiz o TechQuiz e meu perfil é $perfil com $pontuacao% de compatibilidade!")
            startActivity(Intent.createChooser(intent, "Compartilhar via"))
        }

        findViewById<Button>(R.id.btnHistorico).setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            intent.putExtra("usuario_id", usuarioId)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        findViewById<Button>(R.id.btnRecomecar).setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        findViewById<ImageButton>(R.id.btnPerfil).setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            intent.putExtra("usuario_id", usuarioId)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    private fun setIconePerfil(perfil: String) {
        val icone = when (perfil) {
            "Backend Developer" -> R.drawable.ic_backend
            "Frontend Developer" -> R.drawable.ic_frontend
            "DevOps Engineer" -> R.drawable.ic_devops
            "Data Analyst" -> R.drawable.ic_data
            else -> R.drawable.ic_backend
        }
        findViewById<ImageView>(R.id.ivPerfilIcon).setImageResource(icone)
    }
}