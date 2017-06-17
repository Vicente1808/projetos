/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author pablo
 */
public class TelaGerenciamentos extends JFrame implements ActionListener{
    
    private JButton btFuncionarios;
    private JButton btVeiculos;
    private JButton btRegistros;
    
    private final ControladorPrincipal controladorPrincipal;
    
    public TelaGerenciamentos(ControladorPrincipal controladorPrincipal){
        super("TELA DE GERENCIAMENTOS");
        this.controladorPrincipal = controladorPrincipal;
        inicia();
    }
    
    public void inicia(){
        
        btFuncionarios = new JButton();
        btVeiculos = new JButton();
        btRegistros = new JButton();
        
        Container container = getContentPane();
        container.setLayout(null);
        
        btFuncionarios.setText("GERENCIAMENTO DE FUNCIONARIOS");
        btFuncionarios.setBounds(25, 25, 300, 100);
        btFuncionarios.addActionListener(this);
        
        btVeiculos.setText("GERENCIAMENTO DE VEICULOS");
        btVeiculos.setBounds(25, 125, 300, 100);
        btVeiculos.addActionListener(this);
        
        btRegistros.setText("GERENCIAMENTO DE REGISTROS");
        btRegistros.setBounds(25, 225, 300, 100);
        btRegistros.addActionListener(this);

        container.add(btFuncionarios);
        container.add(btVeiculos);
        container.add(btRegistros);
        
        setSize(350,375);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btFuncionarios){
            ControladorPrincipal.getInstance().carregaTelaGerenciamentoFuncionarios();
        }else if(ae.getSource()==btVeiculos){
            
        }else if(ae.getSource()==btRegistros){
            
        }
    }
    
    
}
