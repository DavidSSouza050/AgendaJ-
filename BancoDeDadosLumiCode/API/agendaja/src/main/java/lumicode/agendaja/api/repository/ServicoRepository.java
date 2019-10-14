package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Servico;

public interface ServicoRepository 
	extends JpaRepository<Servico, Long>{

}
