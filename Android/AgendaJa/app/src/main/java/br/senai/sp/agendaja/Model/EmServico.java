package br.senai.sp.agendaja.Model;

import com.google.gson.annotations.SerializedName;

public class EmServico {


 // @SerializedName("idEmServico")
  private int idEmServico;
  //@SerializedName("idEstabelecimento")
  private int idEstabelecimento;
  //@SerializedName("idFuncionario")
  private int idFuncionario;
  //@SerializedName("diaMes")
  private String diaMes;
  //@SerializedName("mes")
  private String mes;
  //@SerializedName("ano")
  private String ano;
  //@SerializedName("ocupadoInicio")
  private String horaInicio;
  //@SerializedName("ocupadoFim")
  private String horaFim;

  public int getIdEmServico() {
    return idEmServico;
  }

  public void setIdEmServico(int idEmServico) {
    this.idEmServico = idEmServico;
  }

  public int getIdEstabelecimento() {
    return idEstabelecimento;
  }

  public void setIdEstabelecimento(int idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }

  public int getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(int idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public String getDiaMes() {
    return diaMes;
  }

  public void setDiaMes(String diaMes) {
    this.diaMes = diaMes;
  }

  public String getMes() {
    return mes;
  }

  public void setMes(String mes) {
    this.mes = mes;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public String getHoraInicio() {
    return horaInicio;
  }

  public void setHoraInicio(String horaInicio) {
    this.horaInicio = horaInicio;
  }

  public String getHoraFim() {
    return horaFim;
  }

  public void setHoraFim(String horaFim) {
    this.horaFim = horaFim;
  }
}
