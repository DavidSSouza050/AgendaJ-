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

import lumicode.agendaja.api.model.Endereco;
import lumicode.agendaja.api.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	private List<Endereco> getEndereco(){
		return enderecoRepository.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{id}")
	private Endereco visualizarEndereco(@PathVariable Long id) {
		return enderecoRepository.findById(id).get();
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	private ResponseEntity<Endereco> salvarEndereco(
			@Validated @RequestBody Endereco endereco,
			HttpServletResponse response){
		
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequestUri()
				  .path("/{id}")
				  .buildAndExpand(endereco.getIdEndereco())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(enderecoSalvo);
		
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/{id}")
	private ResponseEntity<Endereco> atualizarEndereco(
			@Validated @RequestBody Endereco endereco, @PathVariable Long id){
		
		Endereco enderecoAtualizado = enderecoRepository.findById(id).get();
		
		BeanUtils.copyProperties(endereco, enderecoAtualizado, "id");
		
		enderecoRepository.save(enderecoAtualizado);
		
		return ResponseEntity.ok(enderecoAtualizado);
		
	}
	
	
	
}
