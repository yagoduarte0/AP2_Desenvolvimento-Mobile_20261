package com.example.ap2_devmobile.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ap2_devmobile.R
import com.example.ap2_devmobile.network.RetrofitClient
import kotlinx.coroutines.launch

class HistoricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        val usuarioId = intent.getIntExtra("usuario_id", 0)
        val recycler = findViewById<RecyclerView>(R.id.recyclerHistorico)
        recycler.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val resultados = RetrofitClient.api.buscarResultados(usuarioId)
                recycler.adapter = ResultadoAdapter(resultados)
            } catch (e: Exception) {
                Toast.makeText(this@HistoricoActivity, "Erro: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}