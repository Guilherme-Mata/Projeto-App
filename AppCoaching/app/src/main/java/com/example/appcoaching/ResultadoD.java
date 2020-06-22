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

public class ResultadoD extends AppCompatActivity {
    TextView txtImportancia, txtCircustancia, txtUrgencia;
    Button btnConfirmar;
    int A, B, C;
    double importancia, urgencia, circustancia, totGeral;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();

    private String uidUsuario, nomeUsuario, emailUsuario, cpfUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_d);
        txtImportancia = findViewById(R.id.txtImportancia);
        txtCircustancia = findViewById(R.id.txtCircustancia);
        txtUrgencia = findViewById(R.id.txtUrgencia);
        btnConfirmar = findViewById(R.id.btnConfimarD);

        Intent Relatorio = getIntent();
        Bundle parametros = Relatorio.getExtras();

        if (parametros != null){
            A = parametros.getInt("conjuntoA");
            B = parametros.getInt("conjuntoB");
            C = parametros.getInt("conjuntoC");
            totGeral = parametros.getInt("totGeral");
        }
        importancia = B / totGeral * (100);
        urgencia = A / totGeral * (100);
        circustancia = C / totGeral * (100);

        txtImportancia.setText(""+(int) importancia);
        txtCircustancia.setText(""+(int)circustancia);
        txtUrgencia.setText(""+(int)urgencia);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAOmenu = new Intent(getApplicationContext(), TelaMenu.class);
                startActivity(voltarAOmenu);
                D d = new D();
                Calendar calendar = Calendar.getInstance();
                String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
                d.setUid(UUID.randomUUID().toString());
                d.setNome(nomeUsuario);
                d.setEmail(emailUsuario);
                d.setCpf(cpfUsuario);
                d.setData(currentdate);
                d.setImportancia(importancia);
                d.setUrgencia(urgencia);
                d.setCircustancia(circustancia);
                ref.child("TesteD").child(d.getUid()).setValue(d);

                ref.child("Usuario/"+uidUsuario).child("TesteD").child(d.getUid()).child("Importancia").setValue((int)importancia);
                ref.child("Usuario/"+uidUsuario).child("TesteD").child(d.getUid()).child("Urgencia").setValue((int)urgencia);
                ref.child("Usuario/"+uidUsuario).child("TesteD").child(d.getUid()).child("Circustancia").setValue((int)circustancia);
                ref.child("Usuario/"+uidUsuario).child("TesteD").child(d.getUid()).child("data").setValue(currentdate);
            }
        });
        ref.child("Usuario/"+uidUsuario).child("Importancia").setValue((int)importancia);
        ref.child("Usuario/"+uidUsuario).child("Urgencia").setValue((int)urgencia);
        ref.child("Usuario/"+uidUsuario).child("Circustancia").setValue((int)circustancia);
    }
}
