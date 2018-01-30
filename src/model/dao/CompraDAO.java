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
import model.bean.Compra;
import model.bean.Fornecedor;

/**
 *
 * @author Master
 */
public class CompraDAO {
    
    private Connection con = null;
    public CompraDAO(){
        
        con = connection.ConnectionFactory.getConnection();
        
    }
    public boolean save(Compra compra){
        
       String sql = "INSERT INTO compra(dt_compra,numero_nota, qtd, descricao, unidade, preco, tip_compra, vlr_entrada, dt_primeira, vlr_primeira, dt_segunda, vlr_segunda, dt_terceira, vlr_terceira, fornecedor_idfornec) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, compra.getDt_compra());
            stmt.setString(2, compra.getNumero_nota());
            stmt.setDouble(3, compra.getQtd());
            stmt.setString(4, compra.getDescricao());
            stmt.setString(5, compra.getUnidade());
            stmt.setDouble(6, compra.getPreco());
            stmt.setString(7, compra.getTip_compra());
            stmt.setDouble(8, compra.getVlr_entrada());
            stmt.setString(9, compra.getDt_primeira());
            stmt.setDouble(10, compra.getVlr_primeira());
            stmt.setString(11, compra.getDt_segunda());
            stmt.setDouble(12, compra.getVlr_segunda());
            stmt.setString(13, compra.getDt_terceira());
            stmt.setDouble(14, compra.getVlr_terceira());
            stmt.setInt(15, compra.getFornecedor().getIdfornec());

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
    
    public boolean update(Compra compra){
        
        String sql = "UPDATE compra SET dt_compra = ?,numero_nota = ?, qtd = ?, descricao = ?, unidade = ?, preco = ?, tip_compra = ?, vlr_entrada = ?, dt_primeira = ?, vlr_primeira = ?, dt_segunda = ?, vlr_segunda = ?, dt_terceira = ?, vlr_terceira = ?, fornecedor_idfornec = ? WHERE idcompra = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, compra.getDt_compra());
            stmt.setString(2, compra.getNumero_nota());
            stmt.setDouble(3, compra.getQtd());
            stmt.setString(4, compra.getDescricao());
            stmt.setString(5, compra.getUnidade());
            stmt.setDouble(6, compra.getPreco());
            stmt.setString(7, compra.getTip_compra());
            stmt.setDouble(8, compra.getVlr_entrada());
            stmt.setString(9, compra.getDt_primeira());
            stmt.setDouble(10, compra.getVlr_primeira());
            stmt.setString(11, compra.getDt_segunda());
            stmt.setDouble(12, compra.getVlr_segunda());
            stmt.setString(13, compra.getDt_terceira());
            stmt.setDouble(14, compra.getVlr_terceira());
            stmt.setInt(15, compra.getFornecedor().getIdfornec());
            
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
    
    public boolean delete(Compra compra) {

        String sql;
        sql = "DELETE FROM Compra WHERE idcompra = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, compra.getIdcompra());
            

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
    
    
    public List<Compra> readCompras() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM compra");
            rs = stmt.executeQuery();

            while (rs.next()) {
                

                Compra compra = new Compra();

                compra.setIdcompra(rs.getInt("Id"));
                compra.setDt_atual(rs.getString("dt_atual"));
                compra.setDt_compra(rs.getString("dt_compra"));
                compra.setNumero_nota(rs.getString("numero_nota"));
                compra.setQtd(rs.getDouble("qtd"));
                compra.setDescricao(rs.getString("descricao"));
                compra.setUnidade(rs.getString("unidade"));
                compra.setPreco(rs.getDouble("preco"));
                compra.setTip_compra(rs.getString("tip_compra"));
                compra.setVlr_entrada(rs.getDouble("vlr_entrada"));
                compra.setDt_primeira(rs.getString("dt_primeira"));
                compra.setVlr_primeira(rs.getDouble("vlr_primeira"));
                compra.setDt_segunda(rs.getString("dt_segunda"));
                compra.setVlr_segunda(rs.getDouble("vlr_segunda"));
                compra.setDt_terceira(rs.getString("dt_terceira"));
                compra.setVlr_terceira(rs.getDouble("vlr_terceira"));
                
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdfornec(rs.getInt("idfornec"));
                
                compra.setFornecedor(fornecedor);

                compras.add(compra);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return compras;
    }
    
    public List<Compra> readForNumeroDaNota(String numero_nota) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM compra WHERE numero_nota LIKE ?");
            stmt.setString(0, "%" + numero_nota + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                

                Compra compra = new Compra();

                compra.setIdcompra(rs.getInt("Id"));
                compra.setDt_atual(rs.getString("dt_atual"));
                compra.setDt_compra(rs.getString("dt_compra"));
                compra.setNumero_nota(rs.getString("numero_nota"));
                compra.setQtd(rs.getDouble("qtd"));
                compra.setDescricao(rs.getString("descricao"));
                compra.setUnidade(rs.getString("unidade"));
                compra.setPreco(rs.getDouble("preco"));
                compra.setTip_compra(rs.getString("tip_compra"));
                compra.setVlr_entrada(rs.getDouble("vlr_entrada"));
                compra.setDt_primeira(rs.getString("dt_primeira"));
                compra.setVlr_primeira(rs.getDouble("vlr_primeira"));
                compra.setDt_segunda(rs.getString("dt_segunda"));
                compra.setVlr_segunda(rs.getDouble("vlr_segunda"));
                compra.setDt_terceira(rs.getString("dt_terceira"));
                compra.setVlr_terceira(rs.getDouble("vlr_terceira"));
                
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdfornec(rs.getInt("idfornec"));
                
                compra.setFornecedor(fornecedor);

                compras.add(compra);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return compras;
    }

    
}
