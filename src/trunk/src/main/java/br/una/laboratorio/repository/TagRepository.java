package br.una.laboratorio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.una.laboratorio.model.Tag;

@Repository
public class TagRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Tag> findAll() {
		return (List<Tag>) this.entityManager.createQuery("SELECT t FROM " + Tag.class.getSimpleName() + " t" ).getResultList();
	}
	
	public void save(Tag tag) {
		this.entityManager.persist(tag);
	}

}
