package br.una.laboratorio.sgate.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.una.laboratorio.sgate.model.domain.entity.Servico;
import br.una.laboratorio.sgate.model.persistence.dao.ServicoDAO;

@Named
public class ServicoBO {

	@Inject
	@DAO( implementation = DAOImplementation.HIBERNATE )
	private ServicoDAO dao;

	@Transactional
	public Servico save( Servico servico ) {
		return dao.createOrUpdate(servico);
	}
	
	@Transactional
	public Servico update( Servico servico ) {
		return dao.retrieve( servico.getId() )
		.setTitulo( servico.getTitulo() )
		.setDescricao( servico.getDescricao() )
		.setPreco( servico.getPreco() );
	}

	@Transactional(readOnly = true)
	public Servico retrieve(Long id) {
		return dao.retrieve(id);
	}

	@Transactional(readOnly = true)
	public List<Servico> retrieve() {
		return dao.retrieve();
	}
	
	@Transactional
	public void delete( Servico servico ) {
		Servico retrievedEntity = dao.retrieve( servico.getId() ); 
		dao.delete( retrievedEntity );
	}
	
}
