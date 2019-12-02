package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.EmServico;

import retrofit2.Call;

public class CadastarEmServico {
  private FileServiceEmServico fileService;


  public Call<EmServico> postEmServico(Integer idAgendamento){

    fileService =  RetrofitConfigCadastrarEmServico.getFileService();

    Call<EmServico> postEmServico = fileService.postEmServico(idAgendamento, MainActivity.TOKEN);

    return postEmServico;
  }

}
