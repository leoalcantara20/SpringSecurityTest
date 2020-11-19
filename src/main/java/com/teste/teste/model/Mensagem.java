package com.teste.teste.model;

public class Mensagem {

    private Integer id;
    private String menssagem;

    public Mensagem() {}

    public Mensagem(Integer id, String menssagem) {
        this.id = id;
        this.menssagem = menssagem;
    }

    public Integer getId() {
        return id;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
