package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Cidade;

public interface CidadeRepository 
	extends JpaRepository<Cidade, Long> {

}
