package com.example.clientesapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "https://app1.celso.dev.br/clientes-app/api/"

    // Parte usada para fazer a conexão e a conversao para se conectar com uma api
    private val retrofitFactory =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun getClienteService(): ClienteService {
        return retrofitFactory.create(ClienteService::class.java)
    }
}