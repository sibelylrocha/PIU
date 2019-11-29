package dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Venda;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VendaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private DAO<Venda> dao;
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	public VendaDAO() {
		
	}

	public VendaDAO(EntityManager manager){
		this.dao = new DAO<Venda>(manager, Venda.class);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Venda Cadastrar(Venda t) {
		return dao.Cadastrar(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Excluir(Venda t) {
		dao.Excluir(t);;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Venda> listaVendas() {
		return dao.listaTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Venda ConsultarVenda(int Id) {
		return dao.ConsultarVenda(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Venda Atualiza(Venda t) {
		return dao.atualizar(t);
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

