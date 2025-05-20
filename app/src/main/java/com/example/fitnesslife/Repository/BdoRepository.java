package com.example.fitnesslife.Repository;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BdoRepository {

    private static final String TAG = "LOGIN_REPOSITORY";

    private static final String URL = "jdbc:postgresql://localhost:5432/bdofitneslife";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";


    public boolean VerficacaoLogin(String usuario, String senha) {
        boolean autenticado = false;

        Log.d(TAG, "Iniciando verificação de login...");
        Log.d(TAG, "Usuário recebido: " + usuario);

        try {
            Class.forName("org.postgresql.Driver");
            Log.d(TAG, "Driver PostgreSQL carregado com sucesso.");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Log.d(TAG, "Conexão com o banco de dados estabelecida.");

            String sql = "SELECT senha FROM usuarios WHERE usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);

            Log.d(TAG, "Consulta SQL preparada: " + sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaNoBanco = rs.getString("senha");
                Log.d(TAG, "Senha no banco encontrada: " + senhaNoBanco);

                if (senhaNoBanco.equals(senha)) {
                    autenticado = true;
                    Log.d(TAG, "Senha correta. Usuário autenticado.");
                } else {
                    Log.d(TAG, "Senha incorreta.");
                }
            } else {
                Log.d(TAG, "Usuário não encontrado no banco.");
            }

            rs.close();
            stmt.close();
            conn.close();
            Log.d(TAG, "Conexão com o banco encerrada.");

        } catch (Exception e) {
            Log.e(TAG, "Erro na verificação de usuário: " + e.getMessage());
            e.printStackTrace();
        }

        Log.d(TAG, "Resultado da verificação: " + autenticado);
        return autenticado;
    }
}
