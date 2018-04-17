/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class LoginDAOTest {
    
    public LoginDAOTest() {
    }

    @Test
    @Ignore
    public void salvarUsuario() {
    
        Usuario usuario = new Usuario();
        
        usuario.setNome("Claudia");
        usuario.setFone("(22)99999-9999");
        usuario.setLogin("claudia");
        usuario.setSenha("222");
        
        UsuarioDAO dao = new UsuarioDAO();
        if(dao.saveUsuario(usuario)){
            System.out.println("Salvo com sucesso!");
        }else{
            fail("(Test)Erro ao salvar!");
        }
        
    }
    @Test
    @Ignore
    public void atualizarUsuario() {
    
        Usuario usuario = new Usuario();
        
        
        usuario.setNome("Milano");
        usuario.setFone("(22) 98802-7876");
        usuario.setLogin("milano");
        usuario.setSenha("usuario");
        
        usuario.setIduser(1);
        
        UsuarioDAO dao = new UsuarioDAO();
        
        if(dao.updateUsuario(usuario)){
            System.err.println("(test)Atualizado com sucesso");
//            System.out.println("(test)Atualizado com sucesso!");
        }else{
            fail("(test)Erro ao atualizar!");
        }
        
    }
    @Test
    @Ignore
    public void deleteUsuario() {
    
        Usuario usuario = new Usuario();
        usuario.setIduser(12);
        
        UsuarioDAO dao = new UsuarioDAO();
        if(dao.deleteUsuario(usuario)){
            System.err.println("(test)Excluido com sucesso");
//            System.out.println("(test)Atualizado com sucesso!");
        }else{
            fail("(test)Erro ao excluir!");
        }
        
    }
    
}
