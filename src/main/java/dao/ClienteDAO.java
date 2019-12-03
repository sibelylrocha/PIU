package dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Cliente;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Cliente> dao;
	
	public ClienteDAO() {}

	public void ClienteDAO(EntityManager manager){
		dao = new DAO<Cliente>(manager, Cliente.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Cliente>(manager, Cliente.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Cliente t) {
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Cliente cliente) throws Exception{
		dao.Excluir(cliente);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente atualiza(Cliente t) throws Exception{
		return dao.atualizar(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cliente> listaTodos() {
		return dao.listaTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente ConsultarClientePorCpf(String Cpf) {
		return dao.ConsultarClientePorCpf(Cpf);
	}
	

	public void close() {
		this.dao.close();
	}
	public void comitarCache() {
		dao.comitarCache();
	}

}
