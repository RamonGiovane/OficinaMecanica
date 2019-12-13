package tsi.daw.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tsi.daw.dao.UsuarioDAO;
import tsi.daw.modelo.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB {
	private Usuario usuario = new Usuario();
	private boolean mecanico, gerente, recepcionista;
	public String logIn() {
		UsuarioDAO dao = new UsuarioDAO();
		
		boolean usuarioEncontrado = dao.existe(usuario);
		
		if(usuarioEncontrado){
			
			definirPapel(dao);
			
			definirPermissao();
			
			return "menu?faces-redirect=true";
		}
		else {
			usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
	
	private void definirPapel(UsuarioDAO dao){
		usuario.setPapel(dao.obterPapel(usuario));
	}
	
	private void definirPermissao(){
		switch(usuario.getPapel()){
		case "Recepcionista":
			mecanico = false;
			gerente = false;
			recepcionista = true;
			break;
		
		case "Gerente":
			mecanico = false;
			gerente = true;
			recepcionista = false;
			break;
		
		case "Mecânico":
			mecanico = true;
			gerente = false;
			recepcionista = false;
			break;
			
	}
	}
	public String logOut(){
		usuario = new Usuario();
		mecanico = gerente = recepcionista = false;
		return "login?faces-redirect=true";
	}
	
	public boolean isUsuarioLogado() {
		return usuario.getUsuario() != null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public boolean isMecanico() {
		return mecanico;
	}

	public void setMecanico(boolean mecanico) {
		this.mecanico = mecanico;
	}

	public boolean isGerente() {
		return gerente;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}

	public boolean isRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(boolean atendente) {
		this.recepcionista = atendente;
	}
	
}
