package br.una.laboratorio.sgate.model.service.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.una.laboratorio.sgate.model.domain.entity.Plano;
import br.una.laboratorio.sgate.model.persistence.dao.PlanoDAO;

@Named
public class PlanoBO {

	@Inject
	@DAO( implementation = DAOImplementation.HIBERNATE )
	private PlanoDAO dao;

	@Transactional
	public Plano save( Plano plano ) {
		return dao.createOrUpdate(plano);
	}
	
	@Transactional(readOnly = true)
	public List<Plano> retrieve() {
		return dao.retrieve();
	}	
	
}
