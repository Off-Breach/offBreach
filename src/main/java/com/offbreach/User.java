package com.offbreach;

public class User {

    private String nome = "";
    private String email = "";
    private String senha = "";

    DatabaseConnection dbConnection = new DatabaseConnection();

    public User(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public User() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return nome + email + senha;
    }
}
