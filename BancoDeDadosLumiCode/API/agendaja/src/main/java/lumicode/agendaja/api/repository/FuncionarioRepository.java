package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Funcionario;

public interface FuncionarioRepository 
	extends JpaRepository<Funcionario, Long>{

}