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

import model.Venda;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Venda> dao;
	
	public VendaDAO() {}
	
	public VendaDAO(EntityManager manager) {
		this.dao = new DAO<Venda>(manager, Venda.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Venda>(manager, Venda.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Venda t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Venda t) {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer codigo) {
		String hql = "DELETE FROM Venda WHERE codigo = :codigo";
		Query query = manager.createQuery(hql);
		query.setParameter("codigo", codigo);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Venda t) {
		dao.atualiza(t);
	}

	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

	public Venda buscaPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}

	public void comitarCache() {
		dao.comitarCache();
	}

}
