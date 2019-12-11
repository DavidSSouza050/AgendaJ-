package lumicode.agendaja.api.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.view.ServicosFuncionarioVIEW;

public interface ServicosFuncionarioVIEWRepository 
	extends JpaRepository<ServicosFuncionarioVIEW, Long>{
	
	@Query(value = "SELECT * FROM view_servicos_funcionario WHERE mes = ?1 AND ano = ?2 AND finalizado = 1 AND funcionario = ?3", nativeQuery = true)
	public List<ServicosFuncionarioVIEW> pegarServicos(Integer mes, Integer ano, Long idFuncionario);
}
