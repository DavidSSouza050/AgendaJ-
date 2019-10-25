package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lumicode.agendaja.api.model.dto.ClienteDTO;

@Entity
@Table(name = "tbl_endereco_cliente")
public class EnderecoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEnderecoCliente;
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private ClienteDTO cliente;

	public Long getIdEnderecoCliente() {
		return idEnderecoCliente;
	}

	public void setIdEnderecoCliente(Long idEnderecoCliente) {
		this.idEnderecoCliente = idEnderecoCliente;
	}

	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
}
