package com.estagio.back;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Banco
{
    private static Conexao con=null;
    
    public static boolean conectarBanco(){
        if(con == null){
            con = new Conexao();
            return con.conectar("jdbc:postgresql:localhost:5432/", "ProjetoEstagio", "postgres", "postgres123");
        }
        return false;
    }
    
    public static Conexao getCon(){
        return con;
    }   
    
    public static boolean criarBD(String BD){ 
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url,"postgres","postgres123");

            Statement statement = con.createStatement();
            statement.execute("CREATE DATABASE "+BD+" WITH OWNER = postgres ENCODING = 'UTF8'  "
                                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252'  "
                                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
        }
        catch(Exception e){  
            System.out.println(e.getMessage()); 
            return false;
        }
        return true;               
    }
    public static boolean backup(String arquivo) throws Exception{     
        final ArrayList<String> comandos = new ArrayList();     
        comandos.add("dbutil/pg_dump.exe"); 
        comandos.add("--host");    comandos.add("localhost"); //ou  comandos.add("192.168.0.1");
        comandos.add("--port");    comandos.add("5432"); 
        comandos.add("--username");comandos.add("postgres");
        comandos.add("--format");comandos.add("custom");     
        comandos.add("--blobs");
        comandos.add("--verbose");
        comandos.add("--file");comandos.add(arquivo);  
        comandos.add("animelist");     
        ProcessBuilder pb = new ProcessBuilder(comandos);     
        pb.environment().put("PGPASSWORD", "postgres123");
        try {     
            final Process process = pb.start();     
            final BufferedReader r = new BufferedReader( new InputStreamReader(process.getErrorStream()));     
            String line = r.readLine();     
            while (line != null) {     
                System.err.println(line); line = r.readLine();     
            }     
            r.close();     
            process.waitFor();   
            process.destroy();            
            JOptionPane.showMessageDialog(null,"Backup realizado com sucesso!"); 
            return true;
       } 
       catch (Exception e) {     
            JOptionPane.showMessageDialog(null,"Erro na realiza????o do backup.");   
            return false;
       }
    }

    public static boolean restaurar(String arquivo) throws Exception{     
        final ArrayList<String> comandos = new ArrayList();     
        comandos.add("dbutil/pg_restore.exe"); comandos.add("-c");     
        comandos.add("--host"); comandos.add("localhost");
        comandos.add("--port"); comandos.add("5432");     
        comandos.add("--username"); comandos.add("postgres");     
        comandos.add("--dbname"); comandos.add("animelist");    
        comandos.add("--verbose");  
        comandos.add(arquivo);  
        ProcessBuilder pb = new ProcessBuilder(comandos);     
        pb.environment().put("PGPASSWORD", "postgres123");           
        try {     
            final Process process = pb.start();     
            final BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));     
            String line = r.readLine();     
            while (line != null) {     
                System.err.println(line);     
                line = r.readLine();     
            }     
            r.close();     
            process.waitFor();   
            process.destroy();
            JOptionPane.showMessageDialog(null,"Restaura????o com sucesso!"); 
            return true;
        } 
        catch (Exception e) {     
            JOptionPane.showMessageDialog(null,"Erro na restaura????o.");   
            return false;
        }   
    }
}
