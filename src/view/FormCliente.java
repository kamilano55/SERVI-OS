/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cidade;
import model.bean.Cliente;
import model.bean.Estado;
import model.dao.CidadeDAO;
import model.dao.ClienteDAO;
//import model.dao.ContratoDAO;
import model.dao.EstadoDAO;

/**
 *
 * @author Master
 */
public class FormCliente extends javax.swing.JFrame {

    /**
     * Creates new form FormAdministradora
     */
    public FormCliente() {
        initComponents();

        //As linhas abaixo servem para ordenar a JTableAdmin
        DefaultTableModel modelo = (DefaultTableModel) jTableCliente.getModel();
        jTableCliente.setRowSorter(new TableRowSorter(modelo));

        //A linha abaixo chama o método que preenche a tabela lendo os dados do banco 
        readTable();
        // a linha abaixo chama o método que prepara os combobox estado e cidade lendo do banco
        comboBoxEstado();
        comboBoxCidade();
//        comboBoxCidadeCompleta();
        //As linhas abaixo preparam botões e campos iniciais
        inicializaSistema();

    }

    public void inicializaSistema() {
        //As linhas abaixo preparam botões e campos iniciais
        txtId.setEnabled(false);
        txtCnpjCpf.setEnabled(false);
        txtNome.setEnabled(false);
        txtContato.setEnabled(false);
        txtCargo.setEnabled(false);
        txtUrl.setEnabled(false);
        txtEmail.setEnabled(false);
        jFormattedTextFone1.setEnabled(false);
        jFormattedTextFone2.setEnabled(false);
        jFormattedTextCelular.setEnabled(false);
        txtObs.setEnabled(false);

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

        lblFoto.setEnabled(false);
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoSys270x250.png")));
        lblNomeFoto.setEnabled(false);

        btnNovo.setEnabled(true);
        btnLimpar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnSair.setEnabled(true);
        
        //recarrego o comboboxCidade com todas as cidades
            comboBoxCidade();
    }

    public void limpaCampos() {
        //Limpa campos
        txtId.setText("");
        txtCnpjCpf.setText("");
        txtNome.setText("");
        txtContato.setText("");
        txtCargo.setText("");
        txtUrl.setText("");
        txtEmail.setText("");
        jFormattedTextFone1.setText("");
        jFormattedTextFone2.setText("");
        jFormattedTextCelular.setText("");
        txtObs.setText("");

        txtRua.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtReferencia.setText("");
        jFormattedTextCep.setText("");
        jComboBoxEstado.setSelectedIndex(0);
        jComboBoxCidade.setSelectedIndex(0);
        txtGps.setText("");

        //Limpa o label da foto e preenche com foto padrão        
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoSys270x250.png")));
        lblNomeFoto.setText("");

        //Limpa o campo texto da consulta        
        txtConsulta.setText("");
    }

    public void habilitaCampos() {
        //Habilita campos
        txtCnpjCpf.setEnabled(true);
        txtNome.setEnabled(true);
        txtContato.setEnabled(true);
        txtCargo.setEnabled(true);
        txtUrl.setEnabled(true);
        txtEmail.setEnabled(true);
        jFormattedTextFone1.setEnabled(true);
        jFormattedTextFone2.setEnabled(true);
        jFormattedTextCelular.setEnabled(true);
        txtObs.setEnabled(true);

        txtRua.setEnabled(true);
        txtNumero.setEnabled(true);
        txtComplemento.setEnabled(true);
        txtBairro.setEnabled(true);
        txtReferencia.setEnabled(true);
        jFormattedTextCep.setEnabled(true);
        jComboBoxEstado.setEnabled(true);
        jComboBoxCidade.setEnabled(true);
        txtGps.setEnabled(true);
        lblFoto.setEnabled(true);
        lblNomeFoto.setEnabled(false);
    }

