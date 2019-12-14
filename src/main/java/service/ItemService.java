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

import dao.ItemDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import model.Intem;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ItemService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private ItemDAO dao;
		
		public ItemService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarItem(Intem item) throws ValidacaoException{
			validaItem(item);
			dao.adiciona(item);
		}

		public List<Intem> listarItem() {
			return dao.listaTodos();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerItem(Integer Codigo) {
			boolean resultado = dao.removePorCodigo(Codigo);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarItem(Intem Item) throws Exception{
			dao.atualiza(Item);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarItem(Integer Codigo, Intem item) throws Exception {
			Intem itemmodificado = item;
			itemmodificado.setCodigo(Codigo);
			//itemDoBanco.atualizarCampos(item);
			dao.atualiza(itemmodificado);
			dao.comitarCache();
		}
		
		public void validaItem(Intem item) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Intem>> violations = validator.validate(item);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Intem> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
