package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.ClienteEndereco;
import lumicode.agendaja.api.repository.ClienteEnderecoRepository;
import lumicode.agendaja.api.utils.ConverterDatas;


@RestController
@RequestMapping("/clienteEndereco")
public class ClienteEnderecoResource {
	
	@Autowired
	private ClienteEnderecoRepository clienteEnderecoRepository;
	
	//pegando todos os clientes e endereços juntos
	@GetMapping
	private List<ClienteEndereco> getClienteEndereco(){
		return clienteEnderecoRepository.findAll();
	}
	
	//pegando apenas um endereco e cliete
	@GetMapping("/{id}")
	private ClienteEndereco visualizarClienteEndereco(@PathVariable Long id) {
		return clienteEnderecoRepository.findById(id).get();
	}
	
	//cadastrando relação entre cliente e endereco
	@PostMapping
	private ResponseEntity<ClienteEndereco> salvarRelacaoClienteEndereco(@RequestBody ClienteEndereco clienteEndereco,
			HttpServletResponse response){
		ConverterDatas converterDatas = new ConverterDatas();
		//setando a data de criação do relacionamento
		clienteEndereco.setCriadoEm(converterDatas.dataAtual());
		clienteEndereco.setAtualizadoEm(converterDatas.dataAtual());
		//********************
		ClienteEndereco clienteEnderecoSalvo = clienteEnderecoRepository.save(clienteEndereco);
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(clienteEndereco.getIdClienteEndereco())
				  .toUri();
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(clienteEnderecoSalvo);
	}
	
	
	//atualizar a relação cliente endereco
	@PutMapping("/{id}")
	private ResponseEntity<ClienteEndereco> atualizarCliente(@RequestBody ClienteEndereco clienteEndereco,
				@PathVariable Long id){
		ClienteEndereco clienteEnderecoAtualizado = clienteEnderecoRepository.findById(id).get();
		//setando a data de atualização
		ConverterDatas converterDatas = new ConverterDatas();
		clienteEndereco.setAtualizadoEm(converterDatas.dataAtual());
		String criadoEm =  clienteEnderecoAtualizado.getCriadoEm();
		clienteEndereco.setCriadoEm(criadoEm);
		//******
		BeanUtils.copyProperties(clienteEndereco, clienteEnderecoAtualizado, "id");
		
		clienteEnderecoRepository.save(clienteEndereco);
		
		return ResponseEntity.ok(clienteEnderecoAtualizado);
	}
	
	
}
