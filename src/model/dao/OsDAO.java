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
import model.bean.Cliente;
import model.bean.Os;

/**
 *
 * @author Master
 */
public class OsDAO {

    private Connection con = null;

    public OsDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Os os) {

        String sql = "INSERT INTO os(nome_cliente, nome_equip, defeito, tecnico, hr_inic, servico, uso_peca, equip_retirado, obs, aberta_fech, hr_fim, tiposerv_id_tserv, cliente_idcliente, equipamento_idequip) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, os.getNome_cliente());
            stmt.setString(2, os.getNome_equip());
            stmt.setString(3, os.getDefeito());
            stmt.setString(4, os.getTecnico());
            stmt.setString(5, os.getHr_inic());
            stmt.setString(6, os.getServico());
            stmt.setBoolean(7, os.getUso_peca());
            stmt.setBoolean(8, os.getEquip_retirado());
            stmt.setString(9, os.getObs());
            stmt.setBoolean(10, os.getAberta_fech());
            stmt.setString(11, os.getHr_fim());
            stmt.setInt(12, os.getTiposerv().getId_tserv());
            stmt.setInt(13, os.getCliente().getIdcliente());
            stmt.setInt(14, os.getEquipamento().getIdequip());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!! ");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean update(Os os) {

        String sql = "UPDATE os SET nome_cliente = ?, nome_equip = ?, defeito = ?, tecnico = ?, hr_inic = ?, servico = ?, uso_peca = ?, equip_retirado = ?, obs = ?, aberta_fech = ?, hr_fim = ?, tiposerv_id_tserv = ?, cliente_idcliente = ?, equipamento_idequip = ? WHERE idos = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, os.getNome_cliente());
            stmt.setString(2, os.getNome_equip());
            stmt.setString(3, os.getDefeito());
            stmt.setString(4, os.getTecnico());
            stmt.setString(5, os.getHr_inic());
            stmt.setString(6, os.getServico());
            stmt.setBoolean(7, os.getUso_peca());
            stmt.setBoolean(8, os.getEquip_retirado());
            stmt.setString(9, os.getObs());
            stmt.setBoolean(10, os.getAberta_fech());
            stmt.setString(11, os.getHr_fim());
            stmt.setInt(12, os.getTiposerv().getId_tserv());
            stmt.setInt(13, os.getCliente().getIdcliente());
            stmt.setInt(14, os.getEquipamento().getIdequip());

            stmt.setInt(15, os.getIdos());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na atualização!! ");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Os os) {

        String sql = "DELETE FROM os WHERE idos = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, os.getIdos());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!! ");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!! ");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Os> readAllOs() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Os> oss = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT os.idos as Cod, os.nome_equip as Equip, os.defeito as Defeito, os.servico as Serviço, os.tecnico as Téc, cliente.nome as Cliente, cliente.fone1 as Fone1Cli FROM os inner join cliente on os.cliente_idcliente = cliente.idcliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Os os = new Os();

                os.setIdos(rs.getInt("Cod"));
                os.setNome_equip(rs.getString("Equip"));
                os.setDefeito(rs.getString("Defeito"));
                os.setServico(rs.getString("Serviço"));
                os.setTecnico(rs.getString("Téc"));

                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("Cliente"));
                cliente.setFone1(rs.getString("Fone1Cli"));

                os.setCliente(cliente);

                oss.add(os);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da Tabela_consulta de ordem de serviço!!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return oss;
    }
    
    public List<Os> readForName(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Os> oss = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT os.idos as Cod, os.nome_equip as Equip, os.defeito as Defeito, os.servico as Serviço, os.tecnico as Téc, cliente.nome as Cliente, cliente.fone1 as Fone1Cli FROM os inner join cliente on os.cliente_idcliente = cliente.idcliente WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Os os = new Os();

                os.setIdos(rs.getInt("Cod"));
                os.setNome_equip(rs.getString("Equip"));
                os.setDefeito(rs.getString("Defeito"));
                os.setServico(rs.getString("Serviço"));
                os.setTecnico(rs.getString("Téc"));

                Cliente cliente = new Cliente();

                cliente.setNome(rs.getString("Cliente"));
                cliente.setFone1(rs.getString("Fone1Cli"));

                os.setCliente(cliente);


                oss.add(os);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return oss;
    }

}
