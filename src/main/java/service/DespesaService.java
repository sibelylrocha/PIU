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

import dao.DespesaDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.Despesa;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DespesaService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private DespesaDAO dao;
		
		public DespesaService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarDespesa(Despesa despesa) throws ValidacaoException{
			validaDespesa(despesa);
			dao.adiciona(despesa);
		}

		public List<Despesa> listarDespesa() {
			return dao.listaTodos();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerDespesa(Integer Codigo) {
			boolean resultado = dao.removePorId(Codigo);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarDespesa(Despesa despesa) throws Exception{
			dao.atualiza(despesa);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarDespesa(Integer Codigo, Despesa despesa) throws Exception {
			Despesa despesamodificado = despesa;
			despesamodificado.setCodigo(Codigo);
			//despesaDoBanco.atualizarCampos(despesa);
			dao.atualiza(despesamodificado);
			dao.comitarCache();
		}
		
		public void validaDespesa(Despesa despesa) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Despesa>> violations = validator.validate(despesa);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Despesa> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
