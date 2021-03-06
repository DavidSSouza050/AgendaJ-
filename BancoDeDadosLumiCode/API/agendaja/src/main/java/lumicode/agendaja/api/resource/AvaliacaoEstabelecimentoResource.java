package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.AvaliacaoEstabelecimento;
import lumicode.agendaja.api.repository.AvaliacaoEstabelecimentoRepository;

@RestController
@RequestMapping("/avaliacoesEstabelecimentos")
@CrossOrigin(origins="*")
public class AvaliacaoEstabelecimentoResource {
	@Autowired
	private AvaliacaoEstabelecimentoRepository avaliacaoEstabelecimentoRepository;
	
	
	/*retornar todas as avaliacoes exitentes*/
	@GetMapping
	private List<AvaliacaoEstabelecimento> getAvaliacoes(){
		return avaliacaoEstabelecimentoRepository.findAll();
	}
	
	/*Retorna a avaliacao do o id correspondente*/
	@GetMapping("/{id}")
	private AvaliacaoEstabelecimento visualizarAvaliacao(@PathVariable Long id){
		return avaliacaoEstabelecimentoRepository.findById(id).get();
	}
	
	
	/*Retorna avaliacoes do estabelecimento*/
	@GetMapping("/avaliacao/estabelecimento/{id}")
	private List<AvaliacaoEstabelecimento> getAvaliacaoCliente(@PathVariable Long id){
		return avaliacaoEstabelecimentoRepository.avaliacaoDoEstabeleciemento(id);
	}
	
	
	/*Retorna o total de avaliacao do cliente*/
	@GetMapping("/avaliacao/media/estabelecimento/{id}")
	private String getTotalAvaliacao(@PathVariable Long id) {
		
		//pegar o total de avalicoes 
		Double totalDeAvalicoes = (double) avaliacaoEstabelecimentoRepository.totalDeavaliacoes(id);

		//pegar soma de avaliacoes 
		Double somaDeAvalicoes = (double)avaliacaoEstabelecimentoRepository.somaDeavaliacoes(id);
		
		Double media = (double) (somaDeAvalicoes/totalDeAvalicoes);
		
		return "{\"media\":\""+media+"\"}";
	}
	
	
	@PostMapping
	private ResponseEntity<AvaliacaoEstabelecimento> cadastrarAvaliacao(@RequestBody AvaliacaoEstabelecimento avaliacao,
			HttpServletResponse response) { 
		
		
		AvaliacaoEstabelecimento avaliacaoEstabelecimentoSalvo = avaliacaoEstabelecimentoRepository.save(avaliacao);
	
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(avaliacao.getIdAvaliacaoEstabelecimento())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(avaliacaoEstabelecimentoSalvo);
	}
	
	
	@PutMapping("/avaliacao/{id}")
	private ResponseEntity<AvaliacaoEstabelecimento> atualizarAvaliacao(@RequestBody AvaliacaoEstabelecimento avaliacao,
			@PathVariable Long id){
		
		AvaliacaoEstabelecimento avaliacaoEstabelecimentoAtualizado = avaliacaoEstabelecimentoRepository.findById(id).get();
		
		//*****
		BeanUtils.copyProperties(avaliacao, avaliacaoEstabelecimentoAtualizado, "id");
		
		avaliacaoEstabelecimentoRepository.save(avaliacaoEstabelecimentoAtualizado);
		
		return ResponseEntity.ok(avaliacaoEstabelecimentoAtualizado);
	}
	
}
