package br.una.laboratorio.sgate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import br.una.laboratorio.sgate.model.domain.entity.Servico;
import br.una.laboratorio.sgate.model.service.bo.ServicoBO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/infrastructure-config.xml" })
public class ServicoTest {
	
	@Inject public ServicoBO service;
	private static Long id;

	@Test
	public void gravandoUmaEntidadeServicoNoBanco() {
		Servico servico = new Servico();
		servico.setTitulo("TV a Cabo");
		servico.setDescricao("Serviço prestação de tv a cabo");
		servico.setPreco(120.5D);
		service.save(servico);
		id = servico.getId();
		assertNotNull("Objeto não salvo! id nulo", id);
	}

	@Test
	public void listandoTodosServicosNoBanco() {
		List<Servico> servicos = service.retrieve();
		boolean isEmpty = CollectionUtils.isEmpty(servicos);
		assertFalse("Lista vazia", isEmpty);

	}

	@Test
	public void recuperandoUmServicoEspecifico() {
		Servico servico = service.retrieve(id);
		assertNotNull("Objeto serviço não encontrado!", servico);
	}
	
	@Test
	public void deletarUmServico(){
		Servico servico = service.retrieve(id);
		service.delete( servico );
		servico = service.retrieve(id);
		assertNull("Entidade serviço não foi deletada", servico);
	}
	
}