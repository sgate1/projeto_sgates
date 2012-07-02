package br.una.laboratorio.sgate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.ufla.lemaf.commons.model.service.to.MessageReturnTO;
import br.ufla.lemaf.commons.model.service.to.ReturnTO.Status;

public abstract class ApplicationController {

	private static Logger log = Logger.getLogger( ApplicationController.class );

	@ExceptionHandler( Exception.class )
	public void exceptionHandler( Exception ex, HttpServletResponse response, HttpServletRequest request ) {

		try {
			log.error( ex.getMessage() );
			ex.printStackTrace();

			response.setContentType( "application/json" );

			ObjectMapper mapper = new ObjectMapper();
			MessageReturnTO to = new MessageReturnTO( Status.ERROR, ex.getMessage() );
			mapper.writeValue( response.getOutputStream(), to );

		} catch ( IOException e ) {

			e.printStackTrace();
		}
	}

}
