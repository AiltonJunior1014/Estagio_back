package com.estagio.back.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.estagio.back.Conexao;
import com.estagio.back.Model.Fornecedor;

public class FornecedorDAO {
    
    public boolean gravar (Conexao con, Fornecedor forne){
        String sql = "insert into fornecedores values (default, #1, #2, #3, #4, #5, #6, #7);";
        sql = sql.replace("#1", "'"+forne.getNome()+"'");
        sql = sql.replace("#2", "'"+forne.getCPFCNPJ()+"'");
        sql = sql.replace("#3", "'"+forne.getTelefone()+"'");
        sql = sql.replace("#4", "'"+forne.getEndereco()+"'");
        sql = sql.replace("#5", "'"+forne.getNumero()+"'");
        sql = sql.replace("#6", "'"+forne.getBairro()+"'");
        sql = sql.replace("#7", "'"+forne.getMunicipio()+"'");
        return con.manipular(sql);
    }
    
    public boolean alterar (Conexao con, Fornecedor forne){
        String sql = "update fornecedores forne_nome='#1', forne_cpfcnpj='#2', forne_telefone='#3', forne_endereco='#4', forne_numero='#5', forne_bairro='#6', forne_municipio='#7' where forne_codigo="+forne.getCodigo();
        sql = sql.replace("#1", "'"+forne.getNome()+"'");
        sql = sql.replace("#2", "'"+forne.getCPFCNPJ()+"'");
        sql = sql.replace("#3", "'"+forne.getTelefone()+"'");
        sql = sql.replace("#4", "'"+forne.getEndereco()+"'");
        sql = sql.replace("#5", "'"+forne.getNumero()+"'");
        sql = sql.replace("#6", "'"+forne.getBairro()+"'");
        sql = sql.replace("#7", "'"+forne.getMunicipio()+"'");
        return con.manipular(sql);
    }
    
    
    public boolean excluir (Conexao con, Fornecedor forne){
        String sql = "delete from Fornecedores where forne_codigo="+forne.getCodigo();
        return con.manipular(sql);
    }
    
    public Fornecedor  get (Conexao con, int id){
        Fornecedor forne=null;
        String sql = "select * from Fornecedores where forne_codigo="+id;
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                forne = new Fornecedor(rs.getInt("forne_codigo"),rs.getString("forne_nome"),rs.getString("forne_cpfcnpj"),rs.getString("forne_telefone"),
                rs.getString("forne_logradouro"),rs.getString("forne_numero"),rs.getString("forne_bairro"),rs.getString("forne_municipio"));
        }
        catch(Exception e){
            
        }
        return forne;
    }
    
    public Fornecedor  get_nome (Conexao con, String nome){
        Fornecedor forne=null;
        String sql = "select * from Fornecedores where forne_nome='"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                forne = new Fornecedor(rs.getInt("forne_codigo"),rs.getString("forne_nome"),rs.getString("forne_cpfcnpj"),rs.getString("forne_telefone"),
                rs.getString("forne_logradouro"),rs.getString("forne_numero"),rs.getString("forne_bairro"),rs.getString("forne_municipio"));
        }
        catch(Exception e){
            
        }
        return forne;
    }
    
    public List<Fornecedor> getAll(Conexao con)
   {
        List<Fornecedor> forne = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedores";
        try {
            ResultSet rs = con.consultar(sql);
            while(rs.next())
            {
                Fornecedor c = new Fornecedor();
                c.setCodigo(rs.getInt("forne_codigo"));
                c.setNome(rs.getString("forne_nome"));
                c.setTelefone(rs.getString("forne_telefone"));
                c.setCPFCNPJ(rs.getString("forne_CPFCNPJ"));
                c.setEndereco(rs.getString("forne_logradouro"));
                c.setNumero(rs.getString("forne_numero"));
                c.setBairro(rs.getString("forne_bairro"));
                c.setMunicipio(rs.getString("forne_municipio"));
                forne.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista!");
            return null;
        }
        return forne;
    }
}
