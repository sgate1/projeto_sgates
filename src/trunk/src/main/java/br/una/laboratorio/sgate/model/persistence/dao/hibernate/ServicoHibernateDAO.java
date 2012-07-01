package br.una.laboratorio.sgate.model.persistence.dao.hibernate;

import javax.inject.Named;

import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;
import br.una.laboratorio.sgate.model.domain.entity.Servico;
import br.una.laboratorio.sgate.model.persistence.dao.ServicoDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class ServicoHibernateDAO extends HibernateDAO<Servico, Long> implements ServicoDAO {
	
}
