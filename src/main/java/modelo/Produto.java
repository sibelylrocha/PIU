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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tipo é obrigatório!")
	@Column(nullable = false)
	private String Tipo;
	
	@NotBlank(message = "Descrição é obrigatório!")
	@Column(nullable = false)
	private String Descricao;
	
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

	@ManyToMany(mappedBy = "Produto", cascade = CascadeType.ALL)
	private List<Venda> Vendas;

	public Produto() {
	}

	public Produto(String Tipo, String Descricao, String Marca, int Id) {
		this.Id = Id;
		this.Marca = Marca;
		this.Tipo = Tipo;
		this.Descricao = Descricao;
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

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
	}

}
