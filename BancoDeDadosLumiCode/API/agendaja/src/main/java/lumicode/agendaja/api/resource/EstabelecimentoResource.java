package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.repository.EstabelecimentoRepository;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	//pegando todos os estabelecimentos
	@GetMapping
	private List<Estabelecimento> getEstabelecimento(){
		return estabelecimentoRepository.findAll();
	}
	
	//pegando apenas um estabelecimento
	@GetMapping("/{id}")
	private Estabelecimento visualizarEstabelecimento(@PathVariable Long id) {
		return estabelecimentoRepository.findById(id).get();
	}
	
	//cadastrando um estabelecimento 
	@PostMapping
	private ResponseEntity<Estabelecimento> salvarEstabelecimento(
			@RequestBody Estabelecimento estabelecimento,
		HttpServletResponse response){
		
		Estabelecimento estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimento);
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequestUri()
				  .path("/{id}")
				  .buildAndExpand(estabelecimento.getIdEstabelecimento())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(estabelecimento);
	}
	
	
	//Atualizar o estabelecimento
	@PutMapping("/{id}")
	private ResponseEntity<Estabelecimento> atualizarEstabelecimento(@RequestBody 
			Estabelecimento estabelecimento, @PathVariable Long id){
		
		Estabelecimento estabelecimentoAtualizado = estabelecimentoRepository
					.findById(id).get();
		BeanUtils.copyProperties(estabelecimento, estabelecimentoAtualizado, "id");
		
		estabelecimentoRepository.save(estabelecimento);
				
		return ResponseEntity.ok(estabelecimentoAtualizado);
	}
	
	
	
	
	
	
	
	
	
	
	
}
