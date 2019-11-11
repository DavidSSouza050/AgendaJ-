package br.senai.sp.agendaja.Model;

import java.io.Serializable;
import java.util.List;

public class Estabelecimento extends Endereco implements Serializable {

  private int idEstabelecimento;
  private String cnpj;
  private String razaoSocial;
  private String nomeEstabelecimento;
  private String celular;
  private String foto;
  private String telefone;
  private String email;
  private String senha;
  private List<Horario> horarios;

  public int getIdEstabelecimento() {
    return idEstabelecimento;
  }

  public void setIdEstabelecimento(int idEstabelecimento) {
    this.idEstabelecimento = idEstabelecimento;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getNomeEstabelecimento() {
    return nomeEstabelecimento;
  }

  public void setNomeEstabelecimento(String nomeEstabelecimento) {
    this.nomeEstabelecimento = nomeEstabelecimento;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
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

  public List<Horario> getHorarios() {
    return horarios;
  }

  public void setHorarios(List<Horario> horarios) {
    this.horarios = horarios;
  }

  @Override
  public String toString() {
    return "Estabelecimento{" +
         "idEstabelecimento=" + idEstabelecimento +
         ", cnpj='" + cnpj + '\'' +
         ", razaoSocial='" + razaoSocial + '\'' +
         ", nomeEstabelecimento='" + nomeEstabelecimento + '\'' +
         ", celular='" + celular + '\'' +
         ", foto='" + foto + '\'' +
         ", telefone='" + telefone + '\'' +
         ", email='" + email + '\'' +
         ", senha='" + senha + '\'' +
         ", horarios=" + horarios +
         '}';
  }
}
