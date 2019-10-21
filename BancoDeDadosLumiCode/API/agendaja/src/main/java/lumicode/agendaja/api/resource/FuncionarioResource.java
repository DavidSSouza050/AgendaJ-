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

import lumicode.agendaja.api.model.Funcionario;
import lumicode.agendaja.api.repository.FuncionarioRepository;
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	private List<Funcionario> getFuncionario(){
		return funcionarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private Funcionario visualizarFuncionario(@PathVariable Long id) {
		return funcionarioRepository.findById(id).get();
	}
	
	@PostMapping
	private ResponseEntity<Funcionario> cadastrarFuncionario(
			@Validated @RequestBody Funcionario funcionario,
			HttpServletResponse response){
		
		ConverterDatas converterDatas = new ConverterDatas();
		//setando o criado em 
		funcionario.setCriadoEm(converterDatas.dataAtual());
		//setando o atualizado 
		funcionario.setAtualizadoEm(converterDatas.dataAtual());
		//setando status
		funcionario.setStatus(1);
		
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(funcionario.getIdFuncionario())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(funcionarioSalvo);
		
	
	}
		
	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarFuncinario(@RequestBody Funcionario funcionario,
			@PathVariable Long id ){
	
		Funcionario funcionarioAtualizado = funcionarioRepository.findById(id).get();

		//declarando o coverter datas 
		ConverterDatas converterDatas = new ConverterDatas();
		//setando o atualizada em
		funcionario.setAtualizadoEm(converterDatas.dataAtual());
		//para não atualizar o criadoEm estou setando de novo apra nao copiar
//		String criadoEm = funcionarioAtualizado.getCriadoEm();
//		funcionario.setCriadoEm(criadoEm);
		// *************************
		
		BeanUtils.copyProperties(funcionario, funcionarioAtualizado, "id");
	
		funcionarioRepository.save(funcionario);
		
		return ResponseEntity.ok(funcionarioAtualizado);
	
	}

	@PutMapping("/desativar/{id}")
	private ResponseEntity<?> desativarFuncinario(@PathVariable Long id){
		
		Funcionario funcionario = funcionarioRepository.findById(id).get();
		funcionario.setStatus(0);
		
		ConverterDatas converterDatas = new ConverterDatas();
		//setando data atualizada
		funcionario.setAtualizadoEm(converterDatas.dataAtual());

		funcionarioRepository.save(funcionario);
		
		return ResponseEntity.ok("{\"message\":\"Funcionario desativado\"}");
		
	}
	
	
}