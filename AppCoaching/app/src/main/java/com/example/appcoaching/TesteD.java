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

public class TesteD extends AppCompatActivity {
    private ConstraintLayout layoutD;
    private TextView txtTituloD, txtPerguntaD, txtNquestaoD;
    private RadioGroup radioGroupD;
    private RadioButton rD1, rD2, rD3, rD4, rD5;
    private ProgressBar progressBarD;
    private Button btOKD;

    String[] perguntas = {"1.Costumo ir a eventos, festas ou cursos, mesmo sem ter muita vontade, para agradar meu chefe meus amigos ou família.",
            "2.Não consigo realizar tudo que me propus fazer no dia e preciso fazer hora extra e levar trabalho para casa.",
            "3.Quando recebo um novo e-mail, costumo dar uma olhada para checar o conteúdo.","4.Costumo visitar com regularidade pessoas relevantes em minha vida como amigos parentes, filhos.",
            "5.É comum aparecer problemas inesperados no meu dia a dia.",
            "6.Assumo compromissos com outras pessoas ou aceito novas posições na empresa, mesmo que não goste muito da nova atividade, se for para aumentar meus rendimentos ou obter uma promoção.",
            "7.Tenho um tempo definido para dedicar a mim mesmo e nele, posso fazer o que quiser.","8.Costumo deixar para fazer relatórios, imposto de renda, compras de Natal, estudar para provas e outras tarefas perto do prazo de entrega.",
            "9.Nos dias de descanso, costumo passar boa parte do dia assistindo à televisão, jogando ou acessando a internet.","10.Faço um planejamento por escrito de tudo que preciso fazer durante minha semana.",
            "11.Posso afirmar que estou conseguindo realizar tudo que gostaria em minha vida e que o tempo está passando na realidade correta.",
            "12.Costumo participar de reuniões sem saber direito o conteúdo, o porquê devo participar ou a que resultado aquele encontro pode levar.","13.Consigo melhores resultados e me sinto mais produtivo quando estou sob pressão ou com o prazo curto.",
            "14.Quando quero alguma coisa, defino esse objetivo por escrito, estabeleço prazos em minha agenda, monitoro os resultados obtidos e os comparo com os esperados.",
            "15.Leio muitos e-mails desnecessários, com piadas, correntes, propagandas, apresentações, produtos e etc.","16.Estive atrasado com minhas tarefas ou reuniões nas últimas semanas.",
            "17.Faço esporte com regularidade, me alimento da forma adequada e tenho o lazer que gostaria.","18.É comum reduzir meu horário de almoço ou até mesmo comer enquanto trabalho para concluir um projeto ou tarefa."};

    String[] respA = {"1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca.","1.Nunca."};

    String[] respB = {"2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente.",
            "2.Raramente.","2.Raramente.","2.Raramente.","2.Raramente."};

    String[] respC = {"3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.","3.Às vezes.",
            "3.Às vezes.","3.Às vezes.","3.Às vezes."};

    String[] respD = {"4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.",
            "4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre.","4.Quase sempre."};

    String[] respE = {"5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre.","5.Sempre."};

    int[] listaRespostas = new int[perguntas.length];
    int numeroPerguntas = 0, ConjuntoA = 0, ConjuntoB = 0, ConjuntoC = 0;

    int[] CA = {1,0,3,0,0,6,0,0,9,0,0,12,0,0,15,0,0,0};
    int[] CB = {0,0,0,4,0,0,7,0,0,10,11,0,0,14,0,0,17,0};
    int[] CC = {0,2,0,0,5,0,0,8,0,0,0,0,13,0,0,16,0,18};

    int[] numQ = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    int totGeral = 0, nQues = 0;
    Animation some;
    Animation aparece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_d);
        layoutD = findViewById(R.id.layoutD);
        txtTituloD = findViewById(R.id.textTituloD);
        txtPerguntaD = findViewById(R.id.textPerguntaD);
        txtNquestaoD = findViewById(R.id.textNquestaoD);
        radioGroupD = findViewById(R.id.radioD);
        rD1 = findViewById(R.id.radioD1);
        rD2 = findViewById(R.id.radioD2);
        rD3 = findViewById(R.id.radioD3);
        rD4 = findViewById(R.id.radioD4);
        rD5 = findViewById(R.id.radioD5);
        progressBarD = findViewById(R.id.progressBarD);
        btOKD = findViewById(R.id.btnOKD);

        btOKD.setEnabled(false);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1000);
        aparece.setDuration(1000);
        layoutD.setVisibility(View.GONE);
        layoutD.startAnimation(aparece);

        txtTituloD.setText("Triade do Tempo");

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutD.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBarD.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutD.setVisibility(View.VISIBLE);
                progressBarD.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AtualizaPerguntaTesteD(btOKD);
        radioGroupD.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioD1:
                        listaRespostas[numeroPerguntas-1] = 1;
                        break;

                    case R.id.radioD2:
                        listaRespostas[numeroPerguntas-1] = 2;
                        break;
                    case R.id.radioD3:
                        listaRespostas[numeroPerguntas-1] = 3;
                        break;

                    case R.id.radioD4:
                        listaRespostas[numeroPerguntas-1] = 4;
                        break;

                    case R.id.radioD5:
                        listaRespostas[numeroPerguntas-1] = 5;
                        break;
                }
                btOKD.setEnabled(true);
            }
        });
    }

    public void AtualizaPerguntaTesteD(View view){
        if (numeroPerguntas==perguntas.length) {
            rD1.setEnabled(false);
            rD2.setEnabled(false);
            rD3.setEnabled(false);
            rD4.setEnabled(false);
            rD5.setEnabled(false);
            radioGroupD.clearCheck();
            btOKD.setEnabled(false);

            ConjuntoA();
            ConjuntoB();
            ConjuntoC();
            telaResultado();
        }

        else {
            txtPerguntaD.setText(perguntas[numeroPerguntas]);
            rD1.setText(respA[numeroPerguntas]);
            rD2.setText(respB[numeroPerguntas]);
            rD3.setText(respC[numeroPerguntas]);
            rD4.setText(respD[numeroPerguntas]);
            rD5.setText(respE[numeroPerguntas]);
            nQues++;
            txtNquestaoD.setText(" " + nQues + "/18");
            numeroPerguntas++;
            radioGroupD.clearCheck();
            progressBarD.setVisibility(View.VISIBLE);
            layoutD.startAnimation(aparece);
            btOKD.setEnabled(false);
        }
    }

    public void ConjuntoA(){
        int contLista = 0;
        for (int numero : CA){
            System.out.println(numero);
            if (numero == numQ[contLista]){
                ConjuntoA += listaRespostas[contLista];
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void ConjuntoB(){
        int contLista = 0;
        for (int numero : CB){
            System.out.println(numero);
            if (numero == numQ[contLista]){
                ConjuntoB += listaRespostas[contLista];
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void ConjuntoC(){
        int contLista = 0;
        for (int numero : CC){
            System.out.println(numero);
            if (numero == numQ[contLista]){
                ConjuntoC += listaRespostas[contLista];
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    void telaResultado(){
        totGeral = ConjuntoA + ConjuntoB + ConjuntoC;
        Intent intent = new Intent(getApplicationContext(), ResultadoD.class);
        Bundle paramentros = new Bundle();
        paramentros.putInt("conjuntoA" , ConjuntoA);
        paramentros.putInt("conjuntoB", ConjuntoB);
        paramentros.putInt("conjuntoC", ConjuntoC);
        paramentros.putInt("totGeral", totGeral);
        intent.putExtras(paramentros);
        startActivity(intent);
    }

}
