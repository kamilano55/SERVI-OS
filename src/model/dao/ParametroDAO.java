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
import model.bean.Parametro;

/**
 *
 * @author MILANO
 */
public class ParametroDAO {
    
    private Connection con = null;
    
    public ParametroDAO(){
        
        con = connection.ConnectionFactory.getConnection();
    }
    
    public boolean saveDadosLoginInParametro(Parametro p){
        
        String sql = "INSERT INTO parametro (login, senha, tipo) VALUES (?,?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            
            stmt.setString(1, p.getLogin());
            stmt.setString(2, p.getSenha());
            stmt.setString(3, p.getTipo());
            
           
            
            stmt.executeUpdate();
            
//            JOptionPane.showMessageDialog(null, "SEU ACESSO ESTÁ LIBERADO !!!  " + " -  " + p.getLogin().toUpperCase());
            return true;
            
        } catch (SQLException ex) {
            System.err.println("Erro: saveDadosLoginInParametro" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Parametro> readUsuarioEmParametro(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Parametro> usu = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT senha FROM parametro ORDER BY idparametro DESC limit 1");
            rs = stmt.executeQuery();
            
            
            while (rs.next()){
                
                Parametro parametro = new Parametro();
                
                parametro.setSenha(rs.getString("senha"));
               
                
                
                usu.add(parametro);
                JOptionPane.showMessageDialog(null, usu);
                
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na obtenção da senhha do usuario em parametro!!" +ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usu;
    }
    
    
    
    
    
    
}
