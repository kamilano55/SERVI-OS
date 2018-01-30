/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cliente;
import model.bean.Equipamento;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class EquipamentoDAOTest {
    
    public EquipamentoDAOTest() {
    }

    @Test
    @Ignore
    public void SaveEquipamento() {
        
        Equipamento equipamento = new Equipamento();
        Cliente cliente = new Cliente();
        cliente.setIdcliente(1);
        
        equipamento.setNome("Portão pivotante");
        equipamento.setFabricante("Motorola");
        equipamento.setModelo("22");
        equipamento.setDt_fabric("**/**/****");
        equipamento.setDt_instal("**/**/****");
        equipamento.setHistor_inicial("Histórico inicial");
        equipamento.setGap_manut("30");
        equipamento.setDt_ultmanut("**/**/****");
        equipamento.setFoto("2132156");
        equipamento.setCliente(cliente);
        
        EquipamentoDAO dao = new EquipamentoDAO();
        
        if(dao.save(equipamento)){
            System.out.println("Salvo com sucesso!! ");
        }else{
            fail("Erro ao Salvar!! ");
        }
    }
    
    @Test
    @Ignore
    public void UpdateEquipamento() {
        
        Equipamento equipamento = new Equipamento();
        Cliente cliente = new Cliente();
        cliente.setIdcliente(1);
        
        equipamento.setNome("Portão deslizante");
        equipamento.setFabricante("Motorola");
        equipamento.setModelo("22");
        equipamento.setDt_fabric("**/**/****");
        equipamento.setDt_instal("**/**/****");
        equipamento.setHistor_inicial("Histórico inicial");
        equipamento.setGap_manut("30");
        equipamento.setDt_ultmanut("**/**/****");
        equipamento.setFoto("2132156");
        equipamento.setCliente(cliente);
        equipamento.setIdequip(1);
        
        EquipamentoDAO dao = new EquipamentoDAO();
        
        if(dao.update(equipamento)){
            System.out.println("Atualizado com sucesso!! ");
        }else{
            fail("Erro ao Atualizar!! ");
        }
    }
    
    
    @Test
    @Ignore
    public void DeleteEquipamento() {
        
        Equipamento equipamento = new Equipamento();
        
        equipamento.setIdequip(3);
        
        EquipamentoDAO dao = new EquipamentoDAO();
        
        if(dao.delete(equipamento)){
            System.out.println("Excluido com sucesso!! ");
        }else{
            fail("Erro ao Excluir!! ");
        }
    }
    
    
}
