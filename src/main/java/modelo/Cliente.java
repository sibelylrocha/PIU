package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@Entity
@Table(name = "Cliente")
@JsonInclude(Include.NON_EMPTY)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	private String Telefone;
	
	@NotBlank(message = "Nome é obrigatório!")
	@Column(nullable = false)
	private String Nome;
	
	@NotBlank(message = "Endereço é obrigatório!")
	@Column(nullable = false)
	private String Endereco;
	
	@NotBlank(message = "Cpf é obrigatório!")
	@Column(nullable = false)
	private String Cpf;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer Id;

	@OneToMany(mappedBy = "cliente")
	private List<ProblemaRelatado> ProblemasRelatados;

	@OneToMany(mappedBy = "cliente")
	private List<Venda> Vendas;

	public Cliente() {

	}

	public Cliente(String Telefone, String Nome, String Endereco, String Cpf, int Id) {
		this.Nome = Nome;
		this.Telefone = Telefone;
		this.Id = Id;
		this.Endereco = Endereco;
		this.Cpf = Cpf;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String Cpf) {
		this.Cpf = Cpf;
	}

	public List<Venda> getVendas() {
		return Vendas;
	}

	public void setVendas(List<Venda> vendas) {
		Vendas = vendas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ProblemaRelatado> getProblemasRelatados() {
		return ProblemasRelatados;
	}

	public void setProblemasRelatados(List<ProblemaRelatado> problemasRelatados) {
		ProblemasRelatados = problemasRelatados;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String Telefone) {
		this.Telefone = Telefone;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String Endereco) {
		this.Endereco = Endereco;
	}

	@Override
	public String toString() {
		return "Cliente [Telefone=" + Telefone + ", Nome=" + Nome + ", Endereco=" + Endereco + ", Cpf=" + Cpf + ", Id="
				+ Id + "]";
	}
	
	public void atualizarCampos(Cliente cliente) {
		if(cliente == null) return;
		
		if(cliente.getNome()!= null) {
			this.setNome(cliente.getNome());
		}
		if(cliente.getTelefone()!= null) {
			this.setTelefone(cliente.getTelefone());
		}
		if(cliente.getEndereco()!= null) {
			this.setEndereco(cliente.getEndereco());
		}
		if(cliente.getCpf()!= null) {
			this.setCpf(cliente.getCpf());
		}
	}
}
