package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_agendamento_servico")
public class AgendamentoServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgendamentoServico;
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;
	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	private Agendamento agendamento;

	public Long getIdAgendamentoServico() {
		return idAgendamentoServico;
	}

	public void setIdAgendamentoServico(Long idAgendamentoServico) {
		this.idAgendamentoServico = idAgendamentoServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	
	
}
