package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;
	@NotNull
	@Size(min = 5, max = 100, message = "O logradouro deve conter mais de 5 caracteres!")
	private String logradouro;
	@NotNull
	@Size(min = 5, max = 100, message = "O Bairro deve conter mais de 5 caracteres!")
	private String bairro;
	@NotNull
	@Size(max = 8, message = "Digite o cep corretamente!")
	private String cep;
	private String numero;
	@ManyToOne
	@JoinColumn(name = "id_tipo_endereco")
	private TipoEndereco idTipoEndereco;
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade idCidade;
	private String criadoEm;
	private String atualizadoEm;
	

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
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
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