    public void desabilitaCampos() {
        //As linhas abaixo preparam botões e campos
        txtId.setEnabled(false);
        txtCnpjCpf.setEnabled(false);
        txtNome.setEnabled(false);
        txtContato.setEnabled(false);
        txtCargo.setEnabled(false);
        txtUrl.setEnabled(false);
        txtEmail.setEnabled(false);
        jFormattedTextFone1.setEnabled(false);
        jFormattedTextFone2.setEnabled(false);
        jFormattedTextCelular.setEnabled(false);
        txtObs.setEnabled(false);

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

    //As linhas abaixo preenchem os combobox estado
    public void comboBoxEstado() {

        EstadoDAO estdao = new EstadoDAO();
        jComboBoxEstado.removeAllItems();
        jComboBoxEstado.addItem("Escolha");
        for (Estado e : estdao.readAllEstado()) {
            jComboBoxEstado.addItem(e);
        }
    }

    public void comboBoxCidade() {

        CidadeDAO citdao = new CidadeDAO();
        jComboBoxCidade.removeAllItems();
        jComboBoxCidade.addItem("Escolha");
        for (Cidade c : citdao.readAllCidade()) {
            jComboBoxCidade.addItem(c);
        }
    }

    //Monta a tabela de Clientes
    public void readTable() {
        DefaultTableModel modelo = (DefaultTableModel) jTableCliente.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();

        for (Cliente p : pdao.readTableAllCliente()) {
            modelo.addRow(new Object[]{
                p.getIdcliente(),
                p.getCnpjcpf(),
                p.getNome(),
                p.getContato(),
                p.getCargo(),
                p.getUrl(),
                p.getEmail(),
                p.getFone1(),
                p.getFone2(),
                p.getCelular(),
                p.getObs(),
                p.getRua(),
                p.getNumero(),
                p.getComplemento(),
                p.getBairro(),
                p.getReferencia(),
                p.getCep(),
                p.getEstado(),
                p.getCidade(),
                p.getGps(),
                p.getFoto()
            });
        }
    }

    //Monta a tabela de clientes para consulta por nome
    public void readTableforNome(String nome) {
        DefaultTableModel modelo = (DefaultTableModel) jTableCliente.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();

        for (Cliente p : pdao.readForNomeCliente(nome)) {
            modelo.addRow(new Object[]{
                p.getIdcliente(),
                p.getCnpjcpf(),
                p.getNome(),
                p.getContato(),
                p.getCargo(),
                p.getUrl(),
                p.getEmail(),
                p.getFone1(),
                p.getFone2(),
                p.getCelular(),
                p.getObs(),
                p.getRua(),
                p.getNumero(),
                p.getComplemento(),
                p.getBairro(),
                p.getReferencia(),
                p.getCep(),
                p.getEstado(),
                p.getCidade(),
                p.getGps(),
                p.getFoto()
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
        txtCnpjCpf = new javax.swing.JTextField();
        lblFone1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblUrl = new javax.swing.JLabel();
        lblContato = new javax.swing.JLabel();
        txtContato = new javax.swing.JTextField();
        txtUrl = new javax.swing.JTextField();
        lblCnpjCpf = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jFormattedTextFone1 = new javax.swing.JFormattedTextField();
        jFormattedTextCelular = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFone2 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtObs = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblNomeFoto = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnConsulta = new javax.swing.JButton();
        txtConsulta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CADASTRO DE CLIENTE");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        txtCnpjCpf.setToolTipText("O conteúdo deste campo será armazenado da mesma forma que for digitado.\nPor isso, convem padronizar o método com ou sem caracteres de separação.");
        txtCnpjCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCnpjCpfKeyPressed(evt);
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

        lblCnpjCpf.setText("* CNPJ/CPF");

        lblCargo.setText(" Cargo");

        txtCargo.setToolTipText("Fique a vontade para digitar pois o conteúdo, será sempre, armazenado todo em maiúscula.");
        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCargoKeyPressed(evt);
            }
        });

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

        jLabel3.setText("Obs");

        txtObs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObsKeyPressed(evt);
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(txtContato))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblContato)
                                        .addGap(238, 238, 238)))
                                .addGap(16, 16, 16)
                                .addComponent(lblCargo)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addComponent(txtCargo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtObs))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCnpjCpf)
                            .addComponent(lblNome))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCnpjCpf)
                    .addComponent(txtCnpjCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContato)
                    .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCargo)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jFormattedTextCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lblFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotoMouseClicked(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("NOME DA FOTO"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(txtConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsulta)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        btnSair.setBackground(new java.awt.Color(0, 153, 153));
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
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
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓD", "CNPJ/CPF", "NOME", "CONTATO", "CARGO", "URL", "EMAIL", "FONE1", "FONE2", "CELULAR", "OBS", "RUA", "NÚMERO", "COMPL.", "BAIRRO", "REFERÊNCIA", "CEP", "ESTADO", "CIDADE", "GPS", "FOTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClienteMouseClicked(evt);
            }
        });
        jTableCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableClienteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCliente);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1386, 739));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampos();
        habilitaCampos();

        // Prepara botões para rotina Novo
        btnLimpar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);

        // Posiciona o cursor
        txtCnpjCpf.requestFocus();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        //A linha abaixo chama o metodo de limpeza de campos
        limpaCampos();

        //Posiciona o cursor
        txtCnpjCpf.requestFocus();

        //preenche o combobox cidade
        jComboBoxEstado.setSelectedIndex(0);
        jComboBoxCidade.setSelectedIndex(0);

