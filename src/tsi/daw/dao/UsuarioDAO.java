package tsi.daw.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import tsi.daw.modelo.Usuario;

public class UsuarioDAO {
	public boolean existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		Query query = em.createQuery("from Usuario u where u.usuario = :usuario and u.senha = :senha");
		query.setParameter("usuario", usuario.getUsuario());
		query.setParameter("senha", usuario.getSenha());
		
		boolean encontrado = !query.getResultList().isEmpty();
		
		em.getTransaction().commit();
		em.close();
		
		return encontrado;
		
	}
	
	public String obterPapel(Usuario usuario) {
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		Query query = em.createQuery("select u.papel from Usuario u where u.usuario = :usuario and u.senha = :senha");
		query.setParameter("usuario", usuario.getUsuario());
		query.setParameter("senha", usuario.getSenha());
		
		Object encontrado = query.getResultList().get(0);
		
		em.getTransaction().commit();
		em.close();
		
		return encontrado.toString();
		
	}
}
