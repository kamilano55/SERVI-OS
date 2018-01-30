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

    public boolean save(Equipamento equipamento) {

        String sql = "INSERT INTO equipamento(nome, fabricante, modelo, dt_fabric, dt_instal, histor_inicial, gap_manut, dt_ultimanut, foto, cliente_idcliente) VALUES (?,?,?,?,?,?,?,?,?,?)";

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

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean update(Equipamento equipamento) {

        String sql = "UPDATE equipamento SET nome = ?, fabricante = ?, modelo = ?, dt_fabric = ?, dt_instal = ?, histor_inicial = ?, gap_manut = ?, dt_ultimanut = ?, foto = ?, cliente_idcliente = ? WHERE idequip = ?";

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
            stmt.setInt(11, equipamento.getIdequip());

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

    public boolean delete(Equipamento equipamento) {

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
                equipamento.setFabricante(rs.getString("equipamento"));
                equipamento.setModelo(rs.getString("modelo"));
                equipamento.setDt_fabric(rs.getString("dt_fabric"));
                equipamento.setDt_instal(rs.getString("dt_instal"));
                equipamento.setHistor_inicial(rs.getString("histor_inicial"));
                equipamento.setGap_manut(rs.getString("gap_manut"));
                equipamento.setDt_ultmanut(rs.getString("dt_ultmanut"));
                equipamento.setFoto(rs.getString("foto"));
                
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                
                equipamento.setCliente(cliente);
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
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
            stmt.setString(0, "%" + nome + "%");
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
                equipamento.setFoto(rs.getString("foto"));
                
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                
                equipamento.setCliente(cliente);
                

                equipamentos.add(equipamento);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return equipamentos;
    }

}
