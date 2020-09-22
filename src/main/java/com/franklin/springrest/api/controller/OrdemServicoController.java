package com.franklin.springrest.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.franklin.springrest.api.model.OrdemServicoDTO;
import com.franklin.springrest.api.model.OrdemServicoNewDTO;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoNewDTO ordemServicoNewDTO) {
		OrdemServico ordemServico = toEntity(ordemServicoNewDTO);
		return toModel(gestaoOrdemServico.criar(ordemServico));		
	}
	
	@GetMapping
	public List<OrdemServicoDTO> listar() {
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> buscar(@Valid @PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		if(ordemServico.isPresent()) {
			OrdemServicoDTO model = toModel(ordemServico.get());
			return ResponseEntity.ok(model);
		}
		return ResponseEntity.notFound().build();		
	}
	
	//Conversion Methods
	
	private OrdemServicoDTO toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
		
	}
	
	private List<OrdemServicoDTO> toCollectionModel(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(OrdemServicoNewDTO ordemServicoNewDTO) {
		return modelMapper.map(ordemServicoNewDTO, OrdemServico.class);
		
	}
}
