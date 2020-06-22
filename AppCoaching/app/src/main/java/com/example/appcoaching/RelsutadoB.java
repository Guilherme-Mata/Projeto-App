package com.example.appcoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

public class RelsutadoB extends AppCompatActivity {
    private TextView txtResultadoA, txtResultadoG, txtResultadoT, txtResultadoL;
    private ProgressBar progreesA, progressG, progressT, progressL;
    private Button confirmarResultados;
    int A, G, T, L;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();

    private String uidUsuario, nomeUsuario, emailUsuario, cpfUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relsutado_b);
        txtResultadoA = (TextView)findViewById(R.id.textAguiaR);
        txtResultadoG = (TextView)findViewById(R.id.textGatoR);
        txtResultadoT = (TextView)findViewById(R.id.textTubaraoR);
        txtResultadoL = (TextView)findViewById(R.id.textLoboR);

        progreesA = (ProgressBar) findViewById(R.id.progressAguia);
        progressG = (ProgressBar)findViewById(R.id.progressGato);
        progressT = (ProgressBar)findViewById(R.id.progressTubarao);
        progressL = (ProgressBar)findViewById(R.id.progressLobo);

        confirmarResultados = findViewById(R.id.btnConfirmarResultadosB);
        Intent Relatorio = getIntent();
        Bundle parametros = Relatorio.getExtras();

        if (parametros != null){
            A = parametros.getInt("Aguia");
            G = parametros.getInt("Gato");
            T = parametros.getInt("Tubarao");
            L = parametros.getInt("Lobo");
        }
        progreesA.setProgress(A);
        txtResultadoA.setText(" " + A + "%");

        progressG.setProgress(G);
        txtResultadoG.setText(" " + G + "%");

        progressT.setProgress(T);
        txtResultadoT.setText(" " + T + "%");

        progressL.setProgress(L);
        txtResultadoL.setText(" " + L + "%");
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

        confirmarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAOmenu = new Intent(getApplicationContext(), TelaMenu.class);
                startActivity(voltarAOmenu);
                Calendar calendar = Calendar.getInstance();
                String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
                B b = new B();
                b.setUid(UUID.randomUUID().toString());
                b.setNome(nomeUsuario);
                b.setEmail(emailUsuario);
                b.setCpf(cpfUsuario);
                b.setAguia(A);
                b.setGato(G);
                b.setLobo(L);
                b.setTubarao(T);
                b.setData(currentdate);
                ref.child("TesteB").child(b.getUid()).setValue(b);

                ref.child("Usuario/"+uidUsuario).child("TesteB").child(b.getUid()).child("Aguia").setValue(A);
                ref.child("Usuario/"+uidUsuario).child("TesteB").child(b.getUid()).child("Gato").setValue(G);
                ref.child("Usuario/"+uidUsuario).child("TesteB").child(b.getUid()).child("Lobo").setValue(L);
                ref.child("Usuario/"+uidUsuario).child("TesteB").child(b.getUid()).child("Tubarao").setValue(T);
                ref.child("Usuario/"+uidUsuario).child("TesteB").child(b.getUid()).child("data").setValue(currentdate);


            }
        });
        ref.child("Usuario/"+uidUsuario).child("Aguia").setValue(A);
        ref.child("Usuario/"+uidUsuario).child("Gato").setValue(G);
        ref.child("Usuario/"+uidUsuario).child("Lobo").setValue(L);
        ref.child("Usuario/"+uidUsuario).child("Tubarao").setValue(T);
    }
}
