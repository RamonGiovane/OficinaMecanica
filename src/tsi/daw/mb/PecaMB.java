package tsi.daw.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tsi.daw.dao.DAO;
import tsi.daw.modelo.Peca;

@SessionScoped
@ManagedBean
public class PecaMB {
	private Peca peca = new Peca();
	private List<Peca> pecas;
	public Peca getPeca() {
		return peca;
	}
	
	public String grava(){
		DAO<Peca> dao =  new DAO<>(Peca.class);
		
		if(peca.getId() == null)
			dao.adiciona(peca);
		
		else
			dao.altera(peca);
			
		this.peca =  new Peca();
		return "listar-pecas?faces-redirect=true";
	}
	
	public void remove(Peca peca){
		DAO<Peca> dao = new DAO<>(Peca.class);
		dao.remove(peca);
	}
	
	public String alterar(Peca peca){
		this.peca = peca;
		return "nova-peca?faces-redirect=true";
	}
	
	public String retirar(Peca peca){
		this.peca = peca;
		return "retirar-peca?faces-redirect=true";
	}
	
	public String retirarPecas() {
		if(peca != null) {
			peca.retirarPecas();
			return grava();
		}
		return "listar-pecas?faces-redirect=true";
		
	}
	
	public List<Peca> getPecas(){
		DAO<Peca> dao = new DAO<>(Peca.class);
		pecas = dao.listaTodos();
		return pecas;	
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	

	
	
	
}
