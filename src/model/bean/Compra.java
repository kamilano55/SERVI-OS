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
public class Compra {
    
   private int idcompra;
   private String dt_atual;
   private String dt_compra;
   private String numero_nota;
   private Double qtd;
   private String descricao;
   private String unidade;
   private Double preco;
   private String tip_compra;
   private Double vlr_entrada;
   private String dt_primeira;
   private Double vlr_primeira;
   private String dt_segunda;
   private Double vlr_segunda;
   private String dt_terceira;
   private Double vlr_terceira;
   
   private Fornecedor fornecedor;

    public Compra() {
    }

    public Compra(int idcompra, String dt_atual, String dt_compra, String numero_nota, Double qtd, String descricao, String unidade, Double preco, String tip_compra, Double vlr_entrada, String dt_primeira, Double vlr_primeira, String dt_segunda, Double vlr_segunda, String dt_terceira, Double vlr_terceira, Fornecedor fornecedor) {
        this.idcompra = idcompra;
        this.dt_atual = dt_atual;
        this.dt_compra = dt_compra;
        this.numero_nota = numero_nota;
        this.qtd = qtd;
        this.descricao = descricao;
        this.unidade = unidade;
        this.preco = preco;
        this.tip_compra = tip_compra;
        this.vlr_entrada = vlr_entrada;
        this.dt_primeira = dt_primeira;
        this.vlr_primeira = vlr_primeira;
        this.dt_segunda = dt_segunda;
        this.vlr_segunda = vlr_segunda;
        this.dt_terceira = dt_terceira;
        this.vlr_terceira = vlr_terceira;
        this.fornecedor = fornecedor;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public String getDt_atual() {
        return dt_atual;
    }

    public void setDt_atual(String dt_atual) {
        this.dt_atual = dt_atual;
    }

    public String getDt_compra() {
        return dt_compra;
    }

    public void setDt_compra(String dt_compra) {
        this.dt_compra = dt_compra;
    }

    public String getNumero_nota() {
        return numero_nota;
    }

    public void setNumero_nota(String numero_nota) {
        this.numero_nota = numero_nota;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTip_compra() {
        return tip_compra;
    }

    public void setTip_compra(String tip_compra) {
        this.tip_compra = tip_compra;
    }

    public Double getVlr_entrada() {
        return vlr_entrada;
    }

    public void setVlr_entrada(Double vlr_entrada) {
        this.vlr_entrada = vlr_entrada;
    }

    public String getDt_primeira() {
        return dt_primeira;
    }

    public void setDt_primeira(String dt_primeira) {
        this.dt_primeira = dt_primeira;
    }

    public Double getVlr_primeira() {
        return vlr_primeira;
    }

    public void setVlr_primeira(Double vlr_primeira) {
        this.vlr_primeira = vlr_primeira;
    }

    public String getDt_segunda() {
        return dt_segunda;
    }

    public void setDt_segunda(String dt_segunda) {
        this.dt_segunda = dt_segunda;
    }

    public Double getVlr_segunda() {
        return vlr_segunda;
    }

    public void setVlr_segunda(Double vlr_segunda) {
        this.vlr_segunda = vlr_segunda;
    }

    public String getDt_terceira() {
        return dt_terceira;
    }

    public void setDt_terceira(String dt_terceira) {
        this.dt_terceira = dt_terceira;
    }

    public Double getVlr_terceira() {
        return vlr_terceira;
    }

    public void setVlr_terceira(Double vlr_terceira) {
        this.vlr_terceira = vlr_terceira;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
