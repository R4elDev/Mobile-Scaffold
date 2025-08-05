package com.example.clientesapp.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientesapp.Greeting
import com.example.clientesapp.R
import com.example.clientesapp.ui.theme.ClientesappTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            BarraDeTitulo()
        },
        bottomBar = {
            BarraDeNavegacao()
        },
        floatingActionButton = {
            BotaoCadastrar()
        }

    )
    // Content
    {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Icone da lista de clientes",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lista de clientes"
                )
            }

            LazyColumn {
                items(count = 10){
                    ClientCard()
                }
            }
        }

    }
}

@Composable
fun ClientCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            ),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(86.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(text = "Nome do cliente")
                Text(text = "Email do client")
            }

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Excluir"
            )
        }

    }
}

@Preview
@Composable
private fun ClientCardPreview() {
    ClientesappTheme {
        ClientCard()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTitulo(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "RaelJunior",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "rael@gmail.com",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Card (
                    modifier = Modifier
                        .size(60.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.perfilphoto),
                        contentDescription = "Foto do Perfil",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun BarraDeTituloPreview() {
    ClientesappTheme {
        BarraDeTitulo()
    }
}

@Composable
fun BarraDeNavegacao(modifier: Modifier = Modifier) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            label = {
                Text(
                    text = "Home",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            label = {
                Text(
                    text = "Favoritos",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            label = {
                Text(
                    text = "Menu",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Composable
fun BotaoCadastrar(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botão Adicionar",
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }
}







@Preview
@Composable
private fun BotaoCadastrarPreview() {
    ClientesappTheme {
        BotaoCadastrar()
    }
}

@Preview
@Composable
private fun BarraDeNavegacaoPreview() {
    ClientesappTheme {
        BarraDeNavegacao()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    ClientesappTheme {
        HomeScreen()
    }
}