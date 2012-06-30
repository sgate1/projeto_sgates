package br.una.laboratorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.una.laboratorio.model.Servico;
import br.una.laboratorio.service.ServicoService;

@Controller
@RequestMapping({ "/servico/**" })
public class ServicoController {

	@Autowired
	private ServicoService servicoService;

	@RequestMapping(value = "/servico", method = RequestMethod.POST)
	public Servico save(@RequestBody Servico servico) {
		return this.servicoService.save(servico);
	}

	@RequestMapping(value = "/servico", method = RequestMethod.GET)
	public List<Servico> list() {
		return this.servicoService.findAll();
	}

}
