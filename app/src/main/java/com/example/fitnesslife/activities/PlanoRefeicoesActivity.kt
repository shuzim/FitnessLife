package com.example.fitnesslife.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesslife.ui.theme.FitnessLifeTheme

data class Refeicao(
    val nome: String,
    val horario: String,
    val itens: List<String>,
    val icone: ImageVector
)

val planoRefeicoesFicticio = listOf(
    Refeicao("Café da Manhã", "08:00", listOf("Ovos mexidos", "Pão integral", "Fruta"), Icons.Filled.FreeBreakfast),
    Refeicao("Lanche da Manhã", "10:30", listOf("Iogurte natural", "Castanhas"), Icons.Filled.KebabDining),
    Refeicao("Almoço", "13:00", listOf("Frango grelhado", "Salada colorida", "Arroz integral"), Icons.Filled.Restaurant),
    Refeicao("Lanche da Tarde", "16:00", listOf("Maçã", "Pasta de amendoim"), Icons.Filled.RestaurantMenu),
    Refeicao("Jantar", "19:30", listOf("Salmão assado", "Legumes cozidos"), Icons.Filled.DinnerDining),
    Refeicao("Ceia (opcional)", "22:00", listOf("Chá de camomila"), Icons.Filled.EmojiFoodBeverage)
)

class PlanoRefeicoesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessLifeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlanoRefeicoesScreen(planoRefeicoesFicticio)
                }
            }
        }
    }
}

@Composable
fun PlanoRefeicoesScreen(refeicoes: List<Refeicao>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Plano de Refeições Semanal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(refeicoes) { refeicao ->
                RefeicaoCard(refeicao)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefeicaoCard(refeicao: Refeicao) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = refeicao.icone,
                    contentDescription = refeicao.nome,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = refeicao.nome,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Horário: ${refeicao.horario}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            refeicao.itens.forEach { item ->
                Text(
                    text = "• $item",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanoRefeicoesScreenPreview() {
    FitnessLifeTheme {
        PlanoRefeicoesScreen(planoRefeicoesFicticio)
    }
}