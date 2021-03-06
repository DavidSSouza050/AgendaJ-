package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Endereco;
import lumicode.agendaja.api.model.EnderecoEstabelecimento;
import lumicode.agendaja.api.model.Estabelecimento;

public interface EnderecoEstabelecimentoRepository 
	extends JpaRepository<EnderecoEstabelecimento, Long>{

	@Query("SELECT e.endereco FROM EnderecoEstabelecimento as e "
			+ "INNER JOIN e.estabelecimento as es "
			+ "WHERE es.idEstabelecimento = ?1")
	public List<Endereco> pegarEndereco(Long idEstabelecimento);
	
	
	@Query("SELECT e FROM EnderecoEstabelecimento as ene "
			+ "INNER JOIN ene.endereco as en "
			+ "INNER JOIN ene.estabelecimento as e "
			+ "INNER JOIN en.idCidade as c "
			+ "INNER JOIN c.idMicrorregiao as m "
			+ "WHERE c.idCidade = ?1")
	public List<Estabelecimento> estabelecimentoPorCidade(Long idCidade);
	
	
}
