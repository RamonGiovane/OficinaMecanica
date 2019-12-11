package tsi.daw.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T>{
	
	private Class<T> classe;
	
	public DAO(Class<T> classe){
		this.classe = classe;
	}
	
	public void adiciona(T t){
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public void altera(T t){
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		
		em.close();
		
	}
	
	public void remove(T t){
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		
		em.close();
	}
	
	public T buscaPorId(Long id){
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		T t = em.find(this.classe, id);
		
		em.close();
		return t;
		
	}
	
	public List<T> listaTodos(){
		EntityManager em = new JPAUtil().getEmf();
		
		em.getTransaction().begin();
		
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		
		em.close();
		return lista;              
		
	}

	
	
	
}