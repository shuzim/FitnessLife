package com.example.fitnesslife.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnesslife.R
import com.example.fitnesslife.data.DatabaseHelper
import com.example.fitnesslife.databinding.ActivityLoginBinding
import android.content.Intent

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding // Variável para ViewBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflar layout com ViewBinding
        setContentView(binding.root) // Definir a view raiz

        dbHelper = DatabaseHelper(this)

        binding.buttonEntrar.setOnClickListener {
            val emailOuUsuario = binding.editTextEmailUsuario.text.toString().trim()
            val senha = binding.editTextSenha.text.toString().trim()

            if (emailOuUsuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, getString(R.string.campos_obrigatorios), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (dbHelper.verificarLogin(emailOuUsuario, senha)) {
                Toast.makeText(this, getString(R.string.login_sucesso), Toast.LENGTH_SHORT).show()
                // Navegar para a tela de Menu
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish() // Finaliza a LoginActivity para não voltar com o botão "back"
            } else {
                Toast.makeText(this, getString(R.string.login_falhou), Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewCadastreSe.setOnClickListener {
            // Navegar para a tela de Cadastro Parte 1
            val intent = Intent(this, CadastroParte1Activity::class.java)
            startActivity(intent)
        }
    }
}