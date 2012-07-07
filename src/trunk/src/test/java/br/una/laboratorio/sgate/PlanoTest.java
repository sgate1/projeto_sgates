package br.una.laboratorio.sgate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import br.una.laboratorio.sgate.model.domain.entity.Plano;
import br.una.laboratorio.sgate.model.domain.entity.Servico;
import br.una.laboratorio.sgate.model.service.bo.PlanoBO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/infrastructure-config.xml" })
public class PlanoTest {

	@Inject
	public PlanoBO bo;
	
	@Test
	public void inserindoUmPlanoNoBanco(){
		Plano plano = new Plano();
		
		plano.setDesconto(20l);
		plano.setDescricao("Teste de plano" );

		List<Servico> servicos  = new ArrayList<Servico>();
		servicos.add(new Servico(1l));
		plano.setServicos(servicos);
	
		bo.save(plano);
		assertNotNull("Objeto não salvo! id nulo",plano.getId());
		
	}
	
	@Test
	public void buscandoTodosOsPlanosDoBanco(){
		List<Plano> planos = bo.retrieve();
		assertNotNull("Deveria retornar uma lista de planos", planos);
		assertFalse("A lista não deveria estar vazia", CollectionUtils.isEmpty(planos));
	}
	
	
}
