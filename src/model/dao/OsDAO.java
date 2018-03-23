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
import model.bean.Equipamento;
import model.bean.Os;
import model.bean.Tiposerv;

/**
 *
 * @author Master
 */
public class OsDAO {

    private Connection con = null;

    public OsDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean saveAberturaOs(Os os) {

        String sql = "INSERT INTO os(nome_cliente, nome_equip, defeito, obs, tiposerv_id_tserv, cliente_idcliente, equipamento_idequip) VALUES (?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, os.getNome_cliente());

            stmt.setString(2, os.getNome_equip());
            stmt.setString(3, os.getDefeito());
            stmt.setString(4, os.getObs());
            stmt.setInt(5, os.getTiposerv().getId_tserv());
            stmt.setInt(6, os.getCliente().getIdcliente());
            stmt.setInt(7, os.getEquipamento().getIdequip());

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

    public boolean saveOsAtendida(Os os) {

        String sql = "UPDATE os SET tecnico = ?, dt_inicio = ?, hr_inic = ?, servico = ?, uso_peca = ?, equip_retirado = ?, aberta_fech = ?, dt_fim = ?, hr_fim = ? WHERE idos = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, os.getTecnico());
            stmt.setString(2, os.getDt_inicio());
            stmt.setString(3, os.getHr_inic());
            stmt.setString(4, os.getServico());
            stmt.setBoolean(5, os.getUso_peca());
            stmt.setBoolean(6, os.getEquip_retirado());
            stmt.setBoolean(7, os.getAberta_fech());
            stmt.setString(8, os.getDt_fim());
            stmt.setString(9, os.getHr_fim());

            stmt.setInt(10, os.getIdos());

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

    public boolean updateAbertura(Os os) {

        String sql = "UPDATE os SET nome_cliente = ?, nome_equip = ?, defeito = ?, obs = ?, tiposerv_id_tserv = ?, cliente_idcliente = ?, equipamento_idequip = ? WHERE idos = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, os.getNome_cliente());
            stmt.setString(2, os.getNome_equip());
            stmt.setString(3, os.getDefeito());
            stmt.setString(4, os.getObs());
            stmt.setInt(5, os.getTiposerv().getId_tserv());
            stmt.setInt(6, os.getCliente().getIdcliente());
            stmt.setInt(7, os.getEquipamento().getIdequip());

            stmt.setInt(8, os.getIdos());

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

    public List<Os> readTableOsAbertura() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Os> oss = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT o.idos, o.data, c.idcliente, c.nome as nomecli, e.idequip, e.nome as nomequip, o.defeito, t.id_tserv, t.descricao, o.obs, o.tecnico, o.dt_inicio, o.dt_fim, o.servico, o.aberta_fech\n"
                    + " FROM os as o\n"
                    + " INNER JOIN tiposerv as t\n"
                    + " INNER JOIN cliente as c\n"
                    + " INNER JOIN equipamento as e\n"
                    + " ON o.cliente_idcliente = c.idcliente and o.tiposerv_id_tserv = t.id_tserv and o.equipamento_idequip = e.idequip");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Os os = new Os();

                os.setIdos(rs.getInt("idos"));
                os.setData(rs.getString("data"));

                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nomecli"));

                os.setCliente(cliente);

                Equipamento e = new Equipamento();
                e.setIdequip(rs.getInt("idequip"));
                e.setNome(rs.getString("nomequip"));

                os.setEquipamento(e);

                os.setDefeito(rs.getString("defeito"));

                Tiposerv serv = new Tiposerv();
                serv.setDescricao(rs.getString("descricao"));
                serv.setId_tserv(rs.getInt("id_tserv"));

                os.setTiposerv(serv);

                os.setObs(rs.getString("obs"));

                os.setTecnico(rs.getString("tecnico"));
                os.setDt_inicio(rs.getString("dt_inicio"));
                os.setDt_fim(rs.getString("dt_fim"));
                os.setServico(rs.getString("servico"));
                os.setAberta_fech(rs.getBoolean("aberta_fech"));

                oss.add(os);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da Tabela_consulta de ordem de serviço!!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return oss;
    }

    public List<Os> readConsultaTableOsAbertura(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Os> oss = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT o.idos, o.data, c.nome, c.idcliente, o.nome_equip, o.defeito, t.descricao, t.id_tserv, o.obs, o.tecnico, o.dt_inicio, o.dt_fim, o.servico, o.aberta_fech"
                    + " FROM os as o"
                    + " INNER JOIN tiposerv as t"
                    + " INNER JOIN cliente as c"
                    + " INNER JOIN equipamento as e ON o.cliente_idcliente = c.idcliente and o.tiposerv_id_tserv = t.id_tserv and o.equipamento_idequip = e.idequip WHERE c.nome LIKE ? ");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Os os = new Os();

                os.setIdos(rs.getInt("idos"));
                os.setData(rs.getString("data"));

                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setIdcliente(rs.getInt("idcliente"));
                os.setCliente(cliente);

                Equipamento e = new Equipamento();
                e.setNome(rs.getString("nomeeq"));
                e.setIdequip(rs.getInt("idequip"));
                os.setEquipamento(e);

                os.setDefeito(rs.getString("defeito"));

                Tiposerv serv = new Tiposerv();
                serv.setDescricao(rs.getString("descricao"));
                serv.setId_tserv(rs.getInt("id_tserv"));
                os.setTiposerv(serv);

                os.setTecnico(rs.getString("tecnico"));
                os.setDt_inicio(rs.getString("dt_inicio"));
                os.setDt_fim(rs.getString("dt_fim"));
                os.setServico(rs.getString("servico"));

                oss.add(os);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da Tabela_consulta de ordem de serviço!!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return oss;
    }

