package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.Cidade;
import lumicode.agendaja.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	//pegando todas as cidades
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	private List<Cidade> getCidade(){
		return cidadeRepository.findAll();
	}

	//pegando so uma cidade
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{id}")
	private Cidade visualizarCidade(@PathVariable Long id) {
		return cidadeRepository.findById(id).get();
	}
	
}
