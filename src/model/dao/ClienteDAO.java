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

    public boolean saveCliente(Cliente cliente) {

        String sql = "INSERT INTO cliente(cnpjcpf, nome, rua, numero, complemento, bairro, cep, referencia, gps, fone1, fone2, celular, url, email, foto, obs, contato, cargo, cidade_idcidade, estado_idestado, contrato_idcontrato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            stmt.setString(17, cliente.getContato());
            stmt.setString(18, cliente.getCargo());
            
            

            stmt.setInt(19, cliente.getCidade().getIdcidade());
            stmt.setInt(20, cliente.getEstado().getIdestado());
            stmt.setInt(21, cliente.getContrato().getIdcontrato());

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

    public boolean updateCliente(Cliente cliente) {

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

    public boolean deleteCliente(Cliente cliente) {

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
                cliente.setObs(rs.getString("obs"));
                cliente.setUrl(rs.getString("url"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFoto(rs.getString("foto"));
                
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
            System.err.println("Erro no readAllCliente: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    public List<Cliente> readForNomeCliente(String nome) {
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
    
    public List<Cliente> readTableAllCliente() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT a.idcliente, a.cnpjcpf, a.nome, a.contato, a.cargo, a.url, a.email, a.fone1, a.fone2, a.celular, a.obs, a.rua,"
                    + " a.numero, a.complemento, a.bairro, a.referencia, a.cep, e.idestado, e.nome as estnome, e.uf, c.idcidade, c.nome as cidnome,"
                    + " c.estado_idestado, cont.idcontrato, cont.tipodesc, a.gps, a.foto"
                    + " FROM cliente a"
                    + " INNER JOIN cidade c"
                    + " INNER JOIN estado e"
                    + " INNER JOIN contrato cont ON a.cidade_idcidade = c.idcidade and a.estado_idestado = e.idestado and a.contrato_idcontrato = cont.idcontrato");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setCnpjcpf(rs.getString("cnpjcpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setContato(rs.getString("contato"));
                cliente.setCargo(rs.getString("cargo"));
                cliente.setUrl(rs.getString("url"));
                cliente.setEmail(rs.getString("email"));
                cliente.setFone1(rs.getString("fone1"));
                cliente.setFone2(rs.getString("fone2"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setObs(rs.getString("obs"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setReferencia(rs.getString("referencia"));
                cliente.setCep(rs.getString("cep"));
                
                cliente.setGps(rs.getString("gps"));
                cliente.setFoto(rs.getString("foto"));
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));
//                cidade.setEstado(rs.getInt(Integer.parseInt("estado_idestado")));
                
                cliente.setCidade(cidade);
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));
                
                cliente.setEstado(estado);
                
                Contrato contrato = new Contrato();
                contrato.setIdcontrato(rs.getInt("idcontrato"));
//                JOptionPane.showMessageDialog(null, contrato.getIdcontrato());
                contrato.setTipodesc(rs.getString("tipodesc"));
//                JOptionPane.showMessageDialog(null, contrato.getTipodesc());
                
                cliente.setContrato(contrato);

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro no readTableAllCliente: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

}
