package modelo;

import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "Pagamento")
@JsonInclude(Include.NON_EMPTY)
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Temporal(TemporalType.DATE)
	@Column
	private Date Data;
	
	@Column(nullable = false)
	private String TipoForma;
	
	@PositiveOrZero
	@Column
	private double Valor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_ordem", referencedColumnName = "Id")
	private OrdemServico ordemservico;

	@OneToMany(mappedBy = "pagamento")
	private List<Venda> Vendas;

	public Pagamento() {

	}

	public Pagamento(Date Data, String TipoForma, double Valor, int Id, Cliente Cliente, Venda Codigo) {
		this.Valor = Valor;
		this.Id = Id;
		this.Data = Data;
		this.TipoForma = TipoForma;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Venda> getVendas() {
		return Vendas;
	}

	public void setVendas(List<Venda> vendas) {
		Vendas = vendas;
	}

	public OrdemServico getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServico ordemservico) {
		this.ordemservico = ordemservico;
	}

	public double getValor() {
		return Valor;
	}

	public void setValor(double Valor) {
		this.Valor = Valor;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date Data) {
		this.Data = Data;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getTipoForma() {
		return TipoForma;
	}

	public void setTipoForma(String TipoForma) {
		this.TipoForma = TipoForma;
	}
}
