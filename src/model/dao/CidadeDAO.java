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
import model.bean.Cidade;
import model.bean.Estado;

/**
 *
 * @author Master
 */
public class CidadeDAO {

    private Connection con = null;

    public CidadeDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean saveCidade(Cidade cidade) {
        
        String sql = "INSERT INTO cidade (nome, estado_idestado) value (?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getEstado().getIdestado());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!! ");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean updateCidade(Cidade cidade) {
        String sql = "UPDATE cidade SET nome = ?, estado_idestado = ? WHERE idcidade = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getEstado().getIdestado());
            stmt.setInt(3, cidade.getIdcidade());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("ErroDAO: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean deleteCidade(Cidade cidade) {

        String sql = "DELETE FROM cidade WHERE idcidade = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, cidade.getIdcidade());

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
    
    public List<Cidade> readAllCidade() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cidade> cidades = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cidade");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("nome"));
                
                //Como colocar na lista a chave estrangeira 
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("estado_idestado"));
//                estado.setNome(rs.getString("nome"));
//                estado.setUf(rs.getString("uf"));
                
                cidade.setEstado(estado);
                

                cidades.add(cidade);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cidades;

    }
    
    public List<Cidade> readAllForCidade() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cidade> cidades = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT c.idcidade, c.nome as cidnome, e.idestado, e.nome as estnome, e.uf"
                    + " FROM cidade c"
                    + " INNER JOIN estado e"
                    + " on c.estado_idestado = e.idestado");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cidade cidade = new Cidade();

                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));
                
//Como colocar na lista a chave estrangeira 
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));
                
                cidade.setEstado(estado);
                

                cidades.add(cidade);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cidades;

    }
    
    public List<Cidade> readForNameCidade(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cidade> cidades = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT c.idcidade, c.nome as cidnome, e.idestado, e.nome as estnome, e.uf "
                    + "FROM cidade c "
                    + "INNER JOIN estado e"
                    + " on c.estado_idestado = e.idestado WHERE c.nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cidade cidade = new Cidade();

                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));
                
                //Como colocar na lista a chave estrangeira 
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));
                
                cidade.setEstado(estado);

                cidades.add(cidade);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return cidades;
    }
    
    public List<Cidade> readComboBoxCidade(int indice) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cidade> cidades = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cidade as c"
                    + " INNER JOIN estado as e"
                    + " ON c.estado_idestado = e.idestado WHERE e.idestado = ?");
            stmt.setInt(1, indice);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cidade cidade = new Cidade();
                
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("nome"));
                
                
//Como colocar na lista a chave estrangeira 
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
//                estado.setNome(rs.getString("nome"));
//                estado.setUf(rs.getString("uf"));
                
                cidade.setEstado(estado);
                

                cidades.add(cidade);

            }
        } catch (SQLException ex) {
            System.err.println("Erro na consulta: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cidades;

    }

}
