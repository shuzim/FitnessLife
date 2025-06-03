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

data class Receita(
    val nome: String,
    val tempoPreparo: String,
    val dificuldade: String,
    val icone: ImageVector
)

val receitasFicticias = listOf(
    Receita("Salada de Quinoa com Legumes", "25 min", "Fácil", Icons.Filled.Spa),
    Receita("Smoothie Verde Detox", "10 min", "Fácil", Icons.Filled.Blender),
    Receita("Frango com Batata Doce Assado", "45 min", "Médio", Icons.Filled.OutdoorGrill),
    Receita("Panqueca de Aveia e Banana", "15 min", "Fácil", Icons.Filled.BakeryDining),
    Receita("Sopa de Lentilha Nutritiva", "50 min", "Médio", Icons.Filled.SoupKitchen)
)

class ReceitasSaudaveisActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessLifeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReceitasSaudaveisScreen(receitasFicticias)
                }
            }
        }
    }
}

@Composable
fun ReceitasSaudaveisScreen(receitas: List<Receita>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Receitas Saudáveis",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(receitas) { receita ->
                ReceitaCard(receita)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceitaCard(receita: Receita) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = receita.icone,
                contentDescription = receita.nome,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = receita.nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Preparo: ${receita.tempoPreparo}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Dificuldade: ${receita.dificuldade}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Ver receita",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceitasSaudaveisScreenPreview() {
    FitnessLifeTheme {
        ReceitasSaudaveisScreen(receitasFicticias)
    }
}