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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Peca")
@JsonInclude(Include.NON_EMPTY)
public class Peca implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Descrição é obrigatório!")
	@Column(nullable = false)
	private String Descricao;
	
	@PositiveOrZero
	@Column
	private double Valor;
	
	@PositiveOrZero
	@Column
	private int Quantidade;
	
	@NotBlank(message = "Marca é obrigatório!")
	@Column(nullable = false)
	private String Marca;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_estoque", referencedColumnName = "Id")
	private Estoque estoque;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Ordem_Peca", joinColumns = { @JoinColumn(name = "Id_peca") }, inverseJoinColumns = {
			@JoinColumn(name = "Id_ordem") })
	private List<OrdemServico> ordemservico;

	public Peca() {

	}

	public Peca(String Descricao, double Valor, int Quantidade, String Marca, int Id) {
		this.Id = Id;
		this.Marca = Marca;
		this.Quantidade = Quantidade;
		this.Valor = Valor;
		this.Descricao = Descricao;
	}

	public List<OrdemServico> getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(List<OrdemServico> ordemservico) {
		this.ordemservico = ordemservico;
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

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String Marca) {
		this.Marca = Marca;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int Quantidade) {
		this.Quantidade = Quantidade;
	}

	public double getValor() {
		return Valor;
	}

	public void setValor(double Valor) {
		this.Valor = Valor;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
	}

}
