package com.franklin.springrest.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franklin.springrest.api.model.Comentario;
import com.franklin.springrest.domain.exception.ExceptionsBO;
import com.franklin.springrest.domain.model.Cliente;
import com.franklin.springrest.domain.model.OrdemServico;
import com.franklin.springrest.domain.model.StatusOrdemServico;
import com.franklin.springrest.domain.repository.ClienteRepository;
import com.franklin.springrest.domain.repository.ComentarioRepository;
import com.franklin.springrest.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServiceRepository;
	@Autowired
	private ClienteRepository clienteServiceRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteServiceRepository.findById(ordemServico.getCliente().getId()).orElseThrow(() -> new ExceptionsBO("Cliente Não Encontrado"));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		return ordemServiceRepository.save(ordemServico);

	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServiceRepository.findById(ordemServicoId).orElseThrow(() -> new ExceptionsBO("Ordem de Serviço Não Encontrada"));
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}

}