//Reinicia a tabela        
        readTable();

// Prepara botões
        btnLimpar.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnExcluir.setEnabled(false);
        btnAtualizar.setEnabled(false);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if ((jFormattedTextCelular.getText().equals("(  )     -    "))
                || (txtCnpjCpf.getText().isEmpty())
                || (txtNome.getText().isEmpty())
                || (txtRua.getText().isEmpty())
                || (txtBairro.getText().isEmpty())
                || (txtEmail.getText().isEmpty())
                || (txtContato.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCnpjCpf.requestFocus();
            return;
        }

        if (jComboBoxEstado.getSelectedItem() != "Escolha" && jComboBoxCidade.getSelectedItem() != "Escolha") {

            Cliente cli = new Cliente();
            ClienteDAO dao = new ClienteDAO();

            cli.setCnpjcpf(txtCnpjCpf.getText());
            cli.setNome(txtNome.getText().toUpperCase());
            cli.setRua(txtRua.getText().toUpperCase());
            cli.setNumero(txtNumero.getText());
            cli.setComplemento(txtComplemento.getText());
            cli.setBairro(txtBairro.getText().toUpperCase());
            cli.setCep(jFormattedTextCep.getText());
            cli.setReferencia(txtReferencia.getText().toUpperCase());
            cli.setGps(txtGps.getText());
            cli.setFone1(jFormattedTextFone1.getText());
            cli.setFone2(jFormattedTextFone2.getText());
            cli.setCelular(jFormattedTextCelular.getText());
            cli.setObs(txtObs.getText());
            cli.setUrl(txtUrl.getText());
            cli.setEmail(txtEmail.getText());
            cli.setContato(txtContato.getText().toUpperCase());
            cli.setCargo(txtCargo.getText().toUpperCase());
            cli.setFoto(lblNomeFoto.getText());

//As linhas abaixo pegam no combobox, que contem o objeto, o idcidade e o idestado
            Cidade city = (Cidade) jComboBoxCidade.getSelectedItem();
            city.setIdcidade(city.getIdcidade());
            cli.setCidade(city);

            Estado estado = (Estado) jComboBoxEstado.getSelectedItem();
            estado.setIdestado(estado.getIdestado());
            cli.setEstado(estado);

            dao.saveCliente(cli);

            //Reinicia o formulario        
            readTable();
            limpaCampos();
            inicializaSistema();

        } else {
            JOptionPane.showMessageDialog(null, "Verifique os campos estado e cidade, AMBOS DEVEM SER SELECIONADOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // a linha abaixo fecha a pagina
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed

        if ((jFormattedTextCelular.getText().equals("(  )     -    "))
                || (txtCnpjCpf.getText().isEmpty())
                || (txtNome.getText().isEmpty())
                || (txtRua.getText().isEmpty())
                || (txtBairro.getText().isEmpty())
                || (txtEmail.getText().isEmpty())
                || (txtContato.getText().isEmpty())) {
//                || (jComboBoxContrato.getSelectedIndex() == 0)) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCnpjCpf.requestFocus();
            return;
        }

        if (jComboBoxEstado.getSelectedItem() != "Escolha" && jComboBoxCidade.getSelectedItem() != "Escolha") {

            Cliente cli = new Cliente();
            ClienteDAO dao = new ClienteDAO();

            cli.setCnpjcpf(txtCnpjCpf.getText());
            cli.setNome(txtNome.getText().toUpperCase());
            cli.setRua(txtRua.getText().toUpperCase());
            cli.setNumero(txtNumero.getText());
            cli.setComplemento(txtComplemento.getText());
            cli.setBairro(txtBairro.getText().toUpperCase());
            cli.setCep(jFormattedTextCep.getText());
            cli.setReferencia(txtReferencia.getText().toUpperCase());
            cli.setGps(txtGps.getText());
            cli.setFone1(jFormattedTextFone1.getText());
            cli.setFone2(jFormattedTextFone2.getText());
            cli.setCelular(jFormattedTextCelular.getText());
            cli.setObs(txtObs.getText());
            cli.setUrl(txtUrl.getText());
            cli.setEmail(txtEmail.getText());
            cli.setContato(txtContato.getText().toUpperCase());
            cli.setCargo(txtCargo.getText().toUpperCase());
            cli.setFoto(lblNomeFoto.getText());

            //As linhas abaixo pegam no combobox, que contem o objeto, o idcidade e o idestado
            Cidade city = (Cidade) jComboBoxCidade.getSelectedItem();
            city.setIdcidade(city.getIdcidade());
            cli.setCidade(city);

            Estado estado = (Estado) jComboBoxEstado.getSelectedItem();
            estado.setIdestado(estado.getIdestado());
            cli.setEstado(estado);

            cli.setIdcliente((int) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 0));

            dao.updateCliente(cli);

            //Reinicia o formulario        
            readTable();
            limpaCampos();
            inicializaSistema();
        } else {
            JOptionPane.showMessageDialog(null, "Verifique os campos estado e cidade, AMBOS DEVEM SER SELECIONADOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
// As linhas abaixo exclui um objeto(registro) do banco de dados

        int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja EXCLUIR este registro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (excluir == JOptionPane.YES_OPTION) {

            Cliente cli = new Cliente();
            ClienteDAO dao = new ClienteDAO();

            cli.setIdcliente((int) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 0));

            dao.deleteCliente(cli);
            
            //Reinicia o formulario        
            readTable();
            limpaCampos();
            inicializaSistema();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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

    private void lblFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotoMouseClicked
        //testa se os campos obrigatórios estão todos preenchidos
        if ((jFormattedTextCelular.getText().equals("(  )     -    "))
                || (txtCnpjCpf.getText().isEmpty())
                || (txtNome.getText().isEmpty())
                || (txtRua.getText().isEmpty())
                || (txtBairro.getText().isEmpty())
                || (txtEmail.getText().isEmpty())
                || (txtContato.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios, TODOS DEVEM ESTAR PREENCHIDOS!! ", "AVISO", JOptionPane.WARNING_MESSAGE);
            txtCnpjCpf.requestFocus();
            return;
        }

        // Seleciona, posiciona e armazena a foto escolhida
        if (evt.getClickCount() == 2 && (!txtCnpjCpf.getText().isEmpty())) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Procurar arquivos");
            fc.setCurrentDirectory(new File("C:\\Users\\MILANO\\Pictures"));
            fc.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File file) {
                    String name = file.getAbsolutePath();
                    return name.endsWith("jpg") | name.endsWith("png") | name.endsWith("bmp") | file.isDirectory();

                }

                @Override
                public String getDescription() {
                    return "Filtro(jpg,png,bmp e Diretórios)";
                }
            });

            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                String path = fc.getSelectedFile().getAbsolutePath();
                ImageIcon icone1 = new ImageIcon(path);
                Image img = icone1.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT);
                lblFoto.setIcon(new ImageIcon(img));
                String nomeImagem = System.currentTimeMillis() + ".jpg";
                lblNomeFoto.setText(nomeImagem);

                File novaImagem = new File("C:\\Users\\MILANO\\Pictures\\imagens-Projeto Os2\\" + nomeImagem);

                BufferedImage bi = new BufferedImage(lblFoto.getWidth(), lblFoto.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = bi.createGraphics();
                g2d.drawImage(img, null, null);
                g2d.dispose();
                try {
                    ImageIO.write(bi, "JPG", novaImagem);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Falha na criação, dimensionamento ou gravação do arquivo imagem");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um arquivo de imagem válido");

            }
        }
    }//GEN-LAST:event_lblFotoMouseClicked

    private void jFormattedTextCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextCelularKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtObs.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextCelularKeyPressed

    private void jFormattedTextFone1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFone1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextFone2.requestFocus();
        }
    }//GEN-LAST:event_jFormattedTextFone1KeyPressed

    private void txtCargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUrl.requestFocus();
        }
    }//GEN-LAST:event_txtCargoKeyPressed

    private void txtUrlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUrlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtUrlKeyPressed

    private void txtContatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContatoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCargo.requestFocus();
        }
    }//GEN-LAST:event_txtContatoKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jFormattedTextFone1.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtCnpjCpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCnpjCpfKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNome.requestFocus();
        }
    }//GEN-LAST:event_txtCnpjCpfKeyPressed

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

    private void txtObsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtRua.requestFocus();
        }
    }//GEN-LAST:event_txtObsKeyPressed

    private void jTableClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableClienteKeyReleased
        // TODO add your handling code here:
        //carrega o combobox cidade com todas as cidades
        comboBoxCidade();
        
        //As linhas abaixo selecionam um item da tabela para ser alterado e copiam o item para os campos
        if (jTableCliente.getSelectedRow() != -1) {
            habilitaCampos();

            //Prepara bottons
            if (FormMenu.lblUsuario.getText().equals("ADMGERAL")) {
                btnExcluir.setEnabled(true);
            } else {
                btnExcluir.setEnabled(false);
            }
            
            btnAtualizar.setEnabled(true);
            btnSalvar.setEnabled(false);
            btnLimpar.setEnabled(false);
            
            //Pega dados direto da tabela txtId
            txtId.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 0).toString());
            txtCnpjCpf.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 1).toString());
            txtNome.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 2).toString());
            txtContato.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 3).toString());
            txtCargo.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 4).toString());
            txtUrl.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 5).toString());
            txtEmail.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 6).toString());
            jFormattedTextFone1.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 7).toString());
            jFormattedTextFone2.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 8).toString());
            jFormattedTextCelular.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 9).toString());
            txtObs.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 10).toString());

            txtRua.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 11).toString());
            txtNumero.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 12).toString());
            txtComplemento.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 13).toString());
            txtBairro.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 14).toString());
            txtReferencia.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 15).toString());
            jFormattedTextCep.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 16).toString());

            //As linhas abaixo selecionam o estado no combobox baseado na informação da linha selecionada na tabela
            Estado est = (Estado) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 17);
            est.setIdestado(Integer.parseInt(String.valueOf(est.getIdestado())));
            jComboBoxEstado.setSelectedIndex(est.getIdestado());

            //As linhas abaixo seleciona a cidade no combobox
            Cidade cid = (Cidade) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 18);
            cid.setIdcidade(Integer.parseInt(String.valueOf(cid.getIdcidade())));
            jComboBoxCidade.setSelectedIndex(cid.getIdcidade());

            txtGps.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 19).toString());

            Cliente c = new Cliente();

            c.setFoto((String) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 20));
            String nomeImagem = c.getFoto();

            if (!nomeImagem.isEmpty()) {

                ImageIcon icone = new ImageIcon("C:\\Users\\MILANO\\Pictures\\imagens-Projeto Os2\\" + nomeImagem);
                lblFoto.setIcon(icone);
                lblNomeFoto.setText(nomeImagem);

            } else {
                JOptionPane.showMessageDialog(null, "Não existe Imagem para este registro");

                //Reinicia label foto 
                lblNomeFoto.setText("");
                lblNomeFoto.setEnabled(false);
                lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoSys270x250.png")));
            }
        }
        //posiciona o cursor no primeiro campo
        txtCnpjCpf.requestFocus();
    }//GEN-LAST:event_jTableClienteKeyReleased

    private void jTableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClienteMouseClicked
        // TODO add your handling code here:
        //carrega o combobox cidade com todas as cidades
        comboBoxCidade();
        
        // As linhas abaixo selecionam um item da tabela para ser alterado e copiam o item para os campos.
        if (jTableCliente.getSelectedRow() != -1) {
            habilitaCampos();

             //Prepara bottons
            if (FormMenu.lblUsuario.getText().equals("ADMGERAL")) {
                btnExcluir.setEnabled(true);
            } else {
                btnExcluir.setEnabled(false);
            }
            
            btnAtualizar.setEnabled(true);
            btnSalvar.setEnabled(false);
            btnLimpar.setEnabled(false);

            //Pega dados direto da tabela
            txtId.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 0).toString());
            txtCnpjCpf.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 1).toString());
            txtNome.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 2).toString());
            txtContato.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 3).toString());
            txtCargo.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 4).toString());
            txtUrl.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 5).toString());
            txtEmail.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 6).toString());
            jFormattedTextFone1.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 7).toString());
            jFormattedTextFone2.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 8).toString());
            jFormattedTextCelular.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 9).toString());
            txtObs.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 10).toString());

            txtRua.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 11).toString());
            txtNumero.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 12).toString());
            txtComplemento.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 13).toString());
            txtBairro.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 14).toString());
            txtReferencia.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 15).toString());
            jFormattedTextCep.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 16).toString());

            //As linhas abaixo selecionam o estado no combobox baseado na informação da linha selecionada na tabela
            Estado est = (Estado) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 17);
            est.setIdestado(Integer.parseInt(String.valueOf(est.getIdestado())));
            jComboBoxEstado.setSelectedIndex(est.getIdestado());

            //As linhas abaixo seleciona a cidade no combobox
            Cidade cid = (Cidade) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 18);
            cid.setIdcidade(Integer.parseInt(String.valueOf(cid.getIdcidade())));
            jComboBoxCidade.setSelectedIndex(cid.getIdcidade());

            txtGps.setText(jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 19).toString());

            Cliente c = new Cliente();
