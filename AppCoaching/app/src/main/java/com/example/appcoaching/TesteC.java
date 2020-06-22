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
import android.widget.Switch;
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

public class TesteC extends AppCompatActivity {
    private ConstraintLayout layoutC;
    private TextView txtTituloC, txtPerguntaC, txtNqC;
    private RadioGroup radioGroupC;
    private RadioButton rd1, rd2, rd3;
    private Button btnOKC;
    private ProgressBar progressBarC;
    String[] perguntas = {"1) Você foi encarregado de executar um determinado projeto e tem carta branca para agir. O que você acharia importante fazer logo?",
            "2) Se fosse possível moldar os filhos ao nosso gosto, como gostaria que fossem os seus?",
            "3) Três empregos são oferecidos na mesma organização com os mesmos salários , mas as funções são diferentes. Qual você escolheria?",
            "4) A pesquisa que mais lhe interessaria:","5) Qual a qualidade de liderança que mais valoriza?","6) Onde você preferiria empregar dinheiro?",
            "7) Supondo que os vencimentos e o horário de trabalho fossem os mesmos para três cargos e supondo também que tivesse competência para todos, qual escolheria?",
            "8) Imagine três mesas, cada uma com três convidados. De qual delas gostaria de participar?",
            "9) A organização da qual mais se orgulharia em ser membro efetivo:","10) Sem considerar o preço, qual o presente que mais lhe agradaria receber?"};

    String[] RespA = {"1) Definir os objetivos e as dificuldades que poderão surgir.","4) Pessoas realizadoras e cheias de iniciativa.","7) Planejar projetos novos.",
            "10) Uma pesquisa de clima motivacional.","13) Capacidade de planejar e executar.","16) Em um projeto imobiliário.","19) Ser assistente de um conselheiro matrimonial.",
            "22) Santos Dumont, Bernardinho e Barão de Mauá.","25) National Aeronautics and Space Administration (Nasa).","10) Sem considerar o preço, qual o presente que mais lhe agradaria receber?"};

    String[] RespB = {"2) Escolher pessoas companheiras e de sua confiança para trabalhar com você.","5) Pessoas compreensivas e tolerantes.","8) Apaziguar conflitos e promover o bom relacionamento entre as pessoas.",
            "11) Uma pesquisa sobre centros de poder.","14) Capacidade de estimular e persuadir.","17) Em uma campanha contra tóxicos.","20) Ser assistente de um deputado federal.",
            "23) Lula da Silva, Tancredo Neves e Juscelino Kubitschek.","26) Organização das Nações Unidas para a Educação, a Ciência e a Cultura (UNESCO).","29) Uma máquina fotográfica.",};

    String[] RespC = {"3) Estabelecer claramente as normas que irão reger o bom andamento do projeto.","6) Pessoas influentes e líderes na sociedade.","9) Dirigir um projeto importante",
            "12) Uma pesquisa técnico-científica.","15) Capacidade de compreender e tolerar.","18) Em assistência aos menores carentes.","21) Ser assistente de um executivo para novos negócios",
            "24) Zilda Arns, Nelson Mandela e João Paulo ll.","27) Organização das Nações Unidas (ONU).","30) Um álbum de família"};

    int[] listaRespostas = new int[perguntas.length];

    int[] SomaR = {1,4,7,12,13,16,21,22,25,29};
    int[] gabaritoR = {1,1,1,3,1,1,3,1,1,2};

    int[] SomaA = {2,5,8,10,15,18,19,24,26,30};
    int[] gabaritoA = {2,2,2,1,3,3,1,3,2,3};

    int[] SomaP = {3,6,9,11,14,17,20,23,27,28};
    int[] gabaritoP = {3,3,3,2,2,2,2,2,3,1};

    int MR=0, MA=0, MP=0, numeroPerguntas=0, nQuest = 0;
    Animation some;
    Animation aparece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_c);
        layoutC = findViewById(R.id.layoutC);
        txtTituloC = findViewById(R.id.textTituloC);
        txtPerguntaC = findViewById(R.id.textPerguntaC);
        txtNqC = findViewById(R.id.textNquestaoC);
        radioGroupC = findViewById(R.id.radioC);
        rd1 = findViewById(R.id.radioC1);
        rd2 = findViewById(R.id.radioC2);
        rd3 = findViewById(R.id.radioC3);
        btnOKC = findViewById(R.id.btnOKC);
        progressBarC = findViewById(R.id.progressBarC);

        btnOKC.setEnabled(false);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1000);
        aparece.setDuration(1000);
        layoutC.setVisibility(View.GONE);
        layoutC.startAnimation(aparece);

        txtTituloC.setText("O QUE TE MOTIVA");
        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutC.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBarC.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutC.setVisibility(View.VISIBLE);
                progressBarC.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutC.setVisibility(View.VISIBLE);
                progressBarC.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AtualizaPerguntaTesteC(btnOKC);
        radioGroupC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioC1:
                        listaRespostas[numeroPerguntas-1] = 1;
                        break;
                    case R.id.radioC2:
                        listaRespostas[numeroPerguntas-1] = 2;
                        break;
                    case R.id.radioC3:
                        listaRespostas[numeroPerguntas-1] = 3;
                        break;
                }
                btnOKC.setEnabled(true);
            }
        });

    }

    public void AtualizaPerguntaTesteC(View view){
        if (numeroPerguntas==perguntas.length) {
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            radioGroupC.clearCheck();
            btnOKC.setEnabled(false);

            ConfereR();
            ConfereA();
            ConfereP();
            telaResultado();
        }
        else {
            txtPerguntaC.setText(perguntas[numeroPerguntas]);
            rd1.setText(RespA[numeroPerguntas]);
            rd2.setText(RespB[numeroPerguntas]);
            rd3.setText(RespC[numeroPerguntas]);
            nQuest++;
            txtNqC.setText(" " + nQuest + "/10");
            numeroPerguntas++;
            btnOKC.setEnabled(false);
            radioGroupC.clearCheck();
            progressBarC.setVisibility(View.VISIBLE);
            layoutC.startAnimation(aparece);
            btnOKC.setEnabled(false);
        }
    }

    public void ConfereR(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoR[contLista]){
                MR+=SomaR[contLista];
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void ConfereA(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoA[contLista]){
                MA+=SomaA[contLista];
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void ConfereP(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoP[contLista]){
                MP+=SomaP[contLista];
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    void telaResultado(){
        Intent intent = new Intent(getApplicationContext(), ResultadoC.class);
        Bundle paramentros = new Bundle();
        paramentros.putInt("MR" , MR);
        paramentros.putInt("MA" , MA);
        paramentros.putInt("MP", MP);
        intent.putExtras(paramentros);
        startActivity(intent);
    }
}

