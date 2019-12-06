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
import modelo.OrdemServico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrdemServicoDAO implements Serializable{
	private static final long serialVersionUID = 1L;

		private DAO<OrdemServico> dao;
		
		@PersistenceContext(unitName = "Projeto")
		private EntityManager manager;
		
		public OrdemServicoDAO() {
			
		}

		public OrdemServicoDAO(EntityManager manager){
			this.dao = new DAO<OrdemServico>(manager, OrdemServico.class);
		}

		@PostConstruct
		private void initDao() {
			this.dao = new DAO<OrdemServico>(manager, OrdemServico.class);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Cadastrar(OrdemServico t) {
			dao.Cadastrar(t);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Excluir(OrdemServico t) throws Exception{
			dao.Excluir(t);;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Atualiza(OrdemServico t) throws Exception{
			dao.atualizar(t);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<OrdemServico> listaTodos() {
			return dao.listaTodos();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public OrdemServico ConsultarOrdemServico(int Id) {
			return dao.ConsultarOrdemServico(Id);
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
