package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Funcionario")
@JsonInclude(Include.NON_EMPTY)
public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_FUNCIONARIO")
	private Integer codigo;
	
	@NotBlank(message = "O NOME � obrigat�rio!")
	@Column(name = "NOME", nullable = false, length = 45)
	private String nome;
	
	@NotBlank(message = "O TELEFONE � obrigat�rio!")
	@Column(name = "TELEFONE", nullable = false, length = 15)
	private String telefone;
	
	@NotBlank(message = "A SENHA � obrigat�ria!")
	@Column(name = "SENHA", nullable = false , unique = true, length = 6)
	private String senha;
	
	public Funcionario() {}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@JsonIgnore
	public String getSenha() {
		return senha;
	}
	
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/*public String toString() {
		return this.getNome() + ", " + this.getUsername();
	}
	*/ //verificar
	
	public void atualizarCampos(Funcionario funcionario) {
		if(funcionario == null) return;
		
		if(funcionario.getNome()!= null) {
			this.setNome(funcionario.getNome());
		}
		if(funcionario.getTelefone() != null) {
			this.setTelefone(funcionario.getTelefone());
		}
		if(funcionario.getSenha() != null) {
			this.setSenha(funcionario.getSenha());
		}
	}
}
