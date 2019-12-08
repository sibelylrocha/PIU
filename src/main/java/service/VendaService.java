package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.VendaDAO;
import exception.ValidacaoException;
import modelo.Venda;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VendaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendaDAO dao;
	
	public VendaService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarVenda(Venda venda) throws ValidacaoException{
		dao.Cadastrar(venda);
	}

	public List<Venda> listarVendas() {
		return dao.listaVendas();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Venda ConsultarVenda(int Id) {
		return dao.ConsultarVenda(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerVenda(Integer Id) {
		boolean resultado = dao.removePorId(Id);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarUsuario(Venda venda) throws Exception{
		dao.Atualiza(venda);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarVenda(Integer Id, Venda venda) throws Exception {
		Venda vendamodificado = venda;
		vendamodificado.setId(Id);
		//clienteDoBanco.atualizarCampos(cliente);
		dao.Atualiza(vendamodificado);
		dao.comitarCache();
	}
}
