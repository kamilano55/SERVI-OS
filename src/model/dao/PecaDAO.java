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
import model.bean.Os;
import model.bean.Peca;

/**
 *
 * @author Master
 */
public class PecaDAO {
    
    private Connection con = null;
    
    public PecaDAO() {
        
        con = connection.ConnectionFactory.getConnection();
    }
    
    public boolean save(Peca peca) {
        
        String sql = "INSERT INTO peca(qtd, descricao, unidade, preco, os_idos) VALUES (?,?,?,?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setDouble(1, peca.getQtd());
            stmt.setString(2, peca.getDescricao());
            stmt.setString(3, peca.getUnidade());
            stmt.setDouble(4, peca.getPreco());
            stmt.setInt(5, peca.getOs().getIdos());
            
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
    
    public boolean update(Peca peca) {
        
        String sql = "UPDATE peca SET qtd = ?, descricao = ?, unidade = ?, preco = ?, os_idos = ? WHERE idpeca = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setDouble(1, peca.getQtd());
            stmt.setString(2, peca.getDescricao());
            stmt.setString(3, peca.getUnidade());
            stmt.setDouble(4, peca.getPreco());
            stmt.setInt(5, peca.getOs().getIdos());
            stmt.setInt(6, peca.getIdpeca());
            
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
    
    public boolean delete(Peca peca) {
        
        String sql;
        sql = "DELETE FROM peca WHERE idpeca =?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, peca.getIdpeca());
            
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
    
    public List<Peca> readAllpeca() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Peca> pecas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM peca");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Peca peca = new Peca();
                
                peca.setQtd(rs.getDouble("qtd"));
                peca.setDescricao(rs.getString("descricao"));
                peca.setUnidade(rs.getString("unidade"));
                peca.setPreco(rs.getDouble("preco"));
                
                Os os = new Os();
                os.setIdos(rs.getInt("idos"));
                
                peca.setOs(os);
                
                pecas.add(peca);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pecas;
    }
    
    public List<Peca> readPecaForOs(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Peca> pecas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT idpeca, qtd, descricao, unidade, preco, os_idos idos FROM peca WHERE os_idos = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Peca peca = new Peca();
                
                peca.setIdpeca(rs.getInt("idpeca"));
                peca.setQtd(rs.getDouble("qtd"));
                peca.setDescricao(rs.getString("descricao"));
                peca.setUnidade(rs.getString("unidade"));
                peca.setPreco(rs.getDouble("preco"));
                
                Os os1 = new Os();
                os1.setIdos(rs.getInt("idos"));
                
                peca.setOs(os1);
                
                pecas.add(peca);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return pecas;
    }
}
