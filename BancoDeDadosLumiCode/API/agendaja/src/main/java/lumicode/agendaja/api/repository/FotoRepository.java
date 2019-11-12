package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Foto;

public interface FotoRepository
	extends JpaRepository<Foto, Long>{
	
	@Query("SELECT f FROM Foto as f "
			+ "INNER JOIN f.estabelecimento as e WHERE e.idEstabelecimento = ?1")
	public List<Foto> galeriaEstabelecimento(Long idEstabelecimento);
}
