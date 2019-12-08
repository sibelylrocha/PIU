package service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.EmpresaDAO;
import exception.ValidacaoException;
import modelo.Empresa;

@Stateless
public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaDAO empresaDAO;

	public Object logar(String Password, String UserName) throws ValidacaoException {
		if (Password.trim().isEmpty() != true && UserName.trim().isEmpty() != true) {
			Empresa empresa = empresaDAO.loginEmpresa(Password, UserName);
			 if (empresa != null) {
				return empresa;
			} else {
				return null;
			}

		} else {
			throw new ValidacaoException("Erro de Login");
		}

	}

}
