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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Os;
import model.dao.OsDAO;

/**
 *
 * @author MILANO
 */
public class FormOsAtendida extends javax.swing.JFrame {

    private boolean statusPeca;
    private boolean statusRetirado;
    private boolean statusFechada;

    /**
     * Creates new form FormAtendeOs
     */
    public FormOsAtendida() {
        initComponents();
        desabilitaCampos();
        desabilitaBottons();

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
            } else {
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

    }

    public void desabilitaCampos() {
        txtCodigo.setEnabled(false);
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

    public void habilitaBottonsNovo() {
        btnLimpar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnAtualizar.setEnabled(false);
        btnExcluir.setEnabled(false);
        txtCodigo.requestFocus();
    }

    public void limpaCampos() {
        txtCodigo.setText("");
        jComboBoxTecnico.setSelectedIndex(0);
        jFormattedTextDtIni.setText("");
        jFormattedTextHrIni.setText("");
        jComboBoxPecas.setSelectedIndex(0);
        jRadioEquipRet.setEnabled(false);
        jRadioStatusOs.setEnabled(false);
        jRadioUsoPeca.setEnabled(false);
        jFormattedTextDtFim.setText("");
        jFormattedTextHrFim.setText("");
        jTextArea1.setText("");

    }

    private void testaStatusBotton() {
        if (jRadioUsoPeca.isSelected()) {
            statusPeca = true;
        } else {
            statusPeca = false;
        }

        if (jRadioEquipRet.isSelected()) {
            statusRetirado = true;
        } else {
            statusRetirado = false;
        }

        if (jRadioStatusOs.isSelected()) {
            statusFechada = true;
        } else {
            statusFechada = false;
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

        jPanel1 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO DE ATENDIMENTO DE OS`S");

        btnSalvar.setBackground(new java.awt.Color(0, 51, 255));
        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(0, 51, 255));
        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnLimpar.setBackground(new java.awt.Color(0, 51, 255));
        btnLimpar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLimpar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpar.setText("LIMPAR");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnAtualizar.setBackground(new java.awt.Color(0, 51, 255));
        btnAtualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizar.setText("ATUALIZAR");

        btnExcluir.setBackground(new java.awt.Color(0, 51, 255));
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
                            .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSair.setBackground(new java.awt.Color(0, 51, 255));
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

        jRadioStatusOs.setText("Os Fechada?");
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

        jComboBoxTecnico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Carlos", "Alberto", "Milano" }));
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel1.setText("Código Os:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:

        if ((jComboBoxTecnico.getSelectedItem() == null)
                || (jFormattedTextDtIni == null)
                || (jFormattedTextHrIni == null)
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
            os.setDt_fim(jFormattedTextDtIni.getText());
            os.setHr_fim(jFormattedTextHrIni.getText());

            os.setIdos(Integer.parseInt(txtCodigo.getText()));

            dao.saveOsAtendida(os);
        }
        desabilitaBottons();
        limpaCampos();
        desabilitaCampos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitaBottonsNovo();
        txtCodigo.setEnabled(true);
    }//GEN-LAST:event_btnNovoActionPerformed

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
            jComboBoxPecas.setEnabled(true);
        } else {
            jComboBoxPecas.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioUsoPecaMouseClicked

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o código de uma Os para iniciar o processo", "AVISO", JOptionPane.WARNING_MESSAGE);
                return;
            }
            OsDAO dao = new OsDAO();
            if (dao.checkOsAtendidaExiste(Integer.parseInt(txtCodigo.getText()))) {
                habilitaBottonsNovo();
                habilitaCampos();

                preencheFormeOsAtend(Integer.parseInt(txtCodigo.getText()));

                readTableOsAbertaForId(Integer.parseInt(txtCodigo.getText()));

                if (jRadioUsoPeca.isSelected()) {
                    jComboBoxPecas.setEnabled(true);
                } else {
                    jComboBoxPecas.setEnabled(false);
                }

                if (jRadioEquipRet.isSelected()) {
                    jRadioStatusOs.setEnabled(false);
                } 
                jComboBoxTecnico.requestFocus();
            } else {
                limpaCampos();
                desabilitaCampos();
                desabilitaBottons();
                JOptionPane.showMessageDialog(null, "OS NÃO LOCALIZADA");
                txtCodigo.requestFocus();
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
            jFormattedTextHrFim.requestFocus();
        }

    }//GEN-LAST:event_jFormattedTextDtFimKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void jRadioEquipRetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioEquipRetMouseClicked
        // TODO add your handling code here:
        if (jRadioEquipRet.isSelected()) {
            jRadioStatusOs.setSelected(false);
            jRadioStatusOs.setEnabled(false);
        }else{
            jRadioStatusOs.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioEquipRetMouseClicked

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
            java.util.logging.Logger.getLogger(FormOsAtendida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOsAtendida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Object> jComboBoxPecas;
    private javax.swing.JComboBox<String> jComboBoxTecnico;
    private javax.swing.JFormattedTextField jFormattedTextDtFim;
    private javax.swing.JFormattedTextField jFormattedTextDtIni;
    private javax.swing.JFormattedTextField jFormattedTextHrFim;
    private javax.swing.JFormattedTextField jFormattedTextHrIni;
    private javax.swing.JLabel jLabel1;
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
