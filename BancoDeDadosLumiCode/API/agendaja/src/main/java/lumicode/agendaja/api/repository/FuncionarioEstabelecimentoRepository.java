package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.FuncionarioEstabelecimento;

public interface FuncionarioEstabelecimentoRepository 
	extends JpaRepository<FuncionarioEstabelecimento, Long>{

}
