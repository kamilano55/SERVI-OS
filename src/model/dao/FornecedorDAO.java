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
import model.bean.Fornecedor;

/**
 *
 * @author Master
 */
public class FornecedorDAO {

    private Connection con = null;

    public FornecedorDAO() {

        con = ConnectionFactory.getConnection();
    }

    public boolean save(Fornecedor fornecedor) {

        String sql = "INSERT INTO fornecedor(nome, cnpj, rua, numero, complemento, bairro, cep, referencia, gps, fone1, fone2, celular, url, email, contato, estado_idestado, cidade_idcidade) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getRua());
            stmt.setString(4, fornecedor.getNumero());
            stmt.setString(5, fornecedor.getComplemento());
            stmt.setString(6, fornecedor.getBairro());
            stmt.setString(7, fornecedor.getCep());
            stmt.setString(8, fornecedor.getReferencia());
            stmt.setString(9, fornecedor.getGps());
            stmt.setString(10, fornecedor.getFone1());
            stmt.setString(11, fornecedor.getFone2());
            stmt.setString(12, fornecedor.getCelular());
            stmt.setString(13, fornecedor.getUrl());
            stmt.setString(14, fornecedor.getEmail());
            stmt.setString(15, fornecedor.getContato());
            stmt.setInt(16, fornecedor.getEstado().getIdestado());
            stmt.setInt(17, fornecedor.getCidade().getIdcidade());

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

    public boolean update(Fornecedor fornecedor) {

        String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, referencia = ?, gps = ?, fone1 = ?, fone2 = ?, celular = ?, url = ?, email = ?, contato = ?, estado_idestado = ?, cidade_idcidade = ? WHERE idfornec = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getRua());
            stmt.setString(4, fornecedor.getNumero());
            stmt.setString(5, fornecedor.getComplemento());
            stmt.setString(6, fornecedor.getBairro());
            stmt.setString(7, fornecedor.getCep());
            stmt.setString(8, fornecedor.getReferencia());
            stmt.setString(9, fornecedor.getGps());
            stmt.setString(10, fornecedor.getFone1());
            stmt.setString(11, fornecedor.getFone2());
            stmt.setString(12, fornecedor.getCelular());
            stmt.setString(13, fornecedor.getUrl());
            stmt.setString(14, fornecedor.getEmail());
            stmt.setString(15, fornecedor.getContato());
            stmt.setInt(16, fornecedor.getEstado().getIdestado());
            stmt.setInt(17, fornecedor.getCidade().getIdcidade());
            stmt.setInt(18, fornecedor.getIdfornec());

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

    public boolean delete(Fornecedor fornecedor) {

        String sql;
        sql = "DELETE FROM fornecedor WHERE idfornec = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, fornecedor.getIdfornec());

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

    public List<Fornecedor> readAllFornecedor() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setRua(rs.getString("rua"));
                fornecedor.setNumero(rs.getString("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setReferencia(rs.getString("referencia"));
                fornecedor.setGps(rs.getString("gps"));
                fornecedor.setFone1(rs.getString("fone1"));
                fornecedor.setFone2(rs.getString("fone2"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setUrl(rs.getString("url"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setContato(rs.getString("contato"));
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                
                fornecedor.setEstado(estado);
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                
                fornecedor.setCidade(cidade);

                fornecedores.add(fornecedor);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return fornecedores;
    }

    public List<Fornecedor> readForName(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setRua(rs.getString("rua"));
                fornecedor.setNumero(rs.getString("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setReferencia(rs.getString("referencia"));
                fornecedor.setGps(rs.getString("gps"));
                fornecedor.setFone1(rs.getString("fone1"));
                fornecedor.setFone2(rs.getString("fone2"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setUrl(rs.getString("url"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setContato(rs.getString("contato"));
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                
                fornecedor.setEstado(estado);
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                
                fornecedor.setCidade(cidade);
                

                fornecedores.add(fornecedor);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return fornecedores;
    }
}
