package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.EmServico;

public interface EmServicoRepository 
	extends JpaRepository<EmServico, Long>{

}
