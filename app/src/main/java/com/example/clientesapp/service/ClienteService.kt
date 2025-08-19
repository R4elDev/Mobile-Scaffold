package com.example.clientesapp.service

import com.example.clientesapp.model.Cliente
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

// INTERFACE PARA MIOSTRAR QUAIS SÃO OS METODOS QUE IREI USAR NA API
interface ClienteService {

    @POST("clientes")
    fun cadastrarCliente(@Body cliente: Cliente): Call<Cliente>

    @GET("clientes")
    fun exibirTodosOsClientes(): Call<List<Cliente>>

    @GET("clientes/{id}")
    fun buscarClientePorId(@Path("id") id : Long): Call<Cliente>

    // Fazer isso quando na exclusao o metodo requisitar um body ( estranho quase nunca acontece )
    @HTTP(method = "DELETE", path = "clientes", hasBody = true)
    suspend fun excluirCliente(@Body cliente: Cliente): Response<Unit>

    @PUT("clientes")
    fun atualizarClientes(@Body cliente: Cliente): Call<Cliente>


}