/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.enums.RetiradaEDevolucao;
import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Adri
 */
public class TelaGraficaDevolucao extends JFrame implements ActionListener {
    
    private final ControladorPrincipal ctrl;
    private JLabel lbTitulo;
    private JLabel lbMatricula;
    private JLabel lbPlaca;
    private JLabel lbEvento;
    private JTextField tfMatricula;
    private JTextField tfPlaca;
    private JLabel lbKmAtual;
    private JTextField tfKmAtual;
    private JButton btEnviar;
    private JButton btDevolver;
    private JButton btVoltar;
    private Integer matriculaUsuarioConectado;
    
    public TelaGraficaDevolucao(ControladorPrincipal owner) {
        super("Claviculario Eletrônico");
        ctrl = owner;
        init();
    } 
    
    private void init() {
        
        Container container = getContentPane();
        container.setLayout(null);
        
        lbTitulo = new JLabel();
        lbMatricula = new JLabel();
        lbPlaca = new JLabel();
        lbEvento = new JLabel();
        tfMatricula = new JTextField();
        tfPlaca = new JTextField();
        lbKmAtual = new JLabel();
        tfKmAtual = new JTextField();
        btEnviar = new JButton();
        btDevolver = new JButton();
        btVoltar = new JButton();
        
        Insets insets = container.getInsets();
        
        Dimension size = lbMatricula.getPreferredSize();
        lbMatricula.setBounds(70 + insets.left, 100 + insets.top, 80 + size.width, 20 + size.height);
        
        size = tfMatricula.getPreferredSize();
        tfMatricula.setBounds(130 + insets.left, 100 + insets.top, 128 + size.width, size.height);
        
        size = btEnviar.getPreferredSize();
        btEnviar.setBounds(265 + insets.left, 100 + insets.top, 50 + size.width, 9 + size.height);
        
        size = tfKmAtual.getPreferredSize();
        tfKmAtual.setBounds(130 + insets.left, 125 + insets.top, 128 + size.width, size.height);
        
        size = lbKmAtual.getPreferredSize();
        lbKmAtual.setBounds(71 + insets.left, 125 + insets.top, 80 + size.width, 20 + size.height);
        
        size = lbPlaca.getPreferredSize();
        lbPlaca.setBounds(90 + insets.left, 150 + insets.top, 80 + size.width, 20 + size.height);
        
        size = tfPlaca.getPreferredSize();
        tfPlaca.setBounds(130 + insets.left, 150 + insets.top, 128 + size.width, size.height);
        
        size = btDevolver.getPreferredSize();
        btDevolver.setBounds(265 + insets.left, 150 + insets.top, 50 + size.width, 9 + size.height);
        
        size = lbEvento.getPreferredSize();
        lbEvento.setBounds(90 + insets.left, 180 + insets.top, 300 + size.width, 20 + size.height);
        
        size = btVoltar.getPreferredSize();
        btVoltar.setBounds(235 + insets.left, 280 + insets.top, 80 + size.width, 30 + size.height);
        
        lbMatricula.setText("Matricula:");
        lbPlaca.setText("Placa:");
        lbEvento.setForeground(Color.red);
        
        tfPlaca.setEditable(false);
        
        lbKmAtual.setText("Km Atual:");
        
        tfKmAtual.setEditable(false);
        
        btEnviar.setText("Enviar");
        btEnviar.addActionListener(this);
        
        btDevolver.setText("Devolver");
        btDevolver.addActionListener(this);
        
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(this);
        
        container.add(lbMatricula);
        container.add(lbPlaca);
        container.add(tfMatricula);
        container.add(tfPlaca);
        container.add(tfKmAtual);
        container.add(lbKmAtual);
        container.add(btEnviar);
        container.add(btDevolver);
        container.add(lbEvento);
        container.add(btVoltar);
        
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btEnviar) {
            
            matriculaUsuarioConectado = null;
            
            String mensagemRetorno = "";
            FuncionarioVO funcionarioVO;
            boolean excecao = false;
            int matricula = 0;
            try {
                matricula = Integer.parseInt(tfMatricula.getText());
            } catch(NumberFormatException ex) {
                excecao = true;
            }
            if(!excecao) {
                if(!ControladorFuncionarios.getInstance().validarMatricula(matricula)) {
                    mensagemRetorno = RetiradaEDevolucao.MATRICULAINCORRETA.getMensagem();
                } else {
                    matriculaUsuarioConectado = matricula;
                    funcionarioVO = ControladorFuncionarios.getInstance().getDadosFuncionario(matricula);
                    if(funcionarioVO.veiculoPendente == null) {
                        mensagemRetorno = "Nenhum veiculo pendente";
                    } else {
                        tfKmAtual.setEditable(true);
                        tfPlaca.setText(funcionarioVO.veiculoPendente);
                    }
                }
            } else {
                mensagemRetorno = RetiradaEDevolucao.MATRICULAINCORRETA.getMensagem();
            }
            lbEvento.setText(mensagemRetorno);
        } else if(e.getSource() == btDevolver) {
            
            lbEvento.setText(null);
            String mensagemRetorno;
            boolean excecao = false;
            
            int kmAtual = 0;
            try { 
                kmAtual = Integer.parseInt(tfKmAtual.getText());
            } catch(NumberFormatException ex) {
                excecao = true;
            }
            if(!excecao) {
                ctrl.devolverVeiculo(matriculaUsuarioConectado, tfPlaca.getText(), kmAtual);
                mensagemRetorno = RetiradaEDevolucao.VEICULODEVOLVIDO.getMensagem();
            } else {
                mensagemRetorno = "Quilometragem incorreta";
            }
            lbEvento.setText(mensagemRetorno);
            matriculaUsuarioConectado = null;
        } else if(e.getSource() == btVoltar) {
            ctrl.carregarMenuPrincipal();
        }
    }
    
}
