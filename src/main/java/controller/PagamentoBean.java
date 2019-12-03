package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Pagamento;
import service.PagamentoService;

@Named
@RequestScoped
public class PagamentoBean implements Serializable{

private static final long serialVersionUID = 1L;

	
	@Inject 
	private Pagamento pagamento;
	
	@Inject
	private PagamentoService pgService;
	
	public PagamentoBean() {}
	
	public void salvar() throws ValidacaoException {
		try{
		pgService.cadastarPagamento(pagamento);
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro Realizado Com Sucesso!"));
		}catch(ValidacaoException v){
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "Erro no Cadastro"));
		}
		
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public PagamentoService getPgService() {
		return pgService;
	}

	public void setPgService(PagamentoService pgService) {
		this.pgService = pgService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
