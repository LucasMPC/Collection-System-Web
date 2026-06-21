package com.collectionsystem.model;

public class Jogo {
    private int id;
    private String nome;
    private String dataLancamento;
    private String descricao;
    private String genero;
    private String tipoMidia;
    private int idColecao;
    private Desenvolvedora desenvolvedora;

    public Jogo() {}

    public Jogo(String nome, String dataLancamento, String descricao, String genero, String tipoMidia, int idColecao, Desenvolvedora desenvolvedora) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.genero = genero;
        this.tipoMidia = tipoMidia;
        this.idColecao = idColecao;
        this.desenvolvedora = desenvolvedora;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(String dataLancamento) { this.dataLancamento = dataLancamento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getTipoMidia() { return tipoMidia; }
    public void setTipoMidia(String tipoMidia) { this.tipoMidia = tipoMidia; }
    public int getIdColecao() { return idColecao; }
    public void setIdColecao(int idColecao) { this.idColecao = idColecao; }
    public Desenvolvedora getDesenvolvedora() { return desenvolvedora; }
    public void setDesenvolvedora(Desenvolvedora dev) { this.desenvolvedora = dev; }
}