package lumicode.agendaja.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lumicode.agendaja.api.model.Cliente;
import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.repository.ClienteRepository;
import lumicode.agendaja.api.repository.EstabelecimentoRepository;
import lumicode.agendaja.api.storage.Disco;

@RestController
@RequestMapping("/foto")
public class FotosResource {
	//Atribuindo variaveis 
	@Autowired
	private Disco disco;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	@Autowired	
	private ClienteRepository clienteRepository;
	
	//gravando imagem do estabelecimento
	@PostMapping("/estabelecimento")
	public Estabelecimento uploadRestaurante(@RequestParam MultipartFile foto, @RequestParam Long id) {
		Estabelecimento estabelecimento = new Estabelecimento();
		String localFoto = disco.salvarFoto(foto, "estabelecimento");
		
		if(localFoto != null) {
			estabelecimento = estabelecimentoRepository.getById(id);
			estabelecimento.setFoto(localFoto);
			estabelecimentoRepository.save(estabelecimento);
		}
		
		return estabelecimento;
	}
	
	//gravando a imagem do cliente 
	
	@PostMapping("/cliente")
	public Cliente uploadCliente(@RequestParam MultipartFile foto, @RequestParam Long id) {
		Cliente cliente = new Cliente();
		String localFoto = disco.salvarFoto(foto, "cliente");
		
		if(localFoto != null) {
			cliente = clienteRepository.getById(id);
			cliente.setFotoCliente(localFoto);
			clienteRepository.save(cliente);
		}
		
		return cliente;
	}
	
	
	
	
}
