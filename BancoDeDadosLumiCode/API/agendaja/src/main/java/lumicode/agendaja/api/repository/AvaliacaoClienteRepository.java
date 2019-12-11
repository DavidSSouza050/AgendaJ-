package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.AvaliacaoCliente;

public interface AvaliacaoClienteRepository 
	extends JpaRepository<AvaliacaoCliente, Long>{
	
	/*Retorna todas as avaliacoes do cliente*/
	@Query("SELECT a FROM AvaliacaoCliente as a "
			+ "INNER JOIN a.cliente as c "
			+ "WHERE c.idCliente = ?1")
	public List<AvaliacaoCliente> avaliacaoDoCLiente(Long id);
	
	
	
	/*Retorna o total de registros da avaliação do cliente*/
	@Query(value = "SELECT count(*) FROM tbl_avaliacao_cliente where id_cliente = ?1", nativeQuery = true)
	public Integer totalDeavaliacoes(Long id);
	
	
	/*Retorna a soma de avaliacoes do cliente*/
	@Query(value = "SELECT sum(avaliacao) FROM tbl_avaliacao_cliente where id_cliente = ?1", nativeQuery=true)
	public Integer somaDeavaliacoes(Long id);
	
	
}
