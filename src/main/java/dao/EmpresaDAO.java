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
import javax.persistence.TypedQuery;

import modelo.Empresa;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmpresaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private DAO<Empresa> dao;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	
	public EmpresaDAO() {
		
	}

	public EmpresaDAO(EntityManager manager){
		this.dao = new DAO<Empresa>(manager, Empresa.class);
	}
	
	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Empresa>(manager, Empresa.class);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Empresa t) {
		 dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Empresa t) throws Exception{
		dao.Excluir(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Empresa t) throws Exception{
		dao.atualizar(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Empresa> listaTodos() {
		return dao.listaTodos();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarPeca(int Id) {
		return dao.ConsultarPeca(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarEmpresa(String Cnpj) {
		return dao.ConsultarEmpresa(Cnpj);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarClientePorCnpj(String Cnpj) {
		return dao.ConsultarClientePorCnpj(Cnpj);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarProblemaRelatado(Integer Protocolo) {
		return dao.ConsultarProblemaRelatado(Protocolo);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarClientePorCpf(String Cpf) {
		return dao.ConsultarClientePorCpf(Cpf);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarOrdemServico(int Id) {
		return dao.ConsultarOrdemServico(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarProdutoPorSerie(int Id) {
		return dao.ConsultarProdutoPorId(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarVenda(int Id) {
		return dao.ConsultarVenda(Id);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa ConsultarPagamentoPorId(int Id) {
		return dao.ConsultarProdutoPorId(Id);
	}
	public void close() {
		this.dao.close();
	}
	
	public Empresa loginEmpresa(String Password , String UserName)  {
	try {
		String sql = "select e from empresa e where e.Password =:Password and e.UserName =:UserName";
		TypedQuery<Empresa> query = manager.createQuery(sql , Empresa.class);
		query.setParameter("Password",Password);
		query.setParameter("UserName",UserName);
		Empresa empresa = query.getSingleResult();
		if(empresa != null && empresa.getUserName().equals(UserName) && empresa.getPassword().equals(Password)) {
			return empresa;
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}	
	return null;
}

	public boolean removePorCnpj(String Cnpj) {
		String hql = "DELETE FROM Empresa WHERE Cnpj = :Cnpj";
		Query query = manager.createQuery(hql);
		query.setParameter("Cnpj", Cnpj);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	public void comitarCache() {
		dao.comitarCache();
	}

}
