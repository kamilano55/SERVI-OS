/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Estado;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Master
 */
public class EstadoDAOTest {

    public EstadoDAOTest() {
    }

    @Test
    @Ignore
    public void salvaEstado() {
        Estado est = new Estado();

        est.setNome("Rio de Janeiro");
        est.setUf("RJ");

        EstadoDAO dao = new EstadoDAO();

        if (dao.saveEstado(est)) {
            System.err.println("(Test)Salvo com sucesso!");
        } else {
            fail("(Test)Erro ao salvar");
        }

    }

    @Test
    @Ignore
    public void atualizaEstado() {
        Estado est = new Estado();

        est.setNome("São Paulo");
        est.setUf("SP");
        est.setIdestado(2);

        EstadoDAO dao = new EstadoDAO();

        if (dao.updateEstado(est)) {
            System.err.println("(Test)ATUALIZADO COM SUCESSO!");
        } else {
            fail("(Test)Erro ao salvar");
        }

    }

    @Test
    @Ignore
    public void deleteEstado() {

        Estado estado = new Estado();

        estado.setIdestado(1);

        EstadoDAO dao = new EstadoDAO();
        if (dao.deleteEstado(estado)) {
            System.err.println("(test)Excluido com sucesso");
//            System.out.println("(test)Atualizado com sucesso!");
        } else {
            fail("(test)Erro ao excluir!");
        }

    }

}
