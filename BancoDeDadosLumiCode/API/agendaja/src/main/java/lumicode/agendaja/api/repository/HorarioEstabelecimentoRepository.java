package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lumicode.agendaja.api.model.HorarioEstabelecimento;

public interface HorarioEstabelecimentoRepository 
	extends JpaRepository<HorarioEstabelecimento, Long>{

}
