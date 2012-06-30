package br.una.laboratorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.una.laboratorio.model.Servico;
import br.una.laboratorio.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repository;

	@Transactional
	public Servico save(Servico servico) {
		this.repository.save(servico);
		return servico;
	}

	@Transactional
	public List<Servico> findAll() {
		return this.repository.findAll();
	}

	@Transactional
	public Servico find(long id) {
		return this.repository.find(id);
	}

	@Transactional
	public void delete(Servico servico) {
		this.repository.delete(servico);
	}

}
