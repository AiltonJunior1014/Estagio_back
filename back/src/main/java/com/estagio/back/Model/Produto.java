package com.estagio.back.Model;

public class Produto {
    private int Codigo;
    private String Nome;
    private int Unidade;
    private Float Avista;
    private Float Aprazo;
    private Fornecedor Fornecedor;


    

    public Produto() {
    }

    public Produto( String nome, int unidade, Float avista, Float aprazo, Fornecedor fornecedor) {
        Nome = nome;
        Unidade = unidade;
        Avista = avista;
        Aprazo = aprazo;
        Fornecedor = fornecedor;
    }        

    public Produto(int codigo, String nome, int unidade, Float avista, Float aprazo,Fornecedor fornecedor) {
        Codigo = codigo;
        Nome = nome;
        Unidade = unidade;
        Avista = avista;
        Aprazo = aprazo;
        Fornecedor = fornecedor;
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
    public int getUnidade() {
        return Unidade;
    }
    public void setUnidade(int unidade) {
        Unidade = unidade;
    }
    public Float getAvista() {
        return Avista;
    }
    public void setAvista(Float avista) {
        Avista = avista;
    }
    public Float getAprazo() {
        return Aprazo;
    }
    public void setAprazo(Float aprazo) {
        Aprazo = aprazo;
    }

    public Fornecedor getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.Fornecedor = fornecedor;
    }
    
    
}
