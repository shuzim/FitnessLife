package com.example.fitnesslife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitnesslife.Repository.BdoRepository;


import android.util.Log;

public class MainActivity extends AppCompatActivity {

    EditText editTextTextEmailAddress3, editTextTextPassword;
    Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextEmailAddress3 = findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        LoginButton = findViewById(R.id.LoginButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = editTextTextEmailAddress3.getText().toString().trim();
                String senha = editTextTextPassword.getText().toString().trim();

                Log.d("LOGIN_DEBUG", "Usuário: " + usuario);
                Log.d("LOGIN_DEBUG", "Senha: " + senha);

                BdoRepository repository = new BdoRepository();
                boolean resultado = repository.VerficacaoLogin(usuario, senha);

                Log.d("LOGIN_DEBUG", "Resultado do login: " + resultado);

                if (resultado) {
                    startActivity(new Intent(MainActivity.this, Tela_menu.class));
                } else {
                    Toast.makeText(MainActivity.this, "Login inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void MudarCadastro (View v) {
        Intent in = new Intent(MainActivity.this, Tela_cadastro1.class);
        startActivity(in);
    }

    public void  mudarLogin(View v){
        Intent in =  new Intent(MainActivity.this, Tela_menu.class);
        startActivity(in);
    }
}