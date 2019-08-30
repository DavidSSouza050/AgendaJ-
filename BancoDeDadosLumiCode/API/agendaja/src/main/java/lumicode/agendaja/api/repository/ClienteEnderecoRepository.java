package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.ClienteEndereco;

public interface ClienteEnderecoRepository 
	extends JpaRepository<ClienteEndereco, Long>{

}
