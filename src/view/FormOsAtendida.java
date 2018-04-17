/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionFactory;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Equipamento;
import model.bean.Os;
import model.bean.Peca;
import model.dao.EquipamentoDAO;
import model.dao.OsDAO;
import model.dao.PecaDAO;

/**
 *
 * @author MILANO
 */
public class FormOsAtendida extends javax.swing.JFrame {

    private String statusPeca;
    private String statusRetirado;
    private String statusFechada;

    /**
     * Creates new form FormAtendeOs
     */
    public FormOsAtendida() {
        initComponents();
        desabilitaCampos();
        desabilitaBottons();
        txtCodigo.setEnabled(true);
        txtCodigo.requestFocus();

    }

    private void limparTabela() {
        DefaultTableModel mod = (DefaultTableModel) jTableOsAberta.getModel();
        mod.removeRow(0);
    }

    public void testaData() {
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yy");
        String dataFormatada = formatar.format(data);
//        System.out.println(dataFormatada);
        jFormattedTextDtIni.setText(dataFormatada);
        jFormattedTextDtFim.setText(dataFormatada);
    }

    public void comboboxPecasForIdOsAtendida(int id) {
        PecaDAO pdao = new PecaDAO();
        jComboBoxPecas.removeAllItems();
        jComboBoxPecas.addItem("Lista das Peças usadas");
        JOptionPane.showMessageDialog(null, id);
        for (Peca pe : pdao.comboboxPecaForOsId(id)) {
            jComboBoxPecas.addItem(pe);
        }
    }

    public void readTableOsAbertaForId(int id) {
        DefaultTableModel mod = (DefaultTableModel) jTableOsAberta.getModel();
        mod.setNumRows(0);
        OsDAO dao = new OsDAO();

        for (Os o : dao.readTableOsAberturaForId(id)) {
            mod.addRow(new Object[]{
                o.getCliente(),
                o.getData(),
                o.getNome_equip(),
                o.getDefeito(),
                o.getTiposerv(),
                o.getObs()
            });
        }
    }

