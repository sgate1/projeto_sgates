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

	@Transactional(readOnly = true)
	public Servico retrieve(Long id) {
		return dao.retrieve(id);
	}

	@Transactional(readOnly = true)
	public List<Servico> retrieve() {
		return dao.retrieve();
	}
	
	@Transactional(readOnly = true)
	public void delete( Servico servico ) {
		dao.delete(servico);
	}
	
}
