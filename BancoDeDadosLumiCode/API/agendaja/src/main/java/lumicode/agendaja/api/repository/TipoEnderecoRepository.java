package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.TipoEndereco;

public interface TipoEnderecoRepository 
	extends JpaRepository<TipoEndereco, Long>{

}
