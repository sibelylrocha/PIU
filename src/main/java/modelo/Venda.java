package modelo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Venda")
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int Id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Temporal(TemporalType.DATE)
	@Column
	private Date Data;
	
	@Column
	private Time Hora;
	
	@NegativeOrZero
	@NotEmpty
	@Column
	private double Valor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_pagamento", referencedColumnName = "Id")
	private Pagamento pagamento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_cliente", referencedColumnName = "Id")
	private Cliente cliente;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Id_produto", joinColumns = { @JoinColumn(name = "Id_Venda") }, inverseJoinColumns = {
			@JoinColumn(name = "Id") })
	private List<Produto> Produto;

	public Venda() {

	}

	public Venda(int Id, Date Data, Time Hora, double Valor) {
		this.Id = Id;
		this.Data = Data;
		this.Hora = Hora;
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
		return Produto;
	}

	public void setProduto(List<Produto> produto) {
		Produto = produto;
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

	public Time getHora() {
		return Hora;
	}

	public void setHora(Time Hora) {
		this.Hora = Hora;
	}

	public double getValor() {
		return Valor;
	}

	public void setValor(double Valor) {
		this.Valor = Valor;
	}
}
