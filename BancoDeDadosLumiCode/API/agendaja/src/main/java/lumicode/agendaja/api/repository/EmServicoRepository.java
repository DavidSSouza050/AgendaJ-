package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.EmServico;

public interface EmServicoRepository 
	extends JpaRepository<EmServico, Long>{
	
	@Query(value= "SELECT * FROM tbl_em_servico as em INNER JOIN tbl_funcionario as f 	ON f.id_funcionario = em.id_funcionario INNER JOIN tbl_estabelecimento as e  ON em.id_estabelecimento = e.id_estabelecimento where em.dia_mes = ?1 and em.mes = ?2 and em.ano = ?3 and em.id_estabelecimento = ?4 ", nativeQuery = true)
	public List<EmServico> pegarOcuapados1(Integer dia_mes,Integer mes, Integer ano, Long idEstabelecimento );
	
}
