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

        String sql = "INSERT INTO cliente(cnpjcpf, nome, rua, numero, complemento, bairro, cep, referencia, gps, fone1, fone2, celular, url, email, foto, obs, contato, cargo, cidade_idcidade, estado_idestado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
//            stmt.setInt(21, cliente.getContrato().getIdcontrato());

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

        String sql = "UPDATE cliente SET cnpjcpf = ?, nome = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, referencia = ?, gps = ?, fone1 = ?, fone2 = ?, celular = ?, url = ?, email = ?, foto = ?, obs = ?, contato = ?, cargo = ?, cidade_idcidade = ?, estado_idestado = ? WHERE idcliente = ?";

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
//            stmt.setInt(19, cliente.getContrato().getIdcontrato());

            stmt.setInt(21, cliente.getIdcliente());

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

    public boolean updateClienteForOs(Cliente cliente) {

        String sql = "UPDATE cliente SET rua = ?, numero = ?, complemento = ?, bairro = ?, referencia = ?, fone1 = ?, fone2 = ?, celular = ?, contato = ?, email = ? WHERE idcliente = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getRua());
            stmt.setString(2, cliente.getNumero());
            stmt.setString(3, cliente.getComplemento());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getReferencia());
            stmt.setString(6, cliente.getFone1());
            stmt.setString(7, cliente.getFone2());
            stmt.setString(8, cliente.getCelular());
            stmt.setString(9, cliente.getContato());
            stmt.setString(10, cliente.getEmail());

            stmt.setInt(11, cliente.getIdcliente());

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

                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));

                cliente.setEstado(estado);

                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("nome"));

                cliente.setCidade(cidade);

                cliente.setGps(rs.getString("gps"));
                cliente.setFoto(rs.getString("foto"));

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
            stmt = con.prepareStatement("SELECT a.idcliente, a.cnpjcpf, a.nome, a.contato, a.cargo, a.url, a.email, a.fone1, a.fone2, a.celular, a.obs, a.rua,"
                    + " a.numero, a.complemento, a.bairro, a.referencia, a.cep, e.idestado, e.nome as estnome, e.uf, c.idcidade, c.nome as cidnome,"
                    + " c.estado_idestado, a.gps, a.foto"
                    + " FROM cliente a"
                    + " INNER JOIN estado e"
                    + " INNER JOIN cidade c ON a.cidade_idcidade = c.idcidade and a.estado_idestado = e.idestado WHERE a.nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
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

                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));

                cliente.setEstado(estado);

                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));

                cliente.setCidade(cidade);

                cliente.setGps(rs.getString("gps"));
                cliente.setFoto(rs.getString("foto"));

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro readForNomeCliente: " + ex);
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
                    + " c.estado_idestado, a.gps, a.foto"
                    + " FROM cliente a"
                    + " INNER JOIN estado e"
                    + " INNER JOIN cidade c ON a.cidade_idcidade = c.idcidade and a.estado_idestado = e.idestado");
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

                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));

                cliente.setEstado(estado);

                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));

                cliente.setCidade(cidade);

                cliente.setGps(rs.getString("gps"));
                cliente.setFoto(rs.getString("foto"));

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro no readTableAllCliente: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    public List<Cliente> readAllClienteToEquip() {
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

//                Estado estado = new Estado();
//                estado.setIdestado(rs.getInt("idestado"));
//                estado.setNome(rs.getString("nome"));
//                estado.setUf(rs.getString("uf"));
//                
//                cliente.setEstado(estado);
//                
//                
//                Cidade cidade = new Cidade();
//                cidade.setIdcidade(rs.getInt("idcidade"));
//                cidade.setNome(rs.getString("nome"));
//                
//                cliente.setCidade(cidade);
                cliente.setGps(rs.getString("gps"));
                cliente.setFoto(rs.getString("foto"));

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro no readAllClienteToEquip: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    //em uso
    public List<Cliente> readAllTableClienteForOs(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT a.idcliente, a.nome, a.rua, a.numero, a.complemento, a.referencia, a.bairro, a.fone1, a.fone2, "
                    + "a.celular, a.contato, a.email, e.idestado, e.nome as estnome, e.uf, c.idcidade, c.nome as cidnome"
                    + " FROM cliente a"
                    + " INNER JOIN estado e"
                    + " INNER JOIN cidade c ON a.cidade_idcidade = c.idcidade and a.estado_idestado = e.idestado WHERE a.nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setReferencia(rs.getString("referencia"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setFone1(rs.getString("fone1"));
                cliente.setFone2(rs.getString("fone2"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setContato(rs.getString("contato"));
                cliente.setEmail(rs.getString("email"));

                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));

                cliente.setEstado(estado);

                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));

                cliente.setCidade(cidade);

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro no readAlltableClienteForOs: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    //em uso
    public List<Cliente> readTableClienteForOs() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT a.idcliente, a.nome, a.rua, a.numero, a.complemento, a.referencia, a.bairro, a.fone1, a.fone2, "
                    + "a.celular, a.contato, a.email, e.idestado, e.nome as estnome, e.uf, c.idcidade, c.nome as cidnome"
                    + " FROM cliente a"
                    + " INNER JOIN estado e"
                    + " INNER JOIN cidade c ON a.cidade_idcidade = c.idcidade and a.estado_idestado = e.idestado");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setReferencia(rs.getString("referencia"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setFone1(rs.getString("fone1"));
                cliente.setFone2(rs.getString("fone2"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setContato(rs.getString("contato"));
                cliente.setEmail(rs.getString("email"));

                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));

                cliente.setEstado(estado);

                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));

                cliente.setCidade(cidade);

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro no readtableClienteForOs: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    //em uso
    public List<Cliente> readComboboxClienteForOs() {
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
                cliente.setNome(rs.getString("nome"));

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro no readAlltableClienteForOs: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
}