//            ClienteDAO dao = new ClienteDAO();

            c.setFoto((String) jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 20));
            String nomeImagem = c.getFoto();

            if (!nomeImagem.isEmpty()) {

                ImageIcon icone = new ImageIcon("C:\\Users\\MILANO\\Pictures\\imagens-Projeto Os2\\" + nomeImagem);
                lblFoto.setIcon(icone);
                lblNomeFoto.setText(nomeImagem);

            } else {
                JOptionPane.showMessageDialog(null, "Não existe Imagem para este registro");

                //Reinicia label foto 
                lblNomeFoto.setText("");
                lblNomeFoto.setEnabled(false);
                lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoSys270x250.png")));
            }
        }
        //posiciona o cursor no primeiro campo
        txtCnpjCpf.requestFocus();
    }//GEN-LAST:event_jTableClienteMouseClicked

    private void jComboBoxEstadoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxEstadoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
         // As linhas abaixo fazem a troca dos dados no combobox cidade em função do estado escolhido
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
          // As linhas abaixo fazem a troca dos dados no combobox cidade em função do estado escolhido
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
            java.util.logging.Logger.getLogger(FormCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormCliente().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCargo;
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
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblGps;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeFoto;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCnpjCpf;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtConsulta;
    private javax.swing.JTextField txtContato;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGps;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtObs;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables
}
