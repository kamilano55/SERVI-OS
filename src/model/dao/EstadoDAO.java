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
import model.bean.Estado;

/**
 *
 * @author Master
 */
public class EstadoDAO {

    private Connection con = null;

    public EstadoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean saveEstado(Estado estado) {
        String sql = "INSERT INTO estado(nome, uf) value (?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getUf());

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

    public boolean updateEstado(Estado estado) {

        String sql = "UPDATE estado SET nome = ?, uf = ? WHERE idestado = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getUf());
            stmt.setInt(3, estado.getIdestado());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "(DAO)Atualizado com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean deleteEstado(Estado estado) {

        String sql = "DELETE FROM estado WHERE idestado = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, estado.getIdestado());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!! ");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Estado> readAllEstado() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estado> estados = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM estado");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estado estado = new Estado();

                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));

                estados.add(estado);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return estados;
    }

    public List<Estado> readForName(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estado> estados = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM estado WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estado estado = new Estado();

                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));

                estados.add(estado);

            }
        } catch (SQLException ex) {
            System.err.println("Erro readForName: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return estados;
    }
}