    private void preencheFormeOsAtend(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM os where idos = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                jComboBoxTecnico.setSelectedItem(rs.getString("tecnico"));
                jFormattedTextDtIni.setText(rs.getString("dt_inicio"));
                jFormattedTextHrIni.setText(rs.getString("hr_inic"));
                jRadioUsoPeca.setSelected(rs.getBoolean("uso_peca"));
                jRadioEquipRet.setSelected(rs.getBoolean("equip_retirado"));
                jRadioStatusOs.setSelected(rs.getBoolean("aberta_fech"));
                jFormattedTextDtFim.setText(rs.getString("dt_fim"));
                jFormattedTextHrFim.setText(rs.getString("hr_fim"));
                jTextArea1.setText(rs.getString("servico"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }

    public void desabilitaCampos() {
        txtCodigo.setEnabled(true);
        jComboBoxTecnico.setEnabled(false);
        jFormattedTextDtIni.setEnabled(false);
        jFormattedTextHrIni.setEnabled(false);
        jComboBoxPecas.setEnabled(false);
        jRadioEquipRet.setEnabled(false);
        jRadioStatusOs.setEnabled(false);
        jRadioUsoPeca.setEnabled(false);
        jFormattedTextDtFim.setEnabled(false);
        jFormattedTextHrFim.setEnabled(false);
        jTextArea1.setEnabled(false);
    }

    public void desabilitaBottons() {
//        btnNovo.setEnabled(false);
        btnLimpar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);
        btnSalvar.setEnabled(false);
    }

    public void habilitaCampos() {
        txtCodigo.setEnabled(true);
        jComboBoxTecnico.setEnabled(true);
        jFormattedTextDtIni.setEnabled(true);
        jFormattedTextHrIni.setEnabled(true);
        jComboBoxPecas.setEnabled(false);
        jRadioEquipRet.setEnabled(true);
        jRadioStatusOs.setEnabled(true);
        jRadioUsoPeca.setEnabled(true);
        jFormattedTextDtFim.setEnabled(true);
        jFormattedTextHrFim.setEnabled(true);
        jTextArea1.setEnabled(true);
    }

    public void habilitaBottonsAtendidaExiste() {
        btnLimpar.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnAtualizar.setEnabled(true);
        if (FormMenu.lblUsuario.getText().equals("ADMGERAL")) {
            btnExcluir.setEnabled(true);
        } else {
            btnExcluir.setEnabled(false);
        }
        
    }

    public void habilitaBottonsAtendidaNaoExiste() {
        btnLimpar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        jComboBoxTecnico.setSelectedIndex(0);
    }

    public void limpaCampos() {
        txtCodigo.setText("");
        jComboBoxTecnico.setSelectedIndex(0);
        jFormattedTextDtIni.setText("");
        jFormattedTextHrIni.setText("");
        jComboBoxPecas.setSelectedIndex(0);
        jRadioEquipRet.setSelected(false);
        jRadioStatusOs.setSelected(false);
        jRadioUsoPeca.setSelected(false);
        jFormattedTextDtFim.setText("");
        jFormattedTextHrFim.setText("");
        jTextArea1.setText("");
        txtCodigo.requestFocus();
    }

    private void testaStatusBotton() {
        if (jRadioUsoPeca.isSelected()) {
            statusPeca = "Sim";
        } else {
            statusPeca = "Não";
        }

        if (jRadioEquipRet.isSelected()) {
            statusRetirado = "Sim";
        } else {
            statusRetirado = "Não";
        }

        if (jRadioStatusOs.isSelected()) {
            statusFechada = "Fechada";
        } else {
            statusFechada = "Aberta";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblHrInicio = new javax.swing.JLabel();
        jRadioEquipRet = new javax.swing.JRadioButton();
        lblTecnico = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblDtInicio = new javax.swing.JLabel();
        lblPeca = new javax.swing.JLabel();
        jRadioStatusOs = new javax.swing.JRadioButton();
        lblDtFim = new javax.swing.JLabel();
        lblHrFim = new javax.swing.JLabel();
        jFormattedTextDtIni = new javax.swing.JFormattedTextField();
        jFormattedTextHrIni = new javax.swing.JFormattedTextField();
        jFormattedTextDtFim = new javax.swing.JFormattedTextField();
        jFormattedTextHrFim = new javax.swing.JFormattedTextField();
        jComboBoxPecas = new javax.swing.JComboBox<>();
        jComboBoxTecnico = new javax.swing.JComboBox<>();
        jRadioUsoPeca = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOsAberta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO DE ATENDIMENTO DE OS`S");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/LogoSys270x250.png")).getImage());

        btnSalvar.setBackground(new java.awt.Color(0, 153, 153));
        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnLimpar.setBackground(new java.awt.Color(0, 153, 153));
        btnLimpar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLimpar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpar.setText("LIMPAR");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnAtualizar.setBackground(new java.awt.Color(0, 153, 153));
        btnAtualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizar.setText("ATUALIZAR");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(0, 153, 153));
        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("EXCLUIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnSair.setBackground(new java.awt.Color(0, 153, 153));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("DADOS DE RETORNO DA 'OS'"));

        lblHrInicio.setText("* Hr.Inicio");

        buttonGroup1.add(jRadioEquipRet);
        jRadioEquipRet.setText("Equip.Retirado?");
        jRadioEquipRet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioEquipRetMouseClicked(evt);
            }
        });
        jRadioEquipRet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioEquipRetKeyPressed(evt);
            }
        });

        lblTecnico.setText("* Técnico");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 51, 51));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("* Descrição do serviço realizado"));
        jScrollPane1.setViewportView(jTextArea1);

        lblDtInicio.setText("* Dt.Inicio");

        lblPeca.setText("Peças");

        buttonGroup1.add(jRadioStatusOs);
        jRadioStatusOs.setText("Os Fechada?");
        jRadioStatusOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioStatusOsActionPerformed(evt);
            }
        });
        jRadioStatusOs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioStatusOsKeyPressed(evt);
            }
        });

        lblDtFim.setText("Dt.Fim");

        lblHrFim.setText("Hr.Fim");

        try {
            jFormattedTextDtIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextDtIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextDtIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextDtIniKeyPressed(evt);
            }
        });

        try {
            jFormattedTextHrIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextHrIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextHrIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextHrIniKeyPressed(evt);
            }
        });

        try {
            jFormattedTextDtFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextDtFim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextDtFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextDtFimKeyPressed(evt);
            }
        });

        try {
            jFormattedTextHrFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextHrFim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextHrFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextHrFimKeyPressed(evt);
            }
        });

        jComboBoxPecas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lista zerada de peças!!!" }));

        jComboBoxTecnico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha", "Carlos", "Alberto", "Milano" }));
        jComboBoxTecnico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxTecnicoKeyPressed(evt);
            }
        });

        jRadioUsoPeca.setText("Uso de peças?");
        jRadioUsoPeca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioUsoPecaMouseClicked(evt);
            }
        });
        jRadioUsoPeca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jRadioUsoPecaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(lblDtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextDtIni, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHrInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextHrIni, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jRadioUsoPeca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioEquipRet)
                                .addGap(34, 34, 34)
                                .addComponent(jRadioStatusOs))
                            .addComponent(jComboBoxTecnico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblPeca, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHrFim, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextHrFim, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxPecas, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTecnico)
                            .addComponent(jComboBoxTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblDtInicio)
                                    .addComponent(jFormattedTextDtIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHrInicio)
                                    .addComponent(jFormattedTextHrIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioEquipRet)
                                .addComponent(jRadioStatusOs))
                            .addComponent(jRadioUsoPeca))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPeca)
                            .addComponent(jComboBoxPecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDtFim)
                                .addComponent(jFormattedTextDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHrFim)
                                .addComponent(jFormattedTextHrFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("DADOS DA ABERTURA DA OS SELECIONADA"));

        jTableOsAberta.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTableOsAberta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "CLIENTE", "DATA", "EQUIPAMENTO", "DEFEITO", "TIPO DE SERVIÇO", "OBSERVAÇÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableOsAberta.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableOsAberta);
        if (jTableOsAberta.getColumnModel().getColumnCount() > 0) {
            jTableOsAberta.getColumnModel().getColumn(1).setMinWidth(80);
            jTableOsAberta.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTableOsAberta.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jLabel1.setText("Código Os:");

        txtCodigo.setForeground(new java.awt.Color(255, 0, 51));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(102, 153, 255));
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_building_error_35763 (1).png"))); // NOI18N
        jLabel2.setText("(*) - Campo de preenchimento OBRIGATÓRIO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(69, 69, 69))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        if ((jComboBoxTecnico.getSelectedItem() == null)
                || (jFormattedTextDtIni == null)
                || (jFormattedTextHrIni == null)
                || (jFormattedTextDtFim == null)
                || (jFormattedTextHrFim == null)
                || (jTextArea1 == null)) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();

            return;
        } else {
            Os os = new Os();
            OsDAO dao = new OsDAO();
            testaStatusBotton();

            os.setTecnico(jComboBoxTecnico.getSelectedItem().toString());
            os.setDt_inicio(jFormattedTextDtIni.getText());
            os.setHr_inic(jFormattedTextHrIni.getText());
            os.setServico(jTextArea1.getText());
            os.setUso_peca(statusPeca);
            os.setEquip_retirado(statusRetirado);
            os.setAberta_fech(statusFechada);
            os.setDt_fim(jFormattedTextDtFim.getText());
            os.setHr_fim(jFormattedTextHrFim.getText());
            

            os.setIdos(Integer.parseInt(txtCodigo.getText()));

            dao.saveOsAtendida(os);
            
        }
        desabilitaBottons(); 
        limpaCampos();
        desabilitaCampos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        txtCodigo.requestFocus();
//        desabilitaBottons();
//        desabilitaCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void jRadioUsoPecaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioUsoPecaMouseClicked
        // TODO add your handling code here:

        if (jRadioUsoPeca.isSelected()) {
            Os os = new Os();
            os.setIdos(Integer.parseInt(txtCodigo.getText()));
            FormOsPecas pecas = new FormOsPecas(this, rootPaneCheckingEnabled);
            pecas.importaId(os);
            pecas.setVisible(true);
            
            //Preenche o combobox com as peças lançadas para este atendimento e habilita o combobox
            comboboxPecasForIdOsAtendida(Integer.parseInt(txtCodigo.getText()));
            jComboBoxPecas.setEnabled(true);
        } else {
            jComboBoxPecas.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioUsoPecaMouseClicked

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o código de uma Os valida para iniciar o processo", "AVISO", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                OsDAO dao = new OsDAO();
                if (dao.checkOsAbertaExiste(Integer.parseInt(txtCodigo.getText()))) {
                    readTableOsAbertaForId(Integer.parseInt(txtCodigo.getText()));
                    preencheFormeOsAtend(Integer.parseInt(txtCodigo.getText()));
                    if ((jComboBoxTecnico.getSelectedItem() == null)) {
                        desabilitaBottons();
                        desabilitaCampos();
                        int op = JOptionPane.showConfirmDialog(null, "Não existe atendimento para esta OS. DESEJA CRIAR AGORA??", "Atenção", JOptionPane.YES_NO_OPTION);

                        if (op == JOptionPane.YES_OPTION) {
                            habilitaBottonsAtendidaNaoExiste();
                            habilitaCampos();

                            testaData();

                            jComboBoxTecnico.requestFocus(true);

                        } else {
                            if (op == JOptionPane.NO_OPTION) {
                                txtCodigo.setText("");
                                txtCodigo.requestFocus();
                            }
                        }

                    } else {
                        habilitaBottonsAtendidaExiste();
                        habilitaCampos();
                        jComboBoxTecnico.requestFocus();
//                        txtCodigo.requestFocus();

                        if (jRadioUsoPeca.isSelected()) {
                            jRadioUsoPeca.setEnabled(false);
                            jComboBoxPecas.setEnabled(true);
                            comboboxPecasForIdOsAtendida(Integer.parseInt(txtCodigo.getText()));

                        } else {
                            jComboBoxPecas.removeAllItems();
                            jComboBoxPecas.addItem("Não existem peças para o atendimento");
                            jComboBoxPecas.setEnabled(false);
                            jRadioUsoPeca.setEnabled(true);
                        }

                        if (jRadioEquipRet.isSelected()) {
                            jRadioStatusOs.setEnabled(false);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "NÃO EXISTE OS PARA ESTE CÓDIGO DE ACESSO !!!!");

                    limpaCampos();
                    desabilitaCampos();
                    desabilitaBottons();
                    txtCodigo.requestFocus();
                    limparTabela();
                }
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void jComboBoxTecnicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxTecnicoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextDtIni.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxTecnicoKeyPressed

    private void jFormattedTextDtIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextDtIniKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextHrIni.requestFocus(true);
        }
    }//GEN-LAST:event_jFormattedTextDtIniKeyPressed

    private void jFormattedTextHrIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextHrIniKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jRadioUsoPeca.requestFocus(true);
        }
    }//GEN-LAST:event_jFormattedTextHrIniKeyPressed

    private void jRadioUsoPecaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioUsoPecaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jRadioEquipRet.requestFocus(true);
        }
    }//GEN-LAST:event_jRadioUsoPecaKeyPressed

    private void jRadioEquipRetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioEquipRetKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jRadioStatusOs.requestFocus(true);
        }
    }//GEN-LAST:event_jRadioEquipRetKeyPressed

    private void jRadioStatusOsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioStatusOsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jComboBoxPecas.isEnabled()) {
                jComboBoxPecas.requestFocus();
            } else {
                jFormattedTextDtFim.requestFocus();
            }
        }

    }//GEN-LAST:event_jRadioStatusOsKeyPressed

    private void jFormattedTextDtFimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextDtFimKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // PEGA DATA INICIO 00/00/00
            String dia_inicio = jFormattedTextDtIni.getText().substring(0, 2);
            String mes_inicio = jFormattedTextDtIni.getText().substring(3, 5);
            String ano_inicio = jFormattedTextDtIni.getText().substring(6, 8);
            int data_inicio = (Integer.parseInt(dia_inicio) + Integer.parseInt(mes_inicio) + Integer.parseInt(ano_inicio));

            // PEGA DATA FIM 00/00/00
            String dia_fim = jFormattedTextDtFim.getText().substring(0, 2);
            String mes_fim = jFormattedTextDtFim.getText().substring(3, 5);
            String ano_fim = jFormattedTextDtFim.getText().substring(6, 8);
            int data_fim = (Integer.parseInt(dia_fim) + Integer.parseInt(mes_fim) + Integer.parseInt(ano_fim));
            if (data_fim < data_inicio) {
                JOptionPane.showMessageDialog(this, "DATA FIM NÃO PODE SER MENOR QUE DATA INICIO. FAVOR VERIFICAR", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                jFormattedTextHrFim.requestFocus();
            }

        }

    }//GEN-LAST:event_jFormattedTextDtFimKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void jRadioEquipRetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioEquipRetMouseClicked
        // TODO add your handling code here:
        jRadioStatusOs.setEnabled(true);
    }//GEN-LAST:event_jRadioEquipRetMouseClicked

    private void jRadioStatusOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioStatusOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioStatusOsActionPerformed

    private void jFormattedTextHrFimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextHrFimKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextArea1.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextHrFimKeyPressed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
        if ((jComboBoxTecnico.getSelectedItem() == null)
                || (jFormattedTextDtIni == null)
                || (jFormattedTextHrIni == null)
                || (jFormattedTextDtFim == null)
                || (jFormattedTextHrFim == null)
                || (jTextArea1 == null)) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();

            return;
        } else {
            Os os = new Os();
            OsDAO dao = new OsDAO();
            testaStatusBotton();

            os.setTecnico(jComboBoxTecnico.getSelectedItem().toString());
            os.setDt_inicio(jFormattedTextDtIni.getText());
            os.setHr_inic(jFormattedTextHrIni.getText());
            os.setServico(jTextArea1.getText());
            os.setUso_peca(statusPeca);
            os.setEquip_retirado(statusRetirado);
            os.setAberta_fech(statusFechada);
            os.setDt_fim(jFormattedTextDtFim.getText());
            os.setHr_fim(jFormattedTextHrFim.getText());

            os.setIdos(Integer.parseInt(txtCodigo.getText()));

            dao.saveOsAtendida(os);
        }
        desabilitaBottons();
        limpaCampos();
        desabilitaCampos();
//        limparTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormOsAtendida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Object> jComboBoxPecas;
    private javax.swing.JComboBox<String> jComboBoxTecnico;
    private javax.swing.JFormattedTextField jFormattedTextDtFim;
    private javax.swing.JFormattedTextField jFormattedTextDtIni;
    private javax.swing.JFormattedTextField jFormattedTextHrFim;
    private javax.swing.JFormattedTextField jFormattedTextHrIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioEquipRet;
    private javax.swing.JRadioButton jRadioStatusOs;
    private javax.swing.JRadioButton jRadioUsoPeca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableOsAberta;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblDtFim;
    private javax.swing.JLabel lblDtInicio;
    private javax.swing.JLabel lblHrFim;
    private javax.swing.JLabel lblHrInicio;
    private javax.swing.JLabel lblPeca;
    private javax.swing.JLabel lblTecnico;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
