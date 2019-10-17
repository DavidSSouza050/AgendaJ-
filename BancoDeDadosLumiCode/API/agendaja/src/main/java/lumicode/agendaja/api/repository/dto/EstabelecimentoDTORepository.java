package lumicode.agendaja.api.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;

public interface EstabelecimentoDTORepository 
	extends JpaRepository<EstabelecimentoDTO, Long>{

}
