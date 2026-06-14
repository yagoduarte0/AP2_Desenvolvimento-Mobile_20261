package com.example.ap2_devmobile.network

import com.example.ap2_devmobile.model.Resultado
import com.example.ap2_devmobile.model.Usuario
import com.example.ap2_devmobile.model.UsuarioCreate
import com.example.ap2_devmobile.model.ResultadoCreate
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.DELETE

interface ApiService {

    @POST("usuarios/")
    suspend fun criarUsuario(@Body usuarioCreate: UsuarioCreate): Usuario

    @GET("usuarios/{id}")
    suspend fun buscarUsuario(@Path("id") id: Int): Usuario

    @POST("resultados/")
    suspend fun salvarResultado(@Body resultado: ResultadoCreate): Resultado

    @GET("resultados/{usuario_id}")
    suspend fun buscarResultados(@Path("usuario_id") usuarioId: Int): List<Resultado>

    @PUT("usuarios/{id}")
    suspend fun atualizarUsuario(@Path("id") id: Int, @Body usuario: UsuarioCreate): Usuario

    @DELETE("usuarios/{id}")
    suspend fun deletarUsuario(@Path("id") id: Int)
}