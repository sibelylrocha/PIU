package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.ProblemaRelatado;
import service.ProblemaRelatadoService;

@Named
@RequestScoped
public class ProblemaRelatadoBean implements Serializable{

private static final long serialVersionUID = 1L;

	private Integer Protocolo;
	
	private List<ProblemaRelatado> lista = new ArrayList<ProblemaRelatado>();

	@Inject 
	private ProblemaRelatado problemarelatado;
	
	@Inject
	private ProblemaRelatadoService pbService;
	
	public ProblemaRelatadoBean() {}
	
	public ProblemaRelatadoBean(Integer Protocolo, List<ProblemaRelatado> lista, ProblemaRelatado problemarelatado,
			ProblemaRelatadoService pbService) {
		this.Protocolo = Protocolo;
		this.lista = lista;
		this.problemarelatado = problemarelatado;
		this.pbService = pbService;
	}
	
	public void salvar() throws ValidacaoException {
			pbService.cadastarProblemaRelatado(problemarelatado);
	}
	
	public void excluir() throws Exception {
		pbService.removerProblemaRelatado(problemarelatado.getProtocolo());
	}

	public List<ProblemaRelatado> listaTodos() {
		lista = pbService.listarProblemasRelatados();
		return lista;
	}

	public void atualizarProblemaRelatado() throws Exception {
		pbService.atualizarProblemaRelatado(Protocolo, problemarelatado);
	}

	public Integer getProtocolo() {
		return Protocolo;
	}

	public void setProtocolo(Integer Protocolo) {
		this.Protocolo = Protocolo;
	}

	public List<ProblemaRelatado> getLista() {
		return lista;
	}

	public void setLista(List<ProblemaRelatado> lista) {
		this.lista = lista;
	}

	public ProblemaRelatado getProblemarelatado() {
		return problemarelatado;
	}

	public void setProblemarelatado(ProblemaRelatado problemarelatado) {
		this.problemarelatado = problemarelatado;
	}

	public ProblemaRelatadoService getPbService() {
		return pbService;
	}

	public void setPbService(ProblemaRelatadoService pbService) {
		this.pbService = pbService;
	}
}
