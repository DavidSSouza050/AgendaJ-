package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.EstabelecimentoEndereco;

public interface EstabelecimentoEnderecoRepository 
	extends JpaRepository<EstabelecimentoEndereco, Long>{

}
