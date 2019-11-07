package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.DiaSemana;

public interface DiaSemanaRepository
	extends JpaRepository<DiaSemana, Long>{
	
	@Query("SELECT d FROM DiaSemana as d WHERE d.idDiaSemana = ?1")
	public DiaSemana pegarDiaSemana(Long idDiaSemana);
}
