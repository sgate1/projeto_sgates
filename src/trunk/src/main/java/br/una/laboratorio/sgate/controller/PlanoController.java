package br.una.laboratorio.sgate.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ObjectAndMessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO;
import br.una.laboratorio.sgate.model.domain.entity.Plano;
import br.una.laboratorio.sgate.model.service.bo.PlanoBO;
import br.una.laboratorio.util.ContentBody;

@Named
@RequestMapping( value = "/plano/**" )
public class PlanoController {
	
	@Inject
	private PlanoBO bo;

	@RequestMapping( value = "/plano", method = RequestMethod.PUT )
	public ReturnTO save( HttpServletRequest request ) {
		Plano plano = ContentBody.entity(request, Plano.class);
		return new ObjectAndMessageReturnTO<Plano>( bo.save( plano ) );
	}
	
	@RequestMapping(value = "/plano", method = RequestMethod.GET)
	public ReturnTO findAll() {
		return new ObjectAndMessageReturnTO<List<Plano>>( bo.retrieve() );
	}
	
	@RequestMapping(value = "/plano", method = RequestMethod.DELETE)
	public ReturnTO delete( HttpServletRequest request ) {
		Plano plano = ContentBody.entity(request, Plano.class);
		bo.delete(plano);
		return new MessageReturnTO();
	}
	
}
