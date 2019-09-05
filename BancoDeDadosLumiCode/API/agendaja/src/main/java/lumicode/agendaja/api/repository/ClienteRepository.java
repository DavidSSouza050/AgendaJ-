package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Cliente;

public interface ClienteRepository 
	extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where c.email = ?1 and c.senha = ?2")
	Cliente Entrar(String email, String senha);
	
	@Query("select c from Cliente c where c.email = ?1")
	Cliente VerificarEmail(String email);
	
}
