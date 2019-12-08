package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.ProdutoDAO;
import exception.ValidacaoException;
import modelo.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProdutoService implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO dao;
	
	public ProdutoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarProduto(Produto produto) throws ValidacaoException{
		dao.Cadastrar(produto);
	}

	public List<Produto> listarProduto() {
		return dao.listarProduto();
	}

	public Produto ConsultarProdutoPorId(int Id) {
		return dao.ConsultarProdutoPorId(Id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarUsuario(Produto produto) throws Exception{
		dao.Atualiza(produto);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerProduto(Integer Id) {
		boolean resultado = dao.removePorId(Id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarProduto(Integer Id, Produto produto) throws Exception {
		Produto produtomodificado = produto;
		produtomodificado.setId(Id);
		//clienteDoBanco.atualizarCampos(cliente);
		dao.Atualiza(produtomodificado);
		dao.comitarCache();
	}
}
