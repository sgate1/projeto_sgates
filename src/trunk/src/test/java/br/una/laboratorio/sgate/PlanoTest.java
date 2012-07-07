package br.una.laboratorio.sgate;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.una.laboratorio.sgate.model.domain.entity.Plano;
import br.una.laboratorio.sgate.model.domain.entity.Servico;
import br.una.laboratorio.sgate.model.service.bo.PlanoBO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/infrastructure-config.xml" })
public class PlanoTest {

	@Inject
	public PlanoBO bo;
	
	@Test
	public void testeInserirPlano(){
		Plano plano = new Plano();
		
		plano.setDesconto(20l);
		plano.setDescricao("Teste de plano" );

		List<Servico> servicos  = new ArrayList<Servico>();
		servicos.add(new Servico(1l));
		plano.setServicos(servicos);
	
		bo.save(plano);
		assertNotNull("Objeto não salvo! id nulo",plano.getId());
		
	}
	
	
}
