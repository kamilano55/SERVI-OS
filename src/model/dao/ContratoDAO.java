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
import model.bean.Contrato;

/**
 *
 * @author Master
 */
public class ContratoDAO {
    
    private Connection con = null;
    public ContratoDAO(){
        
        con = connection.ConnectionFactory.getConnection();
        
    }
    public boolean save(Contrato contrato){
        
       String sql = "INSERT INTO contrato(tipodesc, dt_inic, dt_fim, contexto, valor) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, contrato.getTipodesc());
            stmt.setString(2, contrato.getDt_inic());
            stmt.setString(3, contrato.getDt_fim());
            stmt.setString(4, contrato.getContexto());
            stmt.setDouble(5, contrato.getValor());
            

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
    
    public boolean update(Contrato contrato){
        
        String sql = "UPDATE contrato SET tipodesc = ?, dt_inic = ?, dt_fim = ?, contexto = ?, valor = ? WHERE idcontrato = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, contrato.getTipodesc());
            stmt.setString(2, contrato.getDt_inic());
            stmt.setString(3, contrato.getDt_fim());
            stmt.setString(4, contrato.getContexto());
            stmt.setDouble(5, contrato.getValor());
            stmt.setInt(6, contrato.getIdcontrato());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!! ");
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean delete(Contrato contrato) {

        String sql;
        sql = "DELETE FROM contrato WHERE idcontrato =?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, contrato.getIdcontrato());
            

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
    
    
    public List<Contrato> readAllContrato() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Contrato> contratos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM contrato");
            rs = stmt.executeQuery();

            while (rs.next()) {
                

                Contrato contrato = new Contrato();

                contrato.setIdcontrato(rs.getInt("idcontrato"));
                contrato.setTipodesc(rs.getString("tipodesc"));
                contrato.setDt_inic(rs.getString("dt_inic"));
                contrato.setDt_fim(rs.getString("dt_fim"));
                contrato.setContexto(rs.getString("contexto"));
                contrato.setValor(rs.getDouble("valor"));
                
                

                contratos.add(contrato);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return contratos;
    }
    
    public List<Contrato> readForType(String tipo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Contrato> contratos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM contrato WHERE tipo LIKE ?");
            stmt.setString(0, "%" + tipo + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                

                Contrato contrato = new Contrato();

                contrato.setIdcontrato(rs.getInt("idcontrato"));
                contrato.setTipodesc(rs.getString("tipodesc"));
                contrato.setDt_inic(rs.getString("dt_inic"));
                contrato.setDt_fim(rs.getString("dt_fim"));
                contrato.setContexto(rs.getString("contexto"));
                contrato.setValor(rs.getDouble("valor"));
                
                

                contratos.add(contrato);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return contratos;
    }
    
}
