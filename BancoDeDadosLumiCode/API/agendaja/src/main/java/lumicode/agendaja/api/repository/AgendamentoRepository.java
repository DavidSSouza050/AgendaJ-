package lumicode.agendaja.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Agendamento;

public interface AgendamentoRepository
	extends JpaRepository<Agendamento, Long>{
	

	@Query("SELECT a from Agendamento a WHERE a.idAgendamento = ?1")
	public Agendamento  pegarAgendamento(Long idAgendamento);
	
	
	
	
}
