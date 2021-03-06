/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cidade;
import model.bean.Estado;
import model.dao.CidadeDAO;
import model.dao.EstadoDAO;
import model.bean.Fornecedor;
import model.dao.FornecedorDAO;

/**
 *
 * @author Master
 */
public class FormFornecedor extends javax.swing.JFrame {

    /**
     * Creates new form FormAdministradora
     */
    public FormFornecedor() {
        initComponents();

        //As linhas abaixo servem para ordenar a JTableAdmin
        DefaultTableModel modelo = (DefaultTableModel) jTableFornecedor.getModel();
        jTableFornecedor.setRowSorter(new TableRowSorter(modelo));

        //A linha abaixo chama o método que preenche a tabela lendo os dados do banco 
        readTable();
        // a linha abaixo chama o método que prepara os combobox estado
        comboBoxEstado();

        // a linha abaixo chama o método que prepara os combobox cidade
        comboBoxCidade();

        //A linha abaixo chama o metodo que inicializa campos e bottons
        inicializaSistema();
    }

    public void inicializaSistema() {
        //As linhas abaixo preparam botões e campos
        txtId.setEnabled(false);
        txtCnpj.setEnabled(false);
        txtNome.setEnabled(false);
        txtContato.setEnabled(false);
        txtUrl.setEnabled(false);
        txtEmail.setEnabled(false);
        jFormattedTextFone1.setEnabled(false);
        jFormattedTextFone2.setEnabled(false);
        jFormattedTextCelular.setEnabled(false);

        txtRua.setEnabled(false);
        txtNumero.setEnabled(false);
        txtComplemento.setEnabled(false);
        txtBairro.setEnabled(false);
        txtReferencia.setEnabled(false);
        jFormattedTextCep.setEnabled(false);
        jComboBoxEstado.setEnabled(false);
        jComboBoxCidade.setEnabled(false);
        txtGps.setEnabled(false);

        txtConsulta.setEnabled(true);
        btnConsulta.setEnabled(true);

        btnNovo.setEnabled(true);
        btnLimpar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnSair.setEnabled(true);
    }

    //inicializa o combobox estado
    public void comboBoxEstado() {
        //As linhas abaixo preenchem os combobox estado
        EstadoDAO estdao = new EstadoDAO();
        jComboBoxEstado.removeAllItems();
        jComboBoxEstado.addItem("Escolha");
        for (Estado e : estdao.readAllEstado()) {
            jComboBoxEstado.addItem(e);
        }
    }

    //inicializa o combobox cidade
    public void comboBoxCidade() {
        //As linhas abaixo preenchem os combobox cidade
        CidadeDAO citdao = new CidadeDAO();
        jComboBoxCidade.removeAllItems();
        jComboBoxCidade.addItem("Escolha");
        for (Cidade c : citdao.readAllCidade()) {
            jComboBoxCidade.addItem(c);
        }
    }

    public void prepararCampos() {
        //Limpa campos
        txtId.setText("");
        txtCnpj.setText("");
        txtNome.setText("");
        txtContato.setText("");
        txtUrl.setText("");
        txtEmail.setText("");
        jFormattedTextFone1.setText("");
        jFormattedTextFone2.setText("");
        jFormattedTextCelular.setText("");

        txtRua.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtReferencia.setText("");
        jFormattedTextCep.setText("");
        jComboBoxEstado.setSelectedIndex(0);
        jComboBoxCidade.setSelectedIndex(0);
        txtGps.setText("");

        //Limpa o campo texto da consulta        
        txtConsulta.setText("");

        //Reinicia a tabela        
        readTable();

        //Posiciona o cursor
        txtCnpj.requestFocus();
    }
    
