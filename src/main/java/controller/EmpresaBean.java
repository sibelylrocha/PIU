package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Empresa;
import service.EmpresaService;

@Named
@RequestScoped
public class EmpresaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresa empresa;
	
	@Inject
	private EmpresaService emService;
	
	public EmpresaBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
			emService.cadastarEmpresa(empresa);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public EmpresaService getEmService() {
		return emService;
	}

	public void setEmService(EmpresaService emService) {
		this.emService = emService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
