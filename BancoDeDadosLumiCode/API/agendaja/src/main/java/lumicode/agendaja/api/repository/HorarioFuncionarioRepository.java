package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.HorarioFuncionario;

public interface HorarioFuncionarioRepository
	extends JpaRepository<HorarioFuncionario, Long>{

}
