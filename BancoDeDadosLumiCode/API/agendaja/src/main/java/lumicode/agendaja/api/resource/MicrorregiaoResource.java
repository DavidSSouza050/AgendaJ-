package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.Microrregiao;
import lumicode.agendaja.api.repository.MicrorregiaoRepository;

@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {
	@Autowired
	private MicrorregiaoRepository microrregiaoRepository;
	
	//pegando todas as microrregiões
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	private List<Microrregiao> getMicrorregiao(){
		return microrregiaoRepository.findAll();
	}
	
	//pegando apenas uma miccrorregião
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{id}")
	private Microrregiao visualizarMicrorregiao(@PathVariable Long id) {
		return microrregiaoRepository.findById(id).get();
	}
	
	
}
