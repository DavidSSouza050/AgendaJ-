package lumicode.agendaja.api.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.view.TotalComissaoVIEW;

public interface TotalComissaoVIEWRepository 
	extends JpaRepository<TotalComissaoVIEW, Long>{
	
	@Query(value = "SELECT * FROM view_total_comissao WHERE funcionario = ?1 "
			+ "and mes = ?2 "
			+ "and ano = ?3 "
			+ "and finalizado = 1", nativeQuery = true)
	public TotalComissaoVIEW pegarComissaoFuncionario(Long idFuncionario, Integer mesAtual, Integer anoAtual);
	
}
