package com.example.appcoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Usuario extends AppCompatActivity {
    private TextView Nome, Email, cpf, Curso;

    //private Button btResultA, btResultB, btResultC, btnResultD;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();

    private String uidUsuario, nomeUsuario, emailUsuario, cpfUsuario, cursoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Nome = findViewById(R.id.Nome);
        Email = findViewById(R.id.Email);
        cpf = findViewById(R.id.CPF);
        Curso = findViewById(R.id.Curso);

        /*
        btResultA = findViewById(R.id.btnResultTesteA);
        btResultB = findViewById(R.id.btnResulTesteB);
        btResultC = findViewById(R.id.btnResulTesteC);
        btnResultD = findViewById(R.id.btnResulTesteD);

        btResultA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VizualizarDadosA.class);
                startActivity(intent);
            }
        });
        btResultB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VizualizarDadosB.class);
                startActivity(intent);
            }
        });
        btResultC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VizualizarDadosC.class);
                startActivity(intent);
            }
        });
        btnResultD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VizualizarDadosD.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onStart() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uidUsuario = user.getUid();
        //database.setPersistenceEnabled(true);
        ref.child("Usuario").child(uidUsuario).child("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nomeUsuario = dataSnapshot.getValue(String.class);
                Nome.setText(""+nomeUsuario);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("Usuario").child(uidUsuario).child("Email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                emailUsuario = dataSnapshot.getValue(String.class);
                Email.setText("" + emailUsuario);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("CPF").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cpfUsuario = dataSnapshot.getValue(String.class);
                cpf.setText("" + cpfUsuario);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("Usuario").child(uidUsuario).child("curso").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cursoUsuario = dataSnapshot.getValue(String.class);
                Curso.setText("" + cursoUsuario);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        super.onStart();
    }
}