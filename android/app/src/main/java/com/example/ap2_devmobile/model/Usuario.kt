package com.example.ap2_devmobile.model

data class Usuario(
    val id: Int,
    val nome: String,
    val email: String,
    val criado_em: String
)

data class UsuarioCreate(
    val nome: String,
    val email: String
)