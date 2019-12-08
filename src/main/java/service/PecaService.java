package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.PecaDAO;
import exception.ValidacaoException;
import modelo.Peca;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PecaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PecaDAO dao;
	
	public PecaService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarPeca(Peca peca) throws ValidacaoException{
		dao.Cadastrar(peca);
	}

	public List<Peca> listarPecas() {
		return dao.listaTodos();
	}

	public Peca ConsultarPeca(int Id) {
		return dao.ConsultarPeca(Id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerPeca(Integer Id) {
		boolean resultado = dao.removePorId(Id);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarPeca(Peca peca) throws Exception{
		dao.atualiza(peca);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarPeca(Integer Id, Peca peca) throws Exception {
		Peca pecamodificado = peca;
		pecamodificado.setId(Id);
		//clienteDoBanco.atualizarCampos(cliente);
		dao.atualiza(pecamodificado);
		dao.comitarCache();
	}
}
