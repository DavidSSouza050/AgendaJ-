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

import lumicode.agendaja.api.model.HorarioEstabelecimento;
import lumicode.agendaja.api.repository.HorarioEstabelecimentoRepository;

@RestController
@RequestMapping("/horariosEstabelecimentos")
@CrossOrigin(origins = "*")
public class HorarioEstabelecimentoResource {
	@Autowired
	private HorarioEstabelecimentoRepository horarioEstabelecimentoRepository;
	
	@GetMapping
	private List<HorarioEstabelecimento> getHorarioEstabelecimento(){
		return horarioEstabelecimentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private HorarioEstabelecimento visualizarhorarioEstabelecimento(@PathVariable Long id) {
		return horarioEstabelecimentoRepository.findById(id).get();
	}

	
	@PostMapping
	private ResponseEntity<HorarioEstabelecimento> cadastrarFuncionario(
			@Validated @RequestBody HorarioEstabelecimento horarioEstabelecimento,
			HttpServletResponse response){
		
		
		HorarioEstabelecimento horarioEstabelecimentoSalvo = horarioEstabelecimentoRepository.save(horarioEstabelecimento);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(horarioEstabelecimento.getIdHorarioEstabelecimento())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());

		//o cliente cadastrado ira ser retornado no body da resposta
		return ResponseEntity.created(uri).body(horarioEstabelecimentoSalvo);
		
	
	}
		
	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarFuncionario(@RequestBody HorarioEstabelecimento horarioEstabelecimento,
			@PathVariable Long id ){
	
		HorarioEstabelecimento horarioEstabelecimentoAtualizado = horarioEstabelecimentoRepository.findById(id).get();
		
		BeanUtils.copyProperties(horarioEstabelecimento, horarioEstabelecimentoAtualizado, "id");
	
		horarioEstabelecimentoRepository.save(horarioEstabelecimento);
		
		return ResponseEntity.ok(horarioEstabelecimentoAtualizado);
	
	}
	
	
}
