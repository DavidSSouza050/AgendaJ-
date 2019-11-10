package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.AgendamentoServico;
import lumicode.agendaja.api.model.Servico;
import lumicode.agendaja.api.model.dto.AgendamentoServicosIdDTO;
import lumicode.agendaja.api.repository.AgendamentoServicoRepository;
import lumicode.agendaja.api.repository.dto.AgendamentoServicosIdDTORepository;
@RestController
@RequestMapping("/agendamentoServicos")
@CrossOrigin(origins = "*")
public class AgendamentoServicoResource {
	@Autowired
	private AgendamentoServicoRepository agendamentoServicoRepository;
	
	@Autowired
	private AgendamentoServicosIdDTORepository agendamentoServicosIdDTORepository;
	
	@GetMapping
	private List<AgendamentoServico> getAgendamentoServico(){
		return agendamentoServicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private AgendamentoServico visualizar(@PathVariable Long id) {
		return agendamentoServicoRepository.findById(id).get();
	}
	
	@GetMapping("/agendamento/{id}")
	private Servico getAgendamentoServicos(@PathVariable Long id){		
		return agendamentoServicoRepository.pegarAgendamentoServico(id);
	}
	
	
	@PostMapping
	private ResponseEntity<?> cadastrarAgendamento(
			@Validated @RequestBody AgendamentoServico agendamentoServico,
			HttpServletResponse response){
		
		if(agendamentoServicoRepository.verificarRelacionamento(agendamentoServico.getServico().getIdServico(), 
				agendamentoServico.getAgendamento().getIdAgendamento()) != null) {
			return new ResponseEntity<String>("{\"mensage\": \"Relacionamento já cadastrado\"}",HttpStatus.BAD_REQUEST);
		}
		
		
		agendamentoServicoRepository.save(agendamentoServico);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(agendamentoServico.getIdAgendamentoServico())
				  .toUri();
		
		AgendamentoServicosIdDTO agendameneAgendamentoServicosIdDTO = 
					agendamentoServicosIdDTORepository.findById(agendamentoServico.getIdAgendamentoServico())
					.get(); 
		
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(agendameneAgendamentoServicosIdDTO);
	
	}
	
	
	
	
}
