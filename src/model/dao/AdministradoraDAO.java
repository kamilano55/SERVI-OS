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
import model.bean.Administradora;
import model.bean.Cidade;
import model.bean.Estado;

/**
 *
 * @author Master
 */
public class AdministradoraDAO {
    
    private Connection con = null;
    
    public AdministradoraDAO() {
        
        con = connection.ConnectionFactory.getConnection();
    }
    
    public boolean saveAdm(Administradora adm) {
        
        String sql = "INSERT INTO administradora(cnpjcpf, nome, rua, numero, complemento, bairro, cep, referencia, gps, fone1, celular, url, email, contato, cargo, foto, cidade_idcidade, estado_idestado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement stmt = null;
        Cidade cidade = new Cidade();
        Estado estado = new Estado();
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, adm.getCnpjcpf());
            stmt.setString(2, adm.getNome());
            stmt.setString(3, adm.getRua());
            stmt.setString(4, adm.getNumero());
            stmt.setString(5, adm.getComplemento());
            stmt.setString(6, adm.getBairro());
            stmt.setString(7, adm.getCep());
            stmt.setString(8, adm.getReferencia());
            stmt.setString(9, adm.getGps());
            stmt.setString(10, adm.getFone1());
            stmt.setString(11, adm.getCelular());
            stmt.setString(12, adm.getUrl());
            stmt.setString(13, adm.getEmail());
            stmt.setString(14, adm.getContato());
            stmt.setString(15, adm.getCargo());
            stmt.setString(16, adm.getFoto());
            
            stmt.setInt(17, adm.getCidade().getIdcidade());
            stmt.setInt(18, adm.getEstado().getIdestado());
            
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
    
    public boolean updateAdm(Administradora adm) {
        
        String sql = "UPDATE administradora SET cnpjcpf = ?, nome = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, referencia = ?, gps = ?, fone1 = ?, celular = ?, url = ?, email = ?, contato = ?, cargo = ?, foto = ?, cidade_idcidade = ?, estado_idestado = ? WHERE idadmin = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, adm.getCnpjcpf());
            stmt.setString(2, adm.getNome());
            stmt.setString(3, adm.getRua());
            stmt.setString(4, adm.getNumero());
            stmt.setString(5, adm.getComplemento());
            stmt.setString(6, adm.getBairro());
            stmt.setString(7, adm.getCep());
            stmt.setString(8, adm.getReferencia());
            stmt.setString(9, adm.getGps());
            stmt.setString(10, adm.getFone1());
            stmt.setString(11, adm.getCelular());
            stmt.setString(12, adm.getUrl());
            stmt.setString(13, adm.getEmail());
            stmt.setString(14, adm.getContato());
            stmt.setString(15, adm.getCargo());
            stmt.setString(16, adm.getFoto());
            
            stmt.setInt(17, adm.getCidade().getIdcidade());
            stmt.setInt(18, adm.getEstado().getIdestado());
            
            stmt.setInt(19, adm.getIdadmin());
            
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
    
    public boolean deleteAdm(Administradora adm) {
        
        String sql;
        sql = "DELETE FROM Administradora WHERE idadmin = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, adm.getIdadmin());
            
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
    
    public List<Administradora> readAllAdm() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Administradora> administradoras = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM administradora");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Administradora adm = new Administradora();
                
                adm.setIdadmin(rs.getInt("idadmin"));
                adm.setCnpjcpf(rs.getString("cnpjcpf"));
                adm.setNome(rs.getString("nome"));
                adm.setContato(rs.getString("contato"));
                adm.setCargo(rs.getString("cargo"));
                adm.setUrl(rs.getString("url"));
                adm.setEmail(rs.getString("email"));
                adm.setFone1(rs.getString("fone1"));
                adm.setCelular(rs.getString("celular"));
                
                adm.setRua(rs.getString("rua"));
                adm.setNumero(rs.getString("numero"));
                adm.setComplemento(rs.getString("complemento"));
                adm.setBairro(rs.getString("bairro"));
                adm.setReferencia(rs.getString("referencia"));
                adm.setGps(rs.getString("gps"));
                adm.setCep(rs.getString("cep"));
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("estado_idestado"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));
                
                adm.setEstado(estado);
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("cidade_idcidade"));
                cidade.setNome(rs.getString("nome"));
//                cidade.setEstado(rs.getInt("estado_idestado"));
                
