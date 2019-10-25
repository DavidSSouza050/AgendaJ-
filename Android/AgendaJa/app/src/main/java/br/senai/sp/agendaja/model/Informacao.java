package br.senai.sp.agendaja.Model;

import java.io.Serializable;

public class Informacao extends Endereco implements Serializable{
  private String celular;
  private String email;
  private String senha;
  private String telefone;

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

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
