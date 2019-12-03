package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Estoque;
import service.EstoqueService;

@Named
@RequestScoped
public class EstoqueBean implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@Inject 
	private Estoque estoque;
	
	@Inject
	private EstoqueService etService;
	
	public EstoqueBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
			etService.cadastarEstoque(estoque);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
		
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
