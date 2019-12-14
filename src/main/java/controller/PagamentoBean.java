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
import model.Pagamento;
import service.PagamentoService;

@Named
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@Inject
	private Pagamento pagamento;

	private List<Pagamento> lista = new ArrayList<Pagamento>();

	@Inject
	private PagamentoService paService;

	public PagamentoBean() {
	}

	public PagamentoBean(Pagamento pagamento, List<Pagamento> lista, Integer codigo, PagamentoService paService) {
		this.pagamento = pagamento;
		this.lista = lista;
		this.codigo = codigo;
		this.paService = paService;
	}
		

		

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Pagamento> getLista() {
		return lista;
	}

	public void setLista(List<Pagamento> lista) {
		this.lista = lista;
	}

	public PagamentoService getPaService() {
		return paService;
	}

	public void setPaService(PagamentoService paService) {
		this.paService = paService;
	}

	public void salvar() throws ValidacaoException {
		try {
			paService.cadastarPagamento(pagamento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}

	public void excluir() throws Exception {
		paService.removerPagamento(pagamento.getOrdemPagamento());
	}

	public List<Pagamento> listaTodos() {
		lista = paService.listarPagamento();
		return lista;
	}

	public void atualizarPagamento() throws Exception {
		paService.atualizarPagamento(codigo, pagamento);
	}

}
