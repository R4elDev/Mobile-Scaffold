package com.example.clientesapp.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.clientesapp.model.Cliente
import com.example.clientesapp.service.RetrofitFactory
import com.example.clientesapp.ui.theme.ClientesappTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun FormClient(navController: NavHostController) {


    // Variaveis de estado para utilizar no outlined
    var nomeCLiente by remember {
        mutableStateOf(value = "")
    }

    var emailCliente by remember {
        mutableStateOf(value = "")
    }

    // Variaveis de estado para validar a entrada do usuário
    var isNomeError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    // Funçao para validar se o usuario esta digitando as coisas corretas
    fun validar(): Boolean{
        isNomeError = nomeCLiente.length < 1
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()

        return !isNomeError && !isEmailError
    }

    // Variável que vai exibir a caixa de diálogo
    var mostrarTelaSucesso by remember {
        mutableStateOf(value = false)
    }


    // Criar uma instancia do RetrofitFactory
    val clienteApi = RetrofitFactory().getClienteService()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription =  "Iconde do cadastro",
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
            Text(
                text = "Novo Cliente",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        OutlinedTextField(
            value = nomeCLiente,
            onValueChange = {
                nome -> nomeCLiente = nome
            },
            label = {
                Text(text = "Nome do cliente")
            },
            supportingText = {
                if(isNomeError){
                    Text(text = "Nome do cliente é obrigatório")
                }

            },
            isError = isNomeError,
            trailingIcon = {
                if(isNomeError){
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Icone de erro do email",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Icone do nome do cliente",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = emailCliente,
            onValueChange = {
                email -> emailCliente = email
            },
            label = {
                Text(text = "Email do cliente")
            },
            supportingText = {
                if(isEmailError){
                    Text(text = "E-mail do cliente é obrigatório")
                }

            },
            isError = isEmailError,
            trailingIcon = {
                if(isEmailError){
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Icone de erro do email",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }else {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Icone do email do cliente",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            onClick = {
                // Criar um cliente com os dados informados
                if(validar()){
                    val cliente = Cliente(
                        nome = nomeCLiente,
                        email = emailCliente
                    )

                    // Requisiçao POST para a API
                    // GlobalScope quando estiver dentro de um botao
                    GlobalScope.launch(Dispatchers.IO) {                     // Espera o servidor devolver o cliente ( IMPORTANTE )
                        val novoCLiente = clienteApi.cadastrarCliente(cliente).await()
                        mostrarTelaSucesso = true
                    }
                }else {
                    println("****** OS DADOS ESTÃO INCORRETOS *******")
                }
            }
        ) {
            Text(text = "CADASTRAR CLIENTE")
        }

        if(mostrarTelaSucesso){
            // Elemento Usado para fazer modals de alert caso algo acontece, usar ele quando der errado uma hcamada ou caso ela funcione
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "Cadastro Realizado!",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = { Text(text = "CADASTRO REALIZADO COM SUCESSO!") },
                confirmButton = {
                    Button(
                        onClick = {navController.navigate(route = "Home")}
                    ) {
                        Text(text = "Voltar")
                    }
                }
            )
        }
    }
}






