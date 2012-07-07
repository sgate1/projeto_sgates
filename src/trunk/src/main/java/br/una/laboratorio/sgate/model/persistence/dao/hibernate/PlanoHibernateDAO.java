package br.una.laboratorio.sgate.model.persistence.dao.hibernate;

import javax.inject.Named;

import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAO;
import br.ufla.lemaf.commons.model.persistence.dao.annotation.DAOImplementation;
import br.ufla.lemaf.commons.model.persistence.dao.hibernate.HibernateDAO;
import br.una.laboratorio.sgate.model.domain.entity.Plano;
import br.una.laboratorio.sgate.model.persistence.dao.PlanoDAO;

@Named
@DAO( implementation = DAOImplementation.HIBERNATE )
public class PlanoHibernateDAO extends HibernateDAO<Plano, Long> implements PlanoDAO {

}
