package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Empresa;
import service.EmpresaService;

@Named
@RequestScoped
public class EmpresaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresa empresa;
	
	@Inject
	private EmpresaService emService;
	private List<Empresa> lista = new ArrayList<Empresa>();
	
	public EmpresaBean() {}
	
	
	
	public EmpresaBean(Empresa empresa, EmpresaService emService, List<Empresa> lista) {
		this.empresa = empresa;
		this.emService = emService;
		this.lista = lista;
	}



	public void salvar() throws ValidacaoException {
		emService.cadastarEmpresa(empresa);
		
	}
	public void atualizarEmpresa() throws Exception {
		emService.atualizarEmpresa(empresa);
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
	
}
