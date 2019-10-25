package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.AgendamentoServico;
import lumicode.agendaja.api.model.Servico;

public interface AgendamentoServicoRepository 
	extends JpaRepository<AgendamentoServico, Long>{

	@Query("SELECT a.servico FROM AgendamentoServico as a "
			+ "INNER JOIN a.agendamento as ag WHERE ag.idAgendamento = ?1")
	public List<Servico> pegarAgendamentoServico(Long idAgendamento);
	
}
