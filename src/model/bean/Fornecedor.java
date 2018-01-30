/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Master
 */
public class Fornecedor {
    
  private int idfornec;
  private String nome;
  private String cnpj;
  private String rua;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;
  private String referencia;
  private String gps;
  private String fone1;
  private String fone2;
  private String celular;
  private String url;
  private String email;
  private String contato;
  private Estado estado;
  private Cidade cidade;

    public Fornecedor() {
    }

    public Fornecedor(int idfornec, String nome, String cnpj, String rua, String numero, String complemento, String bairro, String cep, String referencia, String gps, String fone1, String fone2, String celular, String url, String email, String contato, Estado estado, Cidade cidade) {
        this.idfornec = idfornec;
        this.nome = nome;
        this.cnpj = cnpj;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.referencia = referencia;
        this.gps = gps;
        this.fone1 = fone1;
        this.fone2 = fone2;
        this.celular = celular;
        this.url = url;
        this.email = email;
        this.contato = contato;
        this.estado = estado;
        this.cidade = cidade;
    }

    public int getIdfornec() {
        return idfornec;
    }

    public void setIdfornec(int idfornec) {
        this.idfornec = idfornec;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
