package tsi.daw.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tsi.daw.dao.DAO;
import tsi.daw.modelo.Cliente;

@SessionScoped
@ManagedBean
public class ClienteMB {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;
	public Cliente getCliente() {
		return cliente;
	}
	
	public String grava(){
		DAO<Cliente> dao =  new DAO<>(Cliente.class);
		
		if(cliente.getId() == null)
			dao.adiciona(cliente);
		else
			dao.altera(cliente);
		
		cliente =  new Cliente();
		return "listar-clientes?faces-redirect=true";
	}
	
	public void remove(Cliente cliente){
		DAO<Cliente> dao = new DAO<>(Cliente.class);
		dao.remove(cliente);
	}
	
	public String alterar(Cliente cliente){
		this.cliente = cliente;
		return "novo-cliente?faces?redirect=true";
	}
	
	public List<Cliente> getClientes(){
		DAO<Cliente> dao = new DAO<>(Cliente.class);
		clientes = dao.listaTodos();
		return clientes;	
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
	
}
