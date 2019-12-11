package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.Endereco;

public interface EnderecoRepository 
	extends JpaRepository<Endereco, Long>{

}
