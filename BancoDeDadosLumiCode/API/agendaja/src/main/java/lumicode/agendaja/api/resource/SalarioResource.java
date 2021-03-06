package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.Calendar;
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
import lumicode.agendaja.api.model.Salario;
import lumicode.agendaja.api.model.view.TotalComissaoVIEW;
import lumicode.agendaja.api.repository.FuncionarioRepository;
import lumicode.agendaja.api.repository.SalarioRepository;
import lumicode.agendaja.api.repository.view.TotalComissaoVIEWRepository;

@RestController
@RequestMapping("/salarios")
@CrossOrigin(origins = "*")
public class SalarioResource {
	@Autowired
	private SalarioRepository salarioRepository;
	
	@Autowired
	private TotalComissaoVIEWRepository totalComissaoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	private List<Salario> getSalario(){
		return salarioRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	private Salario visualizarSalario(@PathVariable Long id) {
		return salarioRepository.findById(id).get();
	}
	
	@GetMapping("/funcionario/{id}")
	private String pegarSalariomes(@PathVariable Long id) {
		Funcionario salarioFuncionario = funcionarioRepository.pegarSalarioDeFuncionario(id);
		
		//total de comissao do mes atual
		Calendar calendar = Calendar.getInstance();
		int mes = calendar.get(Calendar.MONTH)+1;
		int ano = calendar.get(Calendar.YEAR);
		
		Salario salario = salarioFuncionario.getSalario();
		
		String salarioFinal = null;
		
		//se o salario for null ira fazer o calculo de comissao  
		if(salario.getSalario() == 0.0) {
			//conta de percentual
			TotalComissaoVIEW totalComissao = totalComissaoRepository.pegarComissaoFuncionario(id, mes, ano);
			//caso não há agendamentos
			if(totalComissao == null) {
				return salarioFinal = "{\"mesage\" : \"Mês sem agendamentos finalizados\"}";
			}
			
			 Double salarioTotal =  (salario.getPercentual()/100)*totalComissao.getTotalComissao();
			
			 salarioFinal = "{\"salario\":\""+salarioTotal+"\"}";
		}
		//caso o funcionario tenha os dois, ira fazer o calculo do percentual e o total junto o o salario fixo
		if(salario.getPercentual() != null && salario.getSalario() != 00.0) {
			// conta de salario e percentual		
			TotalComissaoVIEW totalComissao = totalComissaoRepository.pegarComissaoFuncionario(id, mes, ano);
			
			
			Double salarioTotal = ((salario.getPercentual()/100)*totalComissao.getTotalComissao()) + salario.getSalario();
			
			
			salarioFinal = "{\"salario\":\""+salarioTotal+"\"}";
		}
		
		
		//se o perncentual for null ira apresentar o salario normal
		if(salario.getPercentual() == null) {
			//conta de salario	
			salarioFinal = "{\"salario\":\""+salario.getSalario()+"\"}";
		}
		
		
		return salarioFinal;
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
		
	//atualizando o salario
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarAgendamento(@RequestBody Salario salario,
			@PathVariable Long id ){
	
		Salario salarioAtualizado = salarioRepository.findById(id).get();

		
		BeanUtils.copyProperties(salario, salarioAtualizado, "id");
	
		salarioRepository.save(salarioAtualizado);
		
		return ResponseEntity.ok(salarioAtualizado);
	
	}
	
	
	
	
	
	
	
	
	
}
