package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Endereco;
import lumicode.agendaja.api.model.EnderecoCliente;
public interface EnderecoClienteRepository 
	extends JpaRepository<EnderecoCliente, Long>{

	@Query("SELECT e.endereco, e.cliente FROM EnderecoCliente as e "
			+ "INNER JOIN e.cliente as c WHERE c.idCliente = ?1")
	public List<Endereco> pegarEndereco(Long idCliente);
	
	
	
}
