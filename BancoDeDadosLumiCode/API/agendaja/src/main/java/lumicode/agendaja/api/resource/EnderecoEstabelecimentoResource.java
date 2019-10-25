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
import lumicode.agendaja.api.model.EnderecoEstabelecimento;
import lumicode.agendaja.api.repository.EnderecoEstabelecimentoRepository;

@RestController
@RequestMapping("/enderecosEstabelecimentos")
@CrossOrigin("*")
public class EnderecoEstabelecimentoResource {
	@Autowired
	private EnderecoEstabelecimentoRepository enderecoEstabelecimentoRepository;
 
	@GetMapping
	private List<EnderecoEstabelecimento> getEnderecoEstabelecimento(){
		return enderecoEstabelecimentoRepository.findAll();
	}

	@GetMapping("/{id}")
	private EnderecoEstabelecimento visualizarEnderecoEstabelecimento(
			@PathVariable Long id) {
		return enderecoEstabelecimentoRepository.findById(id).get();
	}
	
	@GetMapping("/estabelecimento/{id}")
	private List<Endereco> pegarEndereco(@PathVariable Long id) {
		return enderecoEstabelecimentoRepository.pegarEndereco(id);
	}
	
	@PostMapping
	private ResponseEntity<EnderecoEstabelecimento> salvarEndereco(
			@RequestBody EnderecoEstabelecimento enderecoEstabelecimento,
			HttpServletResponse response){
		
		EnderecoEstabelecimento enderecoEstabelecimentoSalvo = enderecoEstabelecimentoRepository.save(enderecoEstabelecimento);
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequestUri()
				  .path("/{id}")
				  .buildAndExpand(enderecoEstabelecimento.getIdEnderecoEstabelecimento())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(enderecoEstabelecimentoSalvo);
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<EnderecoEstabelecimento> atualizarEndereco(
			@Validated @RequestBody EnderecoEstabelecimento enderecoEstabelecimento,
			@PathVariable Long id){
		
		EnderecoEstabelecimento enderecoEstabelecimentoAtualizado = enderecoEstabelecimentoRepository.findById(id).get();
		
		//*****
		BeanUtils.copyProperties(enderecoEstabelecimento, enderecoEstabelecimentoAtualizado, "id");
		
		enderecoEstabelecimentoRepository.save(enderecoEstabelecimentoAtualizado);
		
		return ResponseEntity.ok(enderecoEstabelecimentoAtualizado);
		
	}
	
	
	
	
	
	
	
}
