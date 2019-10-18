package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.Agendamento;
import lumicode.agendaja.api.repository.AgendamentoRepository;
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin(origins = "*")
public class AgendamentoResource {
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@GetMapping
	private List<Agendamento> getAgendamento(){
		return agendamentoRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	private Agendamento visualizarAgendamento(@PathVariable Long id) {
		return agendamentoRepository.findById(id).get();
	}
	
	
	@PostMapping
	private ResponseEntity<Agendamento> cadastrarAgendamento(
			@Validated @RequestBody Agendamento agendamento,
			HttpServletResponse response){
		
		ConverterDatas converterDatas = new ConverterDatas();
		//setando o criado em 
		agendamento.setCriadoEm(converterDatas.dataAtual());
		//setando o atualizado 
		agendamento.setAtualizadoEm(converterDatas.dataAtual());
		
		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(agendamento.getIdAgendamento())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(agendamentoSalvo);
	
	}
		
	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarAgendamento(@RequestBody Agendamento agendamento,
			@PathVariable Long id ){
	
		Agendamento agendamentoAtualizado = agendamentoRepository.findById(id).get();

		//declarando o coverter datas 
		ConverterDatas converterDatas = new ConverterDatas();
		//setando o atualizada em
		agendamento.setAtualizadoEm(converterDatas.dataAtual());
		//para não atualizar o criadoEm estou setando de novo apra nao copiar
		String criadoEm = agendamento.getCriadoEm();
		agendamento.setCriadoEm(criadoEm);
		// *************************
		
		BeanUtils.copyProperties(agendamento, agendamentoAtualizado, "id");
	
		agendamentoRepository.save(agendamentoAtualizado);
		
		return ResponseEntity.ok(agendamentoAtualizado);
	
	}
	
	
	@GetMapping("/agendamento/{id}")
	private Agendamento visualizar(@PathVariable Long id) {
		return agendamentoRepository.pegarServico(id);
	}
	
	
	
}
