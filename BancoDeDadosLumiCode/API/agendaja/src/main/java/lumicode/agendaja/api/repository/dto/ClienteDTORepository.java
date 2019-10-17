package lumicode.agendaja.api.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.dto.ClienteDTO;

public interface ClienteDTORepository 
	extends JpaRepository<ClienteDTO, Long>{

}
