package lumicode.agendaja.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Agendamento;

public interface AgendamentoRepository
	extends JpaRepository<Agendamento, Long>{
	

	@Query("SELECT a from Agendamento a WHERE a.idAgendamento = ?1")
	public Agendamento  pegarAgendamento(Long idAgendamento);
	
	@Query(value = "SELECT count(*) as total_de_agendamentos from tbl_agendamento where "
			+ "finalizado = 1 "
			+ "and status <> 'C' "
			+ "and month(data_horario_agendado) = ?1 "
			+ "and year(data_horario_agendado) = ?2 "
			+ "and day(data_horario_agendado) = ?3 "
			+ "and id_estabelecimento = ?4", nativeQuery = true)
	public Integer  agendamentosPendentesTotal(Integer mes, Integer ano, Integer dia, Long idEstabelecimento);
	
	
}
