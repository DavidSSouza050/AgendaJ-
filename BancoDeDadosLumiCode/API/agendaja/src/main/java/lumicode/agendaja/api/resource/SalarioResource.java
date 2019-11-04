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

import lumicode.agendaja.api.model.Salario;
import lumicode.agendaja.api.repository.SalarioRepository;

@RestController
@RequestMapping("/salarios")
@CrossOrigin(origins = "*")
public class SalarioResource {
	@Autowired
	private SalarioRepository salarioRepository;
	
	@GetMapping
	private List<Salario> getSalario(){
		return salarioRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	private Salario visualizarSalario(@PathVariable Long id) {
		return salarioRepository.findById(id).get();
	}
	
	

	@PostMapping
	private ResponseEntity<Salario> cadastrarAgendamento(
			@Validated @RequestBody Salario salario,
			HttpServletResponse response){
		
		
		Salario salarioSalvo = salarioRepository.save(salario);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(salario.getIdSalario())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(salarioSalvo);
	
	}
		
	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarAgendamento(@RequestBody Salario salario,
			@PathVariable Long id ){
	
		Salario salarioAtualizado = salarioRepository.findById(id).get();

		
		BeanUtils.copyProperties(salario, salarioAtualizado, "id");
	
		salarioRepository.save(salarioAtualizado);
		
		return ResponseEntity.ok(salarioAtualizado);
	
	}
	
	
	
	
	
	
	
	
	
}
