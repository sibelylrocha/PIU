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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "OrdemServico")
public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	private double ValorTotal;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Temporal(TemporalType.DATE)
	@Column
	private Date Data;
	
	@NotBlank(message = "Descrição é obrigatório!")
	@Column(nullable = false)
	private String Descricao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Cnpj_empresa", referencedColumnName = "Cnpj")
	private Empresa empresa;

	@OneToMany(mappedBy = "ordemservico")
	private List<ProblemaRelatado> ProblemasRelatado;

	@OneToMany(mappedBy = "ordemservico")
	private List<Pagamento> Pagamentos;

	@ManyToMany(mappedBy = "ordemservico", cascade = CascadeType.ALL)
	private List<Peca> Pecas;

	public OrdemServico() {

	}

	public OrdemServico(Double ValorTotal, Date Data, String Descricao, int Id) {
		this.Id = Id;
		this.Data = Data;
		this.Descricao = Descricao;
		this.ValorTotal = ValorTotal;
	}

	public List<Peca> getPecas() {
		return Pecas;
	}

	public void setPecas(List<Peca> pecas) {
		Pecas = pecas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ProblemaRelatado> getProblemasRelatado() {
		return ProblemasRelatado;
	}

	public void setProblemasRelatado(List<ProblemaRelatado> problemasRelatado) {
		ProblemasRelatado = problemasRelatado;
	}

	public List<Pagamento> getPagamentos() {
		return Pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		Pagamentos = pagamentos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public double getValorTotal() {
		return ValorTotal;
	}

	public void setValorTotal(double ValorTotal) {
		this.ValorTotal = ValorTotal;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
	}

}
