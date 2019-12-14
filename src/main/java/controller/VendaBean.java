package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import model.Venda;
import service.VendaService;

@Named
@ViewScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@Inject
	private Venda venda;

	private List<Venda> lista = new ArrayList<Venda>();

	@Inject
	private VendaService veService;

	public VendaBean() {
	}

	public VendaBean(Venda venda, List<Venda> lista, Integer codigo, VendaService veService) {
		this.venda = venda;
		this.lista = lista;
		this.codigo = codigo;
		this.veService = veService;
	}
		
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getLista() {
		return lista;
	}

	public void setLista(List<Venda> lista) {
		this.lista = lista;
	}

	public VendaService getVeService() {
		return veService;
	}

	public void setVeService(VendaService veService) {
		this.veService = veService;
	}

	public void salvar() throws ValidacaoException {
		try {
			veService.cadastarVenda(venda);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}

	public void excluir() throws Exception {
		veService.removerVenda(venda.getCodigo());
	}

	public List<Venda> listaTodos() {
		lista = veService.listarVenda();
		return lista;
	}

	public void atualizarVenda() throws Exception {
		veService.atualizarVenda(codigo, venda);
	}

}
