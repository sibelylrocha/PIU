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

import model.Despesa;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DespesaDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Despesa> dao;
	
	public DespesaDAO() {}
	
	public DespesaDAO(EntityManager manager) {
		this.dao = new DAO<Despesa>(manager, Despesa.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Despesa>(manager, Despesa.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Despesa t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Despesa t) {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer codigo) {
		String hql = "DELETE FROM Despesa WHERE codigo = :codigo";
		Query query = manager.createQuery(hql);
		query.setParameter("codigo", codigo);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Despesa t) {
		dao.atualiza(t);
	}

	public List<Despesa> listaTodos() {
		return dao.listaTodos();
	}

	public Despesa buscaPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}

	public void comitarCache() {
		dao.comitarCache();
	}
}
