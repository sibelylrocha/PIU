package dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Estoque;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EstoqueDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
		private DAO<Estoque> dao;
		
		
		
		public EstoqueDAO() {
		}

		@PersistenceContext(unitName = "Projeto")
		private EntityManager manager;
		
		public EstoqueDAO(EntityManager manager){
			this.dao = new DAO<Estoque>(manager, Estoque.class);
		}

		@PostConstruct
		private void initDao() {
			this.dao = new DAO<Estoque>(manager, Estoque.class);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Cadastrar(Estoque t) {
			dao.Cadastrar(t);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Excluir(Estoque t) throws Exception{
			dao.Excluir(t);;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Estoque Atualiza(Estoque t) throws Exception{
			return dao.atualizar(t);
		}
		public void close() {
			this.dao.close();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removePorId(Integer Id) {
			String hql = "DELETE FROM Estoque WHERE Id = :Id";
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
