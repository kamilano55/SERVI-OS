/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cidade;
import model.bean.Estado;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class CidadeDAOTest {

    public CidadeDAOTest() {
    }

    @Test
    @Ignore
    public void salvaCidade() {
        Cidade cidade = new Cidade();
        
        Estado estado = new Estado();
        estado.setIdestado(19);

        cidade.setNome("São Pedro da Aldeia");
        cidade.setEstado(estado);
//        

        CidadeDAO dao = new CidadeDAO();

        if (dao.saveCidade(cidade)) {
            System.out.println("Salvo com sucesso!");
        } else {
            fail("Erro ao salva!");
        }
    }

    @Test
    @Ignore
    public void atualizarCidade() {
        Cidade cidade = new Cidade();

        Estado estado = new Estado();
        estado.setIdestado(1);

        cidade.setNome("Bujari");
        cidade.setEstado(estado);
        cidade.setIdcidade(2);

        CidadeDAO dao = new CidadeDAO();

        if (dao.updateCidade(cidade)) {
            System.out.println("Atualizado com sucesso!");
        } else {
            System.err.println("Erro ao atualizar!");
        }
    }

    @Test
    @Ignore
    public void deletarcidade() {

        Cidade cidade = new Cidade();

        cidade.setIdcidade(2);

        CidadeDAO dao = new CidadeDAO();

        if (dao.deleteCidade(cidade)) {
            System.out.println("Excluido com sucesso!");

        } else {
            System.err.println("Erro ao Excluir!");
        }
    }
}
