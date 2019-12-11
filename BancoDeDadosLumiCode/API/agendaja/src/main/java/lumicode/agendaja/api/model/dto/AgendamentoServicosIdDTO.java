package lumicode.agendaja.api.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_agendamento_servico")
public class AgendamentoServicosIdDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdAgendamentoServico;
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private ServicoIdDTO servico;
	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	private AgendamentoIdDTO agendamento;

	public Long getIdAgendamentoServico() {
		return IdAgendamentoServico;
	}

	public void setIdAgendamentoServico(Long idAgendamentoServico) {
		IdAgendamentoServico = idAgendamentoServico;
	}

	public ServicoIdDTO getServico() {
		return servico;
	}

	public void setServico(ServicoIdDTO servico) {
		this.servico = servico;
	}

	public AgendamentoIdDTO getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(AgendamentoIdDTO agendamento) {
		this.agendamento = agendamento;
	}
	
}
