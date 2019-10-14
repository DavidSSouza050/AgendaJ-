package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_funcionario_servico")
public class FuncionarioServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionarioServico;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcinario;
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;

	public Long getIdFuncionarioServico() {
		return idFuncionarioServico;
	}

	public void setIdFuncionarioServico(Long idFuncionarioServico) {
		this.idFuncionarioServico = idFuncionarioServico;
	}

	public Funcionario getFuncinario() {
		return funcinario;
	}

	public void setFuncinario(Funcionario funcinario) {
		this.funcinario = funcinario;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	
	
	

}
