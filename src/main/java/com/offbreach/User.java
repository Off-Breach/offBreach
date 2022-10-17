package com.offbreach;

public class User {

    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private Integer responsavel;
    private Integer fkClinica;

    private DatabaseConnection dbConnection = new DatabaseConnection();

    public User(Integer id, String nome, String telefone, String email, String senha, Integer responsavel, Integer fkClinica) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.responsavel = responsavel;
        this.fkClinica = fkClinica;
    }

    public User() {
    }

    public DatabaseConnection getDbConnection() {
        return dbConnection;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getResponsavel() {
        return responsavel;
    }

    public Integer getFkClinica() {
        return fkClinica;
    }
    
     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("nome=").append(nome);
        sb.append(", email=").append(email);
        sb.append(", senha=").append(senha);
        sb.append(", fkClinica=").append(fkClinica);
        sb.append(", dbConnection=").append(dbConnection);
        sb.append('}');
        return sb.toString();
    }

}
