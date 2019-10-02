package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Cliente;

public interface ClienteRepository 
	extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where c.email = ?1 and c.senha = ?2")
	public Cliente entrar(String email, String senha);
	
	@Query("select c from Cliente c where c.email = ?1")
	public Cliente verificarEmail(String email);
	
	@Query("select c from Cliente c where c.idCliente = ?1")
	public Cliente getById(Long id);
	
	
}
