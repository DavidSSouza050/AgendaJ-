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

import lumicode.agendaja.api.model.FuncionarioEstabelecimento;
import lumicode.agendaja.api.model.dto.FuncionarioDTO;
import lumicode.agendaja.api.repository.FuncionarioEstabelecimentoRepository;

@RestController
@RequestMapping("/funcionariosEstabelecimentos")
@CrossOrigin(origins = "*")
public class FuncionarioEstabelecimentoResource {
	@Autowired
	private FuncionarioEstabelecimentoRepository funcionarioEstabelecimentoRepository;
	
	
	@GetMapping
	private List<FuncionarioEstabelecimento> getFuncionarioEstabelecimento(){
		return funcionarioEstabelecimentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private FuncionarioEstabelecimento visualizarFuncionarioEstabelecimento(@PathVariable Long id) {
		return funcionarioEstabelecimentoRepository.findById(id).get();
	}
	
	@GetMapping("/estabelecimento/{id}")
	private List<FuncionarioDTO> visualizarfuncionarioPorEstabelecimento(@PathVariable Long id){
		return funcionarioEstabelecimentoRepository.funcionarioPorEstabelecimento(id);
	}
	
	
	// Cadastrando uma relação com funcionario e servico
	@PostMapping
	private ResponseEntity<?> salvarFuncionarioServico(
			@Validated @RequestBody FuncionarioEstabelecimento funcionarioEstabelecimento,
		HttpServletResponse response){
		
		FuncionarioEstabelecimento funcionarioEstabelecimentoSalvo = funcionarioEstabelecimentoRepository.save(funcionarioEstabelecimento);
			
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(funcionarioEstabelecimento.getIdFuncionarioEstabelecimento())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(funcionarioEstabelecimentoSalvo);
	
	}
	
	

	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarCliente(@RequestBody FuncionarioEstabelecimento funcionarioEstabelecimento,
			@PathVariable Long id ){
		
		FuncionarioEstabelecimento fucionarioEstabelecimentoAtualizado = funcionarioEstabelecimentoRepository.findById(id).get();
		
		BeanUtils.copyProperties(funcionarioEstabelecimento, fucionarioEstabelecimentoAtualizado, "id");
	
		funcionarioEstabelecimentoRepository.save(funcionarioEstabelecimento);
		
		return ResponseEntity.ok(fucionarioEstabelecimentoAtualizado);
	
	}
	
	
	
	
	
	
	
	
	
	
}
