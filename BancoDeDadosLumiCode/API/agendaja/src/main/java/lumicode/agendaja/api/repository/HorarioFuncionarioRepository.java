package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.HorarioFuncionario;

public interface HorarioFuncionarioRepository
	extends JpaRepository<HorarioFuncionario, Long>{
	@Query(value = "SELECT * FROM tbl_funcionario as f INNER JOIN tbl_funcionario_estabelecimento as fe " + 
			"ON f.id_funcionario = fe.id_funcionario INNER JOIN tbl_estabelecimento as e " + 
			"ON fe.id_estabelecimento = e.id_estabelecimento INNER JOIN  tbl_horario_funcionario as hf " + 
			"ON f.id_funcionario = hf.id_funcionario WHERE e.id_estabelecimento = ?1 AND hf.id_dia_semana = ?2", nativeQuery = true)
	public List<HorarioFuncionario> pegarHorarioDefuncionario(Long idEstabelecimento, Integer id_dia_semana);

}
