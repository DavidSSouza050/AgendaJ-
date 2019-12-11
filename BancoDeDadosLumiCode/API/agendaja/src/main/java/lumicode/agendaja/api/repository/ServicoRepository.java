package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Servico;

public interface ServicoRepository 
	extends JpaRepository<Servico, Long>{
	
	@Query("SELECT s FROM Servico as s "
			+ "INNER JOIN s.estabelecimento as e WHERE e.idEstabelecimento = ?1")
	public List<Servico> visualizarServicosPorestabelecimento(Long id);
	
}
