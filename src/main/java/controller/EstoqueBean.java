package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Estoque;
import service.EstoqueService;

@Named
@ViewScoped
public class EstoqueBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer Id;
	
	private List<Estoque> lista = new ArrayList<Estoque>();
	
	@Inject 
	private Estoque estoque;
	
	@Inject
	private EstoqueService etService;
	
	public EstoqueBean() {}
	
	public EstoqueBean(Estoque estoque, EstoqueService etService, Integer Id, List<Estoque> lista) {
		this.estoque = estoque;
		this.etService = etService;
		this.Id = Id;
		this.lista = lista;
	}

	public void salvar() throws ValidacaoException {
			etService.cadastarEstoque(estoque);
	}
	
	public void excluir() throws Exception {
		etService.removerEstoque(estoque.getId());
	}

	public void atualizarEstoque() throws Exception {
		etService.atualizarEstoque(Id, estoque);
	}
	
	public List<Estoque> listaTodos() {
		lista = etService.listarEstoque();
		return lista;
	}
	
	public List<Estoque> getLista() {
		return lista;
	}

	public void setLista(List<Estoque> lista) {
		this.lista = lista;
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
