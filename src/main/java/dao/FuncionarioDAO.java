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

import model.Funcionario;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class FuncionarioDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Funcionario> dao;
		
	public FuncionarioDAO() {}
	
	public FuncionarioDAO(EntityManager manager) {
		this.dao = new DAO<Funcionario>(manager, Funcionario.class);
	}
	
	
	@PostConstruct
	private void initDAO() {
		this.dao  = new DAO<Funcionario>(manager, Funcionario.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Funcionario t) {
		dao.adiciona(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Funcionario t) {
		dao.remove(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer codigo) {
		String hql = "DELETE FROM Funcionario WHERE codigo = :codigo";
		Query query = manager.createQuery(hql);
		query.setParameter("codigo", codigo); //verificar esse id
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Funcionario t) {
		dao.atualiza(t);
	}
	
	public List<Funcionario> listaTodos(){
		return dao.listaTodos();
	}
	
	public Funcionario buscaPorCodigo(Integer codigo) {
		return dao.buscaPorCodigo(codigo);
	}
	
	public void comitarCache() {
		dao.comitarCache();
	}
	
}
