package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Produto;
import service.ProdutoService;

@Named
@RequestScoped
public class ProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produto produto;
	
	@Inject
	private ProdutoService pdService;
	
	public ProdutoBean() {}
	
	public void salvar() throws ValidacaoException {
		pdService.cadastarProduto(produto);
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