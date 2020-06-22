package com.example.appcoaching;

public class D {
    private String nome, email, cpf, uid, data;
    private double Importancia, Urgencia, Circustancia;

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

    public double getImportancia() {
        return Importancia;
    }

    public void setImportancia(double importante) {
        this.Importancia = importante;
    }

    public double getUrgencia() {
        return Urgencia;
    }

    public void setUrgencia(double urgencia) {
        this.Urgencia = urgencia;
    }

    public double getCircustancia() {
        return Circustancia;
    }

    public void setCircustancia(double circustancia) {
        this.Circustancia = circustancia;
    }
    @Override
    public String toString() {
        return "Importancia: "+Importancia+'\n'+ "Circustancia: "+Circustancia+'\n'+"Urgencia: "+Urgencia+'\n'+"Data: "+data;
    }
}
