package com.example.fitnesslife.activities

import android.content.Intent // Certifique-se que esta importação existe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// Removido o Toast já que vamos navegar diretamente
// import android.widget.Toast
import com.example.fitnesslife.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPerfilNutricional.setOnClickListener {
            // Iniciar PerfilNutricionalActivity (que usa Compose)
            val intent = Intent(this, PerfilNutricionalActivity::class.java)
            startActivity(intent)
        }

        binding.buttonPlanoRefeicoes.setOnClickListener {
            // Iniciar PlanoRefeicoesActivity (que usa Compose)
            val intent = Intent(this, PlanoRefeicoesActivity::class.java)
            startActivity(intent)
        }

        binding.buttonReceitasSaudaveis.setOnClickListener {
            // Iniciar ReceitasSaudaveisActivity (que usa Compose)
            val intent = Intent(this, ReceitasSaudaveisActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRastreamentoAlimentos.setOnClickListener {
            // Iniciar RastreamentoAlimentosActivity (que usa Compose)
            val intent = Intent(this, RastreamentoAlimentosActivity::class.java)
            startActivity(intent)
        }
    }
}