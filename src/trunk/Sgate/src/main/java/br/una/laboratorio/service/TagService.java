package br.una.laboratorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.una.laboratorio.model.Tag;
import br.una.laboratorio.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;
	
	public List<Tag> findAll() {
		return this.repository.findAll();
	}
	
	@Transactional
	public Tag save(Tag tag) {
		this.repository.save(tag);
		return tag;
	}
}
