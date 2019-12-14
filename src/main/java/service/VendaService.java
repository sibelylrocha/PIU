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

import dao.VendaDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.Venda;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VendaService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private VendaDAO dao;
		
		public VendaService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarVenda(Venda venda) throws ValidacaoException{
			validaVenda(venda);
			dao.adiciona(venda);
		}

		public List<Venda> listarVenda() {
			return dao.listaTodos();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerVenda(Integer Codigo) {
			boolean resultado = dao.removePorId(Codigo);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarVenda(Venda venda) throws Exception{
			dao.atualiza(venda);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarVenda(Integer Codigo, Venda venda) throws Exception {
			Venda vendamodificado = venda;
			vendamodificado.setCodigo(Codigo);
			//vendaDoBanco.atualizarCampos(venda);
			dao.atualiza(vendamodificado);
			dao.comitarCache();
		}
		
		public void validaVenda(Venda venda) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Venda>> violations = validator.validate(venda);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Venda> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
