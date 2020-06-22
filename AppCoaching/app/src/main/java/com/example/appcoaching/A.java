package com.example.appcoaching;

public class A {
    private String nome, email, cpf, uid, data;
    private int visual, cinestecico, auditivo, digital;

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

    public int getVisual() {
        return visual;
    }

    public void setVisual(int visual) {
        this.visual = visual;
    }

    public int getCinestecico() {
        return cinestecico;
    }

    public void setCinestecico(int cinestecico) {
        this.cinestecico = cinestecico;
    }

    public int getAuditivo() {
        return auditivo;
    }

    public void setAuditivo(int auditivo) {
        this.auditivo = auditivo;
    }

    public int getDigital() {
        return digital;
    }

    public void setDigital(int digital) {
        this.digital = digital;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Visual: "+visual +'\n'+ "Auditivo: "+auditivo+'\n'+"Digital: "+digital+'\n'+"Cinestecico: "+cinestecico+'\n'+"Data: "+data;
    }
}