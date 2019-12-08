package controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Empresa;
import service.LoginService;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LoginService loginService;
	private Empresa empresa;
	
	private String Password;
	private String UserName;
	
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	
	public String logar() {
	try {
		Object user = loginService.logar(Password, UserName);
		FacesContext sessao = FacesContext.getCurrentInstance();
		if (user instanceof Empresa) {
			sessao.getExternalContext().getSessionMap().put("Perfil", (Empresa) user);
			empresa = (Empresa) user;
			return "perfil_Empresa";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "UserName ou Senha Incorretos."));
		}

	} catch (Exception v) {
		v.getMessage();
	}
	return null;

}

	
	public String Deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Deslogado com sucesso!"));
		return"CadastroCliente.xhtml?faces-redirect=true";
	}
}
