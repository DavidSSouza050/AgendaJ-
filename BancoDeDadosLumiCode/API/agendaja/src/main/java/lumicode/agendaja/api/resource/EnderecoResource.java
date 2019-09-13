package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	private List<Endereco> getEndereco(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private Endereco visualizarEndereco(@PathVariable Long id) {
		return enderecoRepository.findById(id).get();
	}
	
	@PostMapping
	private ResponseEntity<Endereco> salvarEndereco(
			@Validated @RequestBody Endereco endereco,
			HttpServletResponse response){
		//setando data
		ConverterDatas converterDatas = new ConverterDatas();
		endereco.setCriadoEm(converterDatas.dataAtual());
		endereco.setAtualizadoEm(converterDatas.dataAtual());
		//
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequestUri()
				  .path("/{id}")
				  .buildAndExpand(endereco.getIdEndereco())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(enderecoSalvo);
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Endereco> atualizarEndereco(
			@Validated @RequestBody Endereco endereco, @PathVariable Long id){
		
		Endereco enderecoAtualizado = enderecoRepository.findById(id).get();
		//setando data atual
		ConverterDatas converterDatas = new ConverterDatas();
		endereco.setAtualizadoEm(converterDatas.dataAtual());
		String criadoEm = enderecoAtualizado.getCriadoEm();
		endereco.setCriadoEm(criadoEm);
		//*****
		BeanUtils.copyProperties(endereco, enderecoAtualizado, "id");
		
		enderecoRepository.save(endereco);
		
		return ResponseEntity.ok(enderecoAtualizado);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}