                adm.setCidade(cidade);
                
                adm.setGps(rs.getString("gps"));
                adm.setFoto(rs.getString("foto"));
                
                administradoras.add(adm);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro em readAllAdm : " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return administradoras;
    }
    
    public List<Administradora> readForNameAdm(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Administradora> administradoras = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT a.idadmin, a.cnpjcpf, a.nome, a.contato, a.cargo, a.url, a.email, a.fone1, a.celular, \n" +
"a.rua, a.numero, a.complemento, a.bairro, a.referencia, a.cep, e.idestado, e.nome as estnome, e.uf, c.idcidade, c.nome as cidnome, a.gps, a.foto\n" +
"FROM administradora a\n" +
"inner join estado e\n" +
"inner join cidade c on a.estado_idestado = e.idestado and a.cidade_idcidade = c.idcidade WHERE a.nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Administradora adm = new Administradora();
                
                adm.setIdadmin(rs.getInt("idadmin"));
                adm.setCnpjcpf(rs.getString("cnpjcpf"));
                adm.setNome(rs.getString("nome"));
                adm.setContato(rs.getString("contato"));
                adm.setCargo(rs.getString("cargo"));
                adm.setUrl(rs.getString("url"));
                adm.setEmail(rs.getString("email"));
                adm.setFone1(rs.getString("fone1"));
                adm.setCelular(rs.getString("celular"));
                
                adm.setRua(rs.getString("rua"));
                adm.setNumero(rs.getString("numero"));
                adm.setComplemento(rs.getString("complemento"));
                adm.setBairro(rs.getString("bairro"));
                adm.setReferencia(rs.getString("referencia"));
                adm.setCep(rs.getString("cep"));
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));
                
                adm.setEstado(estado);
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));
//                cidade.setEstado(rs.getInt("estado_idestado"));
                
                adm.setCidade(cidade);
                
                adm.setGps(rs.getString("gps"));
                adm.setFoto(rs.getString("foto"));
                
                administradoras.add(adm);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro em readForNameAdm: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return administradoras;
    }
    
    public List<Administradora> readAllTabelaAdm() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Administradora> administradoras = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT a.idadmin, a.cnpjcpf, a.nome, a.contato, a.cargo, a.url, a.email, a.fone1, a.celular, a.rua, a.numero, a.complemento, a.bairro, a.referencia, a.cep, e.idestado, e.nome as estnome, e.uf, c.idcidade, c.nome as cidnome, c.estado_idestado, a.gps, a.foto \n"
                    + "FROM administradora a \n"
                    + "INNER JOIN cidade c\n"
                    + "INNER JOIN estado e on a.estado_idestado = e.idestado and a.cidade_idcidade = c.idcidade");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Administradora adm = new Administradora();
                
                adm.setIdadmin(rs.getInt("idadmin"));
                adm.setCnpjcpf(rs.getString("cnpjcpf"));
                adm.setNome(rs.getString("nome"));
                adm.setContato(rs.getString("contato"));
                adm.setCargo(rs.getString("cargo"));
                adm.setUrl(rs.getString("url"));
                adm.setEmail(rs.getString("email"));
                adm.setFone1(rs.getString("fone1"));
                adm.setCelular(rs.getString("celular"));
                
                adm.setRua(rs.getString("rua"));
                adm.setNumero(rs.getString("numero"));
                adm.setComplemento(rs.getString("complemento"));
                adm.setBairro(rs.getString("bairro"));
                adm.setReferencia(rs.getString("referencia"));
                adm.setCep(rs.getString("cep"));
                
                Estado estado = new Estado();
                estado.setIdestado(rs.getInt("idestado"));
                estado.setNome(rs.getString("estnome"));
                estado.setUf(rs.getString("uf"));
                
                adm.setEstado(estado);
                
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setNome(rs.getString("cidnome"));
//                cidade.setEstado(rs.getInt("estado_idestado"));
                
                adm.setCidade(cidade);
                
                adm.setGps(rs.getString("gps"));
                adm.setFoto(rs.getString("foto"));
                
                administradoras.add(adm);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro em readAllTabelaAdm: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return administradoras;
    }
}
