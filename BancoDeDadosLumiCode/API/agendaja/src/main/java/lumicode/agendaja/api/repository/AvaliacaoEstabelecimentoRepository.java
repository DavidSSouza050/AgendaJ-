package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.AvaliacaoEstabelecimento;

public interface AvaliacaoEstabelecimentoRepository 
	extends JpaRepository<AvaliacaoEstabelecimento, Long>{
	
	/*Retorna todas as avaliacoes do cliente*/
	@Query("SELECT a FROM AvaliacaoEstabelecimento as a "
			+ "INNER JOIN a.estabelecimento as e "
			+ "WHERE e.idEstabelecimento = ?1")
	public List<AvaliacaoEstabelecimento> avaliacaoDoEstabeleciemento(Long id);
	
	
	
	/*Retorna o total de registros da avaliação do cliente*/
	@Query(value = "SELECT count(*) FROM tbl_avaliacao_estabelecimento where id_estabelecimento = ?1", nativeQuery = true)
	public Integer totalDeavaliacoes(Long id);
	
	
	/*Retorna a soma de avaliacoes do cliente*/
	@Query(value = "SELECT sum(avaliacao) FROM tbl_avaliacao_estabelecimento where id_estabelecimento = ?1", nativeQuery=true)
	public Integer somaDeavaliacoes(Long id);
}
