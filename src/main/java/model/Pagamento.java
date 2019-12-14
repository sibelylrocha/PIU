package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Pagamento")
@JsonInclude(Include.NON_EMPTY)
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDEM_PAGAMENTO")
	private Integer ordemPagamento;
	
	/*@EmbeddedId
	private PagamentoPK pagamentoPK;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEDIDO_ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
	@MapsId("idPedido")
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUTO_ID_PRODUTO", referencedColumnName = "ID_PRODUTO")
	@MapsId("idProduto")
	private Produto produto;
	*/
	
	@Column(name = "VALOR", nullable = false, length = 45)
	private double valor;
	
	@Column(name = "FORMA_PAGAMENTO", nullable = false, length = 45) //verificar anota��o
	private boolean formPagamento;
	
	@Column(name = "DATA_PAGAMENTO", nullable = false, length = 45) //verificar anota��o
	private Date dataPagamento;
	
	public Pagamento() {}
	
	public Integer getOrdemPagamento() {
		return ordemPagamento;
	}
	
	public void setOrdemPagamento(Integer ordemPagamento) {
		this.ordemPagamento = ordemPagamento;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public boolean isFormPagamento() {
		return formPagamento;
	}
	
	public void setFormPagamento(boolean formPagamento) {
		this.formPagamento = formPagamento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

}
