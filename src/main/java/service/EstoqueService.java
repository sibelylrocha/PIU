package service;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import dao.EstoqueDAO;
import exception.ValidacaoException;
import modelo.Estoque;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EstoqueService implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueDAO dao;
	
	public EstoqueService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarEstoque(Estoque estoque) throws ValidacaoException{
		dao.Cadastrar(estoque);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerEstoque(Integer Id) {
		boolean resultado = dao.removePorId(Id);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarEstoque(Estoque estoque) {
		dao.Atualiza(estoque);
	}
}
