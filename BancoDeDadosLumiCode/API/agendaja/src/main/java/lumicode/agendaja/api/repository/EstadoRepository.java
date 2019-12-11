package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Estado;

public interface EstadoRepository 
	extends JpaRepository<Estado, Long>{

}
