package service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import dao.ClienteDAO;
import exception.ValidacaoException;
import modelo.Cliente;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private ClienteDAO dao;
		
		public ClienteService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarCliente(Cliente cliente) throws ValidacaoException{
			dao.Cadastrar(cliente);
		}

		public List<Cliente> listarClientes() {
			return dao.listaTodos();
		}

		public Cliente getClientePorCpf(String Cpf) {
			return dao.ConsultarClientePorCpf(Cpf);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerCliente(Integer Id) {
			boolean resultado = dao.removePorId(Id);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarCliente(Cliente cliente) {
			dao.atualiza(cliente);
		}
}
