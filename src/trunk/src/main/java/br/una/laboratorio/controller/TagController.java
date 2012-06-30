package br.una.laboratorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.una.laboratorio.model.Tag;
import br.una.laboratorio.service.TagService;

@Controller
@RequestMapping( { "/tag/**" } )
public class TagController {

	@Autowired
	private TagService service;
	
	@RequestMapping( value = "/inserir", method = RequestMethod.GET)
	public Tag save(@RequestParam String nome) {
		
		Tag tag = new Tag();
		tag.setNome(nome);
		
		return this.service.save(tag);
	}
	
	@RequestMapping( value = "/tag", method = RequestMethod.GET)
	public List<Tag> findAll() {
		return this.service.findAll();
	}
	
}
