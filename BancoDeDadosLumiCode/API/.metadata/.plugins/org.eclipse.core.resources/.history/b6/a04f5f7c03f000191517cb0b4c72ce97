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

import lumicode.agendaja.api.model.FuncionarioServico;
import lumicode.agendaja.api.repository.FuncionarioServicoRepository;

@RestController
@RequestMapping("/funcionarioServico")
@CrossOrigin(origins = "*")
public class FuncionarioServicoResource {
	
	@Autowired
	private FuncionarioServicoRepository funcionarioServicoRepository;
	
	@GetMapping
	private List<FuncionarioServico> getFuncionarioServico(){
		return funcionarioServicoRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	private FuncionarioServico visualizarFuncionarioServico(@PathVariable Long id) {
		return funcionarioServicoRepository.findById(id).get();
	}
	
	// Cadastrando uma relação com funcionario e servico
	@PostMapping
	private ResponseEntity<?> salvarFuncionarioServico(
			@Validated @RequestBody FuncionarioServico funcionarioServico,
		HttpServletResponse response){
		
		FuncionarioServico funcionarioServicoSalvo = funcionarioServicoRepository.save(funcionarioServico);
			
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(funcionarioServico.getIdFuncionarioServico())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(funcionarioServicoSalvo);
	
	}
	
	

	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarCliente(@RequestBody FuncionarioServico funcionarioServico,
			@PathVariable Long id ){
		
		FuncionarioServico clienteAtualizado = funcionarioServicoRepository.findById(id).get();
		
		BeanUtils.copyProperties(funcionarioServico, clienteAtualizado, "id");
	
		funcionarioServicoRepository.save(funcionarioServico);
		
		return ResponseEntity.ok(clienteAtualizado);
	
	}
	
}
