package com.example.fitnesslife.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fitnesslife.utils.Constants

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {

    companion object {
        private const val CREATE_TABLE_USUARIOS = "CREATE TABLE ${Constants.TABLE_USUARIOS} (" +
                "${Constants.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Constants.COLUMN_NOME} TEXT," +
                "${Constants.COLUMN_SOBRENOME} TEXT," +
                "${Constants.COLUMN_EMAIL} TEXT UNIQUE," + // Email deve ser único
                "${Constants.COLUMN_SENHA} TEXT," +
                "${Constants.COLUMN_IDADE} INTEGER," +
                "${Constants.COLUMN_SEXO} TEXT," +
                "${Constants.COLUMN_ALTURA} INTEGER," +
                "${Constants.COLUMN_PESO} REAL)" // Peso pode ser decimal
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USUARIOS)
        // Inserir usuários de teste
        inserirUsuarioTeste(db, "admin", "Admin", "admin@fitness.life", "123", 30, "Masculino", 180, 75.5)
        inserirUsuarioTeste(db, "usuario", "Usuário", "usuario@fitness.life", "123", 25, "Feminino", 165, 60.2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_USUARIOS}")
        onCreate(db)
    }

    private fun inserirUsuarioTeste(
        db: SQLiteDatabase?,
        nome: String,
        sobrenome: String,
        email: String,
        senha: String, // Em um app real, use hashing para senhas!
        idade: Int,
        sexo: String,
        altura: Int,
        peso: Double
    ) {
        val values = ContentValues().apply {
            put(Constants.COLUMN_NOME, nome)
            put(Constants.COLUMN_SOBRENOME, sobrenome)
            put(Constants.COLUMN_EMAIL, email)
            put(Constants.COLUMN_SENHA, senha) // NUNCA armazene senhas em texto plano em produção!
            put(Constants.COLUMN_IDADE, idade)
            put(Constants.COLUMN_SEXO, sexo)
            put(Constants.COLUMN_ALTURA, altura)
            put(Constants.COLUMN_PESO, peso)
        }
        db?.insert(Constants.TABLE_USUARIOS, null, values)
    }

    fun adicionarUsuario(
        nome: String,
        sobrenome: String,
        email: String,
        senha: String,
        idade: Int,
        sexo: String,
        altura: Int,
        peso: Double
    ): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(Constants.COLUMN_NOME, nome)
            put(Constants.COLUMN_SOBRENOME, sobrenome)
            put(Constants.COLUMN_EMAIL, email)
            put(Constants.COLUMN_SENHA, senha) // HASHING DE SENHA É ESSENCIAL EM PRODUÇÃO
            put(Constants.COLUMN_IDADE, idade)
            put(Constants.COLUMN_SEXO, sexo)
            put(Constants.COLUMN_ALTURA, altura)
            put(Constants.COLUMN_PESO, peso)
        }
        val id = db.insert(Constants.TABLE_USUARIOS, null, values)
        db.close()
        return id // Retorna o ID do novo usuário ou -1 em caso de erro
    }

    @SuppressLint("Range")
    fun verificarLogin(emailOuUsuario: String, senhaInserida: String): Boolean {
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            // Tenta buscar pelo email primeiro
            cursor = db.query(
                Constants.TABLE_USUARIOS,
                arrayOf(Constants.COLUMN_SENHA),
                "${Constants.COLUMN_EMAIL} = ?",
                arrayOf(emailOuUsuario),
                null, null, null
            )

            if (cursor != null && cursor.moveToFirst()) {
                val senhaArmazenada = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_SENHA))
                if (senhaArmazenada == senhaInserida) { // EM PRODUÇÃO: Comparar hashes
                    return true
                }
            }
            cursor?.close() // Fecha o cursor anterior

            // Se não encontrou pelo email, tenta buscar pelo nome de usuário (campo nome)
            cursor = db.query(
                Constants.TABLE_USUARIOS,
                arrayOf(Constants.COLUMN_SENHA),
                "${Constants.COLUMN_NOME} = ?", // Assumindo que o "usuário" pode ser o campo nome
                arrayOf(emailOuUsuario),
                null, null, null
            )

            if (cursor != null && cursor.moveToFirst()) {
                val senhaArmazenada = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_SENHA))
                if (senhaArmazenada == senhaInserida) { // EM PRODUÇÃO: Comparar hashes
                    return true
                }
            }
        } finally {
            cursor?.close()
            db.close()
        }
        return false
    }

    fun verificarEmailExistente(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            Constants.TABLE_USUARIOS,
            arrayOf(Constants.COLUMN_ID),
            "${Constants.COLUMN_EMAIL} = ?",
            arrayOf(email),
            null, null, null
        )
        val existe = cursor.count
        cursor.close()
        db.close()
        return existe > 0
    }
}