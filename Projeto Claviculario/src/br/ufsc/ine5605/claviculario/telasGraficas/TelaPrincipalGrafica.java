/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author pablo
 */
public class TelaPrincipalGrafica extends JFrame implements ActionListener {
    
    private ControladorPrincipal ctrl;
    
    public TelaPrincipalGrafica() {
        init();
        ctrl = ControladorPrincipal.getInstance();
    }
    
    public void init() {
        Container container = this.getContentPane();
        container.setLayout(null);
        
        JButton btRetirar;
        JButton btDevolver;
        JButton btGerenciar;
        
        btRetirar = new JButton("Retirar Veiculo");
        btDevolver = new JButton("Devolver Veiculo");
        btGerenciar = new JButton("Gerenciar");
        
        Insets insets = container.getInsets();
        
        Dimension size = btRetirar.getPreferredSize();
        btRetirar.setBounds(50 + insets.left, 50 + insets.top, 300 + size.width, 50 + size.height);
        
        size = btDevolver.getPreferredSize();
        btDevolver.setBounds(50 + insets.left, 150 + insets.top, 290 + size.width, 50 + size.height);
        
        size = btGerenciar.getPreferredSize();
        btGerenciar.setBounds(50 + insets.left, 250 + insets.top, 330 + size.width, 50 + size.height);
        
        container.add(btRetirar);
        container.add(btDevolver);
        container.add(btGerenciar);
        
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
