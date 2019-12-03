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
import modelo.Peca;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PecaDAO implements Serializable{
	private static final long serialVersionUID = 1L;

	private DAO<Peca> dao;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	
	public PecaDAO() {
		
	}

	public PecaDAO(EntityManager manager){
		this.dao = new DAO<Peca>(manager, Peca.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Peca>(manager, Peca.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Peca t) {
		dao.Cadastrar(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Peca t) throws Exception{
		dao.Excluir(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Peca atualiza(Peca t) throws Exception{
		return dao.atualizar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Peca> listaTodos() {
		return dao.listaTodos();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Peca ConsultarPeca(int Id) {
		return dao.ConsultarPeca(Id);
	}
	public void close() {
		this.dao.close();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer id) {
		String hql = "DELETE FROM Usuario WHERE id = :id";
		Query query = manager.createQuery(hql);
		query.setParameter("id", id);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}

}
