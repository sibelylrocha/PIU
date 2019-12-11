package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Venda;
import service.VendaService;

@Named
@ViewScoped
public class VendaBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	private List<Venda> lista = new ArrayList<Venda>();
	
	@Inject
	private Venda venda;
	
	@Inject
	private VendaService vdService;
	
	public VendaBean() {}
	
	public VendaBean(Integer Id, List<Venda> lista, Venda venda, VendaService vdService) {
		this.Id = Id;
		this.lista = lista;
		this.venda = venda;
		this.vdService = vdService;
	}

	public void salvar() throws ValidacaoException {
		System.out.println("oi");
			vdService.cadastarVenda(venda);
	}

	public void excluir() throws Exception {
		vdService.removerVenda(venda.getId());
	}

	public List<Venda> listaTodos() {
		lista = vdService.listarVendas();
		return lista;
	}

	public void atualizarVenda() throws Exception {
		vdService.atualizarVenda(Id, venda);
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public List<Venda> getLista() {
		return lista;
	}

	public void setLista(List<Venda> lista) {
		this.lista = lista;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public VendaService getVdService() {
		return vdService;
	}

	public void setVdService(VendaService vdService) {
		this.vdService = vdService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
