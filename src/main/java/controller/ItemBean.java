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
import model.Intem;
import service.ItemService;

@Named
@ViewScoped
public class ItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@Inject
	private Intem item;

	private List<Intem> lista = new ArrayList<Intem>();

	@Inject
	private ItemService itService;

	public ItemBean() {
	}

	public ItemBean(Intem item, List<Intem> lista, Integer codigo, ItemService itService) {
		this.item = item;
		this.lista = lista;
		this.codigo = codigo;
		this.itService = itService;
	}

	
		
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Intem getItem() {
		return item;
	}

	public void setItem(Intem item) {
		this.item = item;
	}

	public List<Intem> getLista() {
		return lista;
	}

	public void setLista(List<Intem> lista) {
		this.lista = lista;
	}

	public ItemService getItService() {
		return itService;
	}

	public void setItService(ItemService itService) {
		this.itService = itService;
	}

	public void salvar() throws ValidacaoException {
		try {
			itService.cadastarItem(item);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}

	public void excluir() throws Exception {
		itService.removerItem(item.getCodigo());
	}

	public List<Intem> listaTodos() {
		lista = itService.listarItem();
		return lista;
	}

	public void atualizarItem() throws Exception {
		itService.atualizarItem(codigo, item);
	}

}
