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
import com.google.firebase.database.ChildEventListener;
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

public class ResultadoA extends AppCompatActivity {
    private ProgressBar Visual, Cinestesico, Auditivo, Digital;
    private TextView txtVisualR, txtCinestesicoR, txtAuditivoR, txtDigitalR;
    private Button confimarResultados;
    int V, C, A, D;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();

    private String uidUsuario, nomeUsuario, emailUsuario, cpfUsuario;
    String idTesteA = UUID.randomUUID().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadoa);
        Visual = (ProgressBar) findViewById(R.id.progressVisual);
        Cinestesico = (ProgressBar) findViewById(R.id.progressCinestecico);
        Auditivo = (ProgressBar) findViewById(R.id.progressAuditivo);
        Digital = (ProgressBar) findViewById(R.id.progressDigital);

        txtVisualR = (TextView) findViewById(R.id.textVisualR);
        txtCinestesicoR = (TextView) findViewById(R.id.textCinestesicoR);
        txtAuditivoR = (TextView) findViewById(R.id.textAuditivoR);
        txtDigitalR = (TextView) findViewById(R.id.textDigitalR);

        confimarResultados = findViewById(R.id.btnComfirmarTesteA);

        Intent RelatorioA = getIntent();
        Bundle parametrosA = RelatorioA.getExtras();

        if (parametrosA != null) {
            V = parametrosA.getInt("Visual");
            C = parametrosA.getInt("Cinestecico");
            A = parametrosA.getInt("Auditivo");
            D = parametrosA.getInt("Digital");
        }
        V *= 2;
        Visual.setProgress(V);
        txtVisualR.setText(" " + V + "%");

        C *= 2;
        Cinestesico.setProgress(C);
        txtCinestesicoR.setText(" " + C + "%");

        A *= 2;
        Auditivo.setProgress(A);
        txtAuditivoR.setText(" " + A + "%");

        D *= 2;
        Digital.setProgress(D);
        txtDigitalR.setText(" " + D + "%");

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

        confimarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAOmenu = new Intent(getApplicationContext(), TelaMenu.class);
                startActivity(voltarAOmenu);
                Calendar calendar = Calendar.getInstance();
                String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
                A  a = new A();
                a.setUid(UUID.randomUUID().toString());
                a.setNome(nomeUsuario);
                a.setEmail(emailUsuario);
                a.setCpf(cpfUsuario);
                a.setVisual(V);
                a.setCinestecico(C);
                a.setAuditivo(A);
                a.setDigital(D);
                a.setData(currentdate);
                ref.child("TesteA").child(a.getUid()).setValue(a);

                ref.child("Usuario/"+uidUsuario).child("TesteA").child(a.getUid()).child("visual").setValue(V);
                ref.child("Usuario/"+uidUsuario).child("TesteA").child(a.getUid()).child("cinestecico").setValue(C);
                ref.child("Usuario/"+uidUsuario).child("TesteA").child(a.getUid()).child("auditivo").setValue(A);
                ref.child("Usuario/"+uidUsuario).child("TesteA").child(a.getUid()).child("digital").setValue(D);
                ref.child("Usuario/"+uidUsuario).child("TesteA").child(a.getUid()).child("data").setValue(currentdate);


/*
                Map<String, Object> ResultadoA = new HashMap<>();
                ResultadoA.put("uid", uidUsuario);
                ResultadoA.put("Visual", V);
                ResultadoA.put("Cinestecico", C);
                ResultadoA.put("Auditivo", A);
                ResultadoA.put("Digital", D);
                ResultadoA.put("Nome", nomeUsuario);
                ResultadoA.put("Email", emailUsuario);
                ResultadoA.put("CPF", cpfUsuario);
                ref.child("TesteA").child(cpfUsuario).setValue(ResultadoA);*/
            }
        });
        ref.child("Usuario/"+uidUsuario).child("Visual").setValue(V);
        ref.child("Usuario/"+uidUsuario).child("Cinestecico").setValue(C);
        ref.child("Usuario/"+uidUsuario).child("Auditivo").setValue(A);
        ref.child("Usuario/"+uidUsuario).child("Digital").setValue(D);
    }
}
