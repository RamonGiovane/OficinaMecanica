package tsi.daw.autorizador;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import tsi.daw.mb.UsuarioMB;

@SuppressWarnings("serial")
public class Autorizador implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent evento) {
		FacesContext contexto = evento.getFacesContext();
		if("/login.xhtml".equals(contexto.getViewRoot().getViewId()))
			return;
		
		UsuarioMB usuarioMB = 
				contexto.getApplication().evaluateExpressionGet(contexto, "#{usuarioMB}", UsuarioMB.class);
		
		if(!usuarioMB.isUsuarioLogado()) {
			NavigationHandler handler = contexto.getApplication().getNavigationHandler();
			handler.handleNavigation(contexto, null, "login?faces-redirect=true");
			contexto.renderResponse();
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
