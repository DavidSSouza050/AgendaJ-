package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cliente_servico")
public class ClienteServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClienteServico;
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	public Long getIdClienteServico() {
		return idClienteServico;
	}

	public void setIdClienteServico(Long idClienteServico) {
		this.idClienteServico = idClienteServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
