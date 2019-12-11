package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Empresa;
import service.EmpresaService;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	@Inject
	private Empresa empresa;
	
	private List<Empresa> lista = new ArrayList<Empresa>();
	
	@Inject
	private EmpresaService emService;
	
	public EmpresaBean() {}

	public EmpresaBean(Empresa empresa, EmpresaService emService, List<Empresa> lista, Integer Id) {
		this.empresa = empresa;
		this.emService = emService;
		this.lista = lista;
		this.Id = Id;
	}

	public void salvar() throws ValidacaoException {
		System.out.println("hello");
		emService.cadastarEmpresa(empresa);
		
	}
	public void atualizarCliente() throws Exception {
		emService.atualizarEmpresa(Id, empresa);
	}
	public List<Empresa> listaTodos() {
		lista = emService.listarEmpresas();
		return lista;
	}
	
	public void excluir() throws Exception {
		emService.removerEmpresa(empresa.getCnpj());
	}

	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public EmpresaService getEmService() {
		return emService;
	}

	public void setEmService(EmpresaService emService) {
		this.emService = emService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return Id;
	}

	public void setCnpj(Integer Id) {
		this.Id = Id;
	}
	
}
