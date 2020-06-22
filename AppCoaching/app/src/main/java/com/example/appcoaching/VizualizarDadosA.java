package com.example.appcoaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VizualizarDadosA extends AppCompatActivity {
    private Toolbar toolbarA;
    private TextView txtV, txtC, txtA, txtD;

    private ListView listView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    private List<A> list = new ArrayList<>();
    private ArrayAdapter<A> arrayAdapter;
    private String uidUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizar_dados_a);
        txtV = findViewById(R.id.textResultV);
        txtC = findViewById(R.id.textResultC);
        txtA = findViewById(R.id.textResultA);
        txtD = findViewById(R.id.textResultD);
        listView = findViewById(R.id.listaTesteA);
        toolbarA = findViewById(R.id.toolbarA);

        setSupportActionBar(toolbarA);
        toolbarA.setTitle("Resultados Atuais");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        uidUsuario = user.getUid();

        ref.child("Usuario").child(uidUsuario).child("Visual").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtV.setText(dataSnapshot.getValue().toString() + "%");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("Usuario").child(uidUsuario).child("Cinestecico").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtC.setText(dataSnapshot.getValue().toString() + "%");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("Usuario").child(uidUsuario).child("Auditivo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtA.setText(dataSnapshot.getValue().toString() + "%");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("Usuario").child(uidUsuario).child("Digital").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtD.setText(dataSnapshot.getValue().toString() + "%");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("Usuario").child(uidUsuario).child("TesteA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot objtoSnap : dataSnapshot.getChildren()) {
                    A a = objtoSnap.getValue(A.class);
                    list.add(a);
                    arrayAdapter = new ArrayAdapter<>(VizualizarDadosA.this, android.R.layout.simple_list_item_1, list);
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
                Intent intent = new Intent(getApplicationContext(), TesteA.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
