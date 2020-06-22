package com.example.appcoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResultadoC extends AppCompatActivity {
    private TextView txtMR, txtMA, txtMP;
    private Button btnConfimarResultados;
    int MR, MA, MP;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();

    private String uidUsuario, nomeUsuario, emailUsuario, cpfUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_c);
        txtMR = (TextView)findViewById(R.id.textRealizacaoR);
        txtMA = (TextView)findViewById(R.id.textAfiliacaoR);
        txtMP = (TextView)findViewById(R.id.textPoderR);
        btnConfimarResultados = findViewById(R.id.btnConfirmarC);

        Intent Relatorio = getIntent();
        Bundle parametros = Relatorio.getExtras();

        if (parametros != null){
            MR = parametros.getInt("MR");
            MA = parametros.getInt("MA");
            MP = parametros.getInt("MP");
        }
        txtMR.setText("Pontuação: " + MR);
        txtMA.setText("Pontuação: " + MA);
        txtMP.setText("Pontuação: " + MP);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uidUsuario = user.getUid();

        ref.child("Usuario").child(uidUsuario).child("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nomeUsuario = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("Usuario").child(uidUsuario).child("Email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                emailUsuario = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("CPF").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cpfUsuario = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnConfimarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAOmenu = new Intent(getApplicationContext(), TelaMenu.class);
                startActivity(voltarAOmenu);
                Calendar calendar = Calendar.getInstance();
                String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
                C c = new C();
                c.setUid(UUID.randomUUID().toString());
                c.setNome(nomeUsuario);
                c.setEmail(emailUsuario);
                c.setCpf(cpfUsuario);
                c.setMR(MR);
                c.setMA(MA);
                c.setMP(MP);
                c.setData(currentdate);
                ref.child("TesteC").child(c.getUid()).setValue(c);

                ref.child("Usuario/"+uidUsuario).child("TesteC").child(c.getUid()).child("MR").setValue(MR);
                ref.child("Usuario/"+uidUsuario).child("TesteC").child(c.getUid()).child("MA").setValue(MA);
                ref.child("Usuario/"+uidUsuario).child("TesteC").child(c.getUid()).child("MP").setValue(MP);
                ref.child("Usuario/"+uidUsuario).child("TesteC").child(c.getUid()).child("data").setValue(currentdate);

            }
        });
        ref.child("Usuario/"+uidUsuario).child("MR").setValue(MR);
        ref.child("Usuario/"+uidUsuario).child("MA").setValue(MA);
        ref.child("Usuario/"+uidUsuario).child("MP").setValue(MP);
    }
}
