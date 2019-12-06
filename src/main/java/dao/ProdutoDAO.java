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
import modelo.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProdutoDAO implements Serializable{
	private static final long serialVersionUID = 1L;

		private DAO<Produto> dao;
		@PersistenceContext(unitName = "Projeto")
		private EntityManager manager;
		
		public ProdutoDAO() {
		
		}

		public ProdutoDAO(EntityManager manager){
			this.dao = new DAO<Produto>(manager, Produto.class);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<Produto> listarProduto() {
			return dao.listaTodos();
		}
		
		@PostConstruct
		private void initDao() {
			this.dao = new DAO<Produto>(manager, Produto.class);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Cadastrar(Produto t) {
			dao.Cadastrar(t);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Excluir(Produto t) throws Exception{
			dao.Excluir(t);;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void Atualiza(Produto t) throws Exception{
			dao.atualizar(t);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Produto ConsultarProdutoPorId(int Id) {
			return dao.ConsultarProdutoPorId(Id);
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
