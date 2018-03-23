/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Contrato;
import model.bean.Equipamento;

/**
 *
 * @author Master
 */
public class EquipamentoDAO {

    private Connection con = null;

    public EquipamentoDAO() {

        con = connection.ConnectionFactory.getConnection();

    }

    public boolean saveEquipamento(Equipamento equipamento) {

        String sql = "INSERT INTO equipamento(nome, fabricante, modelo, dt_fabric, dt_instal, histor_inicial, gap_manut, dt_ultimanut,"
                + " foto, cliente_idcliente, contrato_idcontrato) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getFabricante());
            stmt.setString(3, equipamento.getModelo());
            stmt.setString(4, equipamento.getDt_fabric());
            stmt.setString(5, equipamento.getDt_instal());
            stmt.setString(6, equipamento.getHistor_inicial());
            stmt.setString(7, equipamento.getGap_manut());
            stmt.setString(8, equipamento.getDt_ultmanut());
            stmt.setString(9, equipamento.getFoto());
            stmt.setInt(10, equipamento.getCliente().getIdcliente());
            stmt.setInt(11, equipamento.getContrato().getIdcontrato());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            System.err.println("Erro: saveEquipamento" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean updateEquipamento(Equipamento equipamento) {

        String sql = "UPDATE equipamento SET nome = ?, fabricante = ?, modelo = ?, dt_fabric = ?, dt_instal = ?, histor_inicial = ?, gap_manut = ?, dt_ultimanut = ?, foto = ?, cliente_idcliente = ?, contrato_idcontrato = ? WHERE idequip = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getFabricante());
            stmt.setString(3, equipamento.getModelo());
            stmt.setString(4, equipamento.getDt_fabric());
            stmt.setString(5, equipamento.getDt_instal());
            stmt.setString(6, equipamento.getHistor_inicial());
            stmt.setString(7, equipamento.getGap_manut());
            stmt.setString(8, equipamento.getDt_ultmanut());
            stmt.setString(9, equipamento.getFoto());
            stmt.setInt(10, equipamento.getCliente().getIdcliente());
            stmt.setInt(11, equipamento.getContrato().getIdcontrato());
            
            stmt.setInt(12, equipamento.getIdequip());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean deleteEquipamento(Equipamento equipamento) {

        String sql;
        sql = "DELETE FROM equipamento WHERE idequip = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, equipamento.getIdequip());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Equipamento> readAllEquipamento() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Equipamento> equipamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM equipamento");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Equipamento equipamento = new Equipamento();
                
                equipamento.setNome(rs.getString("nome"));
                equipamento.setFabricante(rs.getString("fabricante"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setDt_fabric(rs.getString("dt_fabric"));
                equipamento.setDt_instal(rs.getString("dt_instal"));
                equipamento.setHistor_inicial(rs.getString("histor_inicial"));
                equipamento.setGap_manut(rs.getString("gap_manut"));
                equipamento.setDt_ultmanut(rs.getString("dt_ultimanut"));
                equipamento.setDt_inic_contrato(rs.getString("dt_inic_contrato"));
                equipamento.setFoto(rs.getString("foto"));
                
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("cliente_idcliente"));
                
                equipamento.setCliente(cliente);
                
                Contrato contrato = new Contrato();
                contrato.setIdcontrato(rs.getInt("contrato_idcontrato"));
                
                equipamento.setContrato(contrato);
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: readAllEquipamento" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return equipamentos;
    }

    public List<Equipamento> readForName(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Equipamento> equipamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM equipamento WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Equipamento equipamento = new Equipamento();

                equipamento.setNome(rs.getString("nome"));
                equipamento.setFabricante(rs.getString("equipamento"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setDt_fabric(rs.getString("dt_fabric"));
                equipamento.setDt_instal(rs.getString("dt_instal"));
                equipamento.setHistor_inicial(rs.getString("histor_inicial"));
                equipamento.setGap_manut(rs.getString("gap_manut"));
                equipamento.setDt_ultmanut(rs.getString("dt_ultmanut"));
                equipamento.setDt_inic_contrato(rs.getString("dt_inic_contrato"));
                equipamento.setFoto(rs.getString("foto"));
                
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                
                equipamento.setCliente(cliente);
                
                Contrato contrato = new Contrato();
                contrato.setIdcontrato(rs.getInt("idcontrato"));
                
                equipamento.setContrato(contrato);
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: readForName" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return equipamentos;
    }
    
    public List<Equipamento> readTableAllEquipamento() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Equipamento> equipamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT e.idequip, e.nome, e.fabricante, e.modelo, e.dt_fabric, e.dt_instal, e.histor_inicial,"
                    + " e.gap_manut, e.dt_ultimanut, e.dt_inic_contrato, e.foto, cl.idcliente, cl.nome as clinome, co.idcontrato, co.tipodesc"
                    + " FROM equipamento e"
                    + " INNER JOIN cliente cl"
                    + " INNER JOIN contrato co ON e.cliente_idcliente = cl.idcliente and e.contrato_idcontrato = co.idcontrato");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Equipamento equipamento = new Equipamento();
                
                equipamento.setIdequip(rs.getInt("idequip"));
                equipamento.setNome(rs.getString("nome"));
                equipamento.setFabricante(rs.getString("fabricante"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setDt_fabric(rs.getString("dt_fabric"));
                equipamento.setDt_instal(rs.getString("dt_instal"));
                equipamento.setHistor_inicial(rs.getString("histor_inicial"));
                equipamento.setGap_manut(rs.getString("gap_manut"));
                equipamento.setDt_ultmanut(rs.getString("dt_ultimanut"));
                equipamento.setDt_inic_contrato(rs.getString("dt_inic_contrato"));
                equipamento.setFoto(rs.getString("foto"));
                
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("clinome"));
                
                equipamento.setCliente(cliente);
                
                Contrato contrato = new Contrato();
                contrato.setIdcontrato(rs.getInt("idcontrato"));
                contrato.setTipodesc(rs.getString("tipodesc"));
                
                equipamento.setContrato(contrato);
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro readTableAllEquipamento: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return equipamentos;
    }
    
    public List<Equipamento> readAllEquipamentoForComboboxOs(int indice) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Equipamento> equipamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT e.idequip, e.nome FROM equipamento e INNER JOIN cliente c ON e.cliente_idcliente = c.idcliente WHERE c.idcliente = ?");
            stmt.setInt(1, indice);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Equipamento equipamento = new Equipamento();
                
                equipamento.setIdequip(rs.getInt("idequip"));
                equipamento.setNome(rs.getString("nome"));
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro readAllEquipamentoForComboboxOs: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return equipamentos;
    }
    
        public List<Equipamento> readAllEquipamentoForComboboxOs2(int indice) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Equipamento> equipamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT e.nome FROM equipamento e INNER JOIN cliente c ON e.cliente_idcliente = c.idcliente WHERE c.idcliente = ?");
            stmt.setInt(1, indice);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Equipamento equipamento = new Equipamento();
                
                equipamento.setNome(rs.getString("nome"));
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro readAllEquipamentoForComboboxOs2: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return equipamentos;
    }
}
