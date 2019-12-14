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
import model.Funcionario;
import service.FuncionarioService;

@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;

	@Inject
	private Funcionario funcionario;

	private List<Funcionario> lista = new ArrayList<Funcionario>();

	@Inject
	private FuncionarioService fuService;

	public FuncionarioBean() {
	}

	public FuncionarioBean(Funcionario funcionario, List<Funcionario> lista, Integer codigo, FuncionarioService fuService) {
		this.funcionario = funcionario;
		this.lista = lista;
		this.codigo = codigo;
		this.fuService = fuService;
	}

		

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public FuncionarioService getFuService() {
		return fuService;
	}

	public void setFuService(FuncionarioService fuService) {
		this.fuService = fuService;
	}

	public void salvar() throws ValidacaoException {
		try {
			fuService.cadastarFuncionario(funcionario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}

	public void excluir() throws Exception {
		fuService.removerFuncionario(funcionario.getCodigo());
	}

	public List<Funcionario> listaTodos() {
		lista = fuService.listarFuncionarios();
		return lista;
	}

	public void atualizarFuncionario() throws Exception {
		fuService.atualizarFuncionario(codigo, funcionario);
	}

}
