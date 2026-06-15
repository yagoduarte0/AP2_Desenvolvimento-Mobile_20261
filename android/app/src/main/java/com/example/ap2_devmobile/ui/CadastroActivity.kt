package com.example.ap2_devmobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ap2_devmobile.R
import com.example.ap2_devmobile.model.UsuarioCreate
import com.example.ap2_devmobile.network.RetrofitClient
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnComecar = findViewById<Button>(R.id.btnComecar)

        btnComecar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val usuario = RetrofitClient.api.criarUsuario(UsuarioCreate(nome, email))
                    val intent = Intent(this@CadastroActivity, QuizActivity::class.java)
                    intent.putExtra("usuario_id", usuario.id)
                    intent.putExtra("usuario_nome", usuario.nome)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@CadastroActivity, "Erro: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}