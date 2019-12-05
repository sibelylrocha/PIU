package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "ProblemaRelatado")
@JsonInclude(Include.NON_EMPTY)
public class ProblemaRelatado implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Descrição é obrigatório!")
	@Column(nullable = false)
	private String Descricao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Protocolo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_ordem", referencedColumnName = "Id")
	private OrdemServico ordemservico;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_cliente", referencedColumnName = "Id")
	private Cliente cliente;

	public ProblemaRelatado() {

	}

	public ProblemaRelatado(String Descricao, int Protocolo) {
		this.Descricao = Descricao;
		this.Protocolo = Protocolo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String Descricao) {
		this.Descricao = Descricao;
	}

	public int getProtocolo() {
		return Protocolo;
	}

	public void setProtocolo(int Protocolo) {
		this.Protocolo = Protocolo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OrdemServico getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServico ordemservico) {
		this.ordemservico = ordemservico;
	}

}
