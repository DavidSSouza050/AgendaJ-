package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.CategoriaServico;

public interface CategoriaServicoRepository 
	extends JpaRepository<CategoriaServico, Long>{

}
