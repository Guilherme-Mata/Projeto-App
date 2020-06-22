package com.example.appcoaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VizualizarDadosB extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtRA, txtRG, txtRL, txtRT;
    private ListView listView;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();

    private List<B> list = new ArrayList<>();
    private ArrayAdapter<B> arrayAdapter;
    private String uidUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizar_dados_b);
        txtRA = findViewById(R.id.textRA);
        txtRG = findViewById(R.id.textRG);
        txtRL = findViewById(R.id.textRL);
        txtRT = findViewById(R.id.textRT);
        listView = findViewById(R.id.listaB);
        toolbar = findViewById(R.id.toolbarC);

        setSupportActionBar(toolbar);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uidUsuario = user.getUid();

        ref.child("Usuario").child(uidUsuario).child("Aguia").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtRA.setText(dataSnapshot.getValue().toString() + "%");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("Gato").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtRG.setText(dataSnapshot.getValue().toString() + "%");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("Lobo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtRL.setText(dataSnapshot.getValue().toString() + "%");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("Tubarao").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtRT.setText(dataSnapshot.getValue().toString() + "%");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("TesteB").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot objtoSnap : dataSnapshot.getChildren()) {
                    B b = objtoSnap.getValue(B.class);
                    list.add(b);
                    arrayAdapter = new ArrayAdapter<>(VizualizarDadosB.this, android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resultado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Resultados:
                Intent intent = new Intent(getApplicationContext(), TesteB.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
