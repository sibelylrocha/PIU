package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Pagamento;
import service.PagamentoService;

@Named
@ViewScoped
public class PagamentoBean implements Serializable{

private static final long serialVersionUID = 1L;

	private Integer Id;
	
	private List<Pagamento> lista = new ArrayList<Pagamento>();

	@Inject 
	private Pagamento pagamento;
	
	@Inject
	private PagamentoService pgService;
	
	public PagamentoBean() {}
	
	
	public PagamentoBean(Integer Id, List<Pagamento> lista, Pagamento pagamento, PagamentoService pgService) {
		this.Id = Id;
		this.lista = lista;
		this.pagamento = pagamento;
		this.pgService = pgService;
	}


	public void salvar() throws ValidacaoException {
		pgService.cadastarPagamento(pagamento);	
	}
	
	public void excluir() throws Exception {
		pgService.atualizarPagamento(Id, pagamento);
	}

	public List<Pagamento> listaTodos() {
		lista = pgService.listarPagamentos();
		return lista;
	}

	public void atualizarPagamento() throws Exception {
		pgService.atualizarPagamento(Id, pagamento);
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public List<Pagamento> getLista() {
		return lista;
	}

	public void setLista(List<Pagamento> lista) {
		this.lista = lista;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public PagamentoService getPgService() {
		return pgService;
	}

	public void setPgService(PagamentoService pgService) {
		this.pgService = pgService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
