package lumicode.agendaja.api.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Disco {
	//desendo quis são as variaveis de anbiente feitas na applocation.properties
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos;
	//****************************
	
	
	public String salvarFoto(MultipartFile foto, String pasta) {
		return this.salvar(this.diretorioFotos+"/"+pasta, foto);
	}
	
	//metodo de tratamento para salvar a imagem
	public String salvar(String diretorio, MultipartFile arquivo) {
		String local = null;
		String caminho = null;
		
		Path diretorioPath = Paths.get(this.raiz, diretorio); 
		//criando o nome do arquivo 
		local = (System.currentTimeMillis() +"_"+ arquivo.getOriginalFilename());
		//
		//onde o arquivo vai ser salvo mais o nome do arquivo
		Path arquivoPath = diretorioPath.resolve(local);
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			caminho = (raiz +"/" + diretorio + "/" + local);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return caminho;
		
	}
	
	
	public void deletar(String caminho) {
		File apaga = new File(caminho);
		
		if(apaga.exists() && apaga.isFile()) {
			
			apaga.delete();
				 
		}
		
	}
	
	
	
}
