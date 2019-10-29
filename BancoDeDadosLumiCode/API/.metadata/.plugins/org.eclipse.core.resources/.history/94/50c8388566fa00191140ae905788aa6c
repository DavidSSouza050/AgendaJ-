package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Endereco;
import lumicode.agendaja.api.model.EnderecoEstabelecimento;
import lumicode.agendaja.api.model.dto.EnderecoDTO;

public interface EnderecoEstabelecimentoRepository 
	extends JpaRepository<EnderecoEstabelecimento, Long>{

	@Query("SELECT e.endereco FROM EnderecoEstabelecimento as e "
			+ "INNER JOIN e.estabelecimento as es "
			+ "WHERE es.idEstabelecimento = ?1")
	public List<Endereco> pegarEndereco(Long idEstabelecimento);
	
}
