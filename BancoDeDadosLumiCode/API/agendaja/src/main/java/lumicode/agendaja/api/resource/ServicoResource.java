package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.Calendar;
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
@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*")
public class ServicoResource {
	@Autowired
	private ServicoRepository servicoRepository;

	
	
	Calendar calendar = Calendar.getInstance();
	Integer mes = calendar.get(Calendar.MONTH)+1;
	Integer ano = calendar.get(Calendar.YEAR);
	
	
	@GetMapping
	private List<Servico> getServico(){
		return servicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private Servico visualizarServico(@PathVariable Long id) {
		return servicoRepository.findById(id).get();
	}
	
	
	@GetMapping("/estabelecimento/{id}")
	private List<Servico> visualizaeServicosPorEstabelecimento(@PathVariable Long id){
		return servicoRepository.visualizarServicosPorestabelecimento(id);
	}
	
	@PostMapping
	private ResponseEntity<Servico> salvarServico(
			@Validated @RequestBody Servico servico,
			HttpServletResponse response){
		
		
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
	
		Servico servicoAtualizado = servicoRepository.findById(id).get();
		
		
		BeanUtils.copyProperties(servico, servicoAtualizado, "id");
	
		servicoRepository.save(servico);
		
		return ResponseEntity.ok(servicoAtualizado);
	
	
	}
	
	
	
	
	
}
