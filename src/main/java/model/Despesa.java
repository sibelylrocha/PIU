package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "DESPESA")
@JsonInclude(Include.NON_EMPTY) //verificar anota��o
public class Despesa implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_DESPESA")
	private Integer codigo;
	
	@NotBlank(message = "Descri��o � obrigat�rio!")
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@NotBlank(message = "VALOR � obrigat�rio!")
	@Column(name = "VALOR", nullable = false)
	private double valor;
		
	@NotBlank(message = "A DATA DE VENCIMENTO � obrigat�rio!")
	@Column(name = "DATA_VENCIMENTO)", nullable = false)
	private String dataVencimento;
	
	@Column(name = "TOTAL_DESPESA)", nullable = false)
	private double totalDespesa;
	
	@Column(name = "DATA", nullable = false)
	private Date data;
	
	public Despesa() {}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public double getTotalDespesa() {
		return totalDespesa;
	}
	
	public void setTotalDespesa(double totalDespesa) {
		this.totalDespesa = totalDespesa;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	

}
