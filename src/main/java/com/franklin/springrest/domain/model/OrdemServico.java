package com.franklin.springrest.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.franklin.springrest.domain.ValidationGroups;

@Entity
public class OrdemServico {
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String decricao;
	
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;
	
	private LocalDateTime dataAbertura;
	
	private LocalDateTime dataFinalizacao;
	@ManyToOne
	private Cliente cliente;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return decricao;
	}
	
	public void setDescricao(String descricao) {
		this.decricao = descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public StatusOrdemServico getStatus() {
		return status;
	}
	
	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}
	
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public LocalDateTime getDataFinalização() {
		return dataFinalizacao;
	}
	
	public void setDataFinalização(LocalDateTime dataFinalização) {
		this.dataFinalizacao = dataFinalização;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
