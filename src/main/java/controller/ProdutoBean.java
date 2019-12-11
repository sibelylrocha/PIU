package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Produto;
import service.ProdutoService;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	private List<Produto> lista = new ArrayList<Produto>();
	
	@Inject
	private Produto produto;
	
	@Inject
	private ProdutoService pdService;
	
	public ProdutoBean() {}
	
	public ProdutoBean(Integer Id, List<Produto> lista, Produto produto, ProdutoService pdService) {
		this.Id = Id;
		this.lista = lista;
		this.produto = produto;
		this.pdService = pdService;
	}

	public void salvar() throws ValidacaoException {
			pdService.cadastarProduto(produto);
	}

	public void excluir() throws Exception {
		pdService.removerProduto(produto.getId());
	}

	public List<Produto> listaTodos() {
		lista = pdService.listarProduto();
		return lista;
	}

	public void atualizarProduto() throws Exception {
		pdService.atualizarProduto(Id, produto);
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public void setPdService(ProdutoService pdService) {
		this.pdService = pdService;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoService getPdService() {
		return pdService;
	}
	
}