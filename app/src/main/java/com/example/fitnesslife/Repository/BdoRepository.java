package com.example.fitnesslife.Repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BdoRepository {

    private static final String TAG = "LOGIN_REPOSITORY";
    private final com.example.fitnesslife.Repository.DBHelper dbHelper;

    public BdoRepository(Context context) {
        this.dbHelper = new com.example.fitnesslife.Repository.DBHelper(context);
    }

    public boolean VerficacaoLogin(String usuario, String senha) {
        boolean autenticado = false;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT senha FROM usuarios WHERE usuario = ?", new String[]{usuario});

        if (cursor.moveToFirst()) {
            String senhaNoBanco = cursor.getString(0);
            if (senha.equals(senhaNoBanco)) {
                autenticado = true;
                Log.d(TAG, "Login bem-sucedido para: " + usuario);
            } else {
                Log.d(TAG, "Senha incorreta para: " + usuario);
            }
        } else {
            Log.d(TAG, "Usuário não encontrado: " + usuario);
        }

        cursor.close();
        db.close();
        return autenticado;
    }
}
