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
import modelo.Pagamento;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PagamentoDAO implements Serializable{
	private static final long serialVersionUID = 1L;

		private DAO<Pagamento> dao;
		
		@PersistenceContext(unitName = "Projeto")
		private EntityManager manager;
		
		public PagamentoDAO() {
			
		}

		public PagamentoDAO(EntityManager manager){
			this.dao = new DAO<Pagamento>(manager, Pagamento.class);
		}
		
		@PostConstruct
		private void initDao() {
			this.dao = new DAO<Pagamento>(manager, Pagamento.class);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Cadastrar(Pagamento t) {
			dao.Cadastrar(t);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Excluir(Pagamento t) throws Exception{
			dao.Excluir(t);;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Pagamento Atualiza(Pagamento t) throws Exception{
			return dao.atualizar(t);
		}
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<Pagamento> listaTodos() {
			return dao.listaTodos();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Pagamento ConsultarPagamentoPorId(Integer Id) {
			return dao.ConsultarPagamentoPorId(Id);
		}
		public void close() {
			this.dao.close();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removePorId(Integer Id) {
			String hql = "DELETE FROM Pagamento WHERE Id = :Id";
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
