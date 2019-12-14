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

import model.Pagamento;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PagamentoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Pagamento> dao;
	
	public PagamentoDAO() {}
	
	public PagamentoDAO(EntityManager manager) {
		this.dao = new DAO<Pagamento>(manager, Pagamento.class);
	}
	
	@PostConstruct
	private void initDAO() {
		this.dao = new DAO<Pagamento>(manager, Pagamento.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Pagamento t) {
		dao.adiciona(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Pagamento t) {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorCodigo(Integer codigo) {
		String hql = "DELETE FROM Pagamento WHERE codigo = :codigo";
		Query query = manager.createQuery(hql);
		query.setParameter("codigo", codigo);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Pagamento t) {
		dao.atualiza(t);
	}

	public List<Pagamento> listaTodos() {
		return dao.listaTodos();
	}

	public Pagamento buscaPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}

	public void comitarCache() {
		dao.comitarCache();
	}
}
