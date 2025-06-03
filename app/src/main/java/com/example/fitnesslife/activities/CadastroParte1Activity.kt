package com.example.fitnesslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fitnesslife.R // <-- Add this import
import com.example.fitnesslife.data.DatabaseHelper
import com.example.fitnesslife.databinding.ActivityCadastroParte1Binding
import com.example.fitnesslife.utils.Constants

class CadastroParte1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroParte1Binding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroParte1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.buttonProximo.setOnClickListener {
            val nome = binding.editTextNome.text.toString().trim()
            val sobrenome = binding.editTextSobrenome.text.toString().trim()
            val email = binding.editTextEmailCadastro.text.toString().trim()
            val senha = binding.editTextSenhaCadastro.text.toString().trim()

            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, getString(R.string.campos_obrigatorios), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Verificar se o email já existe
            if (dbHelper.verificarEmailExistente(email)) {
                Toast.makeText(this, getString(R.string.email_ja_cadastrado), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Passar dados para a próxima tela de cadastro
            val intent = Intent(this, CadastroParte2Activity::class.java).apply {
                putExtra("NOME", nome)
                putExtra("SOBRENOME", sobrenome)
                putExtra("EMAIL", email)
                putExtra(
                    "SENHA",
                    senha
                ) // Em um app real, não passe a senha assim. Considere um fluxo mais seguro.
            }
            startActivity(intent)
        }
    }
}