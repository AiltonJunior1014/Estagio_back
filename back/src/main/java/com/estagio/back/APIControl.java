package com.estagio.back;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estagio.back.DAO.ClienteDAO;
import com.estagio.back.DAO.FornecedorDAO;
import com.estagio.back.DAO.MesasDAO;
import com.estagio.back.DAO.ProdutoDAO;
import com.estagio.back.DAO.UsuariosDAO;
import com.estagio.back.Model.Cliente;
import com.estagio.back.Model.Fornecedor;
import com.estagio.back.Model.Mesa;
import com.estagio.back.Model.Produto;
import com.estagio.back.Model.Usuario;
import com.google.gson.Gson;

import net.minidev.json.JSONObject;

@SpringBootApplication
@RestController
public class APIControl {
	private Conexao con = new Conexao();

	private void conectar() {
		this.con.conectar("jdbc:postgresql://localhost:5432/", "Estagio", "postgres", "postgres123");
	}

	private void desconectar(){
		this.con.desconectar();
	} 


	/*CRUD Produto */
	@RequestMapping(value = "/cadastroproduto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postCadastroProduto(@RequestBody Map<String, ?> prod){ 
		this.conectar();
		Produto produto ;
		Gson gson = new Gson();
		JSONObject json = new JSONObject(prod);
		FornecedorDAO forneDAO = new FornecedorDAO();
		if(forneDAO.get(con, Integer.parseInt(json.getAsString("fornecedor"))) != null) {
			produto = new Produto(Integer.parseInt(json.getAsString("codigo")),json.getAsString("nome"),Integer.parseInt(json.getAsString("unidade")),Float.parseFloat(json.getAsString("avista")),Float.parseFloat(json.getAsString("aprazo")), forneDAO.get(con, Integer.parseInt(json.getAsString("fornecedor"))));
			ProdutoDAO prodDAO = new ProdutoDAO();
			if(prodDAO.gravar(con, produto)){
				this.desconectar();
				return true;				
			}
		}
		
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/getproduto", method = RequestMethod.GET)
	public String postgetProduto(@RequestBody int produto){ 
		this.conectar();
		Gson gson = new Gson();
		ProdutoDAO prodDAO = new ProdutoDAO();
		if(produto > 0){
			Produto prod = prodDAO.get(con, produto);
			return gson.toJson(prod);
		}
		return "-1";
	}

	@RequestMapping(value = "/gettodosprodutos", method = RequestMethod.GET)
	public String postgetProdutos(){ 
		this.conectar();
		Gson gson = new Gson();
		ProdutoDAO prodDAO = new ProdutoDAO();
		List<Produto> prod = prodDAO.getAll(con);
		if (prod.size()>0)
			return gson.toJson(prod);
		return "-1";
	}

	@RequestMapping(value = "/updateproduto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postUpdateProduto(@RequestBody Map<String, ?> prod){ 
		this.conectar();
		Produto produto ;
		Gson gson = new Gson();
		JSONObject json = new JSONObject(prod);
		FornecedorDAO forneDAO = new FornecedorDAO();
		if(forneDAO.get(con, Integer.parseInt(json.getAsString("fornecedor"))) != null) {
			produto = new Produto(Integer.parseInt(json.getAsString("codigo")),json.getAsString("nome"),Integer.parseInt(json.getAsString("unidade")),Float.parseFloat(json.getAsString("avista")),Float.parseFloat(json.getAsString("aprazo")), forneDAO.get(con, Integer.parseInt(json.getAsString("fornecedor"))));
			ProdutoDAO prodDAO = new ProdutoDAO();
			if(prodDAO.alterar(con, produto)){
				this.desconectar();
				return true;				
			}
		}
		return false;
	}
	
	@RequestMapping(value = "/deleteproduto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postDeleteProduto(@RequestBody Map<String, ?> prod){ 
		this.conectar();
		Produto produto ;
		Gson gson = new Gson();
		JSONObject json = new JSONObject(prod);
		FornecedorDAO forneDAO = new FornecedorDAO();
		ProdutoDAO prodDAO = new ProdutoDAO();
		produto = new Produto(Integer.parseInt(json.getAsString("codigo")),json.getAsString("nome"),Integer.parseInt(json.getAsString("unidade")),Float.parseFloat(json.getAsString("avista")),Float.parseFloat(json.getAsString("aprazo")), forneDAO.get(con, Integer.parseInt(json.getAsString("fornecedor"))));		
		if(prodDAO.get(con, produto.getCodigo())!= null){
			prodDAO.excluir(con, produto);				
			this.desconectar();
			return true; 
		}
		this.desconectar();
		return false;
	}

	/*CRUD Cliente */
	@RequestMapping(value = "/cadastrocliente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postCadastroCliente(@RequestBody Cliente cliente){ 
		this.conectar();
		ClienteDAO cliDAO = new ClienteDAO();
		if(cliDAO.gravar(con, cliente)){
			this.desconectar();
			return true;				
		}
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/getcliente", method = RequestMethod.POST)
	public String postgetCliente(@RequestBody int cliente){ 
		this.conectar();
		Gson gson = new Gson();
		ClienteDAO cliDAO = new ClienteDAO();
		if(cliente > 0){
			Cliente cli = cliDAO.get(con, cliente);
			this.desconectar();
			return gson.toJson(cli);
		}
		this.desconectar();
		return "-1";
	}

	@RequestMapping(value = "/gettodosclientes", method = RequestMethod.GET)
	public String postgetClientes(){ 
		this.conectar();
		Gson gson = new Gson();
		ClienteDAO cliDAO = new ClienteDAO();
		List<Cliente> cli = cliDAO.getAll(con);
		if (cli.size()>0)
			return gson.toJson(cli);
		return "-1";
	}

	
	@RequestMapping(value = "/updatecliente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postUpdateCliente(@RequestBody Cliente cliente){ 
		this.conectar();
		ClienteDAO cliDAO = new ClienteDAO();
		if(cliDAO.get(con, cliente.getCodigo())!= null){
			cliDAO.alterar(con,  cliente);				
			this.desconectar();
			return true;
		}
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/deletecliente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postDeleteCliente(@RequestBody Cliente cliente){ 
		this.conectar();
		ClienteDAO cliDAO = new ClienteDAO();
		if(cliDAO.get(con, cliente.getCodigo())!= null){
			cliDAO.excluir(con, cliente);				
			this.desconectar();
			return true;
		}
		this.desconectar();
		return false;
	}

	/*CRUD Usuarios*/
	@RequestMapping(value = "/cadastrousuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postCadastroUsuario(@RequestBody String usu){ 
		Usuario usuario;
		this.conectar();
		Gson gson = new Gson();
		usuario = gson.fromJson(usu,Usuario.class);
		UsuariosDAO usuDAO = new UsuariosDAO();
		if(usuDAO.get_nome(con, usuario.getNome())==null)
			if(usuDAO.gravar(con, usuario)){
				this.desconectar();
				return true;				
			}
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/getusuario", method = RequestMethod.POST)
	public String postgetUsuario(@RequestBody int usuario){ 
		this.conectar();
		Gson gson = new Gson();
		UsuariosDAO usuDAO = new UsuariosDAO();
		if(usuario > 0){
			Usuario usu = usuDAO.get(con, usuario);
			this.desconectar();
			return gson.toJson(usu);
		}
		this.desconectar();
		return "-1";
	}

	@RequestMapping(value = "/gettodosusuario", method = RequestMethod.GET)
	public String postgetUsuario(){ 
		this.conectar();
		Gson gson = new Gson();
		UsuariosDAO usuDAO = new UsuariosDAO();
		List<Usuario> usu = usuDAO.getAll(con);
		if (usu.size()>0)
			return gson.toJson(usu);
		return "-1";
	}
	
	@RequestMapping(value = "/updateusuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postUpdateUsuario(@RequestBody Usuario usuario){ 
		this.conectar();
		UsuariosDAO usuDAO = new UsuariosDAO();
		if(usuDAO.get(con, usuario.getCodigo())!= null){
			usuDAO.alterar(con,  usuario);				
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/deleteusuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postDeleteUsuario(@RequestBody Usuario usuario){ 
		this.conectar();
		UsuariosDAO usuDAO = new UsuariosDAO();
		if(usuDAO.get(con, usuario.getCodigo())!= null){
			usuDAO.excluir(con, usuario);				
			this.desconectar();
			return true;
		}
		this.desconectar();
		return false;
	}
    

	/*CRUD Mesas*/
	@RequestMapping(value = "/cadastromesa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postCadastroMesa(@RequestBody Mesa mesa){ 
		this.conectar();
		MesasDAO mesDAO = new MesasDAO();
		if(mesDAO.gravar(con, mesa)){
			this.desconectar();
			return true;				
		}
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/getmesa", method = RequestMethod.POST)
	public String postgetMesa(@RequestBody int mesa){ 
		this.conectar();
		Gson gson = new Gson();
		MesasDAO mesDAO = new MesasDAO();
		if(mesa > 0){
			Mesa mes = mesDAO.get(con, mesa);
			this.desconectar();
			return gson.toJson(mes);
		}
		this.desconectar();
		return "-1";
	}

	@RequestMapping(value = "/gettodosmesa", method = RequestMethod.GET)
	public String postgetMesa(){ 
		this.conectar();
		Gson gson = new Gson();
		MesasDAO mesDAO = new MesasDAO();
		List<Mesa> mes = mesDAO.getAll(con);
		if (mes.size()>0)
			return gson.toJson(mes);
		return "-1";
	}

	
	@RequestMapping(value = "/updatemesa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postUpdateMesa(@RequestBody Mesa mesa){ 
		this.conectar();
		MesasDAO mesDAO = new MesasDAO();
		if(mesDAO.get(con, mesa.getCodigo())!= null){
			mesDAO.alterar(con,  mesa);				
			this.desconectar();
			return true;
		}
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/deletemesa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postDeleteMesa(@RequestBody Mesa mesa){ 
		this.conectar();
		MesasDAO mesDAO = new MesasDAO();
		if(mesDAO.get(con, mesa.getCodigo())!= null){
			mesDAO.excluir(con, mesa);	
			this.desconectar();			
			return true;
		}
		this.desconectar();
		return false;
	}


	@RequestMapping(value = "/cadastrofornecedor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postCadastroFornecedor(@RequestBody String forne) { 
		Fornecedor fornecedor;
		this.conectar();
		Gson gson = new Gson();
		fornecedor = gson.fromJson(forne,Fornecedor.class);
		this.conectar();
		FornecedorDAO forneDAO = new FornecedorDAO();
		if(forneDAO.gravar(con, fornecedor)){
			this.desconectar();
			return true;				
		}
		this.desconectar();
		return false;
	}
	
	@RequestMapping(value = "/getfornecedor", method = RequestMethod.GET)
	public String postgetFornecedor(@RequestBody int fornecedor){ 
		this.conectar();
		Gson gson = new Gson();
		FornecedorDAO forneDAO = new FornecedorDAO();
		if(fornecedor > 0){
			Fornecedor forne = forneDAO.get(con, fornecedor);
			return gson.toJson(forne);
		}
		return "-1";
		
	}

	@RequestMapping(value = "/gettodosfornecedores", method = RequestMethod.GET)
	public String postgetFornecedores(){ 
		this.conectar();
		Gson gson = new Gson();
		FornecedorDAO forneDAO = new FornecedorDAO();
		List<Fornecedor> forne = forneDAO.getAll(con);
		if (forne.size()>0)
			return gson.toJson(forne);
		return "-1";
	}

	@RequestMapping(value = "/updatefornecedor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postUpdateFornecedor(@RequestBody Fornecedor fornecedor){ 
		this.conectar();
		FornecedorDAO forneDAO = new FornecedorDAO();
		if(forneDAO.get(con, fornecedor.getCodigo())!= null){
			forneDAO.alterar(con, fornecedor);				
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/deletefornecedor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean postDeleteFornecedor(@RequestBody Fornecedor fornecedor){ 
		this.conectar();
		FornecedorDAO forneDAO = new FornecedorDAO();
		ProdutoDAO prodDAO = new ProdutoDAO();
		if(prodDAO.getByFornecedor(con, fornecedor.getCodigo())==null){
			if(forneDAO.get(con, fornecedor.getCodigo())!= null){
				forneDAO.excluir(con, fornecedor);				
				this.desconectar();
				return true; 
			}
		}
		this.desconectar();
		return false;
	}
}
