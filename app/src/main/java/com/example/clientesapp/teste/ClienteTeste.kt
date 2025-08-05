package com.example.clientesapp.teste

import com.example.clientesapp.model.Cliente
import com.example.clientesapp.service.RetrofitFactory

fun main() {

    val c1 = Cliente(
        nome = "ribamar",
        email = "ribamardovasco@melhor.do.mundo.com.br"
    )

   val retrofit = RetrofitFactory().getClienteService()
   val cliente = retrofit.cadastrarCliente(c1)


}