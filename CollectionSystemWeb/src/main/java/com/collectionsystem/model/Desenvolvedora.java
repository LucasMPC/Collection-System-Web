package com.collectionsystem.model;

public class Desenvolvedora {
    private int id;
    private String nome;
    private String pais;
    private String dataFundacao;

    public Desenvolvedora() {}

    public Desenvolvedora(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Desenvolvedora(String nome, String pais, String dataFundacao) {
        this.nome = nome;
        this.pais = pais;
        this.dataFundacao = dataFundacao;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getDataFundacao() { return dataFundacao; }
    public void setDataFundacao(String dataFundacao) { this.dataFundacao = dataFundacao; }
}