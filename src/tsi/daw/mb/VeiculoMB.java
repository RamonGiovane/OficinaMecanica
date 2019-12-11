package tsi.daw.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tsi.daw.dao.DAO;
import tsi.daw.modelo.Cliente;
import tsi.daw.modelo.Veiculo;

@SessionScoped
@ManagedBean
public class VeiculoMB {

	private Veiculo veiculo = new Veiculo();
	private List<Veiculo> veiculos;
	private Long idCliente;
	public String grava(){
		
		DAO<Veiculo> dao = new DAO<>(Veiculo.class);
		Cliente cliente = new Cliente();
		cliente = new DAO<>(Cliente.class).buscaPorId(idCliente);
		veiculo.setCliente(cliente);
		
		if(veiculo.getId() == null)
			dao.adiciona(veiculo);
		else
			dao.altera(veiculo);
			
		
		this.veiculo = new Veiculo();
		return "listar-veiculos?faces-redirect=true";
		
	}
	
	public void remove(Veiculo veiculo){
		DAO<Veiculo> dao = new DAO<>(Veiculo.class);
		System.out.println(veiculo.getModelo());
		dao.remove(veiculo);
	}
	
	public String altera(Veiculo veiculo){
		this.veiculo = veiculo;
		return "novo-veiculo?faces-redirect=true";
	}
	
	
	public List<Veiculo> getVeiculos(){
		DAO<Veiculo> dao = new DAO<>(Veiculo.class);
		veiculos = dao.listaTodos();
		return veiculos;
	}

	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	
	
	
	
}
