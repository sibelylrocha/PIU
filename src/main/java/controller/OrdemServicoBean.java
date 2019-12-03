package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.OrdemServico;
import service.OrdemServicoService;

@Named
@RequestScoped
public class OrdemServicoBean implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@Inject 
	private OrdemServico ordemservico;
	
	@Inject
	private OrdemServicoService orService;
	
	public OrdemServicoBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
			orService.cadastarOrdemServico(ordemservico);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
		
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
