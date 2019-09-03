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

import lumicode.agendaja.api.model.EstabelecimentoEndereco;
import lumicode.agendaja.api.repository.EstabelecimentoEnderecoRepository;
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/estabelecimentoEndereco")
public class EstabelecimentoEnderecoResource {
	@Autowired
	private EstabelecimentoEnderecoRepository estabelecimentoEnderecoRepository;
	
	//pegando todas as relaçoes entre endereco e esatbelecimento
	@GetMapping
	private List<EstabelecimentoEndereco> getEstabelecimentoEndereco(){
		return estabelecimentoEnderecoRepository.findAll();
	}
	
	
	//pegando apenas um relacionamento de endereco e estabeleciemto
	@GetMapping("/{id}")
	private EstabelecimentoEndereco visualizarEstabelecimento(@PathVariable Long id) {
		return estabelecimentoEnderecoRepository.findById(id).get();
	}
	
	//Cadastratar relação de endereco e estabelecimento
	@PostMapping
	private ResponseEntity<EstabelecimentoEndereco> salvarEstabelecimentoEndereco(
			@RequestBody EstabelecimentoEndereco estabelecimentoEndereco,
			HttpServletResponse response){
		//setando data
		ConverterDatas converterData = new ConverterDatas();
		estabelecimentoEndereco.setCriadoEm(converterData.dataAtual());
		estabelecimentoEndereco.setAtualizadoEm(converterData.dataAtual());
		//*****
		EstabelecimentoEndereco estabelecimentoEnderecoSalvo = estabelecimentoEnderecoRepository.save(estabelecimentoEndereco); 
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(estabelecimentoEndereco.getIdEstabelecimentoEndereco())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(estabelecimentoEnderecoSalvo);
	}
	
	
	//atualizar Relação
	
	@PutMapping("/{id}")
	private ResponseEntity<EstabelecimentoEndereco> atualizarEstabelecimentoEndereco(
			@RequestBody EstabelecimentoEndereco estabelecimentoEndereco,
			@PathVariable Long id){
		
		ConverterDatas converterDatas = new ConverterDatas();
		estabelecimentoEndereco.setAtualizadoEm(converterDatas.dataAtual());
		
		EstabelecimentoEndereco estabelecimentoEnderecoAtualizado =  
							estabelecimentoEnderecoRepository.findById(id).get();
		
		BeanUtils.copyProperties(estabelecimentoEndereco, estabelecimentoEnderecoAtualizado, "id");
		
		estabelecimentoEnderecoRepository.save(estabelecimentoEndereco);
		
		return ResponseEntity.ok(estabelecimentoEnderecoAtualizado);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
