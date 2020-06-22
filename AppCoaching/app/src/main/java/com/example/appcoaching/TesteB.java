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

public class TesteB extends AppCompatActivity {
    private ConstraintLayout layoutB;
    private RadioGroup radioGroupB;
    private RadioButton rd1, rd2, rd3, rd4;
    private TextView txtTituloB, txtNquestB, txtPerguntasB;
    private Button btOKB;
    private ProgressBar progressBarB;

    String pergunstas [] = {"1. Eu sou...", "2. Eu gosto de...", "3. Se você quiser se dar bem comigo...",
            "4. Para conseguir obter bons resultados é preciso...", "5. Eu me divirto quando...","6. Eu penso que...",
            "7. Minha preocupação é...","8. Eu prefiro...","9. Eu gosto de...","10. Eu gosto de chegar...",
            "11. Um ótimo dia para mim é quando...", "12. Eu vejo a morte como...", "13. Minha filosofia de vida é...",
            "14. Eu sempre gostei de...","15. Eu gosto de mudanças se...","16. Não existe nada de errado em...","17. Eu gosto de buscar conselhos de...",
            "18. Meu lema é...","19. Eu gosto de...","20. Tempo para mim é...","21. Se eu fosse bilionário...",
            "22. Eu acredito que...","23. Eu acredito também que...","24. Eu acredito ainda que...","25. Eu penso que..."};

    String respA [] = {"Idealista, criativo e visionário","Ser piloto","Me dê liberdade","Ter incertezas","Estou me exercitando",
            "Unidos venceremos, divididos perderemos","Gerar a ideia global","Perguntas a respostas","Fazer progresso",
            "Na frente","Consigo fazer muitas coisas","Uma grande aventura misteriosa","Há ganhadores e perdedores, e eu acredito ser um ganhador",
            "Explorar","Me der uma vantagem competitiva","Se colocar na frente","Pessoas bem-sucedidas","Fazer o que precisa ser feito",
            "Complexidade, mesmo se confuso","Algo que detesto desperdiçar","Faria doações para muitas entidades","O destino é mais importante que a jornada",
            "Aquele que hesita está perdido","É melhor prudência do que arrependimento","Não é fácil ficar encurralado"};

    String respB [] = {"Divertido, espiritual e benéfico","Conversar com os passageiros","Me deixe saber sua expectativa","Controlar o essencial",
            "Tenho novidades","O ataque é melhor que a defesa","Fazer com que as pessoas gostem","Ter todos os detalhes","Construir memórias",
            "Junto","Me divirto com meus amigos","Oportunidade para rever os falecidos","Para eu ganhar, ninguém precisa perder","Evitar surpresas",
            "For divertido e puder ser compartilhado","Colocar os outros na frente","Anciões e conselheiros","Fazer bem feito","Ordem e sistematização",
            "Um grande ciclo","Criaria uma poupança avantajada","A jornada é mais importante que o destino","De grão em grão a galinha enche o papo",
            "A autoridade deve ser desafiada","É preferível olhar, antes de pular"};

    String respC [] = {"Confiável, meticuloso e previsível","Planejar a viagem","Lidere, siga ou saia do caminho","Diversão e celebração",
            "Estou com os outros","É bom ser manso, mas andar com um porrete","Fazer com que funcione","Vantagens a meu favor","Fazer sentido",
            "Na hora","Tudo segue conforme planejado","Um modo de receber recompensas","Para ganhar é preciso seguir as regras","Focalizar a meta",
            "Me der mais liberdade e variedade","Mudar de ideia","Autoridades no assunto","Fazer junto com o grupo","Calor humano e animação",
            "Uma flecha que leva ao inevitável","Faria o que desse na cabeça","Um centavo economizado é um centavo ganho","O que vai, volta",
            "Ganhar é fundamental","Duas cabeças pensam melhor que do que uma"};

    String respD [] = {"Focado, determinado e persistente","Explorar novas rotas","Seja amigável, carinhoso e compreensivo","Planejar e obter recursos",
            "Determino as regras","Um homem prevenido vale por dois","Fazer com que aconteça","Que todos tenham a chance de ser ouvido",
            "Tornar as pessoas confortáveis","Em outro lugar","Desfruto de coisas novas e estimulantes","Algo que sempre chega muito cedo",
            "Para ganhar, é necessário inventar novas regras","Realizar uma abordagem natural","Melhorar ou me der mais controle",
            "Ser consistente","Lugares, os mais estranhos","Simplesmente fazer","Coisas claras e simples","Irrelevante","Exibiria bastante com algumas pessoas",
            "Bastam um navio e uma estrela para navegar","Um sorriso ou uma careta é o mesmo para quem é cego","O coletivo é mais importante do que o individual",
            "Se você não tem condições de competir, não compita"};

