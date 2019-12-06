package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	private Integer Id;
	
	@Inject 
	private Cliente cliente;
	
	private List<Cliente> lista = new ArrayList<Cliente>();
	
	@Inject
	private ClienteService clService;
	
	public ClienteBean() {
	}
	public ClienteBean(Cliente cliente, List<Cliente> lista, Integer Id, ClienteService clService) {
		this.cliente = cliente;
		this.lista = lista;
		this.Id = Id;
		this.clService = clService;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}


	public Cliente getCliente() {
		return cliente;
	}
	

	public List<Cliente> getLista() {
		return lista;
	}


	public void setLista(List<Cliente> lista) {
		this.lista = lista;
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
	public void salvar() throws ValidacaoException{
		clService.cadastarCliente(cliente);

	}
	
	public void excluir() throws Exception {
			clService.removerCliente(cliente.getId());
	}

	public List<Cliente> ListaTodos() {
		lista = clService.listarTodos();
	return lista;
	}
	public void AtualizarCliente () throws Exception {
		clService.atualizarCliente(Id, cliente);
	}

}
