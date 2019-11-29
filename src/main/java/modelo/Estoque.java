package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Estoque")
@JsonInclude(Include.NON_EMPTY)
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Descrição é obrigatório!")
	@Column(nullable = false)
	private String Descricao;
	
	@Id
	@Column
	private int Id;
	
	@OneToMany(mappedBy = "estoque")
	private List<Produto> Produtos;

	@OneToMany(mappedBy = "estoque")
	private List<Peca> Pecas;

	public Estoque() {
	}

	public Estoque(String Descricao, int Id, Empresa Empresa) {
		this.Id = Id;
		this.Descricao = Descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescricao() {
		return Descricao;
	}

	public List<Produto> getProdutos() {
		return Produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		Produtos = produtos;
	}

	public List<Peca> getPecas() {
		return Pecas;
	}

	public void setPecas(List<Peca> pecas) {
		Pecas = pecas;
	}

	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}
}