    int[] listaRespostas = new int[pergunstas.length];
    int gabaritoAguia[] = {1,4,1,1,2,3,1,1,4,4,4,1,4,1,3,3,4,1,1,4,3,4,4,2,1};
    int gabaritoGato[] = {2,2,4,3,3,1,2,4,2,2,2,2,2,4,2,2,2,3,3,2,1,2,3,4,3};
    int gabaritoLobo[] = {3,3,2,2,4,4,3,2,3,3,3,3,3,2,4,4,3,2,2,3,2,3,2,1,2};
    int gabaritoTubarao[] = {4,1,3,4,1,2,4,3,1,1,1,4,1,3,1,1,1,4,4,1,4,1,1,3,4};
    int aguia=0, gato=0, lobo=0, tubarao=0, numeroPerguntas = 0;
    int nQuest = 0;
    int I, O, C, A;
    Animation some;
    Animation aparece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_b);
        txtTituloB = findViewById(R.id.textTituloB);
        txtNquestB = findViewById(R.id.textNquestaoB);
        txtPerguntasB = findViewById(R.id.textPerguntasB);
        btOKB = findViewById(R.id.btnOKB);
        progressBarB = findViewById(R.id.progressBarB);
        radioGroupB = findViewById(R.id.radioB);
        rd1 = findViewById(R.id.radioB1);
        rd2 = findViewById(R.id.radioB2);
        rd3 = findViewById(R.id.radioB3);
        rd4 = findViewById(R.id.radioB4);
        layoutB = findViewById(R.id.layoutTesteB);

        btOKB.setEnabled(false);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1000);
        aparece.setDuration(1000);
        layoutB.setVisibility(View.GONE);
        layoutB.startAnimation(aparece);

        txtTituloB.setText("Avaliação de Preferecia Cerebral");
        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutB.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBarB.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutB.setVisibility(View.VISIBLE);
                progressBarB.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AtualizaPergunta(btOKB);
        radioGroupB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioB1:
                        listaRespostas[numeroPerguntas-1] = 1;
                        break;
                    case R.id.radioB2:
                        listaRespostas[numeroPerguntas-1] = 2;
                        break;
                    case R.id.radioB3:
                        listaRespostas[numeroPerguntas-1] = 3;
                        break;

                    case R.id.radioB4:
                        listaRespostas[numeroPerguntas-1] = 4;
                        break;
                }
                btOKB.setEnabled(true);
            }
        });
    }

    public void AtualizaPergunta(View view){
        if (numeroPerguntas==pergunstas.length){
            rd1.setEnabled(false);
            rd2.setEnabled(false);
            rd3.setEnabled(false);
            rd4.setEnabled(false);
            radioGroupB.clearCheck();
            btOKB.setEnabled(false);

            ConfereAguia();
            ConfereGato();
            ConfereLobo();
            ConfereTubarao();
            telaResultado();
        }
        else {
            txtPerguntasB.setText(pergunstas[numeroPerguntas]);
            rd1.setText(respA[numeroPerguntas]);
            rd2.setText(respB[numeroPerguntas]);
            rd3.setText(respC[numeroPerguntas]);
            rd4.setText(respD[numeroPerguntas]);
            nQuest++;
            txtNquestB.setText(" " +nQuest+"/25");
            numeroPerguntas++;
            radioGroupB.clearCheck();
            progressBarB.setVisibility(View.VISIBLE);
            layoutB.startAnimation(aparece);
            btOKB.setEnabled(false);
        }
    }
    public void ConfereAguia(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoAguia[contLista]){
                aguia++;
                I = aguia * 4;
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void ConfereGato(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoGato[contLista]){
                gato++;
                C = gato * 4;
             //   System.out.println("Resposta Certa");
            }//else {
             //   System.out.println("Resposta Errada");
            //}
            contLista++;
        }
    }
    public void ConfereLobo(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoLobo[contLista]){
                lobo++;
                O = lobo * 4;
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }

    public void ConfereTubarao(){
        int contLista = 0;
        for (int numero : listaRespostas){
            System.out.println(numero);
            if (numero == gabaritoTubarao[contLista]){
                tubarao++;
                A = tubarao * 4;
                System.out.println("Resposta Certa");
            }else {
                System.out.println("Resposta Errada");
            }
            contLista++;
        }
    }


    void telaResultado() {
        Intent intent = new Intent(getApplicationContext(), RelsutadoB.class);
        Bundle paramentros = new Bundle();
        paramentros.putInt("Aguia", I);
        paramentros.putInt("Gato", C);
        paramentros.putInt("Lobo", O);
        paramentros.putInt("Tubarao", A);
        intent.putExtras(paramentros);
        startActivity(intent);
    }
}
