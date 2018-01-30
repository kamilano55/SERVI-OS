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
import model.bean.Usuario;

/**
 *
 * @author Master
 */
public class UsuarioDAO {

    private Connection con = null;

    public UsuarioDAO() {

        con = ConnectionFactory.getConnection();
    }

    public boolean saveUsuario(Usuario usuario) {

        String sql;
        sql = "INSERT INTO usuario(nome, fone, login, senha, tipo) VALUES (?,?,?,?,?)";
        
//        JOptionPane.showMessageDialog(null, "Passou pelo SQL!");
        PreparedStatement stmt = null;
//        JOptionPane.showMessageDialog(null, "Passou pelo stmt!");
        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getFone());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTipo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!! ");
            return false;
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean updateUsuario(Usuario usuario) {

        String sql = "UPDATE usuario SET nome = ?, fone = ?, login = ?, senha = ?, tipo = ? WHERE iduser = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getFone());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTipo());
            
            stmt.setInt(6, usuario.getIduser());
            
            stmt.executeUpdate();
//            System.err.println("(test)Atualizado com sucesso");
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!! ");
            return true;

        } catch (SQLException ex) {
//            System.err.println("Erro ao atualizar");
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!! ");
            return false;
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean deleteUsuario(Usuario usuario) {

        String sql = "DELETE FROM usuario WHERE iduser = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getIduser());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!! ");
            return false;
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Usuario> readAllUsuario(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();
            
            
            while (rs.next()){
                
                Usuario usuario = new Usuario();
                
                usuario.setIduser(rs.getInt("iduser"));
                usuario.setNome(rs.getString("nome"));
                usuario.setFone(rs.getString("fone"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                
                
                usuarios.add(usuario);
                
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da lista de usuarios!!" +ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
        
        
    }
    public List<Usuario> readForUsuario(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            
            
            while (rs.next()){
                
                Usuario usuario = new Usuario();
                
                usuario.setIduser(rs.getInt("iduser"));
                usuario.setNome(rs.getString("nome"));
                usuario.setFone(rs.getString("fone"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                
                usuarios.add(usuario);
                
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da lista de usuarios!!" +ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }
    
    
    public boolean checkLogin(String login, String senha){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM usuario WHERE login = ? and senha = ?");
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
               check = true; 
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha no acesso ao DB Usuário" +ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
       
        return check;
        
    }
    public boolean checkConexao(){
        
//Testa a conexão        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;
        if (conexao != null){
        
          try {
            stmt = (PreparedStatement) conexao.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();
            if (rs != null) {
               check = true;
            }
//Return check; 
          } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Falha no acesso ou não existe Usuário cadastrado" +ex.getMessage());
            }finally{
               ConnectionFactory.closeConnection(conexao, stmt, rs);
            }
        }
        return check;
    }   
}
