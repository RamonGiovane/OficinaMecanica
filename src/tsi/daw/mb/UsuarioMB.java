package tsi.daw.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tsi.daw.dao.UsuarioDAO;
import tsi.daw.modelo.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB {
	private Usuario usuario = new Usuario();
	private boolean mecanico, gerente, atendente;
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
		case "atendente":
			mecanico = false;
			gerente = false;
			atendente = true;
			break;
		
		case "gerente":
			mecanico = false;
			gerente = true;
			atendente = false;
			break;
		
		case "mecanico":
			mecanico = true;
			gerente = false;
			atendente = false;
			break;
			
	}
	}
	public String logOut(){
		usuario = new Usuario();
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

	public boolean isAtendente() {
		return atendente;
	}

	public void setAtendente(boolean atendente) {
		this.atendente = atendente;
	}
	
}
