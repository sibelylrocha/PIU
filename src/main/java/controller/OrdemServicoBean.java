package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.OrdemServico;
import service.OrdemServicoService;

@Named
@RequestScoped
public class OrdemServicoBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer Id;
	
	private List<OrdemServico> lista = new ArrayList<OrdemServico>();
	
	@Inject 
	private OrdemServico ordemservico;
	
	@Inject
	private OrdemServicoService orService;
	
	public OrdemServicoBean() {}
	
	public OrdemServicoBean(Integer Id, OrdemServico ordemservico, OrdemServicoService orService, List<OrdemServico> lista) {
		this.lista = lista;
		this.Id = Id;
		this.ordemservico = ordemservico;
		this.orService = orService;
	}



	public void salvar() throws ValidacaoException {
			orService.cadastarOrdemServico(ordemservico);
	}
	
	public void excluir() throws Exception {
		orService.removerOrdemServico(ordemservico.getId());
	}

	public List<OrdemServico> listaTodos() {
		lista = orService.listarOrdemServico();
		return lista;
	}

	public void atualizarOrdemServico() throws Exception {
		orService.atualizarOrdemServico(Id, ordemservico);
	}

	public List<OrdemServico> getLista() {
		return lista;
	}

	public void setLista(List<OrdemServico> lista) {
		this.lista = lista;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public OrdemServico getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServico ordemservico) {
		this.ordemservico = ordemservico;
	}

	public OrdemServicoService getOrService() {
		return orService;
	}

	public void setOrService(OrdemServicoService orService) {
		this.orService = orService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
