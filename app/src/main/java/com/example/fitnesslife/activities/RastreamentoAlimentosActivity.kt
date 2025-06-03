package com.example.fitnesslife.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesslife.ui.theme.FitnessLifeTheme

data class AlimentoConsumido(
    val nome: String,
    val calorias: Int,
    val icone: ImageVector = Icons.Filled.Fastfood // Ícone padrão
)

class RastreamentoAlimentosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessLifeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RastreamentoAlimentosScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RastreamentoAlimentosScreen() {
    var alimentoInput by remember { mutableStateOf("") }
    var caloriasInput by remember { mutableStateOf("") }
    val alimentosConsumidos = remember { mutableStateListOf<AlimentoConsumido>() }
    val totalCalorias = alimentosConsumidos.sumOf { it.calorias }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rastreamento de Alimentos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = alimentoInput,
            onValueChange = { alimentoInput = it },
            label = { Text("Nome do Alimento") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Filled.Restaurant, contentDescription = "Alimento") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = caloriasInput,
            onValueChange = { caloriasInput = it },
            label = { Text("Calorias") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Filled.LocalFireDepartment, contentDescription = "Calorias") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nome = alimentoInput.trim()
                val calorias = caloriasInput.toIntOrNull()
                if (nome.isNotEmpty() && calorias != null && calorias > 0) {
                    alimentosConsumidos.add(AlimentoConsumido(nome, calorias))
                    alimentoInput = ""
                    caloriasInput = ""
                }
                // TODO: Adicionar feedback ao usuário (Toast, Snackbar)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.AddCircle, contentDescription = "Adicionar")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Consumido Hoje:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        if (alimentosConsumidos.isEmpty()) {
            Text(
                "Nenhum alimento adicionado ainda.",
                modifier = Modifier.padding(top = 8.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            alimentosConsumidos.forEach { alimento ->
                AlimentoConsumidoItem(alimento)
            }
        }


        Spacer(modifier = Modifier.weight(1f)) // Empurra o total para baixo

        Text(
            text = "Total de Calorias: $totalCalorias kcal",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun AlimentoConsumidoItem(alimento: AlimentoConsumido) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = alimento.icone,
            contentDescription = alimento.nome,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "${alimento.nome} - ${alimento.calorias} kcal", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun RastreamentoAlimentosScreenPreview() {
    FitnessLifeTheme {
        RastreamentoAlimentosScreen()
    }
}