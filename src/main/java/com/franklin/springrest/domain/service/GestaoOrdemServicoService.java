package com.franklin.springrest.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franklin.springrest.domain.model.OrdemServico;
import com.franklin.springrest.domain.model.StatusOrdemServico;
import com.franklin.springrest.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServiceRepository;
	
	public OrdemServico criar (OrdemServico ordemServico) {
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		return ordemServiceRepository.save(ordemServico);
		
	}

}
