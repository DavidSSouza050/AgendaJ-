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

import lumicode.agendaja.api.model.Servico;
import lumicode.agendaja.api.repository.ServicoRepository;
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "http://localhost:3000")
public class ServicoResource {
	@Autowired
	private ServicoRepository servicoRepository;
	
	@GetMapping
	private List<Servico> getServico(){
		return servicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private Servico visualizarServico(@PathVariable Long id) {
		return servicoRepository.findById(id).get();
	}
	
	@PostMapping
	private ResponseEntity<Servico> salvarServico(
			@Validated @RequestBody Servico servico,
			HttpServletResponse response){
		
		
		//declarando o coverter datas 
		ConverterDatas converterDatas = new ConverterDatas();
		//setando o criado em 
		servico.setCriadoEm(converterDatas.dataAtual());
		//setando o atualizado 
		servico.setAtualizadoEm(converterDatas.dataAtual());
		
		Servico servicoSalvo = servicoRepository.save(servico);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(servico.getIdServico())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//criando o cliente usuario e colocando no body da requiseção depois de salvo
		return ResponseEntity.created(uri).body(servicoSalvo);
	}
	
	
	//atualizando o servico
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarCliente(@RequestBody Servico servico,
			@PathVariable Long id ){
	
		Servico clienteAtualizado = servicoRepository.findById(id).get();
		
		
		//declarando o coverter datas 
		ConverterDatas converterDatas = new ConverterDatas();
		//setando o atualizada em
		servico.setAtualizadoEm(converterDatas.dataAtual());
		//para não atualizar o criadoEm estou setando de novo apra nao copiar
		String criadoEm = clienteAtualizado.getCriadoEm();
		servico.setCriadoEm(criadoEm);
		// *************************
		
		BeanUtils.copyProperties(servico, clienteAtualizado, "id");
	
		servicoRepository.save(servico);
		
		return ResponseEntity.ok(clienteAtualizado);
	
	
	}
	
	
	
	
	
}