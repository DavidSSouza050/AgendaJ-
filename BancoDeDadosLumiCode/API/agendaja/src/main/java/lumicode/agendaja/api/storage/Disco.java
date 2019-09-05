package lumicode.agendaja.api.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lumicode.agendaja.api.utils.ConverterDatas;

@Component
public class Disco {
	//desendo quis são as variaveis de anbiente feitas na applocation.properties
	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos;
	//****************************
	
	
	public void salvarFoto(MultipartFile foto) {
		this.salvar(this.diretorioFotos, foto);
	}
	
	//metodo de tratamento para salvar a imagem
	public String salvar(String diretorio, MultipartFile arquivo) {
		String local = null;
		String caminho = null;
		
		Path diretorioPath = Paths.get(this.raiz, diretorio); 
		//criando o nome do arquivo 
		ConverterDatas data = new ConverterDatas();
		local = (data.dataAtual() +"_"+ arquivo.getOriginalFilename());
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
	
	
	
}
