package controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import modelo.Empresa;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa;

	public String logar() {
		if (empresa.getUserName().equals("maria") && empresa.getPassword().equals("maria123")) {
			return "/faces/menu.xhtml?faces-redirect=true";
		}
		return null;
	}

	/*public String Deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Deslogado com sucesso!"));
		return"menu.xhtml?faces-redirect=true";
	} */
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
