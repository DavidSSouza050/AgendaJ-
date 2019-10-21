package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import lumicode.agendaja.api.model.EnderecoCliente;
import lumicode.agendaja.api.model.dto.EnderecoDTO;

public interface EnderecoClienteRepository 
	extends JpaRepository<EnderecoCliente, Long>{

	@Query("SELECT e.endereco, e.cliente FROM EnderecoCliente as e "
			+ "INNER JOIN e.cliente as c WHERE c.idCliente = ?1")
	public List<EnderecoDTO> pegarEndereco(Long idCliente);
	
	
	
}
