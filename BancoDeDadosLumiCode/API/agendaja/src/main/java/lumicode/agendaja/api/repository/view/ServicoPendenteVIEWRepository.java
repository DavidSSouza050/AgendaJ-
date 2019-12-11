package lumicode.agendaja.api.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.view.ServicoPendenteVIEW;

public interface ServicoPendenteVIEWRepository
	extends JpaRepository<ServicoPendenteVIEW, Long>{

	// Agendamento Pendente do funcionario (mes)
	@Query(value = "SELECT * FROM view_servico_pendente WHERE id_funcionario = ?1 "
			+ "AND month(data_hora) = ?2 "
			+ "AND year(data_hora) = ?3 "
			+ "AND finalizado = 0 "
			+ "AND cancelado <> 'C'", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosPendentesFuncionario(Long idFuncionario, Integer mes, Integer ano);
	
	// Agendamento pendentes do dia 
	@Query(value = "SELECT * FROM view_servico_pendente WHERE id_funcionario = ?1 "
			+ "AND month(data_hora) = ?2 "
			+ "AND year(data_hora) = ?3 "
			+ "AND day(data_hora) = ?4 "
			+ "AND finalizado = 0 "
			+ "AND cancelado <> 'C'", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosPendentesFuncionarioDia(Long idFuncionario, Integer mes, Integer ano, Integer dia);
	
	//Agendamento realizados
	@Query(value = "SELECT * FROM view_servico_pendente WHERE id_funcionario = ?1 "
			+ "AND month(data_hora) = ?2 "
			+ "AND year(data_hora) = ?3 "
			+ "AND finalizado = 1 "
			+ "AND cancelado <> 'C'", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosRealizadosFuncionario(Long idFuncionario, Integer mes, Integer ano);
	
	
	
	@Query(value = "SELECT * FROM view_servico_pendente WHERE id_estabelecimento = ?1 "
			+ "AND month(data_hora) = ?2 "
			+ "AND year(data_hora) = ?3 "
			+ "AND day(data_hora) <> ?4 "
			+ "AND finalizado = 0 "
			+ "AND cancelado <> 'C'", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosPendentesEstabelecimento(Long idEstabelecimento, Integer mes, Integer ano, Integer dia);
	
	@Query(value = "SELECT * FROM view_servico_pendente WHERE id_estabelecimento = ?1 "
			+ "AND month(data_hora) = ?2 "
			+ "AND year(data_hora) = ?3 "
			+ "AND day(data_hora) = ?4 "
			+ "AND finalizado = 0 "
			+ "AND cancelado <> 'C'", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosPendentesEstabelecimentoDia(Long idEstabelecimento, Integer mes, Integer ano, Integer dia);
	
	@Query(value = "SELECT * FROM view_servico_pendente WHERE finalizado = 1 "
			+ "AND id_estabelecimento = ?1 "
			+ "AND cancelado <> 'C' order by data_hora desc", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegarServicosRealizadosEstabelecimento(Long idEstabelecimento);
	
	@Query(value = "SELECT * FROM view_servico_pendente WHERE finalizado = 0 "
			+ "AND cliente = ?1 AND cancelado <> 'C' order by data_hora desc", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosPendentesCliente(Long idCliente);
	
	@Query(value = "SELECT * FROM view_servico_pendente WHERE finalizado = 1 "
			+ "AND cliente = ?1 AND cancelado <> 'C' order by data_hora desc", nativeQuery=true)
	public List<ServicoPendenteVIEW> pegerServicosRealizadosCliente(Long idCliente);
	
	
	
	/*total de agendamentos pendentes*/
	@Query(value = "SELECT count(*) as total_de_agendamentos from view_servico_pendente where "
			+ "finalizado = 0 "
			+ "and cancelado <> 'C' "
			+ "and month(data_hora) = ?1 "
			+ "and year(data_hora) = ?2 "
			+ "and day(data_hora) = ?3 "
			+ "and id_estabelecimento = ?4", nativeQuery = true)
	public Integer  agendamentosPendentesTotal(Integer mes, Integer ano, Integer dia, Long idEstabelecimento);
	
}
