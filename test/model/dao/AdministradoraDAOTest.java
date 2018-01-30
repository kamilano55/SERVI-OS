/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import model.bean.Administradora;
import model.bean.Cidade;
import model.bean.Estado;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class AdministradoraDAOTest {
    
    public AdministradoraDAOTest() {
    }

    @Test
    @Ignore
    public void testSaveAdm() {
        
        Administradora adm = new Administradora();
        
        Cidade cidade = new Cidade();
        cidade.setIdcidade(6639);
        
        Estado estado = new Estado();
        estado.setIdestado(19);
        
        adm.setCnpjcpf("999.999.999./0001");
        adm.setNome("***********");
        adm.setRua("Av Assunção");
        adm.setNumero("333");
        adm.setComplemento("000");
        adm.setBairro("centro");
        adm.setCep("22222-222");
        adm.setReferencia("Em frente ao banco");
        adm.setGps("99999999,99999999");
        adm.setFone1("999999999");
        adm.setCelular("9999999999");
        adm.setUrl("www.teste.com.br");
        adm.setEmail("teste@teste.com.br");
        adm.setContato("Fulano");
        adm.setCargo("cargo");
        adm.setFoto("31311313");
        
        adm.setCidade(cidade);
        adm.setEstado(estado);
        
        AdministradoraDAO dao = new AdministradoraDAO();
        
        if (dao.saveAdm(adm)){
            System.out.println("Salvo com Sucesso!! ");
        }else{
            fail("Erro ao salvar!!");
        }
    }
    
    @Test
    @Ignore
    public void testUpdateAdm() {
        Administradora adm = new Administradora();
        
        Cidade cidade = new Cidade();
        cidade.setIdcidade(6635);
        
        Estado estado = new Estado();
        estado.setIdestado(19);
        
        
        adm.setCnpjcpf("999.999.999./0001");
        adm.setNome("Adjuve administradora");
        adm.setRua("Av Assunção");
        adm.setNumero("333");
        adm.setComplemento("000");
        adm.setBairro("centro");
        adm.setCep("22222-222");
        adm.setReferencia("Em frente ao banco");
        adm.setGps("99999999,99999999");
        adm.setFone1("999999999");
        adm.setCelular("9999999999");
        adm.setUrl("www.teste.com.br");
        adm.setEmail("teste@teste.com.br");
        adm.setContato("Fulano");
        adm.setCargo("cargo");
        adm.setFoto("31311313");
        adm.setIdadmin(2);
        
        adm.setCidade(cidade);
        adm.setEstado(estado);
        
        
        AdministradoraDAO dao = new AdministradoraDAO();
        
        if (dao.updateAdm(adm)){
            System.out.println("Atualizado com Sucesso!! ");
        }else{
            fail("Erro ao Atualizar!!");
        }
    }
    
    @Test
    @Ignore
    public void testDeleteAdm() {
        
        Administradora adm = new Administradora();
        
        adm.setIdadmin(2);
        
        AdministradoraDAO dao = new AdministradoraDAO();
        
        
        if (dao.deleteAdm(adm)) {
            System.out.println("Excluido com sucesso!");
        } else {
            fail("Erro ao excluir!");
        }
    }
    @Test
//    @Ignore
    public void listar(){
    
        AdministradoraDAO dao = new AdministradoraDAO();
        
        for(Administradora c: dao.readAllAdm()){
            System.out.println("nome:" + c.getNome());
            
        }
       
        
        
        
    }
    
}
