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

import model.Cliente;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Cliente> dao;
	
	public ClienteDAO() {}
	
	public ClienteDAO(EntityManager manager) {
		this.dao = new DAO<Cliente>(manager, Cliente.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Cliente>(manager, Cliente.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Cliente t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Cliente t) {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorCodigo(Integer codigo) {
		String hql = "DELETE FROM Cliente WHERE codigo = :codigo";
		Query query = manager.createQuery(hql);
		query.setParameter("codigo", codigo);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Cliente t) {
		dao.atualiza(t);
	}

	public List<Cliente> listaTodos() {
		return dao.listaTodos();
	}

	public Cliente buscaPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}

	public void comitarCache() {
		dao.comitarCache();
	}
}
