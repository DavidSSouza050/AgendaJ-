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

import lumicode.agendaja.api.model.HorarioFuncionario;
import lumicode.agendaja.api.repository.HorarioFuncionarioRepository;

@RestController
@RequestMapping("/horariosFuncionarios")
@CrossOrigin(origins = "*")
public class HorarioFuncionarioResource {
	@Autowired
	private HorarioFuncionarioRepository horarioFuncionarioRepository;
	
	@GetMapping
	private List<HorarioFuncionario> getHorarioFuncionario(){
		return horarioFuncionarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private HorarioFuncionario visualizarHorarioFuncionario(@PathVariable Long id) {
		return horarioFuncionarioRepository.findById(id).get();
	}
	
	@GetMapping("/estabelecimento/{id}")
	private List<HorarioFuncionario> funcionariosPorEstabelecimento(@PathVariable Long id){
		Calendar calendar = Calendar.getInstance();
		Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		return horarioFuncionarioRepository.pegarHorarioDefuncionario(id, diaSemana);
	}
	
	@PostMapping
	private ResponseEntity<HorarioFuncionario> cadastrarFuncionario(
			@Validated @RequestBody HorarioFuncionario horarioFuncionario,
			HttpServletResponse response){
		
		HorarioFuncionario horarioFuncionarioSalvo = horarioFuncionarioRepository.save(horarioFuncionario);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(horarioFuncionario.getIdHorarioFuncionario())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(horarioFuncionarioSalvo);
		
	
	}
		
	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarCliente(@RequestBody HorarioFuncionario horarioFuncionario,
			@PathVariable Long id ){
	
		HorarioFuncionario horarioFuncionarioAtualizado = horarioFuncionarioRepository.findById(id).get();
		
		BeanUtils.copyProperties(horarioFuncionario, horarioFuncionarioAtualizado, "id");
	
		horarioFuncionarioRepository.save(horarioFuncionario);
		
		return ResponseEntity.ok(horarioFuncionarioAtualizado);
	
	}
	
	
	
	
	
	
	
	
	
}
