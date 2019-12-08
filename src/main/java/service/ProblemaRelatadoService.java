package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.ProblemaRelatadoDAO;
import exception.ValidacaoException;
import modelo.ProblemaRelatado;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProblemaRelatadoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProblemaRelatadoDAO dao;
	
	public ProblemaRelatadoService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarProblemaRelatado(ProblemaRelatado problemarelatado) throws ValidacaoException{
		dao.Cadastrar(problemarelatado);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ProblemaRelatado> listarProblemasRelatados() {
		return dao.listaTodos();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ProblemaRelatado ConsultarProblemaRelatado(Integer Protocolo) {
		return dao.ConsultarProblemaRelatado(Protocolo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerProblemaRelatado(Integer Protocolo) {
		boolean resultado = dao.removePorProtocolo(Protocolo);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarProblemaRelatado(ProblemaRelatado problemarelatado) throws Exception{
		dao.Atualiza(problemarelatado);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarProblemaRelatado(Integer Protocolo, ProblemaRelatado problemarelatado) throws Exception {
		ProblemaRelatado problemarelatadomodificado = problemarelatado;
		problemarelatadomodificado.setProtocolo(Protocolo);
		//clienteDoBanco.atualizarCampos(cliente);
		dao.Atualiza(problemarelatadomodificado);
		dao.comitarCache();
	}
}
