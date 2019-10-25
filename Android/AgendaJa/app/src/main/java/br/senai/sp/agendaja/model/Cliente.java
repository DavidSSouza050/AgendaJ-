package br.senai.sp.agendaja.Model;

import java.io.Serializable;

public class Cliente extends Informacao implements Serializable {
  private Integer idCliente;

  public Integer getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Integer idCliente) {
    this.idCliente = idCliente;
  }

  private String foto;
  private String nome;
  private String sobrenome;
  private String cpf;
  private String dataNascimento;
  private String sexo;

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  @Override
  public String toString() {
    return "Cliente{" +
         "idCliente=" + idCliente +
         ", foto=" + foto +
         ", nome='" + nome + '\'' +
         ", sobrenome='" + sobrenome + '\'' +
         ", cpf='" + cpf + '\'' +
         ", dataNascimento='" + dataNascimento + '\'' +
         ", sexo='" + sexo + '\'' +
         '}';
  }
}
