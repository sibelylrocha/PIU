package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Cliente;
import service.ClienteService;

@Named
@RequestScoped
public class ClienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Inject 
	private Cliente cliente;
	
	@Inject
	private ClienteService clService;
	
	public ClienteBean() {}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClService() {
		return clService;
	}

	public void salvar() throws ValidacaoException{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(cliente.toString()));
		cliente.toString();
	clService.cadastarCliente(cliente);
	 
	}
	public void excluir() throws Exception {
		try{
			clService.removerCliente(cliente);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Removido Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro ao ser Removido"));
		}
		
		
	}


}
