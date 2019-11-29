package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.PagamentoDAO;
import exception.ValidacaoException;
import modelo.Pagamento;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PagamentoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PagamentoDAO dao;
	
	public PagamentoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarPagamento(Pagamento pagamento) throws ValidacaoException{
		dao.Cadastrar(pagamento);
	}
	public List<Pagamento> listarPagamentos() {
		return dao.listaTodos();
	}

	public Pagamento ConsultarPagamentoPorId(Integer Id) {
		return dao.ConsultarPagamentoPorId(Id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerPagamento(Integer Id) {
		boolean resultado = dao.removePorId(Id);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarPagamento(Pagamento pagamento) {
		dao.Atualiza(pagamento);
	}
}
