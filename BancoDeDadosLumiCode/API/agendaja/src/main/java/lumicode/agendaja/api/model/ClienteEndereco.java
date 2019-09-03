package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_cliente_endereco")
public class ClienteEndereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClienteEndereco;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente idCliente;
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco idEndereco;
	private String criadoEm;
	private String atualizadoEm;
	
	
	public Long getIdClienteEndereco() {
		return idClienteEndereco;
	}

	public void setIdClienteEndereco(Long idClienteEndereco) {
		this.idClienteEndereco = idClienteEndereco;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public Endereco getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Endereco idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	
	
	
	
}
