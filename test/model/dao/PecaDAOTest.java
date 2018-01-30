/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Os;
import model.bean.Peca;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class PecaDAOTest {
    
    public PecaDAOTest() {
    }

    @Test
    @Ignore
    public void savePeca() {
        
        Peca peca = new Peca();
        
        Os os = new Os();
        os.setIdos(1);
        
        peca.setQtd(1.0);
        peca.setDescricao("eixo sem fim");
        peca.setUnidade("pç");
        peca.setPreco(25.40);
        peca.setOs(os);
        
        PecaDAO dao =new PecaDAO();
        
        if(dao.save(peca)){
            System.out.println("Salvo com sucesso!! ");
        }else{
            fail("Erro ao Salvar!! ");
        }
        
        
    }
    
    @Test
    @Ignore
    public void updatePeca() {
        
        Peca peca = new Peca();
        
        Os os = new Os();
        os.setIdos(2);
        
        peca.setQtd(1.0);
        peca.setDescricao("Placa de contrôle");
        peca.setUnidade("pç");
        peca.setPreco(80.20);
        peca.setOs(os);
        
        peca.setIdpeca(1);
        
        PecaDAO dao =new PecaDAO();
        
        if(dao.update(peca)){
            System.out.println("Atualizado com sucesso!! ");
        }else{
            fail("Erro ao Atualizar!! ");
        }
    }
    
    @Test
//    @Ignore
    public void deletePeca() {
        
        Peca peca = new Peca();
        
        peca.setIdpeca(2);
        
        PecaDAO dao =new PecaDAO();
        
        if(dao.delete(peca)){
            System.out.println("Excluido com sucesso!! ");
        }else{
            fail("Erro ao Excluir!! ");
        }
    }
}
