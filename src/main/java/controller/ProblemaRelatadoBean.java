package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.ProblemaRelatado;
import service.ProblemaRelatadoService;

@Named
@RequestScoped
public class ProblemaRelatadoBean implements Serializable{

private static final long serialVersionUID = 1L;

	
	@Inject 
	private ProblemaRelatado problemarelatado;
	
	@Inject
	private ProblemaRelatadoService pbService;
	
	public ProblemaRelatadoBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
			pbService.cadastarProblemaRelatado(problemarelatado);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
		
	}
}
