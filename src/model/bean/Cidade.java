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
public class Cidade {
    
    private int idcidade;
    private String nome;
    private Estado estado;
    

    public Cidade() {
    }

    public Cidade(int idcidade, String nome, Estado estado) {
        this.idcidade = idcidade;
        this.nome = nome;
        this.estado = estado;
    }
    
    public int getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(int idcidade) {
        this.idcidade = idcidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getNome();//To change body of generated methods, choose Tools | Templates.
    }
    
    
}
