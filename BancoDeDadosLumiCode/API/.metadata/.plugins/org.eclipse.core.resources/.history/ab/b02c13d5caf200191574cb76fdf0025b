package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_funcionario_estabelecimento")
public class FuncionarioEstabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionarioEsatabelecimento;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private Estabelecimento estabelecimento;

	public Long getIdFuncionarioEsatabelecimento() {
		return idFuncionarioEsatabelecimento;
	}

	public void setIdFuncionarioEsatabelecimento(Long idFuncionarioEsatabelecimento) {
		this.idFuncionarioEsatabelecimento = idFuncionarioEsatabelecimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	
	
	
}
