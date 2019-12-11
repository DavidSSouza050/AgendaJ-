package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;
import lumicode.agendaja.api.model.dto.FuncionarioDTO;

@Entity
@Table(name = "tbl_funcionario_estabelecimento")
public class FuncionarioEstabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionarioEstabelecimento;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private FuncionarioDTO funcionario;
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private EstabelecimentoDTO estabelecimento;

	

	public Long getIdFuncionarioEstabelecimento() {
		return idFuncionarioEstabelecimento;
	}

	public void setIdFuncionarioEstabelecimento(Long idFuncionarioEstabelecimento) {
		this.idFuncionarioEstabelecimento = idFuncionarioEstabelecimento;
	}
	
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	
	
	
	
	
}
