package com.example.appcoaching;

public class B {
    private String nome, email, cpf, uid, data;
    private int Aguia, Lobo, Gato, Tubarao;

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

    public int getAguia() {
        return Aguia;
    }

    public void setAguia(int aguia) {
        Aguia = aguia;
    }

    public int getLobo() {
        return Lobo;
    }

    public void setLobo(int lobo) {
        Lobo = lobo;
    }

    public int getGato() {
        return Gato;
    }

    public void setGato(int gato) {
        Gato = gato;
    }

    public int getTubarao() {
        return Tubarao;
    }

    public void setTubarao(int tubarao) {
        Tubarao = tubarao;
    }

    @Override
    public String toString() {
        return "Águia: "+Aguia +'\n'+ "Gato: "+Gato+'\n'+"Lobo: "+Lobo+'\n'+"Tubarão: "+Tubarao+'\n'+"Data: "+data;
    }
}
