package modelo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Venda")
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Temporal(TemporalType.DATE)
	@Column
	private Date Data;

	@PositiveOrZero
	@Column
	private double Valor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_pagamento", referencedColumnName = "Id")
	private Pagamento pagamento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_cliente", referencedColumnName = "Id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venda")
	private List<Produto> produto;

	public Venda() {

	}

	public Venda(int Id, Date Data, Time Hora, double Valor, List<Produto> produto) {
		this.Id = Id;
		this.produto = produto;
		this.Data = Data;
		this.Valor = Valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date Data) {
		this.Data = Data;
	}

	public double getValor() {
		return Valor;
	}

	public void setValor(double Valor) {
		this.Valor = Valor;
	}
}
