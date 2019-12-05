package dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Cliente> dao;
	
	public ClienteDAO() {}

	public void ClienteDAO(EntityManager manager){
		dao = new DAO<Cliente>(manager, Cliente.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Cliente>(manager, Cliente.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Cliente t) {
		System.out.println("ola");
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Cliente cliente) throws Exception{
		dao.Excluir(cliente);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente atualiza(Cliente t) throws Exception{
		
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente BuscaPorId(Integer Id) {
		return dao.buscaPorId(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer Id) {
		String hql = "DELETE FROM Cliente WHERE Id = :Id";
		Query query = manager.createQuery(hql);
		query.setParameter("Id", Id);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	public void close() {
		this.dao.close();
	}
	public void comitarCache() {
		dao.comitarCache();
	}

}
