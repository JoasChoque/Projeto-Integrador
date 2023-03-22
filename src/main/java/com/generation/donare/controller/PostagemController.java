package com.generation.donare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.donare.model.Postagem;
import com.generation.donare.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
	 return ResponseEntity.ok(postagemRepository.findAll());
	}

	
	@GetMapping("/{id}")
    public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id).map(resposta-> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

   @GetMapping("buscaTexto/{texto}")
   public ResponseEntity <List<Postagem>> getByTexto(@PathVariable String texto){
	   return ResponseEntity.ok(postagemRepository.findAllByTextoContainingIgnoreCase(texto));
   }


  @PostMapping
  public ResponseEntity <Postagem> post(@Valid @RequestBody Postagem postagem){
	  return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
  }
       
  

}