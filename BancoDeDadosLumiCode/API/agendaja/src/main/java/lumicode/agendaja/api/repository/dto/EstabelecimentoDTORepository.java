package lumicode.agendaja.api.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;

public interface EstabelecimentoDTORepository 
	extends JpaRepository<EstabelecimentoDTO, Long>{
	
	@Query("SELECT e FROM EstabelecimentoDTO as e WHERE e.idEstabelecimento = ?1")
	public EstabelecimentoDTO pegarEstabelecimento(Long idEstabelecimento);
	
}
