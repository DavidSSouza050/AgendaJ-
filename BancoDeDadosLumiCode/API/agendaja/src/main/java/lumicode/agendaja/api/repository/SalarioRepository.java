package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Salario;

public interface SalarioRepository 
	extends JpaRepository<Salario, Long>{
	

	
	
}
