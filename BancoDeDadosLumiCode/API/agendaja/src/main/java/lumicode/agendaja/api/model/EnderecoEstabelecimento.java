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
@Table (name = "tbl_endereco_estabelecimento")
public class EnderecoEstabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEnderecoEstabelecimento;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private Estabelecimento estabelecimento;

	public Long getIdEnderecoEstabelecimento() {
		return idEnderecoEstabelecimento;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}




	public void setIdEnderecoEstabelecimento(Long idEnderecoEstabelecimento) {
		this.idEnderecoEstabelecimento = idEnderecoEstabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}



	
	
	
	
	
}
