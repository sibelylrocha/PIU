package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import dao.FuncionarioDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.Funcionario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDAO dao;
	
	public FuncionarioService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarFuncionario(Funcionario funcionario) throws ValidacaoException{
		validaFuncionario(funcionario);
		dao.adiciona(funcionario);
	}

	public List<Funcionario> listarFuncionarios() {
		return dao.listaTodos();
	}

	public Funcionario getFuncionarioPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerFuncionario(Integer codigo) {
		boolean resultado = dao.removePorId(codigo);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarFuncionario(Funcionario funcionario) {
		dao.atualiza(funcionario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void trocaFuncionario(Integer codigo, Funcionario funcionario) {
		funcionario.setCodigo(codigo);
		dao.atualiza(funcionario);
		dao.comitarCache();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarFuncionario(Integer codigo, Funcionario funcionario) {
		Funcionario usuarioDoBanco = dao.buscaPorCodigo(codigo);
		usuarioDoBanco.atualizarCampos(funcionario);
		dao.atualiza(usuarioDoBanco);
		dao.comitarCache();
	}
	
	public void validaFuncionario(Funcionario funcionario) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Funcionario>> violations = validator.validate(funcionario);
		
		if(violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();
			
			for (ConstraintViolation<Funcionario> vi : violations) {
				mensagens.add(vi.getMessage());
			}
			
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}
}
