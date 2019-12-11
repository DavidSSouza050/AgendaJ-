package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.FuncionarioServico;

public interface FuncionarioServicoRepository 
	extends JpaRepository<FuncionarioServico, Long>{

}
