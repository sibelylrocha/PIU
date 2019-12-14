package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Venda")
@JsonInclude(Include.NON_EMPTY)
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_VENDA")
	private Integer codigo;
	
	@Column(name = "DESCONTO", nullable = false, length = 45)
	private double desconto;
	
	@Column(name = "VALOR_TOTAL", nullable = false, length = 45)
	private double valorTotal;
	
	@Column(name = "DATA", nullable = false, length = 45)
	private Date data;
	
	@Column(name = "QUANTIDADE", nullable = false, length = 45) //N�o est� no banco
	private int quantidade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Cod_Cliente", referencedColumnName = "COD_CLIENTE")
	private Cliente cliente;
	
	public Venda() {}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	

}
