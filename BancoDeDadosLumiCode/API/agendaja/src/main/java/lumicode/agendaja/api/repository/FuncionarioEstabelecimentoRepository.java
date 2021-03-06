package lumicode.agendaja.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.FuncionarioEstabelecimento;
import lumicode.agendaja.api.model.dto.FuncionarioDTO;
public interface FuncionarioEstabelecimentoRepository 
	extends JpaRepository<FuncionarioEstabelecimento, Long>{

	@Query("SELECT fe.funcionario FROM FuncionarioEstabelecimento as fe "
			+ "INNER JOIN fe.estabelecimento as et WHERE et.idEstabelecimento = ?1")
	public List<FuncionarioDTO> funcionarioPorEstabelecimento(Long idEstabelecimento);
	
}
