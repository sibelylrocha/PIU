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
import dao.EmpresaDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import modelo.Empresa;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmpresaService implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO dao;
	
	public EmpresaService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarEmpresa(Empresa empresa) throws ValidacaoException{
		validaEmpresa(empresa);
		dao.Cadastrar(empresa);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Empresa> listarEmpresas() {
		return dao.listaTodos();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarClientePorCnpj(String Cnpj) {
		return dao.ConsultarClientePorCnpj(Cnpj);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarClientePorCpf(String Cpf) {
		return dao.ConsultarClientePorCpf(Cpf);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarOrdemServicoPorId(Integer Id) {
		return dao.ConsultarOrdemServico(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarPagamentoPorId(Integer Id) {
		return dao.ConsultarPagamentoPorId(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarProdutoPorId(Integer Id) {
		return dao.ConsultarProdutoPorSerie(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarEmpresa(String Cnpj) {
		return dao.ConsultarEmpresa(Cnpj);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerEmpresa(String Cnpj) {
		boolean resultado = dao.removePorCnpj(Cnpj);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarEmpresa(Empresa empresa) throws Exception{
		dao.atualiza(empresa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarEmpresa(String Cnpj, Empresa empresa) throws Exception {
		Empresa empresamodificado = empresa;
		empresamodificado.setCnpj(Cnpj);
		//clienteDoBanco.atualizarCampos(cliente);
		dao.atualiza(empresamodificado);
		dao.comitarCache();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void trocaEmpresa(String Cnpj, Empresa empresa) throws Exception{
		empresa.setCnpj(Cnpj);;
		dao.atualiza(empresa);
		dao.comitarCache();
	}
	
	public void validaEmpresa(Empresa empresa) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Empresa>> violations = validator.validate(empresa);
		
		if(violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();
			
			for (ConstraintViolation<Empresa> vi : violations) {
				mensagens.add(vi.getMessage());
			}
			
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}

}
