package br.senai.sp.agendaja.modal;

import java.io.Serializable;
import java.util.Arrays;

public class Informacao extends Endereco implements Serializable{
  private Integer celular;
  private Integer telefone;
  private String email;
  private String senha;

  public Integer getCelular() {
    return celular;
  }

  public void setCelular(Integer celular) {
    this.celular = celular;
  }

  public Integer getTelefone() {
    return telefone;
  }

  public void setTelefone(Integer telefone) {
    this.telefone = telefone;
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
         ", telefone=" + telefone +
         ", email='" + email + '\'' +
         ", senha='" + senha + '\'' +
         '}';
  }
}
