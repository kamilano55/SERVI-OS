/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cidade;
import model.bean.Estado;
import model.bean.Fornecedor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class FornecedorDAOTest {
    
    public FornecedorDAOTest() {
    }

    @Test
    @Ignore
    public void saveFornec() {
        
        Fornecedor fornecedor = new Fornecedor();
        Estado estado = new Estado();
        estado.setIdestado(19);
        
        Cidade cidade = new Cidade();
        cidade.setIdcidade(6635);
        
        fornecedor.setNome("Casa Bahia");
        fornecedor.setCnpj("1313131313");
        fornecedor.setRua("Subindo sempre");
        fornecedor.setNumero("999");
        fornecedor.setComplemento("2222");
        fornecedor.setBairro("alto");
        fornecedor.setCep("55555-000");
        fornecedor.setReferencia("Referência");
        fornecedor.setGps("gps");
        fornecedor.setFone1("fone1");
        fornecedor.setFone2("fone2");
        fornecedor.setCelular("celular");
        fornecedor.setUrl("www");
        fornecedor.setEmail("email");
        fornecedor.setContato("contato");
        fornecedor.setEstado(estado);
        fornecedor.setCidade(cidade);
        
        FornecedorDAO dao = new FornecedorDAO();
        
        if(dao.save(fornecedor)){
            System.out.println("Salvo com sucesso!! ");
        }else{
            fail("Erro ao Salvar!! ");
        }
    }
    
    @Test
    @Ignore
    public void updateFornec() {
        
        Fornecedor fornecedor = new Fornecedor();
        Estado estado = new Estado();
        estado.setIdestado(19);
        
        Cidade cidade = new Cidade();
        cidade.setIdcidade(6639);
        
        fornecedor.setIdfornec(1);
        
        fornecedor.setNome("Casa da Banha");
        fornecedor.setCnpj("1313131313");
        fornecedor.setRua("Subindo sempre");
        fornecedor.setNumero("999");
        fornecedor.setComplemento("2222");
        fornecedor.setBairro("alto");
        fornecedor.setCep("55555-000");
        fornecedor.setReferencia("Referência");
        fornecedor.setGps("gps");
        fornecedor.setFone1("fone1");
        fornecedor.setFone2("fone2");
        fornecedor.setCelular("celular");
        fornecedor.setUrl("www");
        fornecedor.setEmail("email");
        fornecedor.setContato("contato");
        fornecedor.setEstado(estado);
        fornecedor.setCidade(cidade);
        
        FornecedorDAO dao = new FornecedorDAO();
        
        if(dao.update(fornecedor)){
            System.out.println("Atualizado com sucesso!! ");
        }else{
            fail("Erro ao Atualizar!! ");
        }
    }
    
    
    @Test
//    @Ignore
    public void deleteFornec() {
        
        Fornecedor fornecedor = new Fornecedor();
        
        
        fornecedor.setIdfornec(1);
        
        FornecedorDAO dao = new FornecedorDAO();
        
        if(dao.delete(fornecedor)){
            System.out.println("Excluido com sucesso!! ");
        }else{
            fail("Erro ao Excluir!! ");
        }
    }
}
