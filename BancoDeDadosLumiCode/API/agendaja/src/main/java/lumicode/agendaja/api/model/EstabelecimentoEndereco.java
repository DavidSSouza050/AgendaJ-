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
@Table(name = "tbl_estabelecimento_endereco")
public class EstabelecimentoEndereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstabelecimentoEndereco;
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private Estabelecimento idEstabelecimento;
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco idEndereco;
	@NotNull
	private String criadoEM;
	private String atualizadoEm;
	

	
	public Long getIdEstabelecimentoEndereco() {
		return idEstabelecimentoEndereco;
	}

	public void setIdEstabelecimentoEndereco(Long idEstabelecimentoEndereco) {
		this.idEstabelecimentoEndereco = idEstabelecimentoEndereco;
	}

	public Estabelecimento getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Estabelecimento idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public Endereco getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Endereco idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCriadoEM() {
		return criadoEM;
	}

	public void setCriadoEM(String criadoEM) {
		this.criadoEM = criadoEM;
	}

	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	
	
	
}
