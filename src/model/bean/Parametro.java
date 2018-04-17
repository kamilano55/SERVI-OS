/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author MILANO
 */
public class Parametro {
    
    private String login;
    private String senha;
    private String tipo;
    private String data;

    public Parametro() {
    }

    public Parametro(String senha, String tipo, String data) {
        this.senha = senha;
        this.tipo = tipo;
        this.data = data;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getSenha(); //To change body of generated methods, choose Tools | Templates.
    }

}
