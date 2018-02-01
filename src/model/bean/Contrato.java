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
public class Contrato {
    
    private int idcontrato;
    private String tipodesc;
    private String contexto;
    private double valor;

    public Contrato() {
    }

    public Contrato(int idcontrato, String tipodesc, String contexto, double valor) {
        this.idcontrato = idcontrato;
        this.tipodesc = tipodesc;
        this.contexto = contexto;
        this.valor = valor;
    }

    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getTipodesc() {
        return tipodesc;
    }

    public void setTipodesc(String tipodesc) {
        this.tipodesc = tipodesc;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    
}
