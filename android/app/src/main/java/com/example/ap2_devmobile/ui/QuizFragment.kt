package com.example.ap2_devmobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ap2_devmobile.R

class QuizFragment : Fragment() {

    companion object {
        fun newInstance(numeroPergunta: Int, textoPergunta: String): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt("numero", numeroPergunta)
            args.putString("pergunta", textoPergunta)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        view.findViewById<TextView>(R.id.tvNumeroPergunta).text =
            "Pergunta ${arguments?.getInt("numero")} de 5"
        view.findViewById<TextView>(R.id.tvPerguntaFragment).text =
            arguments?.getString("pergunta")
        return view
    }
}