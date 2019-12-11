package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Peca;
import service.PecaService;

@Named
@RequestScoped
public class PecaBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	private List<Peca> lista = new ArrayList<Peca>();

	@Inject
	private Peca peca;
	
	@Inject
	private PecaService pcService;
	
	public PecaBean() {}
	
	public PecaBean(Integer Id, List<Peca> lista, Peca peca, PecaService pcService) {

		this.Id = Id;
		this.lista = lista;
		this.peca = peca;
		this.pcService = pcService;
	}

	public void salvar() throws ValidacaoException {
			pcService.cadastarPeca(peca);
	}

	public void excluir() throws Exception {
		pcService.removerPeca(peca.getId());
	}

	public List<Peca> listaTodos() {
		lista = pcService.listarPecas();
		return lista;
	}

	public void atualizarCliente() throws Exception {
		pcService.atualizarPeca(Id, peca);
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public List<Peca> getLista() {
		return lista;
	}

	public void setLista(List<Peca> lista) {
		this.lista = lista;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public PecaService getPcService() {
		return pcService;
	}

	public void setPcService(PecaService pcService) {
		this.pcService = pcService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
