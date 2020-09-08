package com.franklin.springrest.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franklin.springrest.domain.exception.ExceptionsBO;
import com.franklin.springrest.domain.model.Cliente;
import com.franklin.springrest.domain.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExiste = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteExiste !=null && cliente.equals(cliente)) {
			throw new ExceptionsBO("Esse Email já esta Cadrastado, tente outro!");
		}
		return clienteRepository.save(cliente);
	}
	
	public void remover(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
	public Cliente alterar(Integer id) {
		Cliente cliente=null;
		return clienteRepository.save(cliente);
		
	}

}
