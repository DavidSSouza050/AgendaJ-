package br.senai.sp.agendaja.Model;

import java.io.Serializable;

public class Endereco implements Serializable {
  private String cep;
  private String logradouro;
  private Integer numero;
  private String bairro;
  private String cidade;
  private String estado;
  private String codIBGE;
  private Integer idEndereco;

  public Integer getIdEndereco() {
    return idEndereco;
  }

  public void setIdEndereco(Integer idEndereco) {
    this.idEndereco = idEndereco;
  }

  public String getCodIBGE() {
    return codIBGE;
  }

  public void setCodIBGE(String codIBGE) {
    this.codIBGE = codIBGE;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  @Override
  public String toString() {
    return "Endereco{" +
         "cep='" + cep + '\'' +
         ", logradouro='" + logradouro + '\'' +
         ", numero=" + numero +
         ", bairro='" + bairro + '\'' +
         ", cidade='" + cidade + '\'' +
         ", estado='" + estado + '\'' +
         ", codIBGE='" + codIBGE + '\'' +
         ", idEndereco=" + idEndereco +
         '}';
  }
}
