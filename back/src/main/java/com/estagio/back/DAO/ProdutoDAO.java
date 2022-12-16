package com.estagio.back.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.estagio.back.Conexao;
import com.estagio.back.Model.Produto;

public class ProdutoDAO {

    public boolean gravar (Conexao con, Produto prod){
        String sql = "insert into produtos values (default, #1, #2, #3, #4, #5);";
        sql = sql.replace("#1", "'"+prod.getNome()+"'");
        sql = sql.replace("#2", ""+prod.getUnidade());
        sql = sql.replace("#3", ""+prod.getAvista());
        sql = sql.replace("#4", ""+prod.getAvista());
        sql = sql.replace("#5", ""+prod.getFornecedor().getCodigo());
        return con.manipular(sql);
    }
    
    public boolean alterar (Conexao con, Produto prod){
        String sql = "update Produtos set prod_nome='#1', prod_unidade='#2', prod_avista='#3', aprazo='#4', fornecedores_forne_codigo='#4' where prod_codigo="+prod.getCodigo();
        sql = sql.replace("#1", "'"+prod.getNome()+"'");
        sql = sql.replace("#2", ""+prod.getUnidade());
        sql = sql.replace("#3", ""+prod.getAvista());
        sql = sql.replace("#4", ""+prod.getAvista());
        sql = sql.replace("#5", ""+prod.getFornecedor().getCodigo());
        return con.manipular(sql);
    }
    
    public boolean excluir (Conexao con, Produto prod){
        String sql = "delete from Produtos where prod_codigo="+prod.getCodigo();
        return con.manipular(sql);
    }
    
    public Produto  get (Conexao con, int id){
        Produto prod=null;
        String sql = "select * from Produtos where prod_codigo="+id;
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                prod = new Produto(rs.getInt("prod_codigo"),rs.getString("prod_nome"),rs.getInt("prod_unidade"),rs.getFloat("prod_avista"),
                    rs.getFloat("prod_aprazo"), new FornecedorDAO().get(con, rs.getInt("fornecedores_forne_codigo")));
        }
        catch(Exception e){
            
        }
        return prod;
    }
    
    public Produto  get_nome (Conexao con, String nome){
        Produto prod=null;
        String sql = "select * from Produtos where nome='"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                prod = new Produto(rs.getInt("prod_codigo"),rs.getString("prod_nome"),rs.getInt("prod_unidade"),rs.getFloat("prod_avista"),
                    rs.getFloat("prod_aprazo"), new FornecedorDAO().get(con, rs.getInt("fornecedores_forne_codigo")));
        }
        catch(Exception e){
            
        }
        return prod;
    }
    
    public List<Produto> getAll(Conexao con)
   {
        List<Produto> prod = new ArrayList<>();
        String sql = "SELECT * FROM Produtos";
        try {
            ResultSet rs = con.consultar(sql);
            while(rs.next())
            {
                Produto p = new Produto();
                p.setCodigo(rs.getInt("prod_codigo"));
                p.setNome(rs.getString("prod_nome"));
                p.setUnidade(rs.getInt("prod_unidade"));
                p.setAvista(rs.getFloat("prod_avista"));
                p.setAprazo(rs.getFloat("prod_aprazo"));
                p.setFornecedor(new FornecedorDAO().get(con, rs.getInt("fornecedores_forne_codigo")));    
                prod.add(p);
                
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista!");
            return null;
        }
        return prod;
    }


    public List<Produto> getByFornecedor(Conexao con, int id)
   {
        List<Produto> prod = new ArrayList<>();
        String sql = "SELECT * FROM Produtos where fornecedores_forne_codigo="+id;
        try {
            ResultSet rs = con.consultar(sql);
            while(rs.next())
            {
                Produto p = new Produto();
                p.setCodigo(rs.getInt("prod_codigo"));
                p.setNome(rs.getString("prod_nome"));
                p.setUnidade(rs.getInt("prod_unidade"));
                p.setAvista(rs.getFloat("prod_avista"));
                p.setAprazo(rs.getFloat("prod_aprazo"));
                p.setFornecedor(new FornecedorDAO().get(con, rs.getInt("fornecedores_forne_codigo")));    
                prod.add(p);
                
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar lista!");
            return null;
        }
        return prod;
    }
    
}
