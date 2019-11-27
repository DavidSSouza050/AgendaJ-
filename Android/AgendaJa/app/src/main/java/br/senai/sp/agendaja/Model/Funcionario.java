package br.senai.sp.agendaja.Model;

import java.io.Serializable;

public class Funcionario implements Serializable{
  private int idFuncionario;
  private String fotoFuncionario;
  private String emailFuncionario;
  private String senhaFuncionario;
  private String nomeFuncionario;

  public int getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(int idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public String getFotoFuncionario() {
    return fotoFuncionario;
  }

  public void setFotoFuncionario(String fotoFuncionario) {
    this.fotoFuncionario = fotoFuncionario;
  }

  public String getEmailFuncionario() {
    return emailFuncionario;
  }

  public void setEmailFuncionario(String emailFuncionario) {
    this.emailFuncionario = emailFuncionario;
  }

  public String getSenhaFuncionario() {
    return senhaFuncionario;
  }

  public void setSenhaFuncionario(String senhaFuncionario) {
    this.senhaFuncionario = senhaFuncionario;
  }

  public String getNomeFuncionario() {
    return nomeFuncionario;
  }

  public void setNomeFuncionario(String nomeFuncionario) {
    this.nomeFuncionario = nomeFuncionario;
  }

  @Override
  public String toString() {
    return "Funcionario{" +
         "idFuncionario=" + idFuncionario +
         ", fotoFuncionario='" + fotoFuncionario + '\'' +
         ", emailFuncionario='" + emailFuncionario + '\'' +
         ", senhaFuncionario='" + senhaFuncionario + '\'' +
         ", nomeFuncionario='" + nomeFuncionario + '\'' +
         '}';
  }
}
