/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Tiposerv;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class TiposervDAOTest {
    
    public TiposervDAOTest() {
    }

    @Test
    @Ignore
    public void saveTiposerv() {
        
        Tiposerv ts = new Tiposerv();
        
        ts.setDescricao("Manut. prev.sem contrato");
        ts.setPreco(20.00);
        ts.setSigla("MPSC");
        
        TiposervDAO dao = new TiposervDAO();
        
        if(dao.save(ts)){
            System.out.println("Salvo com sucesso!! "); 
        }else{
            fail("Erro ao salvar!! ");
        }
    }
    
    @Test
    @Ignore
    public void updateTiposerv() {
        
        Tiposerv ts = new Tiposerv();
        
        ts.setDescricao("Manut.cor.com contrato");
        ts.setPreco(20.00);
        ts.setSigla("MCCC");
        ts.setId_tserv(1);
        
        TiposervDAO dao = new TiposervDAO();
        
        if(dao.update(ts)){
            System.out.println("Atualizado com sucesso!! "); 
        }else{
            fail("Erro ao Atualizar!! ");
        }
    }
    
    @Test
//    @Ignore
    public void deleteTiposerv() {
        
        Tiposerv ts = new Tiposerv();
        
        ts.setId_tserv(4);
        
        TiposervDAO dao = new TiposervDAO();
        
        if(dao.delete(ts)){
            System.out.println("Excluido com sucesso!! "); 
        }else{
            fail("Erro ao Excluir!! ");
        }
    }
    
}
