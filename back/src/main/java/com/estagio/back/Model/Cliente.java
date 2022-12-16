package com.estagio.back.Model;

public class Cliente {
    private int Codigo;
    private String Nome;
    private String CPFCNPJ;
    private String telefone;
    private String Endereco;
    private String numero;
    private String Bairro;
    private String municipio;

    

    public Cliente() {
        
    }



    public Cliente(String nome, String cPFCNPJ, String telefone, String endereco, String numero,
    String bairro, String municipio) {
        Nome = nome;
        CPFCNPJ = cPFCNPJ;
        this.telefone = telefone;
        Endereco = endereco;
        this.numero = numero;
        Bairro = bairro;
        this.municipio = municipio;
    }


    
    public Cliente(int codigo, String nome, String cPFCNPJ, String telefone, String endereco, String numero,
            String bairro, String municipio) {
        Codigo = codigo;
        Nome = nome;
        CPFCNPJ = cPFCNPJ;
        this.telefone = telefone;
        Endereco = endereco;
        this.numero = numero;
        Bairro = bairro;
        this.municipio = municipio;
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
    public String getCPFCNPJ() {
        return CPFCNPJ;
    }
    public void setCPFCNPJ(String cPFCNPJ) {
        CPFCNPJ = cPFCNPJ;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return Endereco;
    }
    public void setEndereco(String endereco) {
        Endereco = endereco;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return Bairro;
    }
    public void setBairro(String bairro) {
        Bairro = bairro;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }


    

}
