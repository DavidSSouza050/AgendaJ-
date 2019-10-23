package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.repository.EstabelecimentoRepository;
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	//pegando todos os estabelecimentos
	@GetMapping
	private List<Estabelecimento> getEstabelecimento(){
		return estabelecimentoRepository.findAll();
	}
	
	//pegando apenas um estabelecimento
	@GetMapping("/{id}")
	private Estabelecimento visualizarEstabelecimento(@PathVariable Long id) {
		return estabelecimentoRepository.findById(id).get();
	}
	
	//Login estabelecimento
	@PostMapping("/login")
	private ResponseEntity<?> loginEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
		Estabelecimento estabelecimentologado = estabelecimentoRepository.loginEstabelecimento(estabelecimento.getEmail(), estabelecimento.getSenha());
		
		if( estabelecimentologado != null) {
			return ResponseEntity.ok(estabelecimentologado);
		}else {
			return  new ResponseEntity<String>("{\"mensage\": \"E-mail ou Senha incorreto\"}",HttpStatus.BAD_REQUEST);
		}
			
	}
	
	
	//cadastrando um estabelecimento
	@PostMapping
	private ResponseEntity<?> salvarEstabelecimento(
			@Validated @RequestBody Estabelecimento estabelecimento,
		HttpServletResponse response){
		
		if(estabelecimentoRepository.verificarEmail(estabelecimento.getEmail()) != null  ) {
			return  new ResponseEntity<String>("{\"mensage\": \"E-mail Já cadastrado\"}",HttpStatus.BAD_REQUEST);
		}
		
		if(estabelecimentoRepository.verificarCNJP(estabelecimento.getCnpj()) != null){
			return  new ResponseEntity<String>("{\"mensage\": \"Cnpj Já cadastrado\"}",HttpStatus.BAD_REQUEST);
		}
		
		if(estabelecimentoRepository.verificarRazaoSocial(estabelecimento.getRazaoSocial()) != null) {
			return  new ResponseEntity<String>("{\"mensage\": \"Razão Social Já cadastrado\"}",HttpStatus.BAD_REQUEST);
		}
		
		if(estabelecimentoRepository.verificarNomeEstebelecimento(estabelecimento.getNomeEstabelecimento()) != null) {
			return  new ResponseEntity<String>("{\"mensage\": \"Nome Do Estabelecimeto Já Existente\"}",HttpStatus.BAD_REQUEST);
		}
		
		try {
			//setando data atual
			ConverterDatas converterDatas = new ConverterDatas();
			estabelecimento.setCriadoEm(converterDatas.dataAtual());
			estabelecimento.setAtualizadoEm(converterDatas.dataAtual());
			//*********
			Estabelecimento estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimento);
			URI uri = ServletUriComponentsBuilder
					  .fromCurrentRequestUri()
					  .path("/{id}")
					  .buildAndExpand(estabelecimento.getIdEstabelecimento())
					  .toUri();
			
			response.addHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(estabelecimentoSalvo);
		}catch (Exception e) {
			return  new ResponseEntity<String>("{\"mensage\": \"Não foi possivel cadastrar\"}",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	//Atualizar o estabelecimento
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarEstabelecimento(@Validated @RequestBody 
			Estabelecimento estabelecimento, @PathVariable Long id){
	
		try {
			Estabelecimento estabelecimentoAtualizado = estabelecimentoRepository
						.findById(id).get();
			
			//setando data atual
			ConverterDatas converterDatas = new ConverterDatas();
			estabelecimento.setAtualizadoEm(converterDatas.dataAtual());
			String criadoEm = estabelecimentoAtualizado.getCriadoEm();
			estabelecimento.setCriadoEm(criadoEm);
			//*********
			//verificando se tem imagem
			String img = estabelecimentoAtualizado.getFoto();
			if(estabelecimentoRepository.verificarImagem(img, id) != null){
				estabelecimento.setFoto(img);
			}
			
			BeanUtils.copyProperties(estabelecimento, estabelecimentoAtualizado, "id");
			
			estabelecimentoRepository.save(estabelecimento);
					
			return ResponseEntity.ok(estabelecimentoAtualizado);
		}catch (Exception e) {
			return  new ResponseEntity<String>("{mensage: 'CNPJ ou Razão social já cadastrado e verefique o CNPJ'}",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
