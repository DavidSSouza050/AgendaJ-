package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.EmServico;

import retrofit2.Call;

public class CadastarEmServico {
  private FileServiceEmServico fileService;


  public Call<EmServico> postEmServico(Integer idAgendamento){

    fileService =  RetrofitConfigCadastrarEmServico.getFileService();


    //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(idAgendamento));
//    RequestBody idEmServico = RequestBody.create(MediaType.parse("text/plain"),String.valueOf(idAgendamento));

    Call<EmServico> postEmServico = fileService.postEmServico(idAgendamento, MainActivity.TOKEN);

    return postEmServico;
  }

}
