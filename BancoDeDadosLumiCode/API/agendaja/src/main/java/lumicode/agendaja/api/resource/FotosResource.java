package lumicode.agendaja.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lumicode.agendaja.api.model.Cliente;
import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.model.dto.FuncionarioDTO;
import lumicode.agendaja.api.repository.ClienteRepository;
import lumicode.agendaja.api.repository.EstabelecimentoRepository;
import lumicode.agendaja.api.repository.dto.FuncionarioDTORepository;
import lumicode.agendaja.api.storage.Disco;
@RestController
@RequestMapping("/fotos")
public class FotosResource {
	//Atribuindo variaveis 
	@Autowired
	private Disco disco;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	@Autowired	
	private ClienteRepository clienteRepository;
	// funcionario
	@Autowired
	private FuncionarioDTORepository funcionarioDTORepository;
	/****/
	//varivavel da raiz da imagem
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	
	//gravando imagem do estabelecimento
	@PostMapping("/estabelecimento")
	public Estabelecimento uploadRestaurante(@RequestParam MultipartFile foto, @RequestParam Long id) {
		Estabelecimento estabelecimento = new Estabelecimento();
		//verificando se o cliente ja tem uma imagem se tiver ele excluir primeiro e depois cria a nova
		estabelecimento = estabelecimentoRepository.getById(id);
		String caminho = raiz+estabelecimento.getFoto();
		if(caminho != null) {
			disco.deletar(caminho);
		}
		
		String localFoto = disco.salvarFoto(foto, "estabelecimento");
		
		if(localFoto != null) {
			estabelecimento.setFoto(localFoto);
			
			//
			estabelecimentoRepository.save(estabelecimento);
		}
		
		return estabelecimento;
	}
	
	//gravando a imagem do cliente 
	@PostMapping("/cliente")
	public Cliente uploadCliente(@RequestParam MultipartFile foto, @RequestParam Long id) {
		Cliente cliente = new Cliente();
		//verificando se o cliente ja tem uma imagem se tiver ele excluir primeiro e depois cria a nova
		cliente = clienteRepository.getById(id);
		String caminho = raiz+cliente.getFotoCliente();
		if(caminho != null) {
			disco.deletar(caminho);
		}
		
		String localFoto = disco.salvarFoto(foto, "cliente");
		
		
		if(localFoto != null) {
			cliente.setFotoCliente(localFoto);
			//setando o cliente com uma imagem
			clienteRepository.save(cliente);
		}
		
		return cliente;
	}
	
	
	//gravando a imagem do funcionario
	@PostMapping("/funcionario")
	public FuncionarioDTO uploadFuncionario(@RequestParam MultipartFile foto, @RequestParam Long id) {
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		//verificando se o cliente ja tem uma imagem se tiver ele excluir primeiro e depois cria a nova
		funcionarioDTO = funcionarioDTORepository.findById(id).get();
		String fotoAntiga = raiz+funcionarioDTO.getFoto();
		if(fotoAntiga != null) {
			disco.deletar(fotoAntiga);
		}
		
		String localFoto = disco.salvarFoto(foto, "funcionario");
		
		
		if(localFoto != null) {
			funcionarioDTO.setFoto(localFoto);
			
			//setando o cliente com uma imagem
			funcionarioDTORepository.save(funcionarioDTO);
		}
		
		return funcionarioDTO;
	}
	
	
}
