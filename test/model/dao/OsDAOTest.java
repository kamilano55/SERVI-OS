/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cliente;
import model.bean.Equipamento;
import model.bean.Os;
import model.bean.Tiposerv;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class OsDAOTest {
    
    public OsDAOTest() {
    }

    @Test
    @Ignore
    public void SaveOs() {
        
        Os os = new Os();
        
        Tiposerv tipserv = new Tiposerv();
        tipserv.setId_tserv(2);
        
        Cliente cliente = new Cliente();
        cliente.setIdcliente(1);
        
        Equipamento equipamento = new Equipamento();
        equipamento.setIdequip(2);
        
        os.setNome_cliente("Enterdata");
        os.setNome_equip("PORTÃO PIVOTANTE");
        os.setDefeito("Parado");
        os.setTecnico("zé");
        os.setHr_inic("08:30");
        os.setServico("REPARO NA FIAÇÃO");
        os.setUso_peca(Boolean.FALSE);
        os.setEquip_retirado(Boolean.FALSE);
        os.setObs("teste");
        os.setAberta_fech(Boolean.TRUE);
        os.setHr_fim("15:30");
        
        os.setTiposerv(tipserv);
        os.setCliente(cliente);
        os.setEquipamento(equipamento);
        
        OsDAO dao = new OsDAO();
        
        if(dao.save(os)){
            System.out.println("Salvo com sucesso!! ");
            
        }else{
            fail("Erro ao Salvar!! ");
        }
        
    }
    
    @Test
//    @Ignore
    public void UpdateOs() {
        
        Os os = new Os();
        
        Tiposerv tipserv = new Tiposerv();
        tipserv.setId_tserv(1);
        
        Cliente cliente = new Cliente();
        cliente.setIdcliente(1);
        
        Equipamento equipamento = new Equipamento();
        equipamento.setIdequip(2);
        
        os.setNome_cliente("Enterdata");
        os.setNome_equip("Portão deslizante");
        os.setDefeito("Parado");
        os.setTecnico("zé");
        os.setHr_inic("08:30");
        os.setServico("Limpeza e lubrificação");
        os.setUso_peca(Boolean.FALSE);
        os.setEquip_retirado(Boolean.FALSE);
        os.setObs("teste com o cliente");
        os.setAberta_fech(Boolean.FALSE);
        os.setHr_fim("15:30");
        
        os.setTiposerv(tipserv);
        os.setCliente(cliente);
        os.setEquipamento(equipamento);
        os.setIdos(3);
        
        OsDAO dao = new OsDAO();
        
        if(dao.update(os)){
            System.out.println("Atualizado com sucesso!! ");
            
        }else{
            fail("Erro ao Atualizar!! ");
        }
    }
    
    @Test
    @Ignore
    public void DeleteOs() {
        
        Os os = new Os();
        
        os.setIdos(4);
        
        OsDAO dao = new OsDAO();
        
        if(dao.delete(os)){
            System.out.println("Excluido com sucesso!! ");
            
        }else{
            fail("Erro ao Excluir!! ");
        }
    }
}
