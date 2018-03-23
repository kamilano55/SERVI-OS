/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author Master
 */
public class Estado {
   private int idestado;
   private String nome;
   private String uf;

    public Estado() {
    }

    public Estado(int idestado, String nome, String uf) {
        this.idestado = idestado;
        this.nome = nome;
        this.uf = uf;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return getNome() + "(" + getUf() + ")";//To change body of generated methods, choose Tools | Templates.
    }

    
    
}
