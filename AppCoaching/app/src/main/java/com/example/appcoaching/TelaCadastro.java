package com.example.appcoaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaCadastro extends AppCompatActivity {
    private EditText EdNome, EdEmail, EdCPF;
    private FirebaseAuth minha;
    private FirebaseAuth.AuthStateListener minhaAuthListner;
    Button btnCadstrar;
    Spinner spinner;
    int v = 0, c = 0, a = 0, d = 0;
    int aguia = 0, gato = 0, lobo = 0, tubarao = 0;
    int mr = 0, ma = 0, mp = 0;
    double importancia = 0, circustancia = 0, urgencia = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    int resposta = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        minha = FirebaseAuth.getInstance();


        EdNome = findViewById(R.id.editNome);
        EdEmail = findViewById(R.id.editEmail);
        EdCPF = findViewById(R.id.editCPF);
        spinner = findViewById(R.id.spinner);
        btnCadstrar = findViewById(R.id.btnCadastrar);

        //SimpleMaskFormatter cpf = new SimpleMaskFormatter("NNNNNNNNNN.N");
        //MaskTextWatcher edit = new MaskTextWatcher(EdCPF, cpf);
        //EdCPF.addTextChangedListener(edit);

        minhaAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d("meuLog", "Usuario Conectado: " + user.getUid());
                    //Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    //startActivity(i);
                } /*else {
                    //   Toast.makeText(TelaCadstro.this, "Erro !!", Toast.LENGTH_SHORT).show();
                    Log.d("meuLog", "Sem Usuarios");
                }*/
            }
        };

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer opcao = (int) spinner.getSelectedItemId();
                if (opcao == 1){
                   resposta = 1;

                }
                if (opcao == 2){
                    resposta = 2;
                }
                if (opcao == 3){
                    resposta = 3;
                }
                if (opcao == 4){
                    resposta = 4;
                }
                if (opcao == 5){
                    resposta = 5;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        minha.addAuthStateListener(minhaAuthListner);
    }
    public void CriarUsuario(View view){
        if (EdNome.getText().toString().isEmpty() || EdEmail.getText().toString().isEmpty() || EdCPF.getText().toString().isEmpty()){
           mensagem("Atenção", "Algum Campo Não esta preenchido");
            
        }
        if (resposta == 0){
            mensagem("Atenção", "Não foi selecionado o curso");
        }
        else {
            minha.createUserWithEmailAndPassword(EdEmail.getText().toString(), EdCPF.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                final DatabaseReference ref = database.getReference("Usuario").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                ref.child("nome").setValue(EdNome.getText().toString());
                                ref.child("Email").setValue(EdEmail.getText().toString());
                                ref.child("CPF").setValue(EdCPF.getText().toString());
                                ref.child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                ref.child("Visual").setValue(v);
                                ref.child("Cinestecico").setValue(c);
                                ref.child("Auditivo").setValue(a);
                                ref.child("Digital").setValue(d);

                                ref.child("Aguia").setValue(aguia);
                                ref.child("Gato").setValue(gato);
                                ref.child("Lobo").setValue(lobo);
                                ref.child("Tubarao").setValue(tubarao);

                                ref.child("MR").setValue(mr);
                                ref.child("MA").setValue(ma);
                                ref.child("MP").setValue(mp);

                                ref.child("Importancia").setValue(importancia);
                                ref.child("Urgencia").setValue(urgencia);
                                ref.child("Circustancia").setValue(circustancia);


                                if (resposta == 1){
                                    ref.child("curso").setValue("Analise e Desenvolvimento de Sistemas");
                                }
                                if (resposta == 2){
                                    ref.child("curso").setValue("Comercio Exterior");
                                }
                                if (resposta == 3){
                                    ref.child("curso").setValue("Gestão de Produção Industrial");
                                }
                                if (resposta == 4){
                                    ref.child("curso").setValue("Agronegocio");
                                }
                                if (resposta == 5){
                                    ref.child("curso").setValue("Gestão Ambiental");
                                }
                            }
                            else{
                                String resposta =  task.getException().toString();
                                Erros(resposta);
                            }
                        }
                    });
        }
    }
    private void mensagem(String titulo, String texto){
        AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
        mensagem.setIcon(android.R.drawable.ic_dialog_alert);
        mensagem.setMessage(texto);
        mensagem.setTitle(titulo);
        mensagem.setNegativeButton("OK !!", null);
        mensagem.show();
    }
    private void Erros(String resposta){
        if (resposta.contains("least 6 characters")){
            mensagem("Atenção", "CPF invalido");
        }
        else if (resposta.contains("address is badly")){
            mensagem("Atenção", "E-mail invalido");
        }
        else if (resposta.contains("address is already")){
            mensagem("Atenção","E-mail já existente");
        }
        else if (resposta.contains("interrupted connection")){
            mensagem("Atenção", "Sem acesso a internet");
        }
        else {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }
}
