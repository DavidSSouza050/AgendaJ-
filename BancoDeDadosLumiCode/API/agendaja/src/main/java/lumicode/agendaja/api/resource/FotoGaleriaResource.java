package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lumicode.agendaja.api.model.Foto;
import lumicode.agendaja.api.repository.FotoRepository;
import lumicode.agendaja.api.storage.Disco;

@RestController
@RequestMapping("/galerias")
@CrossOrigin(origins = "*")
public class FotoGaleriaResource {
	@Autowired
	private Disco disco;
	
	@Autowired
	private FotoRepository fotoRepository;
	
	
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos;
	//****************************
	
	@GetMapping
	private List<Foto> getfoto() {
		return fotoRepository.findAll();
	}
	
	@GetMapping("/galeria/estabelecimento/{id}")
	private List<Foto> visualizarGaleria(@PathVariable Long id){
		return fotoRepository.galeriaEstabelecimento(id);
	}
	
	@PutMapping("/galeria/foto/{id}")
	private ResponseEntity<?> atualizarFoto(@RequestParam MultipartFile img,@PathVariable Long id){
		Foto foto = fotoRepository.findById(id).get();
		String fotoAntiga = foto.getFoto();
			
		disco.deletar(fotoAntiga);
		
		String novaFoto = disco.salvarFoto(img, "estabelecimento/"+foto.getEstabelecimento().getNomeEstabelecimento());
		
		foto.setFoto(novaFoto);
		
		fotoRepository.save(foto);
	
		return ResponseEntity.ok(foto);
	}
	
	

	@DeleteMapping("/galeria/excluir/foto/{id}")
	private ResponseEntity<?> deleteImgem(@PathVariable Long id) {
		Foto foto = fotoRepository.findById(id).get();
		
		disco.deletar(foto.getFoto());
		
		fotoRepository.delete(foto);
		
		return new ResponseEntity<String>("{\"mesage\":\"Foto Excluida\"}",HttpStatus.ACCEPTED);
	}
	
	
	
}
