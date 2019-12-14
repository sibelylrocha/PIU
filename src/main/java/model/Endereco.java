package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "A RUA � obrigat�ria!")
	@Column(name = "RUA", nullable = false)
	private String rua;
	
	@NotBlank(message = "O NUMERO � obrigat�rio!")
	@Column(name = "NUMERO", nullable = false)
	private String numero;
	
	@NotBlank(message = "O BAIRRO � obrigat�rio!")
	@Column(name = "BAIRRO", nullable = false)
	private String bairro;
	
	@NotBlank(message = "A CIDADE � obrigat�rio!")
	@Column(name = "CIDADE", nullable = false)
	private String cidade;
	
	@NotBlank(message = "O ESTADO � obrigat�rio!")
	@Column(name = "ESTADO", nullable = false)
	private String estado;
	
	@NotBlank(message = "O CEP � obrigat�rio!")
	@Column(name = "CEP", nullable = false)
	private String cep;
	
	public Endereco() {}
		
	public String getRua() {
		return rua;
	}
		
	public void setRua(String rua) {
		this.rua = rua;
	}
		
	public String getNumero() {
		return numero;
	}
		
	public void setNumero(String numero) {
		this.numero = numero;
	}
		
	public String getBairro() {
		return bairro;
	}
		
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
		
	public String getCidade() {
		return cidade;
	}
		
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
		
	public String getEstado() {
		return estado;
	}
		
	public void setEstado(String estado) {
		this.estado = estado;
	}
		
	public String getCep() {
		return cep;
	}
		
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public void atualizarCampos(Endereco endereco) {
		if(endereco == null) return;
		
		if(endereco.getRua()!= null) {
			this.setRua(endereco.getRua());
		}
		if(endereco.getNumero() != null) {
			this.setNumero(endereco.getNumero());
		}
		if(endereco.getBairro() != null) {
			this.setBairro(endereco.getBairro());
		}
		if(endereco.getCidade() != null) {
			this.setCidade(endereco.getCidade());
		}
		if(endereco.getEstado() != null) {
			this.setEstado(endereco.getEstado());
		}
		if(endereco.getCep() != null) {
			this.setCep(endereco.getCep());
		}
	}	
}
