package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Foto;

public interface FotoRepository
	extends JpaRepository<Foto, Long>{

}
