/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author pablo
 */
public class TelaPrincipalGrafica extends JFrame implements ActionListener {
    
    private ControladorPrincipal ctrl;
    JButton btRetirar;
    JButton btDevolver;
    JButton btGerenciar;
    JLabel lbTitulo;
    
    public TelaPrincipalGrafica(ControladorPrincipal owner) {
        super("Claviculário Eletrônico");
        init();
        ctrl = owner;
    }
    
    public void init() {
        Container container = this.getContentPane();
        container.setLayout(null);
        
        btRetirar = new JButton();
        btDevolver = new JButton();
        btGerenciar = new JButton();
        lbTitulo = new JLabel();
        
        Insets insets = container.getInsets();
        
        btRetirar.setText("Retirar Veículo");
        btRetirar.addActionListener(this);
        
        btDevolver.setText("DevolverVeiculo");
        btDevolver.addActionListener(this);
        
        btGerenciar.setText("Gerenciar");
        btGerenciar.addActionListener(this);
        
        lbTitulo.setText("Bem-Vindo ao Claviculário");
        lbTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
        
        Dimension size = btRetirar.getPreferredSize();
        btRetirar.setBounds(40 + insets.left, 150 + insets.top, 300 + size.width, 50 + size.height);
        
        size = btDevolver.getPreferredSize();
        btDevolver.setBounds(40 + insets.left, 250 + insets.top, 290 + size.width, 50 + size.height);
        
        size = btGerenciar.getPreferredSize();
        btGerenciar.setBounds(40 + insets.left, 350 + insets.top, 330 + size.width, 50 + size.height);
        
        size = lbTitulo.getPreferredSize();
        lbTitulo.setBounds(180 + insets.left, 50 + insets.top, 200 + size.width, 25 + size.height);
        
        container.add(btRetirar);
        container.add(btDevolver);
        container.add(btGerenciar);
        container.add(lbTitulo);
        
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btRetirar) {
             ctrl.carregarMenuRetirada(); 
             System.out.println("teste");
        }
    }
    
}
