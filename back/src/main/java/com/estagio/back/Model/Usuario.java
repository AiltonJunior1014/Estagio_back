package com.estagio.back.Model;

public class Usuario {
    private int Codigo;
    private String Nome;
    private String Senha;
    private boolean permissao;
    

    
    public Usuario() {
    }

    public Usuario(String nome, boolean permissao, String senha){
        Nome = nome;
        permissao = permissao;
        senha = senha;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public Usuario(int codigo, String nome, boolean permissao, String senha) {
        Codigo = codigo;
        Nome = nome;
        permissao = permissao;
        senha = senha;
    }
    public int getCodigo() {
        return Codigo;
    }
    public void setCodigo(int codigo) {
        Codigo = codigo;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public char isAdmin() {
        if(permissao)
            return '1';
        return '0';
    }
    public void setAdmin(boolean admin) {
        permissao = admin;
    }

    
}
