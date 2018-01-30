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
public class Tiposerv {
    private int id_tserv;
    private String descricao;
    private Double preco;
    private String sigla;

    public Tiposerv() {
    }

    public Tiposerv(int id_tserv, String descricao, Double preco, String sigla) {
        this.id_tserv = id_tserv;
        this.descricao = descricao;
        this.preco = preco;
        this.sigla = sigla;
    }

    public int getId_tserv() {
        return id_tserv;
    }

    public void setId_tserv(int id_tserv) {
        this.id_tserv = id_tserv;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
