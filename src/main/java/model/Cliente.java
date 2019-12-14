package model;

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
@Table(name = "CLIENTE")
@JsonInclude(Include.NON_EMPTY)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_CLIENTE")
	private Integer codigo;
	
	@NotBlank(message = "O NOME � obrigat�rio!")
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@NotBlank(message = "O TELEFONE � obrigat�rio!")
	@Column(name = "TELEFONE", nullable = false)
	private String telefone;
	
	@NotBlank(message = "O CPF � obrigat�rio!")//verificar
	@Column(name = "CPF", nullable = false)
	private String cpf;
	
	@NotBlank(message = "O CNPJ � obrigat�rio!")//verificar
	@Column(name = "CNPJ", nullable = false)
	private String cnpj;
	
	@Column(name = "SALDO_DEVEDOR", nullable = false) //verificar anota��o
	private double saldoDevedor;
	
	@Column(name = "RUA")
	private String rua;
	
	@Column(name = "NUMERO")
	private int numero;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "CEP")
	private String cep;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@OneToMany(mappedBy = "cliente")
	private List<Venda> Vendas;
	
	public Cliente() {}
		
	public String getRua() {
		return rua;
	}

	public List<Venda> getVendas() {
		return Vendas;
	}

	public void setVendas(List<Venda> vendas) {
		Vendas = vendas;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public double getSaldoDevedor() {
		return saldoDevedor;
	}
	
	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
	
	public void atualizarCampos(Cliente cliente) {
		if(cliente == null) return;
		
		if(cliente.getNome()!= null) {
			this.setNome(cliente.getNome());
		}
		if(cliente.getTelefone() != null) {
			this.setTelefone(cliente.getTelefone());
		}
		if(cliente.getCpf() != null) {
			this.setCpf(cliente.getCpf());
		}
		if(cliente.getCnpj() != null) {
			this.setCnpj(cliente.getCnpj());
		}
	}
}
