package com.offbreach;

public class User {

//    private Integer id;
    private String nome;
//    private String telefone;
    private String email;
    private String senha;
//    private Integer responsavel;
//    private Integer fkClinica;

    private DatabaseConnection dbConnection = new DatabaseConnection();

    public User(String nome,String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public User() {
    }

    public DatabaseConnection getDbConnection() {
        return dbConnection;
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
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("nome=").append(nome);
        sb.append(", email=").append(email);
        sb.append(", senha=").append(senha);
        sb.append(", dbConnection=").append(dbConnection);
        sb.append('}');
        return sb.toString();
    }

}
