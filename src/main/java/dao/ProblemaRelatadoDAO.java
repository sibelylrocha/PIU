package dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.ProblemaRelatado;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProblemaRelatadoDAO implements Serializable{
	private static final long serialVersionUID = 1L;

		private DAO<ProblemaRelatado> dao;
		@PersistenceContext(unitName = "Projeto")
		private EntityManager manager;
		
		
		public ProblemaRelatadoDAO() {
			
		}

		public ProblemaRelatadoDAO(EntityManager manager){
			dao = new DAO<ProblemaRelatado>(manager, ProblemaRelatado.class);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public ProblemaRelatado Cadastrar(ProblemaRelatado t) {
			return dao.Cadastrar(t);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public ProblemaRelatado Atualiza(ProblemaRelatado t) {
			return dao.atualizar(t);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Remove(ProblemaRelatado t) {
			dao.Excluir(t);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ProblemaRelatado> listaTodos() {
			return dao.listaTodos();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public ProblemaRelatado ConsultarProblemaRelatado(Integer Protocolo) {
			return dao.ConsultarProblemaRelatado(Protocolo);
		}
		public boolean removePorProtocolo(Integer Protocolo) {
			String hql = "DELETE FROM ProblemaRelatado WHERE Protocolo = :Protocolo";
			Query query = manager.createQuery(hql);
			query.setParameter("Protocolo", Protocolo);
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
