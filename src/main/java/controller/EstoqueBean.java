package controller;

import java.io.Serializable;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Estoque;
import service.EstoqueService;

@Named
@RequestScoped
public class EstoqueBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer Id;
	
	@Inject 
	private Estoque estoque;
	
	@Inject
	private EstoqueService etService;
	
	public EstoqueBean() {}
	
	public EstoqueBean(Estoque estoque, EstoqueService etService, Integer Id) {
		this.estoque = estoque;
		this.etService = etService;
		this.Id = Id;
	}

	public void salvar() throws ValidacaoException {
			etService.cadastarEstoque(estoque);
	}
	
	public void excluir() throws Exception {
		etService.removerEstoque(estoque.getId());
	}

	public void atualizarCliente() throws Exception {
		etService.atualizarEstoque(estoque);
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public EstoqueService getEtService() {
		return etService;
	}

	public void setEtService(EstoqueService etService) {
		this.etService = etService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