    public void habilitaCampos(){
        //As linhas abaixo habilitam os campos
        txtId.setEnabled(true);
        txtCnpj.setEnabled(true);
        txtNome.setEnabled(true);
        txtContato.setEnabled(true);
        txtUrl.setEnabled(true);
        txtEmail.setEnabled(true);
        jFormattedTextFone1.setEnabled(true);
        jFormattedTextFone2.setEnabled(true);
        jFormattedTextCelular.setEnabled(true);

        txtRua.setEnabled(true);
        txtNumero.setEnabled(true);
        txtComplemento.setEnabled(true);
        txtBairro.setEnabled(true);
        txtReferencia.setEnabled(true);
        jFormattedTextCep.setEnabled(true);
        jComboBoxEstado.setEnabled(true);
        jComboBoxCidade.setEnabled(true);
        txtGps.setEnabled(true);

    }

    public void desabilitaCampos() {
        //As linhas abaixo desabilitam os campos
        txtId.setEnabled(false);
        txtCnpj.setEnabled(false);
        txtNome.setEnabled(false);
        txtContato.setEnabled(false);
        txtUrl.setEnabled(false);
        txtEmail.setEnabled(false);
        jFormattedTextFone1.setEnabled(false);
        jFormattedTextFone2.setEnabled(false);
        jFormattedTextCelular.setEnabled(false);

        txtRua.setEnabled(false);
        txtNumero.setEnabled(false);
        txtComplemento.setEnabled(false);
        txtBairro.setEnabled(false);
        txtReferencia.setEnabled(false);
        jFormattedTextCep.setEnabled(false);
        jComboBoxEstado.setEnabled(false);
        jComboBoxCidade.setEnabled(false);
        txtGps.setEnabled(false);
    }

    //inicializa a tabela
    public void readTable() {
        DefaultTableModel modelo = (DefaultTableModel) jTableFornecedor.getModel();
        modelo.setNumRows(0);
        FornecedorDAO pdao = new FornecedorDAO();

        for (Fornecedor p : pdao.readTableAllFornecedor()) {
            modelo.addRow(new Object[]{
                p.getIdfornec(),
                p.getCnpj(),
                p.getNome(),
                p.getContato(),
                p.getUrl(),
                p.getEmail(),
                p.getFone1(),
                p.getFone2(),
                p.getCelular(),
                p.getRua(),
                p.getNumero(),
                p.getComplemento(),
                p.getBairro(),
                p.getReferencia(),
                p.getCep(),
                p.getEstado(),
                p.getCidade(),
                p.getGps()
            });
        }
    }

