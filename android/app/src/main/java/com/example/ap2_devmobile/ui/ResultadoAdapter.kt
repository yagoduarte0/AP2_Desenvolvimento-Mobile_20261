package com.example.ap2_devmobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ap2_devmobile.R
import com.example.ap2_devmobile.model.Resultado

class ResultadoAdapter(private val resultados: List<Resultado>) :
    RecyclerView.Adapter<ResultadoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPerfil: TextView = view.findViewById(R.id.tvPerfilItem)
        val tvPontuacao: TextView = view.findViewById(R.id.tvPontuacaoItem)
        val tvData: TextView = view.findViewById(R.id.tvDataItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resultado, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultado = resultados[position]
        holder.tvPerfil.text = resultado.perfil
        holder.tvPontuacao.text = "${resultado.pontuacao}% de compatibilidade"
        holder.tvData.text = resultado.criado_em
    }

    override fun getItemCount() = resultados.size
}