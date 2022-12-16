package com.estagio.back.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.estagio.back.Conexao;
import com.estagio.back.Model.Usuario;

public class UsuariosDAO {
    public boolean gravar (Conexao con, Usuario usu){
        String sql = "insert into usuario values (default, #1, #2, #3);";
        sql = sql.replace("#1", "'"+usu.getNome()+"'");
        sql = sql.replace("#2", ""+usu.isAdmin());
        sql = sql.replace("#3", "'"+usu.getSenha()+"'");
        return con.manipular(sql);
    }
    
    public boolean alterar (Conexao con, Usuario usu){
        String sql = "update usuario set usu_nome='#1', usu_admin='#2', usu_senha='#3' where usu_codigo="+usu.getCodigo();
        sql = sql.replace("#1", "'"+usu.getNome()+"'");
        sql = sql.replace("#2", ""+usu.isAdmin());
        sql = sql.replace("#3", "'"+usu.getSenha()+"'");
        return con.manipular(sql);
    }
    
    public boolean excluir (Conexao con, Usuario usu){
        String sql = "delete from usuario where usu_codigo="+usu.getCodigo();
        return con.manipular(sql);
    }
    
    public Usuario  get (Conexao con, int id){
        Usuario usu=null;
        String sql = "select * from usuario where usu_codigo="+id;
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                usu = new Usuario(rs.getInt("usu_codigo"),rs.getString("usu_nome"),rs.getBoolean("usu_admin"),rs.getString("usu_senha"));
        }
        catch(Exception e){
            
        }
        return usu;
    }
    
    public Usuario  get_nome (Conexao con, String nome){
        Usuario usu=null;
        String sql = "select * from usuario where usu_nome='"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                usu = new Usuario(rs.getInt("usu_codigo"),rs.getString("usu_nome"),rs.getBoolean("usu_admin"),rs.getString("usu_senha"));
        }
        catch(Exception e){
            
        }
        return usu;
    }
    
    public List<Usuario> getAll(Conexao con)
   {
        List<Usuario> usu = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {
            ResultSet rs = con.consultar(sql);
            while(rs.next())
            {
                Usuario c = new Usuario();
                c.setCodigo(rs.getInt("usu_codigo"));
                c.setNome(rs.getString("usu_nome"));
                c.setAdmin(rs.getBoolean("usu_admin"));
                c.setSenha(rs.getString("usu_senha"));
                usu.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista!");
            return null;
        }
        return usu;
    }
}