    //prepara a consulta por nome
    public void readTableforNome(String nome) {
        DefaultTableModel modelo = (DefaultTableModel) jTableFornecedor.getModel();
        modelo.setNumRows(0);
        FornecedorDAO pdao = new FornecedorDAO();

        for (Fornecedor p : pdao.readForNameFornecedor(nome)) {
            modelo.addRow(new Object[]{
                p.getIdfornec(),
                p.getCnpj(),
                p.getNome(),
                p.getContato(),
                p.getUrl(),
                p.getEmail(),
                p.getFone1(),
                p.getFone2(),
                p.getCelular(),
                p.getRua(),
                p.getNumero(),
                p.getComplemento(),
                p.getBairro(),
                p.getReferencia(),
                p.getCep(),
                p.getEstado(),
                p.getCidade(),
                p.getGps()
            });
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

        jPanel3 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblCidade = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        txtGps = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        lblGps = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblComplemento = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        lblCep = new javax.swing.JLabel();
        lblReferencia = new javax.swing.JLabel();
        lblBairro = new javax.swing.JLabel();
        lblRua = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jComboBoxCidade = new javax.swing.JComboBox<>();
        jFormattedTextCep = new javax.swing.JFormattedTextField();
        lblCodigo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCelular = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCnpj = new javax.swing.JTextField();
        lblFone1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblUrl = new javax.swing.JLabel();
        lblContato = new javax.swing.JLabel();
        txtContato = new javax.swing.JTextField();
        txtUrl = new javax.swing.JTextField();
        lblCnpjCpf = new javax.swing.JLabel();
        jFormattedTextFone1 = new javax.swing.JFormattedTextField();
        jFormattedTextCelular = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFone2 = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        btnConsulta = new javax.swing.JButton();
        txtConsulta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFornecedor = new javax.swing.JTable();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CADASTRO DE FORNECEDORES");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/LogoSys270x250.png")).getImage());

        txtId.setEditable(false);
        txtId.setForeground(new java.awt.Color(255, 51, 51));
        txtId.setToolTipText("Código da Administradora no BD");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ENDEREÇO"));

        lblCidade.setText("* Cidade");

        lblNumero.setText("Número");

        txtRua.setToolTipText("Fique a vontade para digitar pois o conteúdo, será sempre, armazenado todo em maiúscula.");
        txtRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRuaKeyPressed(evt);
            }
        });

        txtGps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGpsKeyPressed(evt);
            }
        });

        txtReferencia.setToolTipText("Fique a vontade para digitar pois o conteúdo,\nserá sempre, armazenado todo em maiúscula.");
        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyPressed(evt);
            }
        });

        lblGps.setText("Gps");

        lblEstado.setText("* Estado");

        lblComplemento.setText("Complemento");

        txtComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtComplementoKeyPressed(evt);
            }
        });

        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroKeyPressed(evt);
            }
        });

        txtBairro.setToolTipText("Fique a vontade para digitar pois o conteúdo, será sempre, armazenado todo em maiúscula.");
        txtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBairroKeyPressed(evt);
            }
        });

        lblCep.setText("Cep");

        lblReferencia.setText("Referência");

        lblBairro.setText("* Bairro");

        lblRua.setText("* Rua");

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha" }));
        jComboBoxEstado.setToolTipText("Click 2 vezes neste campo para criar\n filtro de cidades por estado");
        jComboBoxEstado.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxEstadoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBoxEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxEstadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxEstadoKeyReleased(evt);
            }
        });

        jComboBoxCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha" }));
        jComboBoxCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxCidadeKeyPressed(evt);
            }
        });

        try {
            jFormattedTextCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextCepKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblRua)
                        .addGap(18, 18, 18)
                        .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNumero)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblComplemento)
                            .addComponent(lblReferencia)
                            .addComponent(lblGps)
                            .addComponent(lblEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGps, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblBairro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBairro))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCep)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextCep, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComplemento)
                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBairro)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferencia)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCep)
                    .addComponent(jFormattedTextCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(lblCidade)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGps)
                    .addComponent(txtGps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCodigo.setText("Código");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DADOS GERAIS"));

        lblCelular.setText("* Celular");

        lblEmail.setText("* E-mail");

        lblNome.setText("* Nome");

        txtNome.setToolTipText("Fique a vontade para digitar pois o conteúdo,\nserá sempre, armazenado todo em maiúscula.");
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        txtCnpj.setToolTipText("O conteúdo deste campo será armazenado da mesma forma que for digitado.\nPor isso, convem padronizar o método com ou sem caracteres de separação.");
        txtCnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCnpjKeyPressed(evt);
            }
        });

        lblFone1.setText("Fone1");

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        lblUrl.setText("Url");

        lblContato.setText("* Contato");

        txtContato.setToolTipText("Fique a vontade para digitar pois o conteúdo,\nserá sempre, armazenado todo em maiúscula.");
        txtContato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContatoKeyPressed(evt);
            }
        });

        txtUrl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUrlKeyPressed(evt);
            }
        });

        lblCnpjCpf.setText("* CNPJ");

        try {
            jFormattedTextFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFone1KeyPressed(evt);
            }
        });

        try {
            jFormattedTextCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextCelularKeyPressed(evt);
            }
        });

        jLabel2.setText("Fone2");

        try {
            jFormattedTextFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFone2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFone2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jFormattedTextFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblFone1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCelular)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblUrl))
                                .addGap(18, 18, 18)
                                .addComponent(lblEmail))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(75, 75, 75)
                                    .addComponent(txtContato))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblContato)
                                    .addGap(238, 238, 238))))
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCnpjCpf)
                            .addComponent(lblNome))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCnpjCpf)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContato)
                    .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEmail)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblUrl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFone1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jFormattedTextFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCelular)
                        .addComponent(jFormattedTextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("CONSULTA POR NOME"));

        btnConsulta.setBackground(new java.awt.Color(0, 153, 153));
        btnConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btnConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bg-input-azul.png"))); // NOI18N
        btnConsulta.setText("...");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsulta)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(102, 153, 255));
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_building_error_35763 (1).png"))); // NOI18N
        jLabel1.setText("(*) - Campo de preenchimento OBRIGATÓRIO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigo)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        btnNovo.setBackground(new java.awt.Color(0, 153, 153));
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_building_add_35760.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnLimpar.setBackground(new java.awt.Color(0, 153, 153));
        btnLimpar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/limpar.png"))); // NOI18N
        btnLimpar.setText("LIMPAR");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(0, 153, 153));
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_building_delete_35761 (1).png"))); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(0, 153, 153));
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_building_add_35760 (1).png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnAtualizar.setBackground(new java.awt.Color(0, 153, 153));
        btnAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_building_edit_35762 (1).png"))); // NOI18N
        btnAtualizar.setText("ATUALIZAR");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTableFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓD", "CNPJ", "NOME", "CONTATO", "URL", "E-MAIL", "FONE1", "FONE2", "CELULAR", "RUA", "NUMERO", "COMPLEMENTO", "BAIRRO", "REFERÊNCIA", "CEP", "ESTADO", "CIDADE", "GPS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFornecedorMouseClicked(evt);
            }
        });
        jTableFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableFornecedorKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFornecedor);

        btnSair.setBackground(new java.awt.Color(0, 153, 153));
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-url-ee.png"))); // NOI18N
        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1316, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        //limpa campos, recarrega tabela, posiciona o ponteiro dos comboboxs e posiciona cursor
        prepararCampos();
        
        //a linha abaixo habilita os campos
        habilitaCampos();

        // Prepara botões
        btnLimpar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);
        btnConsulta.setEnabled(true);


    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        //limpa campos, recarrega tabela, posiciona o ponteiro dos comboboxs e posiciona cursor
        prepararCampos();

        // Prepara botões
        btnLimpar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);
        btnConsulta.setEnabled(true);
        
        //recarrego o comboboxCidade com todas as cidades
        comboBoxCidade();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if ((jFormattedTextCelular.getText().equals("(  )     -    "))
                || (txtCnpj.getText().isEmpty())
                || (txtNome.getText().isEmpty())
                || (txtRua.getText().isEmpty())
                || (txtBairro.getText().isEmpty())
                || (txtEmail.getText().isEmpty())
                || (txtContato.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCnpj.requestFocus();
            return;
        }

        if (jComboBoxEstado.getSelectedItem() != "Escolha" && jComboBoxCidade.getSelectedItem() != "Escolha") {

            Fornecedor f = new Fornecedor();
            FornecedorDAO dao = new FornecedorDAO();

            f.setCnpj(txtCnpj.getText());
            f.setNome(txtNome.getText().toUpperCase());
            f.setRua(txtRua.getText().toUpperCase());
            f.setNumero(txtNumero.getText());
            f.setComplemento(txtComplemento.getText());
            f.setBairro(txtBairro.getText().toUpperCase());
            f.setCep(jFormattedTextCep.getText());
            f.setReferencia(txtReferencia.getText().toUpperCase());
            f.setGps(txtGps.getText());
            f.setFone1(jFormattedTextFone1.getText());
            f.setFone2(jFormattedTextFone2.getText());
            f.setCelular(jFormattedTextCelular.getText());
            f.setUrl(txtUrl.getText());
            f.setEmail(txtEmail.getText());
            f.setContato(txtContato.getText().toUpperCase());

            //As linhas abaixo pegam no combobox, que contem o objeto, o idcidade e o idestado
            Cidade city = (Cidade) jComboBoxCidade.getSelectedItem();
            city.setIdcidade(city.getIdcidade());
            f.setCidade(city);

            Estado estado = (Estado) jComboBoxEstado.getSelectedItem();
            estado.setIdestado(estado.getIdestado());
            f.setEstado(estado);

            dao.saveFornecedor(f);

            //limpa campos, recarrega tabela, posiciona o ponteiro dos comboboxs e posiciona cursor
            prepararCampos();

            //As linhas abaixo desabilitam campos
            desabilitaCampos();

            //Atualiza botões        
            btnLimpar.setEnabled(false);
            btnSalvar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnAtualizar.setEnabled(false);
            
            //recarrego o comboboxCidade com todas as cidades
            comboBoxCidade();
            
        } else {
            JOptionPane.showMessageDialog(null, "Verifique os campos estado e cidade, AMBOS DEVEM SER SELECIONADOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // As linhas abaixo testam se campos obrigatórios estão preenchidos
        if ((jFormattedTextCelular.getText().equals("(  )     -    "))
                || (txtCnpj.getText().isEmpty())
                || (txtNome.getText().isEmpty())
                || (txtRua.getText().isEmpty())
                || (txtBairro.getText().isEmpty())
                || (txtEmail.getText().isEmpty())
                || (txtContato.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCnpj.requestFocus();
            return;
        }
        //testa a condi;'ao do combobox e prepara a atualização
        if (jComboBoxEstado.getSelectedItem() != "Escolha" && jComboBoxCidade.getSelectedItem() != "Escolha") {

            Fornecedor f = new Fornecedor();
            FornecedorDAO dao = new FornecedorDAO();

            f.setCnpj(txtCnpj.getText());
            f.setNome(txtNome.getText().toUpperCase());
            f.setRua(txtRua.getText().toUpperCase());
            f.setNumero(txtNumero.getText());
            f.setComplemento(txtComplemento.getText());
            f.setBairro(txtBairro.getText().toUpperCase());
            f.setCep(jFormattedTextCep.getText());
            f.setReferencia(txtReferencia.getText().toUpperCase());
            f.setGps(txtGps.getText());
            f.setFone1(jFormattedTextFone1.getText());
            f.setFone2(jFormattedTextFone2.getText());
            f.setCelular(jFormattedTextCelular.getText());
            f.setUrl(txtUrl.getText());
            f.setEmail(txtEmail.getText());
            f.setContato(txtContato.getText().toUpperCase());

            //As linhas abaixo pegam no combobox, que contem o objeto, o idcidade e o idestado
            Cidade city = (Cidade) jComboBoxCidade.getSelectedItem();
            city.setIdcidade(city.getIdcidade());
            f.setCidade(city);

            Estado estado = (Estado) jComboBoxEstado.getSelectedItem();
            estado.setIdestado(estado.getIdestado());
            f.setEstado(estado);
            
            f.setIdfornec((int) jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 0));
            

            dao.updateFornecedor(f);

            //limpa campos, recarrega tabela, posiciona o ponteiro dos comboboxs e posiciona cursor
            prepararCampos();

            //As linhas abaixo desabilitam campos
            desabilitaCampos();

            //Atualiza botões        
            btnLimpar.setEnabled(false);
            btnSalvar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnAtualizar.setEnabled(false);
            
            //recarrego o comboboxCidade com todas as cidades
            comboBoxCidade();
            
        } else {
            JOptionPane.showMessageDialog(null, "Verifique os campos estado e cidade, AMBOS DEVEM SER SELECIONADOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // As linhas abaixo confirmam o interesse em excluir o registro
        int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja EXCLUIR este registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {

            Fornecedor cli = new Fornecedor();
            FornecedorDAO dao = new FornecedorDAO();

            cli.setIdfornec((int) jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 0));

            dao.deleteFornecedor(cli);

            //limpa campos, recarrega tabela, posiciona o ponteiro dos comboboxs e posiciona cursor
            prepararCampos();

            //As linhas abaixo desabilitam campos
            desabilitaCampos();

            //Atualiza botões
            btnLimpar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnSalvar.setEnabled(false);
            btnAtualizar.setEnabled(false);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTableFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFornecedorMouseClicked
        //carrega o comboboxcidade com todas as cidades
        comboBoxCidade();
        
        // As linhas abaixo selecionam um item da tabela para ser alterado e copiam o item para os campos.
        if (jTableFornecedor.getSelectedRow() != -1) {
            
           //habilita campos
           habilitaCampos();
           
            //pega dados na tabela e preenche os campos
            txtId.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 0).toString());
            txtCnpj.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 1).toString());
            txtNome.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 2).toString());
            txtContato.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 3).toString());
            txtUrl.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 4).toString());
            txtEmail.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 5).toString());
            jFormattedTextFone1.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 6).toString());
            jFormattedTextFone2.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 7).toString());
            jFormattedTextCelular.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 8).toString());

            txtRua.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 9).toString());
            txtNumero.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 10).toString());
            txtComplemento.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 11).toString());
            txtBairro.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 12).toString());
            txtReferencia.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 13).toString());
            jFormattedTextCep.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 14).toString());

            Estado est = (Estado) jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 15);
            est.setIdestado(Integer.parseInt(String.valueOf(est.getIdestado())));
            jComboBoxEstado.setSelectedIndex(est.getIdestado());

            Cidade cid = (Cidade) jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 16);
            cid.setIdcidade(Integer.parseInt(String.valueOf(cid.getIdcidade())));
            jComboBoxCidade.setSelectedIndex(cid.getIdcidade());

            txtGps.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 17).toString());
            
           // Prepara botões
            btnLimpar.setEnabled(false);
            
            if (FormMenu.lblUsuario.getText().equals("Adiministrador")) {
                btnExcluir.setEnabled(true);
            } else {
                btnExcluir.setEnabled(false);
            }
            
            btnAtualizar.setEnabled(true);
            btnSalvar.setEnabled(false);
        }
    }//GEN-LAST:event_jTableFornecedorMouseClicked

    private void jTableFornecedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFornecedorKeyReleased
        //carrega o comboboxcidade com todas as cidades
        comboBoxCidade();
        
        // As linhas abaixo selecionam um item da tabela para ser alterado e copiam o item para os campos.
        if (jTableFornecedor.getSelectedRow() != -1) {
            
           //habilita campos
           habilitaCampos();
           
            //pega dados na tabela e preenche os campos
            txtId.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 0).toString());
            txtCnpj.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 1).toString());
            txtNome.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 2).toString());
            txtContato.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 3).toString());
            txtUrl.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 4).toString());
            txtEmail.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 5).toString());
            jFormattedTextFone1.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 6).toString());
            jFormattedTextFone2.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 7).toString());
            jFormattedTextCelular.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 8).toString());

            txtRua.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 9).toString());
            txtNumero.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 10).toString());
            txtComplemento.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 11).toString());
            txtBairro.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 12).toString());
            txtReferencia.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 13).toString());
            jFormattedTextCep.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 14).toString());

            Estado est = (Estado) jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 15);
            est.setIdestado(Integer.parseInt(String.valueOf(est.getIdestado())));
            JOptionPane.showMessageDialog(null, est.getIdestado());
            jComboBoxEstado.setSelectedIndex(est.getIdestado());

            Cidade cid = (Cidade) jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 16);
            cid.setIdcidade(Integer.parseInt(String.valueOf(cid.getIdcidade())));
            JOptionPane.showMessageDialog(null, cid.getIdcidade());
            jComboBoxCidade.setSelectedIndex(cid.getIdcidade());

            txtGps.setText(jTableFornecedor.getValueAt(jTableFornecedor.getSelectedRow(), 17).toString());
            
            // Prepara botões
            btnLimpar.setEnabled(false);
            
            if (FormMenu.lblUsuario.getText().equals("ADMGERAL")) {
                btnExcluir.setEnabled(true);
            } else {
                btnExcluir.setEnabled(false);
            }
            
            btnAtualizar.setEnabled(true);
            btnSalvar.setEnabled(false);
        }
    }//GEN-LAST:event_jTableFornecedorKeyReleased

    private void txtRuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRuaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNumero.requestFocus();
        }
    }//GEN-LAST:event_txtRuaKeyPressed

    private void txtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtComplemento.requestFocus();
        }
    }//GEN-LAST:event_txtNumeroKeyPressed

    private void txtComplementoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplementoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBairro.requestFocus();
        }
    }//GEN-LAST:event_txtComplementoKeyPressed

    private void txtBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtReferencia.requestFocus();
        }
    }//GEN-LAST:event_txtBairroKeyPressed

    private void txtReferenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextCep.requestFocus();
        }
    }//GEN-LAST:event_txtReferenciaKeyPressed

    private void jFormattedTextCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextCepKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBoxEstado.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextCepKeyPressed

    private void jComboBoxEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxEstadoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jComboBoxCidade.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxEstadoKeyPressed

    private void jComboBoxCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxCidadeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGps.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxCidadeKeyPressed

    private void txtGpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGpsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSalvar.requestFocus();
        }
    }//GEN-LAST:event_txtGpsKeyPressed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        // TODO add your handling code here:

        readTableforNome(txtConsulta.getText());

    }//GEN-LAST:event_btnConsultaActionPerformed

    private void jFormattedTextCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextCelularKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtRua.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextCelularKeyPressed

    private void jFormattedTextFone1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFone1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextFone2.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFone1KeyPressed

    private void txtUrlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtUrlKeyPressed

    private void txtContatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContatoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUrl.requestFocus();
        }
    }//GEN-LAST:event_txtContatoKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextFone1.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtCnpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCnpjKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNome.requestFocus();
        }
    }//GEN-LAST:event_txtCnpjKeyPressed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtContato.requestFocus();
        }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void jFormattedTextFone2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFone2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextCelular.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFone2KeyPressed

    private void jComboBoxEstadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxEstadoPopupMenuWillBecomeInvisible
        // As linhas abaixo filtam as cidades no combobox cidade quando o estado no comboboxestado é mudado
         if (jComboBoxEstado.getSelectedItem() != "Escolha") {

            Estado estado = (Estado) jComboBoxEstado.getSelectedItem();

            estado.setIdestado(estado.getIdestado());
            jComboBoxEstado.setSelectedIndex(estado.getIdestado());

            CidadeDAO citdao = new CidadeDAO();
            jComboBoxCidade.removeAllItems();
            jComboBoxCidade.addItem("Escolha");
            for (Cidade c : citdao.readComboBoxCidade(estado.getIdestado())) {
                jComboBoxCidade.addItem(c);
            }
        }
    }//GEN-LAST:event_jComboBoxEstadoPopupMenuWillBecomeInvisible

    private void jComboBoxEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxEstadoKeyReleased
        // TODO add your handling code here:
         if (jComboBoxEstado.getSelectedItem() != "Escolha") {

            Estado estado = (Estado) jComboBoxEstado.getSelectedItem();

            estado.setIdestado(estado.getIdestado());
            jComboBoxEstado.setSelectedIndex(estado.getIdestado());

            CidadeDAO citdao = new CidadeDAO();
            jComboBoxCidade.removeAllItems();
            jComboBoxCidade.addItem("Escolha");
            for (Cidade c : citdao.readComboBoxCidade(estado.getIdestado())) {
                jComboBoxCidade.addItem(c);
            }
        }
    }//GEN-LAST:event_jComboBoxEstadoKeyReleased

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
            java.util.logging.Logger.getLogger(FormFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFornecedor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormFornecedor().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Object> jComboBoxCidade;
    private javax.swing.JComboBox<Object> jComboBoxEstado;
    private javax.swing.JFormattedTextField jFormattedTextCelular;
    private javax.swing.JFormattedTextField jFormattedTextCep;
    private javax.swing.JFormattedTextField jFormattedTextFone1;
    private javax.swing.JFormattedTextField jFormattedTextFone2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFornecedor;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCep;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCnpjCpf;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblComplemento;
    private javax.swing.JLabel lblContato;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFone1;
    private javax.swing.JLabel lblGps;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtConsulta;
    private javax.swing.JTextField txtContato;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGps;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables
}
