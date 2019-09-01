package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Estabelecimento;

public interface EstabelecimentoRepository 
	extends JpaRepository<Estabelecimento, Long>{
	
	@Query("select e from Estabelecimento e where e.email = ?1 and e.senha = ?2")
	Estabelecimento loginEstabelecimento(String email, String senha);
	
}
