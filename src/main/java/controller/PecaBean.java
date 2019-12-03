package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Peca;
import service.PecaService;

@Named
@RequestScoped
public class PecaBean implements Serializable{

private static final long serialVersionUID = 1L;
	

	@Inject
	private Peca peca;
	
	@Inject
	private PecaService pcService;
	
	public PecaBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
			pcService.cadastarPeca(peca);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public PecaService getPcService() {
		return pcService;
	}

	public void setPcService(PecaService pcService) {
		this.pcService = pcService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
