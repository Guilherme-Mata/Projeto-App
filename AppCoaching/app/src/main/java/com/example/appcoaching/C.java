package com.example.appcoaching;

public class C {
    private String nome, email, cpf, uid, data;
    private int MR, MA, MP;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMR() {
        return MR;
    }

    public void setMR(int MR) {
        this.MR = MR;
    }

    public int getMA() {
        return MA;
    }

    public void setMA(int MA) {
        this.MA = MA;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }
    @Override
    public String toString() {
        return "Motivação Realização: "+MR+'\n'+ "Motivação Afiliação: "+MA+'\n'+"Motivação Poder: "+MP+'\n'+"Data: "+data;
    }
}
