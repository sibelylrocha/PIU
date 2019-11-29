package service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import dao.OrdemServicoDAO;
import exception.ValidacaoException;
import modelo.OrdemServico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrdemServicoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemServicoDAO dao;
	
	public OrdemServicoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarOrdemServico(OrdemServico ordemservico) throws ValidacaoException{
		dao.Cadastrar(ordemservico);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<OrdemServico> listarOrdemServico() {
		return dao.listaTodos();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OrdemServico ConsultarOrdemServicoPorId(Integer Id) {
		return dao.ConsultarOrdemServico(Id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerOrdemServico(Integer Id) {
		boolean resultado = dao.removePorId(Id);
		dao.comitarCache();
		return resultado;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarOrdemServico(OrdemServico ordemservico) {
		dao.Atualiza(ordemservico);
	}
}
