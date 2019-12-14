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
import model.Despesa;
import service.DespesaService;

@Named
@ViewScoped
public class DespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@Inject
	private Despesa despesa;

	private List<Despesa> lista = new ArrayList<Despesa>();

	@Inject
	private DespesaService dsService;

	public DespesaBean() {
	}

	public DespesaBean(Despesa despesa, List<Despesa> lista, Integer codigo, DespesaService dsSService) {
		this.despesa = despesa;
		this.lista = lista;
		this.codigo = codigo;
		this.dsService = dsService;
	}
		

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getLista() {
		return lista;
	}

	public void setLista(List<Despesa> lista) {
		this.lista = lista;
	}

	public DespesaService getDsService() {
		return dsService;
	}

	public void setDsService(DespesaService dsService) {
		this.dsService = dsService;
	}

	public void salvar() throws ValidacaoException {
		try {
			dsService.cadastarDespesa(despesa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}

	public void excluir() throws Exception {
		dsService.removerDespesa(despesa.getCodigo());
	}

	public List<Despesa> listaTodos() {
		lista = dsService.listarDespesa();
		return lista;
	}

	public void atualizarDespesa() throws Exception {
		dsService.atualizarDespesa(codigo, despesa);
	}

}
