package com.estagio.back.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.estagio.back.Conexao;
import com.estagio.back.Model.Mesa;

public class MesasDAO {
    public boolean gravar (Conexao con, Mesa mesa){
        String sql = "insert into mesas values (default, #1);";
        sql = sql.replace("#1", "'"+mesa.getNumero()+"'");
        return con.manipular(sql);
    }
    
    public boolean alterar (Conexao con, Mesa mesa){
        String sql = "update Mesas set mes_numero='#1' where mes_codigo="+mesa.getCodigo();
        sql = sql.replace("#1", "'"+mesa.getNumero()+"'");
        return con.manipular(sql);
    }
    
    public boolean excluir (Conexao con, Mesa mesa){
        String sql = "delete from Mesas where mes_codigo="+mesa.getCodigo();
        return con.manipular(sql);
    }
    
    public Mesa  get (Conexao con, int id){
        Mesa mesa=null;
        String sql = "select * from Mesas where mes_codigo="+id;
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                mesa = new Mesa(rs.getInt("mes_codigo"),rs.getString("mesmes_numero"));
        }
        catch(Exception e){
            
        }
        return mesa;
    }
    
    public Mesa  get_nome (Conexao con, String codigo){
        Mesa mesa=null;
        String sql = "select * from Mesas where mes_codigo='"+codigo+"'";
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
            mesa = new Mesa(rs.getInt("mes_codigo"),rs.getString("mes_numero"));
        }
        catch(Exception e){
            
        }
        return mesa;
    }
    
    public List<Mesa> getAll(Conexao con)
   {
        List<Mesa> mesa = new ArrayList<>();
        String sql = "SELECT * FROM Mesas";
        try {
            ResultSet rs = con.consultar(sql);
            while(rs.next())
            {
                Mesa c = new Mesa();
                c.setCodigo(rs.getInt("mes_codigo"));
                c.setNumero(rs.getString("mes_numero"));
                mesa.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista!");
            return null;
        }
        return mesa;
    }
}
