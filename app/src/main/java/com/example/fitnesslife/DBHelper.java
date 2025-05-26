package com.example.fitnesslife.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";
    private static final String DB_NAME = "fitnesslife.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COL_ID = "id";
    public static final String COL_USUARIO = "usuario";
    public static final String COL_SENHA = "senha";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USUARIO + " TEXT UNIQUE, " +
                COL_SENHA + " TEXT)";
        db.execSQL(createTable);
        Log.d(TAG, "Tabela criada.");

        // Inserir usuários padrões
        inserirUsuarioPadrao(db, "admin", "123");
        inserirUsuarioPadrao(db, "usuario", "123");
    }

    private void inserirUsuarioPadrao(SQLiteDatabase db, String usuario, String senha) {
        ContentValues values = new ContentValues();
        values.put(COL_USUARIO, usuario);
        values.put(COL_SENHA, senha);
        db.insert(TABLE_USUARIOS, null, values);
        Log.d(TAG, "Usuário inserido: " + usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }
}
