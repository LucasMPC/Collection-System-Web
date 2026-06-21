package com.collectionsystem.model;

public class Colecao {
    private int id;
    private String nome;
    private String icone;
    private Usuario usuario;

    public Colecao() {}

    public Colecao(String nome, String icone) {
        this.nome = nome;
        this.icone = icone;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getIcone() { return icone; }
    public void setIcone(String icone) { this.icone = icone; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}