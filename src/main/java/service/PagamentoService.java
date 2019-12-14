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

import dao.PagamentoDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.Pagamento;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PagamentoService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private PagamentoDAO dao;
		
		public PagamentoService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarPagamento(Pagamento pagamento) throws ValidacaoException{
			validaPagamento(pagamento);
			dao.adiciona(pagamento);
		}

		public List<Pagamento> listarPagamento() {
			return dao.listaTodos();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerPagamento(Integer Codigo) {
			boolean resultado = dao.removePorCodigo(Codigo);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarPagamento(Pagamento pagamento) throws Exception{
			dao.atualiza(pagamento);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarPagamento(Integer Codigo, Pagamento pagamento) throws Exception {
			Pagamento pagamentomodificado = pagamento;
			pagamentomodificado.setOrdemPagamento(Codigo);
			//pagamentoDoBanco.atualizarCampos(pagamento);
			dao.atualiza(pagamentomodificado);
			dao.comitarCache();
		}
		
		public void validaPagamento(Pagamento pagamento) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Pagamento>> violations = validator.validate(pagamento);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Pagamento> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
