package lumicode.agendaja.api.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.dto.FuncionarioDTO;

public interface FuncionarioDTORepository 
	extends JpaRepository<FuncionarioDTO, Long>{
	
	@Query("SELECT f FROM FuncionarioDTO as f WHERE f.idFuncionario = ?1")
	public FuncionarioDTO pegarFuncionario(Long idFuncionario);
}
