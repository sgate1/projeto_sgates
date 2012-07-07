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

	@Transactional(readOnly = true)
	public Plano retrieve(Long id) {
		return dao.retrieve(id);
	}
	
	@Transactional
	public Plano update(Plano plano) {
		
		Plano planoAtual = this.retrieve(plano.getId());
		
		planoAtual.setDescricao(plano.getDescricao());
		planoAtual.setDesconto(plano.getDesconto());
		planoAtual.setServicos(plano.getServicos());
		
		return planoAtual;
	}
}
