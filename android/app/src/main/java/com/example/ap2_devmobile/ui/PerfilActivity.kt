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

class PerfilActivity : AppCompatActivity() {

    private var usuarioId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        usuarioId = intent.getIntExtra("usuario_id", 0)
        val etNome = findViewById<EditText>(R.id.etNomePerfil)
        val etEmail = findViewById<EditText>(R.id.etEmailPerfil)

        // Carrega dados atuais do usuário
        lifecycleScope.launch {
            try {
                val usuario = RetrofitClient.api.buscarUsuario(usuarioId)
                etNome.setText(usuario.nome)
                etEmail.setText(usuario.email)
            } catch (e: Exception) {
                Toast.makeText(this@PerfilActivity, "Erro ao carregar perfil", Toast.LENGTH_SHORT).show()
            }
        }

        // Atualizar dados
        findViewById<Button>(R.id.btnSalvarPerfil).setOnClickListener {
            val nome = etNome.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (nome.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    RetrofitClient.api.atualizarUsuario(usuarioId, UsuarioCreate(nome, email))
                    Toast.makeText(this@PerfilActivity, "Perfil atualizado!", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this@PerfilActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Deletar conta
        findViewById<Button>(R.id.btnDeletarConta).setOnClickListener {
            lifecycleScope.launch {
                try {
                    RetrofitClient.api.deletarUsuario(usuarioId)
                    Toast.makeText(this@PerfilActivity, "Conta deletada!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@PerfilActivity, CadastroActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this@PerfilActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}