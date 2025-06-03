package com.example.fitnesslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fitnesslife.data.DatabaseHelper
import com.example.fitnesslife.databinding.ActivityCadastroParte2Binding
import com.example.fitnesslife.R

class CadastroParte2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroParte2Binding
    private lateinit var dbHelper: DatabaseHelper

    private var nome = ""
    private var sobrenome = ""
    private var email = ""
    private var senha = "" // Cuidado com a senha aqui

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroParte2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        // Recuperar dados da Intent
        nome = intent.getStringExtra("NOME") ?: ""
        sobrenome = intent.getStringExtra("SOBRENOME") ?: ""
        email = intent.getStringExtra("EMAIL") ?: ""
        senha = intent.getStringExtra("SENHA") ?: ""


        binding.buttonCadastrar.setOnClickListener {
            val idadeStr = binding.editTextIdade.text.toString().trim()
            val sexo = binding.editTextSexo.text.toString().trim()
            val alturaStr = binding.editTextAltura.text.toString().trim()
            val pesoStr = binding.editTextPeso.text.toString().trim()

            if (idadeStr.isEmpty() || sexo.isEmpty() || alturaStr.isEmpty() || pesoStr.isEmpty()) {
                Toast.makeText(this, getString(R.string.campos_obrigatorios), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idade = idadeStr.toIntOrNull()
            val altura = alturaStr.toIntOrNull()
            val peso = pesoStr.toDoubleOrNull()

            if (idade == null || altura == null || peso == null) {
                Toast.makeText(this, "Valores numéricos inválidos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Adicionar usuário ao banco de dados
            val idNovoUsuario = dbHelper.adicionarUsuario(
                nome, sobrenome, email, senha, // LEMBRE-SE: HASH DE SENHA EM PRODUÇÃO!
                idade, sexo, altura, peso
            )

            if (idNovoUsuario != -1L) {
                Toast.makeText(this, getString(R.string.cadastro_sucesso), Toast.LENGTH_SHORT).show()
                // Navegar para a tela de Login após o cadastro bem-sucedido
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Limpa a pilha de activities
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.cadastro_falhou), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
