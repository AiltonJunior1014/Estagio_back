package com.estagio.back.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.estagio.back.Conexao;
import com.estagio.back.Model.Cliente;

public class ClienteDAO {
    public boolean gravar (Conexao con, Cliente cli){
        String sql = "insert into clientes values (default, #1, #2, #3, #4, #5, #6, #7);";
        sql = sql.replace("#1", "'"+cli.getNome()+"'");
        sql = sql.replace("#2", "'"+cli.getCPFCNPJ()+"'");
        sql = sql.replace("#3", "'"+cli.getTelefone()+"'");
        sql = sql.replace("#4", "'"+cli.getEndereco()+"'");
        sql = sql.replace("#5", "'"+cli.getNumero()+"'");
        sql = sql.replace("#6", "'"+cli.getBairro()+"'");
        sql = sql.replace("#7", "'"+cli.getMunicipio()+"'");
        return con.manipular(sql);
    }
    
    public boolean alterar (Conexao con, Cliente cli){
        String sql = "update Clientes set cli_nome='#1', cli_cpfcnpj='#2', cli_telefone='#3', cli_endereco='#4', cli_numero='#5', cli_bairro='#6', cli_municipio='#7' where cli_codigo="+cli.getCodigo();
        sql = sql.replace("#1", "'"+cli.getNome()+"'");
        sql = sql.replace("#2", "'"+cli.getCPFCNPJ()+"'");
        sql = sql.replace("#3", "'"+cli.getTelefone()+"'");
        sql = sql.replace("#4", "'"+cli.getEndereco()+"'");
        sql = sql.replace("#5", "'"+cli.getNumero()+"'");
        sql = sql.replace("#6", "'"+cli.getBairro()+"'");
        sql = sql.replace("#7", "'"+cli.getMunicipio()+"'");
        return con.manipular(sql);
    }
    
    public boolean excluir (Conexao con, Cliente cli){
        String sql = "delete from Clientes where cli_codigo="+cli.getCodigo();
        return con.manipular(sql);
    }
    
    public Cliente  get (Conexao con, int id){
        Cliente cli=null;
        String sql = "select * from Clientes where cli_codigo="+id;
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                cli = new Cliente(rs.getInt("cli_codigo"),rs.getString("cli_nome"),rs.getString("cli_cpfcnpj"),rs.getString("cli_telefone"),
                rs.getString("cli_endereco"),rs.getString("cli_numero"),rs.getString("cli_bairro"),rs.getString("cli_municipio"));
        }
        catch(Exception e){
            
        }
        return cli;
    }
    
    public Cliente  get_nome (Conexao con, String nome){
        Cliente cli=null;
        String sql = "select * from Clientes where cli_nome='"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                cli = new Cliente(rs.getInt("cli_codigo"),rs.getString("cli_nome"),rs.getString("cli_cpfcnpj"),rs.getString("cli_telefone"),
                rs.getString("cli_endereco"),rs.getString("cli_numero"),rs.getString("cli_bairro"),rs.getString("cli_municipio"));
        }
        catch(Exception e){
            
        }
        return cli;
    }
    
    public List<Cliente> getAll(Conexao con)
   {
        List<Cliente> cli = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            ResultSet rs = con.consultar(sql);
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setCodigo(rs.getInt("cli_codigo"));
                c.setNome(rs.getString("cli_nome"));
                c.setTelefone(rs.getString("cli_telefone"));
                c.setCPFCNPJ(rs.getString("cli_CPFCNPJ"));
                c.setEndereco(rs.getString("cli_endereco"));
                c.setNumero(rs.getString("cli_numero"));
                c.setBairro(rs.getString("cli_bairro"));
                c.setMunicipio(rs.getString("cli_municipio"));
                cli.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista!");
            return null;
        }
        return cli;
    }
}
