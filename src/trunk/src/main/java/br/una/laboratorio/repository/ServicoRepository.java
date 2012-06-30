package br.una.laboratorio.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.una.laboratorio.model.Servico;

@Repository
public class ServicoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Servico servico) {
		entityManager.persist(servico);
	}

	public List<Servico> findAll() {
		return (List<Servico>) this.entityManager.createQuery("SELECT s FROM " + Servico.class.getSimpleName() + " s", Servico.class).getResultList();
	}

	public Servico find(long id) {
		return this.entityManager.find(Servico.class, id);
	}

	public void delete(Servico servico) {
		this.entityManager.remove(servico);
	}

}
