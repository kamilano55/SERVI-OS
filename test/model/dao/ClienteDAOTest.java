/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cidade;
import model.bean.Cliente;
import model.bean.Contrato;
import model.bean.Estado;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }

    @Test
    @Ignore
    public void testSaveCliente() {
        Cliente cliente = new Cliente();
        
        Cidade cidade = new Cidade();
        cidade.setIdcidade(6604);
        
        Estado estado = new Estado();
        estado.setIdestado(19);
        
        Contrato contrato = new Contrato();
        contrato.setIdcontrato(1);
        
        
        cliente.setCnpjcpf("99999999999");
        cliente.setNome("Consermat");
        cliente.setRua("Rua José Lins do Rego");
        cliente.setNumero("955");
        cliente.setComplemento("casa");
        cliente.setBairro("Balneario");
        cliente.setCep("28940000");
        cliente.setReferencia("referência");
        cliente.setGps("999.999.99,999.999.99");
        cliente.setFone1("99999999999");
        cliente.setFone2("99999999999");
        cliente.setCelular("99999999999");
        cliente.setUrl("www.123123.com.br");
        cliente.setEmail("mi@mi.com.br");
        cliente.setFoto("123456");
        cliente.setObs("obs");
        
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setContrato(contrato);
        
        ClienteDAO dao = new ClienteDAO();
        
        if (dao.save(cliente)) {
            System.out.println("Salvo com sucesso!");
        } else {
            fail("Erro ao salvar!");
        }
    }
    
    @Test
    @Ignore
    public void testUpdateCliente() {
        Cliente cliente = new Cliente();
        
        Cidade cidade = new Cidade();
        cidade.setIdcidade(6639);
        
        Estado estado = new Estado();
        estado.setIdestado(19);
        
        Contrato contrato = new Contrato();
        contrato.setIdcontrato(1);
        
        
        cliente.setCnpjcpf("99999999999");
        cliente.setNome("Consermat");
        cliente.setRua("Rua José Lins do Rego");
        cliente.setNumero("955");
        cliente.setComplemento("casa");
        cliente.setBairro("Balneario");
        cliente.setCep("28940000");
        cliente.setReferencia("referência");
        cliente.setGps("999.999.99,999.999.99");
        cliente.setFone1("99999999999");
        cliente.setFone2("99999999999");
        cliente.setCelular("99999999999");
        cliente.setUrl("www.123123.com.br");
        cliente.setEmail("mi@mi.com.br");
        cliente.setFoto("123456");
        cliente.setObs("obs");
        cliente.setIdcliente(1);
        
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setContrato(contrato);
        
        
        ClienteDAO dao = new ClienteDAO();
        
        if (dao.update(cliente)) {
            System.out.println("Alterado com sucesso!");
        } else {
            fail("Erro ao Alterar!");
        }
    }
    
    @Test
    @Ignore
    public void testDeleteCliente() {
        Cliente cliente = new Cliente();
        
        cliente.setIdcliente(1);
        
        ClienteDAO dao = new ClienteDAO();
        
        if (dao.delete(cliente)) {
            System.out.println("Excluido com sucesso!");
        } else {
            fail("Erro ao excluir!");
        }
    }
    
    
}
