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

import model.Intem;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ItemDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Intem> dao;
	
	public ItemDAO() {}
	
	public ItemDAO(EntityManager manager) {
		this.dao = new DAO<Intem>(manager, Intem.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Intem>(manager, Intem.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Intem t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Intem t) {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorCodigo(Integer codigo) {
		String hql = "DELETE FROM Item WHERE codigo = :codigo";
		Query query = manager.createQuery(hql);
		query.setParameter("codigo", codigo);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Intem t) {
		dao.atualiza(t);
	}

	public List<Intem> listaTodos() {
		return dao.listaTodos();
	}

	public Intem buscaPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}

	public void comitarCache() {
		dao.comitarCache();
	}
}

	



