package com.collectionsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String username;
    private String sena;
    private String dataNascimento;
    private String ultimoJogoCadastrado = "Nenhum";
    private List<String> historicoAcesso = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome, String email, String username, String sena, String dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.sena = sena;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getSenha() { return sena; }
    public void setSenha(String sena) { this.sena = sena; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getUltimoJogoCadastrado() { return ultimoJogoCadastrado; }
    public void setUltimoJogoCadastrado(String jogo) { this.ultimoJogoCadastrado = jogo; }
    public List<String> getHistoricoAcesso() { return historicoAcesso; }
    public void setHistoricoAcesso(List<String> historico) { this.historicoAcesso = historico; }
}