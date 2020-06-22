package com.example.appcoaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TesteA extends AppCompatActivity {
    private ConstraintLayout layoutA;
    private TextView txtTituloA, txtPerguntaA, txtSubPergunta;
    private RadioGroup radioGroupA;
    private RadioButton rd1, rd2, rd3, rd4;
    private Button btnOKA;
    private ProgressBar progressBarA;

    String[] perguntas = {"1.Eu tomo decisões importantes baseado em:", "1.Eu tomo decisões importantes baseado em:", "1.Eu tomo decisões importantes baseado em:",
            "1.Eu tomo decisões importantes baseado em:", "2.Durante uma discussão eu sou mais influenciado por:", "2.Durante uma discussão eu sou mais influenciado por:",
            "2.Durante uma discussão eu sou mais influenciado por:", "2.Durante uma discussão eu sou mais influenciado por:",
            "3.Eu comunico mais facilmente o que se passa comigo:", "3.Eu comunico mais facilmente o que se passa comigo:", "3.Eu comunico mais facilmente o que se passa comigo:",
            "3.Eu comunico mais facilmente o que se passa comigo:", "4.É muito fácil para mim:", "4.É muito fácil para mim:", "4.É muito fácil para mim:", "4.É muito fácil para mim:",
            "5.Eu me percebo assim:", "5.Eu me percebo assim:", "5.Eu me percebo assim:", "5.Eu me percebo assim:"};

    String[] SubPerguntas = {"1.1:Intuição", "1.2:O que me soa melhor", "1.3:O que me parece melhor", "1.4:Um estudo preciso e minucioso do assunto", "2.1:O tom de voz da outra pessoa",
            "2.2:Se eu posso ou não ver o argumento da outra pessoa", "2.3:A lógica do argumento da outra pessoa", "2.4:se eu entro em contato ou não com os sentimentos reais do outro",
            "3.1:Do modo como me visto e aparento", "3.2:Pelos sentimentos que compartilho", "3.3:Pelas palavras que escolho", "3.4:Pelo tom da minha voz", "4.1:Achar o volume e a sintonia ideais num sistema de som",
            "4.2:Selecionar o ponto mais relevante relativo a um assunto interessante", "4.3:Escolher os móveis mais confortáveis", "4.4:escolher as combinações de cores mais ricas e atraentes",
            "5.1:Se estou muito em sintonia com os sons do ambiente", "5.2:Se sou muito capaz de raciocinar com fatos e dados novos", "5.3:Eu sou muito sensível à maneira como a roupa veste o meu corpo",
            "5.4:Eu respondo fortemente às cores e à aparência de uma sala"};

    String[] respA = {"4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você",
            "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você",
            "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você", "4 A que melhor descreve você"};

    String[] respB = {"3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição",
            "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição",
            "3 A próxima melhor descrição", "3 A próxima melhor descrição", "3 A próxima melhor descrição"};

    String[] respC = {"2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor",
            "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor", "2 A próxima melhor"};

    String[] respD = {"1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você",
            "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você", "1 A que menos descreve você"};

    int numeroPerguntas = 0, C = 0, V = 0, A = 0, D = 0;

    int[] listaResposta = new int[perguntas.length];
    int[] Vis = {0, 0, 3, 0, 0, 6, 0, 0, 9, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 20};
    int[] Cines = {1, 0, 0, 0, 0, 0, 0, 8, 0, 10, 0, 0, 0, 0, 15, 0, 0, 0, 19, 0};
    int[] Aud = {0, 2, 0, 0, 5, 0, 0, 0, 0, 0, 0, 12, 13, 0, 0, 0, 17, 0, 0, 0};
    int[] Dig = {0, 0, 0, 4, 0, 0, 7, 0, 0, 0, 11, 0, 0, 14, 0, 0, 0, 18, 0, 0};
    int[] numQ = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    int Cinestecico, Visual, Auditivo, Digital;

    Animation some;
    Animation aparece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_a);
        layoutA = findViewById(R.id.layoutTesteA);
        txtTituloA = findViewById(R.id.textTituloA);
        txtPerguntaA = findViewById(R.id.textPerguntaA);
        txtSubPergunta = findViewById(R.id.textSubPergunta);
        radioGroupA = findViewById(R.id.radioA);
        rd1 = findViewById(R.id.radioA1);
        rd2 = findViewById(R.id.radioA2);
        rd3 = findViewById(R.id.radioA3);
        rd4 = findViewById(R.id.radioA4);
        btnOKA = findViewById(R.id.btnOKA);
        progressBarA = findViewById(R.id.progressBarA);

        btnOKA.setEnabled(false);


        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1000);
        aparece.setDuration(1000);
        layoutA.setVisibility(View.GONE);
        layoutA.startAnimation(aparece);

        txtTituloA.setText("Teste do Sistema Representacional");
        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutA.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBarA.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutA.setVisibility(View.VISIBLE);
                progressBarA.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        AtualizaPerguntaTesteA(btnOKA);
        radioGroupA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioA1:
                        listaResposta[numeroPerguntas - 1] = 4;
                        break;

                    case R.id.radioA2:
                        listaResposta[numeroPerguntas - 1] = 3;
                        break;

                    case R.id.radioA3:
                        listaResposta[numeroPerguntas - 1] = 2;
                        break;

                    case R.id.radioA4:
                        listaResposta[numeroPerguntas - 1] = 1;
                        break;
                }
                btnOKA.setEnabled(true);
            }
        });
    }

    public void AtualizaPerguntaTesteA(View view) {
        if (numeroPerguntas == perguntas.length) {
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            radioGroupA.clearCheck();
            btnOKA.setEnabled(false);

            Visual();
            Cines();
            Auditivo();
            Digital();
            telaResultado();
        } else {
            txtPerguntaA.setText(perguntas[numeroPerguntas]);
            txtSubPergunta.setText(SubPerguntas[numeroPerguntas]);
            rd1.setText(respA[numeroPerguntas]);
            rd2.setText(respB[numeroPerguntas]);
            rd3.setText(respC[numeroPerguntas]);
            rd4.setText(respD[numeroPerguntas]);
            numeroPerguntas++;
            radioGroupA.clearCheck();
            progressBarA.setVisibility(View.VISIBLE);
            layoutA.startAnimation(aparece);
            btnOKA.setEnabled(false);
        }

    }

    public void Visual() {
        int contLista = 0;
        for (int numero : Vis) {
            System.out.println(numero);
            if (numero == numQ[contLista]) {
                V += listaResposta[contLista];
                Visual = V * 2;
                System.out.println("Resposta Certa");
            } else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void Cines() {
        int contLista = 0;
        for (int numero : Cines) {
            System.out.println(numero);
            if (numero == numQ[contLista]) {
                C += listaResposta[contLista];
                Cinestecico = C * 2;
                System.out.println("Resposta Certa");
            } else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void Auditivo(){
        int contLista = 0;
        for (int numero : Aud) {
            System.out.println(numero);
            if (numero == numQ[contLista]) {
                A += listaResposta[contLista];
                Auditivo = A * 2;
                System.out.println("Resposta Certa");
            } else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void Digital(){
        int contLista = 0;
        for (int numero : Dig) {
            System.out.println(numero);
            if (numero == numQ[contLista]) {
                D += listaResposta[contLista];
                Digital = D * 2;
                System.out.println("Resposta Certa");
            } else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }
    void telaResultado(){
        Intent intent = new Intent(getApplicationContext(), ResultadoA.class);
        Bundle parametrosA = new Bundle();
        parametrosA.putInt("Visual" , V);
        parametrosA.putInt("Cinestecico" , C);
        parametrosA.putInt("Auditivo", A);
        parametrosA.putInt("Digital", D);
        intent.putExtras(parametrosA);
        startActivity(intent);
    }
}
