package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Assunto;

public interface AssuntoRepository 
	extends JpaRepository<Assunto, Long>{

}
