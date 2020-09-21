package com.franklin.springrest.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.franklin.springrest.domain.model.OrdemServico;
import com.franklin.springrest.domain.repository.OrdemServicoRepository;
import com.franklin.springrest.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {		
		return gestaoOrdemServico.criar(ordemServico);		
	}
	
	@GetMapping
	public List<OrdemServico> listar() {
		return ordemServicoRepository.findAll();
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> buscar(@Valid @PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		if(ordemServico.isPresent()) {
			return ResponseEntity.ok(ordemServico.get());
		}
		return ResponseEntity.notFound().build();
		
	}
}
