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
import model.bean.Tiposerv;

/**
 *
 * @author Master
 */
public class TiposervDAO {

    private Connection con = null;

    public TiposervDAO() {

        con = connection.ConnectionFactory.getConnection();
    }

    public boolean save(Tiposerv tiposerv) {

        String sql = "INSERT INTO tiposerv(descricao, preco, sigla) VALUES (?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, tiposerv.getDescricao());
            stmt.setDouble(2, tiposerv.getPreco());
            stmt.setString(3, tiposerv.getSigla());

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

    public boolean update(Tiposerv tiposerv) {

        String sql = "UPDATE tiposerv SET descricao = ?, preco = ?, sigla = ? WHERE id_tserv = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, tiposerv.getDescricao());
            stmt.setDouble(2, tiposerv.getPreco());
            stmt.setString(3, tiposerv.getSigla());
            stmt.setInt(4, tiposerv.getId_tserv());

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

    public boolean delete(Tiposerv tiposerv) {

        String sql;
        sql = "DELETE FROM tiposerv WHERE id_tserv =?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, tiposerv.getId_tserv());

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

    public List<Tiposerv> readAllTiposerv() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Tiposerv> tiposervs = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tiposerv");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Tiposerv tiposerv = new Tiposerv();
                
                tiposerv.setId_tserv(rs.getInt("id_tserv"));
                tiposerv.setDescricao(rs.getString("descricao"));
                tiposerv.setPreco(rs.getDouble("preco"));
                tiposerv.setSigla(rs.getString("sigla"));

                tiposervs.add(tiposerv);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return tiposervs;
    }

    public List<Tiposerv> readForTiposerv(String sigla) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Tiposerv> tiposervs = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tiposerv WHERE sigla LIKE ?");
            stmt.setString(0, "%" + sigla + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Tiposerv tiposerv = new Tiposerv();
                
                tiposerv.setId_tserv(rs.getInt("idserv"));
                tiposerv.setDescricao(rs.getString("descricao"));
                tiposerv.setPreco(rs.getDouble("preco"));
                tiposerv.setSigla(rs.getString("sigla"));
                

                tiposervs.add(tiposerv);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return tiposervs;
    }

}
