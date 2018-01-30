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
import model.bean.Cliente;
import model.bean.Contrato;
import model.bean.Estado;
/**
 *
 * @author Master
 */
public class ClienteDAO {

    private Connection con = null;

    public ClienteDAO() {

        con = ConnectionFactory.getConnection();
    }

    public boolean save(Cliente cliente) {

        String sql = "INSERT INTO cliente(cnpjcpf, nome, rua, numero, complemento, bairro, cep, referencia, gps, fone1, fone2, celular, url, email, foto, obs, cidade_idcidade, estado_idestado, contrato_idcontrato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getCnpjcpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getRua());
            stmt.setString(4, cliente.getNumero());
            stmt.setString(5, cliente.getComplemento());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getReferencia());
            stmt.setString(9, cliente.getGps());
            stmt.setString(10, cliente.getFone1());
            stmt.setString(11, cliente.getFone2());
            stmt.setString(12, cliente.getCelular());
            stmt.setString(13, cliente.getUrl());
            stmt.setString(14, cliente.getEmail());
            stmt.setString(15, cliente.getFoto());
            stmt.setString(16, cliente.getObs());

            stmt.setInt(17, cliente.getCidade().getIdcidade());
            stmt.setInt(18, cliente.getEstado().getIdestado());
            stmt.setInt(19, cliente.getContrato().getIdcontrato());

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

    public boolean update(Cliente cliente) {

        String sql = "UPDATE cliente SET cnpjcpf = ?, nome = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, referencia = ?, gps = ?, fone1 = ?, fone2 = ?, celular = ?, url = ?, email = ?, foto = ?, obs = ?, cidade_idcidade = ?, estado_idestado = ?, contrato_idcontrato = ? WHERE idcliente = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getCnpjcpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getRua());
            stmt.setString(4, cliente.getNumero());
            stmt.setString(5, cliente.getComplemento());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getReferencia());
            stmt.setString(9, cliente.getGps());
            stmt.setString(10, cliente.getFone1());
            stmt.setString(11, cliente.getFone2());
            stmt.setString(12, cliente.getCelular());
            stmt.setString(13, cliente.getUrl());
            stmt.setString(14, cliente.getEmail());
            stmt.setString(15, cliente.getFoto());
            stmt.setString(16, cliente.getObs());
            
            stmt.setInt(17, cliente.getCidade().getIdcidade());
            stmt.setInt(18, cliente.getEstado().getIdestado());
            stmt.setInt(19, cliente.getContrato().getIdcontrato());
            
            stmt.setInt(20, cliente.getIdcliente());

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

    public boolean delete(Cliente cliente) {

        String sql;
        sql = "DELETE FROM cliente WHERE idcliente = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, cliente.getIdcliente());

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

    public List<Cliente> readAllCliente() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setCnpjcpf(rs.getString("cnpjcpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cliente.setReferencia(rs.getString("referencia"));
                cliente.setGps(rs.getString("gps"));
                cliente.setFone1(rs.getString("fone1"));
                cliente.setFone2(rs.getString("fone2"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setUrl(rs.getString("url"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFoto(rs.getString("foto"));
                cliente.setObs(rs.getString("obs"));
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                
                cliente.setCidade(cidade);
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                
                cliente.setEstado(estado);
                
                Contrato contrato = new Contrato();
                contrato.setIdcontrato(rs.getInt("idcontrato"));
                
                cliente.setContrato(contrato);

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    public List<Cliente> readForNome(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setCnpjcpf(rs.getString("cnpjcpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cliente.setReferencia(rs.getString("referencia"));
                cliente.setGps(rs.getString("gps"));
                cliente.setFone1(rs.getString("fone1"));
                cliente.setFone2(rs.getString("fone2"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setUrl(rs.getString("url"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFoto(rs.getString("foto"));
                cliente.setObs(rs.getString("obs"));
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                
                cliente.setCidade(cidade);
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                
                cliente.setEstado(estado);
                
                Contrato contrato = new Contrato();
                contrato.setIdcontrato(rs.getInt("idcontrato"));
                
                cliente.setContrato(contrato);

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

}
