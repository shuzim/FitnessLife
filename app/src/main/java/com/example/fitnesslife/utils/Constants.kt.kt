package com.example.fitnesslife.utils

object Constants {
    const val DATABASE_NAME = "FitnessLifeDB"
    const val DATABASE_VERSION = 1

    // Tabela de Usuários
    const val TABLE_USUARIOS = "usuarios"
    const val COLUMN_ID = "_id"
    const val COLUMN_NOME = "nome"
    const val COLUMN_SOBRENOME = "sobrenome"
    const val COLUMN_EMAIL = "email"
    const val COLUMN_SENHA = "senha"
    const val COLUMN_IDADE = "idade"
    const val COLUMN_SEXO = "sexo"
    const val COLUMN_ALTURA = "altura"
    const val COLUMN_PESO = "peso"

    // Chaves para SharedPreferences ou Intent Extras (se necessário)
    const val EXTRA_EMAIL = "EXTRA_EMAIL"
}