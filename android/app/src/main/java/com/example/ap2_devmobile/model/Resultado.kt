package com.example.ap2_devmobile.model

data class Resultado(
    val id: Int,
    val usuario_id: Int,
    val perfil: String,
    val pontuacao: Int,
    val criado_em: String
)

data class ResultadoCreate(
    val usuario_id: Int,
    val perfil: String,
    val pontuacao: Int
)