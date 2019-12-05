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
		clService.cadastarCliente(cliente);

	}
	
	public void excluir(Integer Id) throws Exception {
			clService.removerCliente(Id);
	}
	
	public void ListaCliente () throws Exception {
		clService.listarClientes();
	}
	
	public void AtualizarCliente (Integer Id) throws Exception {
		clService.atualizarUsuario(Id, cliente);
	}

}
