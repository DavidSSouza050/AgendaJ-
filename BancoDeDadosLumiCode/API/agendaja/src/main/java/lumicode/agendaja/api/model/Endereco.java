package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdEndereco;
	@NotNull
	@Size(min = 5, max = 100, message = "O logradouro deve conter mais de 5 caracteres!")
	private String logradouro;
	@NotNull
	@Size(min = 5, max = 100, message = "O Bairrodeve conter mais de 5 caracteres!")
	private String bairro;
	private String numero;
	@ManyToOne
	@JoinColumn(name = "id_tipo_endereco")
	private TipoEndereco idTipoEndereco;
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade idCidade;

	
	public Long getIdEndereco() {
		return IdEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		IdEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoEndereco getIdTipoEndereco() {
		return idTipoEndereco;
	}

	public void setIdTipoEndereco(TipoEndereco idTipoEndereco) {
		this.idTipoEndereco = idTipoEndereco;
	}

	public Cidade getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Cidade idCidade) {
		this.idCidade = idCidade;
	}
	
	

	

	
	
}
