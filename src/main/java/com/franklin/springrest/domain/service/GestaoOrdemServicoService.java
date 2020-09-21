package com.franklin.springrest.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franklin.springrest.domain.exception.ExceptionsBO;
import com.franklin.springrest.domain.model.Cliente;
import com.franklin.springrest.domain.model.OrdemServico;
import com.franklin.springrest.domain.model.StatusOrdemServico;
import com.franklin.springrest.domain.repository.ClienteRepository;
import com.franklin.springrest.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServiceRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemServico criar (OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new ExceptionsBO("opoha"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		return ordemServiceRepository.save(ordemServico);		
	}

}
