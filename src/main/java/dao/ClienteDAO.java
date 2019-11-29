package dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Cliente;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private DAO<Cliente> dao;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	
	public ClienteDAO() {
	
	}

	public ClienteDAO(EntityManager manager){
		this.dao = new DAO<Cliente>(manager, Cliente.class);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente Cadastrar(Cliente cliente) {
		return dao.Cadastrar(cliente);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Cliente t) {
		dao.Excluir(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente atualiza(Cliente t) {
		return dao.atualizar(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cliente> listaTodos() {
		return dao.listaTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente ConsultarClientePorCpf(String Cpf) {
		return dao.ConsultarClientePorCpf(Cpf);
	}
	
	public void close() {
		this.dao.close();
	}
	public boolean removePorId(Integer Id) {
		String hql = "DELETE FROM Cliente WHERE Id = :Id";
		Query query = manager.createQuery(hql);
		query.setParameter("Id", Id);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}

	public void comitarCache() {
		dao.comitarCache();
	}

}
