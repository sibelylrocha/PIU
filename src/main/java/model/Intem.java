package model;

import java.io.Serializable;

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
@Table(name = "Item")
@JsonInclude(Include.NON_EMPTY)
public class Intem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_ITEM")
	private int codigo;
	
	@NotBlank(message = "A DESCRICAO � obrigat�ria!")
	@Column(name = "DESCRICAO", nullable = false, length = 45)
	private String descricao;
	
	@NotBlank(message = "O VALOR � obrigat�rio!")
	@Column(name = "VALOR", nullable = false, length = 45)
	private double valor;
	
	@NotBlank(message = "o TIPO � obrigat�rio!")
	@Column(name = "TOPO", nullable = false, length = 45) //verificar anota��o
	private boolean tipo; //Servi�o ou Produto
	
	public Intem() {}
	
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
	
	public boolean isTipo() {
		return tipo;
	}
	
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	
	public void atualizarCampos(Intem item) {
		if(item == null) return;
		
		if(item.getDescricao()!= null) {
			this.setDescricao(item.getDescricao());
		}
		if(item.getValor() != 0) {  //(item.getValor() != null)
			this.setValor(item.getValor());
		}
		if(item.isTipo() != true || false) { //(item.getTipo() != null)
			this.setTipo(item.isTipo());
		}
	}
	
}
