package br.senai.sp.agendaja.modal;

import java.io.Serializable;
import java.util.Arrays;

public class Informacao extends Endereco implements Serializable{
  private String celular;
  private String email;
  private String senha;

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  @Override
  public String toString() {
    return "Informacao{" +
         "celular=" + celular +
         ", email='" + email + '\'' +
         ", senha='" + senha + '\'' +
         '}';
  }
}
