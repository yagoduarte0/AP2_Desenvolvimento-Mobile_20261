package com.example.ap2_devmobile.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ap2_devmobile.R
import com.example.ap2_devmobile.model.ResultadoCreate
import com.example.ap2_devmobile.network.RetrofitClient
import kotlinx.coroutines.launch

class QuizActivity : AppCompatActivity() {

    private val perguntas = listOf(
        Pair("O que você prefere fazer?", listOf("Construir APIs e sistemas", "Criar interfaces bonitas", "Automatizar processos", "Analisar dados e métricas")),
        Pair("Qual ferramenta te interessa mais?", listOf("Docker e Kubernetes", "Figma e CSS", "Python e scripts", "Jupyter e Excel")),
        Pair("Como você prefere trabalhar?", listOf("No servidor, bastidores", "No que o usuário vê", "Otimizando fluxos", "Interpretando números")),
        Pair("Qual área te parece mais empolgante?", listOf("Cloud e infraestrutura", "UX e design de produto", "RPA e automação", "Business Intelligence")),
        Pair("O que você mais curte aprender?", listOf("Arquitetura de sistemas", "Design patterns de UI", "Integração de sistemas", "Estatística e ML"))
    )

    private val perfis = listOf("Backend Developer", "Frontend Developer", "DevOps Engineer", "Data Analyst")
    private val pontuacao = IntArray(4)
    private val respostas = IntArray(5) { -1 }
    private var perguntaAtual = 0
    private var usuarioId = 0
    private var usuarioNome = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        usuarioId = intent.getIntExtra("usuario_id", 0)
        usuarioNome = intent.getStringExtra("usuario_nome") ?: ""

        findViewById<TextView>(R.id.tvNomeUsuario).text = "Olá, $usuarioNome!"

        carregarPergunta()

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            if (perguntaAtual > 0) {
                val respostaAnterior = respostas[perguntaAtual]
                if (respostaAnterior != -1) pontuacao[respostaAnterior]--
                perguntaAtual--
                carregarPergunta()
            }
        }

        findViewById<Button>(R.id.btnProximo).setOnClickListener {
            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
            val selecionado = radioGroup.checkedRadioButtonId

            if (selecionado == -1) {
                Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val indice = when (selecionado) {
                R.id.rb1 -> 0
                R.id.rb2 -> 1
                R.id.rb3 -> 2
                else -> 3
            }

            val respostaAnterior = respostas[perguntaAtual]
            if (respostaAnterior != -1) pontuacao[respostaAnterior]--
            pontuacao[indice]++
            respostas[perguntaAtual] = indice

            perguntaAtual++

            if (perguntaAtual < perguntas.size) {
                carregarPergunta()
            } else {
                finalizarQuiz()
            }
        }
    }

    private fun carregarPergunta() {
        val fragment = QuizFragment.newInstance(perguntaAtual + 1, perguntas[perguntaAtual].first)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        findViewById<ProgressBar>(R.id.progressQuiz).progress = perguntaAtual + 1
        findViewById<RadioGroup>(R.id.radioGroup).clearCheck()
        findViewById<Button>(R.id.btnVoltar).visibility =
            if (perguntaAtual == 0) View.GONE else View.VISIBLE

        val rbs = listOf(R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4)
        rbs.forEachIndexed { i, id ->
            findViewById<RadioButton>(id).text = perguntas[perguntaAtual].second[i]
        }

        val respostaAnterior = respostas[perguntaAtual]
        if (respostaAnterior != -1) {
            val rb = rbs[respostaAnterior]
            findViewById<RadioButton>(rb).isChecked = true
        }
    }

    private fun finalizarQuiz() {
        val indicePerfil = pontuacao.indices.maxByOrNull { pontuacao[it] } ?: 0
        val perfilFinal = perfis[indicePerfil]
        val pontuacaoFinal = pontuacao[indicePerfil] * 20

        lifecycleScope.launch {
            try {
                RetrofitClient.api.salvarResultado(
                    ResultadoCreate(usuarioId, perfilFinal, pontuacaoFinal)
                )
                val intent = Intent(this@QuizActivity, ResultadoActivity::class.java)
                intent.putExtra("perfil", perfilFinal)
                intent.putExtra("pontuacao", pontuacaoFinal)
                intent.putExtra("usuario_id", usuarioId)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@QuizActivity, "Erro: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}