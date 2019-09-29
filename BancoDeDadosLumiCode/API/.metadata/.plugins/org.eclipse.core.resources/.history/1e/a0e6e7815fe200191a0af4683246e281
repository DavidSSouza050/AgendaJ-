package lumicode.agendaja.api.resource;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.TipoEndereco;
import lumicode.agendaja.api.repository.TipoEnderecoRepository;

@RestController
@RequestMapping("/tipoEndereco")
public class TipoEnderecoResource {
	@Autowired
	private TipoEnderecoRepository tipoEnderecoRepository;
	
	@GetMapping
	private List<TipoEndereco> getTipoEndereco(){
		return tipoEnderecoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private TipoEndereco visualizarTipoEndereco(@PathVariable Long id) {
		return tipoEnderecoRepository.findById(id).get();
	}
	
	
	
}
