package com.estagio.back.Model;

public class Mesa {
    private int Codigo;
    private String Numero;
    
    public Mesa() {
    }

    public Mesa(String numero) {
        Numero = numero;
    }

    public Mesa(int codigo, String numero) {
        Codigo = codigo;
        Numero = numero;
    }

    public int getCodigo() {
        return Codigo;
    }
    public void setCodigo(int codigo) {
        Codigo = codigo;
    }
    public String getNumero() {
        return Numero;
    }
    public void setNumero(String numero) {
        Numero = numero;
    }
}
