package controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import exception.ValidacaoException;
import modelo.Venda;
import service.VendaService;

public class VendaBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	private Venda venda;
	
	@Inject
	private VendaService vdService;
	
	public VendaBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
			vdService.cadastarVenda(venda);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public VendaService getVdService() {
		return vdService;
	}

	public void setVdService(VendaService vdService) {
		this.vdService = vdService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
