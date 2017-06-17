/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import br.ufsc.ine5605.claviculario.enums.RetiradaEDevolucao;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author Adri
 */
public class TelaRetiradaVeiculo extends JFrame implements ActionListener {
    
    private final ControladorPrincipal ctrl;
    private JLabel lbRetiradaVeiculo;
    private JTextField tfMatricula;
    private JLabel lbMatricula;
    private JComboBox cbVeiculosDisponiveis;
    private JLabel lbVeiculosDisponiveis;
    private JButton btEnviarMatricula;
    private JButton btEnviarVeiculo;
    private JButton btVoltar;
    private JLabel lbEvento;
    private Integer matriculaUsuarioConectado;

    public TelaRetiradaVeiculo(ControladorPrincipal owner) {
        super("Claviculario Eletrônico");
        this.ctrl = owner;
        init();
    }
    
    private void init() {
        Container container = getContentPane();
        container.setLayout(null);
        
        lbRetiradaVeiculo = new JLabel();
        
        tfMatricula = new JTextField();
        lbMatricula = new JLabel();
        
        cbVeiculosDisponiveis = new JComboBox();
        lbVeiculosDisponiveis = new JLabel();
        
        btEnviarMatricula = new JButton();
        btEnviarVeiculo = new JButton();
        btVoltar = new JButton();
        
        lbEvento = new JLabel();
        
        Insets insets = getInsets();
        
        Dimension size = lbRetiradaVeiculo.getPreferredSize();
        lbRetiradaVeiculo.setBounds(120 + insets.left, 35 + insets.top, 300 + size.width, 20 + size.height);
        
        size = tfMatricula.getPreferredSize();
        tfMatricula.setBounds(130 + insets.left, 100 + insets.top, 128 + size.width, size.height);
        
        size = lbMatricula.getPreferredSize();
        lbMatricula.setBounds(70 + insets.left, 100 + insets.top, 80 + size.width, 20 + size.height);
        
        size = cbVeiculosDisponiveis.getPreferredSize();
        cbVeiculosDisponiveis.setBounds(130 + insets.left, 125 + insets.top, 100 + size.width, size.height);
        
        size = lbVeiculosDisponiveis.getPreferredSize();
        lbVeiculosDisponiveis.setBounds(70 + insets.left, 125 + insets.top, 80 + size.width, 20 + size.height);
        
        size = btEnviarMatricula.getPreferredSize();
        btEnviarMatricula.setBounds(265 + insets.left, 100 + insets.top, 40 + size.width, 9 + size.height);
        
        size = btEnviarVeiculo.getPreferredSize();
        btEnviarVeiculo.setBounds(265 + insets.left, 125 + insets.top, 40 + size.width, 15 + size.height);
        
        size = lbEvento.getPreferredSize();
        lbEvento.setBounds(70 + insets.left, 150 + insets.top, 300 + size.width, 20 + size.height);
        
        size = btVoltar.getPreferredSize();
        btVoltar.setBounds(250 + insets.left, 390 + insets.top, 60 + size.width, 30 + size.height);
        
        lbRetiradaVeiculo.setText("Retire um Veiculo");
        lbRetiradaVeiculo.setFont(new Font("Arial", Font.PLAIN, 20));
        
        lbMatricula.setText("Matricula:");
        lbVeiculosDisponiveis.setText("Veiculos:");
        
        btEnviarMatricula.setText("Enviar");
        btEnviarMatricula.addActionListener(this);
        btEnviarVeiculo.setText("Retirar");
        btEnviarVeiculo.addActionListener(this);
        btVoltar.setText("Voltar");
        btVoltar.addActionListener(this);
        
        
        lbEvento.setForeground(Color.RED);
        
        container.add(lbRetiradaVeiculo);
        container.add(tfMatricula);
        container.add(lbMatricula);
        container.add(cbVeiculosDisponiveis);
        container.add(lbVeiculosDisponiveis);
        container.add(btEnviarMatricula);
        container.add(btEnviarVeiculo);
        container.add(lbEvento);
        container.add(btVoltar);
        
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btEnviarMatricula) {
            
            //matriculaUsuarioConectado = null;
            lbEvento.setText(null);
            cbVeiculosDisponiveis.removeAllItems();
            
            MutableComboBoxModel<String> cbm = (MutableComboBoxModel) cbVeiculosDisponiveis.getModel();           
            ArrayList<String> placas;
            
            String texto = tfMatricula.getText();
            int matricula = 0;
            
            RetiradaEDevolucao mensagemRetorno = null;
            
            boolean excecao = false;
            try {
                matricula = Integer.parseInt(texto);
            } catch(NumberFormatException ex) {
                excecao = true;
            }
            if(!excecao) {
                if(ControladorFuncionarios.getInstance().validarMatricula(matricula)) {
                    placas = ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculos;                
                for(String placa : placas) {
                    cbm.addElement(placa);
                } 
                } else {
                    mensagemRetorno = RetiradaEDevolucao.MATRICULAINCORRETA;
                }
            } else {
                mensagemRetorno = RetiradaEDevolucao.MATRICULAINCORRETA;
            }
            if(mensagemRetorno != null) {
                lbEvento.setText(mensagemRetorno.getMensagem());
            } else {
                matriculaUsuarioConectado = matricula;
            }
            repaint();
        } else if(e.getSource() == btEnviarVeiculo) {
            String placa = (String) cbVeiculosDisponiveis.getModel().getSelectedItem();
            if(placa != null) {
                RetiradaEDevolucao mensagemRetorno = ctrl.retirarVeiculo(matriculaUsuarioConectado, placa);

                lbEvento.setText(mensagemRetorno.getMensagem());
            } 
        } else if(e.getSource() == btVoltar) {
            ctrl.carregarTelaPrincipal();
        }
    }

    

}
