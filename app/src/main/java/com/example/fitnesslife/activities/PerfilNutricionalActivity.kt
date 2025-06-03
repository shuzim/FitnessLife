package com.example.fitnesslife.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesslife.ui.theme.FitnessLifeTheme // Certifique-se que o nome do tema está correto

class PerfilNutricionalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessLifeTheme { // Use o seu tema do app
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PerfilNutricionalScreen()
                }
            }
        }
    }
}

@Composable
fun PerfilNutricionalScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Perfil Nutricional",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        InfoItem(icon = Icons.Filled.AccountCircle, label = "Nome", value = "Usuário Fitness")
        InfoItem(icon = Icons.Filled.Assessment, label = "Idade", value = "30 anos")
        InfoItem(icon = Icons.Filled.RestaurantMenu, label = "Altura", value = "175 cm")
        InfoItem(icon = Icons.Filled.LocalFireDepartment, label = "Peso Atual", value = "70 kg")
        InfoItem(icon = Icons.Filled.RestaurantMenu, label = "Meta de Peso", value = "68 kg")
        InfoItem(
            icon = Icons.Filled.LocalFireDepartment,
            label = "Calorias Diárias (Meta)",
            value = "2000 kcal"
        )
        InfoItem(icon = Icons.Filled.Assessment, label = "IMC", value = "22.9 (Normal)")
    }
}

@Composable
fun InfoItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = value, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilNutricionalScreenPreview() {
    FitnessLifeTheme {
        PerfilNutricionalScreen()
    }
}