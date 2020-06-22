package com.example.appcoaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.firebase.auth.FirebaseAuth;

public class TelaMenu extends AppCompatActivity {
    private Button btTesteA, btTesteB, btTesteC, btTesteD;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);
        btTesteA = findViewById(R.id.btnTesteA);
        btTesteB = findViewById(R.id.btnTesteB);
        btTesteC = findViewById(R.id.btnTesteC);
        btTesteD = findViewById(R.id.btnTesteD);
        toolbar = findViewById(R.id.toolbarMenu);

        setSupportActionBar(toolbar);
        toolbar.setTitle("AppCouthing");

        btTesteA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelatesteA = new Intent(getApplicationContext(), VizualizarDadosA.class);
                startActivity(TelatesteA);
            }
        });
        btTesteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelatesteB = new Intent(getApplicationContext(), VizualizarDadosB.class);
                startActivity(TelatesteB);
            }
        });
        btTesteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelatesteC = new Intent(getApplicationContext(), VizualizarDadosC.class);
                startActivity(TelatesteC);
            }
        });
        btTesteD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TelatesteD = new Intent(getApplicationContext(), VizualizarDadosD.class);
                startActivity(TelatesteD);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Usuario:
                Intent intent = new Intent(getApplicationContext(), Usuario.class);
                startActivity(intent);
                return true;

            case R.id.Sair:
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