    public List<Os> readConsultaTableOsAberturaForId(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Os> oss = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT o.idos, o.data, c.idcliente, c.nome as nomecli, e.idequip, e.nome as nomequip, o.defeito, t.id_tserv, t.descricao, o.obs, o.tecnico, o.dt_inicio, o.dt_fim, o.servico, o.aberta_fech\n"
                    + " FROM os as o\n"
                    + " INNER JOIN tiposerv as t\n"
                    + " INNER JOIN cliente as c\n"
                    + " INNER JOIN equipamento as e\n"
                    + " ON o.cliente_idcliente = c.idcliente and o.tiposerv_id_tserv = t.id_tserv and o.equipamento_idequip = e.idequip WHERE c.idcliente = ? ");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Os os = new Os();

                os.setIdos(rs.getInt("idos"));
                os.setData(rs.getString("data"));

                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nomecli"));

                os.setCliente(cliente);

                Equipamento e = new Equipamento();
                e.setIdequip(rs.getInt("idequip"));
                e.setNome(rs.getString("nomequip"));

                os.setEquipamento(e);

                os.setDefeito(rs.getString("defeito"));

                Tiposerv serv = new Tiposerv();
                serv.setDescricao(rs.getString("descricao"));
                serv.setId_tserv(rs.getInt("id_tserv"));

                os.setTiposerv(serv);

                os.setObs(rs.getString("obs"));

                os.setTecnico(rs.getString("tecnico"));
                os.setDt_inicio(rs.getString("dt_inicio"));
                os.setDt_fim(rs.getString("dt_fim"));
                os.setServico(rs.getString("servico"));
                os.setAberta_fech(rs.getBoolean("aberta_fech"));

                oss.add(os);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da Tabela_consulta de ordem de serviço!!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return oss;
    }

    public List<Os> readTableOsAberturaForId(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Os> oss = new ArrayList<>();

        try {

            stmt = con.prepareStatement("SELECT c.idcliente, c.nome, o.data, e.idequip, o.nome_equip, o.defeito, t.id_tserv, t.descricao, o.obs"
                    + " FROM os as o"
                    + " INNER JOIN tiposerv t"
                    + " INNER JOIN cliente c"
                    + " INNER JOIN equipamento e ON o.cliente_idcliente = c.idcliente and o.tiposerv_id_tserv = t.id_tserv and o.equipamento_idequip = e.idequip WHERE o.idos = ? ");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Os os = new Os();

                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                os.setCliente(cliente);

                os.setData(rs.getString("data"));
                os.setNome_equip(rs.getString("nome_equip"));

//                Equipamento equip = new Equipamento();
//                equip.setIdequip(rs.getInt("idequip"));
//                equip.setNome(rs.getString("nome"));
//                
//                os.setEquipamento(equip);
                os.setDefeito(rs.getString("defeito"));

                Tiposerv serv = new Tiposerv();
                serv.setId_tserv(rs.getInt("id_tserv"));
                serv.setDescricao(rs.getString("descricao"));
                os.setTiposerv(serv);

                os.setObs(rs.getString("obs"));

                oss.add(os);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema na construção da Tabela_consulta de ordem de serviço!!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return oss;
    }

    public boolean checkOsAtendidaExiste(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM os WHERE idos = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Os não localizada");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }

//    public List<Os> preencheFormeOsAtend(int id) {
//        Connection con = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        
//        List<Os> oss = new ArrayList<>();
//        
//        try {
//            stmt = con.prepareStatement("SELECT * FROM os where idos = ?");
//            stmt.setInt(1, id);
//            
//            rs = stmt.executeQuery();
//            
//            if (rs.next()) {
//                
//                Os os = new Os();
//                
//                os.setTecnico(rs.getString("tecnico"));
//
////                jComboBoxTecnico.setSelectedItem(rs.getString("tecnico"));
////                jFormattedTextDtIni.setText(rs.getString("dt_inicio"));
////                jFormattedTextHrIni.setText(rs.getString("hr_inic"));
////                jRadioUsoPeca.setSelected(rs.getBoolean("uso_peca"));
////                jRadioEquipRet.setSelected(rs.getBoolean("equip_retirado"));
////                jRadioStatusOs.setSelected(rs.getBoolean("aberta_fech"));
////                jFormattedTextDtFim.setText(rs.getString("dt_fim"));
////                jFormattedTextHrFim.setText(rs.getString("hr_fim"));
////                jTextArea1.setText(rs.getString("servico"));
//                oss.add(os);
//            }            
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt, rs);
//        }
//        return oss;
//    }
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
