package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Empresa")
@JsonInclude(Include.NON_EMPTY)
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "UserName é obrigatório!")
	@Column(nullable = false)
	private String UserName;
	
	@NotBlank(message = "Password é obrigatório!")
	@Column(nullable = false)
	private String Password;
	
	@NotBlank(message = "Nome é obrigatório!")
	@Column(nullable = false)
	private String Nome;
	
	@Column
	private String Telefone;
	
	@NotBlank(message = "Cnpj é obrigatório!")
	@Column(nullable = false)
	private String Cnpj;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer Id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_estoque", referencedColumnName = "Id")
	private Estoque estoque;

	@OneToMany(mappedBy = "empresa")
	private List<OrdemServico> OrdemServicos;

	public Empresa() {

	}

	public Empresa(String UserName, String Password, String Nome, String Telefone, String Cnpj, Integer Id) {
		this.Cnpj = Cnpj;
		this.Id = Id;
		this.Nome = Nome;
		this.Telefone = Telefone;
		this.UserName = UserName;
		this.Password = Password;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<OrdemServico> getOrdemServicos() {
		return OrdemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		OrdemServicos = ordemServicos;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String Cnpj) {
		this.Cnpj = Cnpj;
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

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

}
