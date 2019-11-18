package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.AvaliacaoCliente;

public interface AvaliacaoClienteRepository 
	extends JpaRepository<AvaliacaoCliente, Long>{

}
