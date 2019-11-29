package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
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
	
	public void salvar() throws ValidacaoException {
		clService.cadastarCliente(cliente);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClService() {
		return clService;
	}

	public void setClService(ClienteService clService) {
		this.clService = clService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
