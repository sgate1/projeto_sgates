package br.una.laboratorio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import br.una.laboratorio.model.Servico;
import br.una.laboratorio.service.ServicoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/infrastructure-config.xml" })
public class ServicoTest {

	@Autowired
	public ServicoService servicoService;

	@Test
	public void gravandoUmaEntidadeServicoNoBanco() {
		Servico servico = new Servico();
		servico.setTitulo("TV a Cabo");
		servico.setDescricao("Serviço prestação de tv a cabo");
		servico.setPreco(120.5D);
		servicoService.save(servico);
		assertNotNull("objeto não salvo! id nulo", servico.getId());
	}

	@Test
	public void listandoTodosServicosNoBanco() {
		List<Servico> servicos = servicoService.findAll();
		boolean isEmpty = CollectionUtils.isEmpty(servicos);
		assertFalse("Lista vazia", isEmpty);

	}

	@Test
	public void recuperandoUmServicoEspecifico() {
		Servico servico = servicoService.find(1L);
		assertNotNull("Objeto não encontrado!", servico);
	}
	
	@Test
	public void deletarUmServico(){
		Servico servico = servicoService.find(1L);
		servicoService.delete(servico);
		servico = servicoService.find(1L);
		assertNull("servico não foi deletado", servico);
	}
}