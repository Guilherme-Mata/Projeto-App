package com.example.appcoaching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edLogin, edSenha;
    private Button btEntrar, btRealizarCad;
    private TextView txtResetSenha;
    private FirebaseAuth minhaAuth;
    private FirebaseAuth.AuthStateListener minhaAuthListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minhaAuth = FirebaseAuth.getInstance();
        edLogin = findViewById(R.id.editLogin);
        edSenha = findViewById(R.id.editSenha);
        btEntrar = findViewById(R.id.btnEntrar);
        btRealizarCad = findViewById(R.id.btnRealizarCad);
        txtResetSenha = findViewById(R.id.textResetSenha);

        //SimpleMaskFormatter senha = new SimpleMaskFormatter("NNNNNNNNNN.N");
        //MaskTextWatcher edit = new MaskTextWatcher(edSenha, senha);
        //edSenha.addTextChangedListener(edit);

        btRealizarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TelaCadastro.class);
                startActivity(intent);
            }
        });

        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent intent = new Intent(getApplicationContext(), ResetSenha.class);
             //   startActivity(intent);
               RedefinirSenha();
            }
        });

        minhaAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Log.d("meuLog", "Usuario Conectado: " + user.getUid());
                    Intent i = new Intent(getApplicationContext(), TelaMenu.class);
                    startActivity(i);
                }

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        minhaAuth.addAuthStateListener(minhaAuthListner);
    }

    public void clicaLogin(View view){
        if (edLogin.getText().toString().isEmpty() || edSenha.getText().toString().isEmpty()){
            mensagem("Atenção !!!", "Erro voce precisa preencher os campos");
        } else {
            minhaAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edSenha.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Log.i("meu log","falha");
                                mensagem("Atenção !!!", "Erro login ou senha estão incoretos");
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

    private void RedefinirSenha(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Informe seu E-mail");
        final EditText Email = new EditText(this);
        builder.setView(Email);

        builder.setPositiveButton("Confimrmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                minhaAuth.sendPasswordResetEmail(Email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    mensagem("Atenção", "Mandamos um E-mail para redefinir sua senha");
                                }else {
                                    mensagem("Atenção", "E-mail não encomtrado");
                                }
                            }
                        });
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.show();
    }

}

