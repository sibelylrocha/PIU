package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager manager, Class<T> classe) {
		this.em = manager;
		this.classe = classe;
	}

	public void Cadastrar(T t) {
		System.out.println("oi");
		em.persist(t);
	}

	public void Excluir(T t) throws Exception {
		em.getTransaction().begin();
		try{
			em.remove(em.merge(t));
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}
	}
	public void comitarCache() {
		em.flush();
	}
	
	public void atualizar(T t)  {
			em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}

	public T ConsultarClientePorCpf(String Cpf) {
		T instancia = em.find(classe, Cpf);
		return instancia;
	}
	public T ConsultarEmpresa(String Cnpj) {
		T instancia = em.find(classe, Cnpj);
		return instancia;
	}
	public T ConsultarVenda(int Id) {
		T instancia = em.find(classe, Id);
		return instancia;
	}
	public T ConsultarPeca(int Id) {
		T instancia = em.find(classe, Id);
		return instancia;
	}
	public T ConsultarClientePorCnpj(String Cnpj) {
		T instancia = em.find(classe, Cnpj);
		return instancia;
	}
	public T ConsultarOrdemServico(int Id) {
		T instancia = em.find(classe, Id);
		return instancia;
	}
	public T ConsultarProdutoPorId(int Id) {
		T instancia = em.find(classe, Id);
		return instancia;
	}
	public T ConsultarPagamentoPorId(int Id) {
		T instancia = em.find(classe, Id);
		return instancia;
	}
	public T ConsultarProblemaRelatado(int Protocolo) {
		T instancia = em.find(classe, Protocolo);
		return instancia;
	}
	public T ConsultarProblemaRelatadoPorProtocolo(int Protocolo) {
		T instancia = em.find(classe, Protocolo);
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}
	public T buscaPorId(Integer Id) {
		T instancia = em.find(classe, Id);
		return instancia;
	}
	
	public void close(){
		this.em.close();
	}


}
