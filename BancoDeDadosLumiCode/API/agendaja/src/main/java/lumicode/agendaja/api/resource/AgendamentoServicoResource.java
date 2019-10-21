package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.AgendamentoServico;
import lumicode.agendaja.api.model.Servico;
import lumicode.agendaja.api.repository.AgendamentoServicoRepository;

@RestController
@RequestMapping("/agendamentoServicos")
@CrossOrigin(origins = "*")
public class AgendamentoServicoResource {
	@Autowired
	private AgendamentoServicoRepository agendamentoServicoRepository;
	
	@GetMapping
	private List<AgendamentoServico> getAgendamentoServico(){
		return agendamentoServicoRepository.findAll();
	}
	
	
	@GetMapping("/agendamento/{id}")
	private List<Servico> getAgendamentoServicos(@PathVariable Long id){
		return agendamentoServicoRepository.pegarAgendamentoServico(id);
	}
	
}