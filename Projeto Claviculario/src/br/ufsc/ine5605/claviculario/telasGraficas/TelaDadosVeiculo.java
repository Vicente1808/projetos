/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorVeiculos;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author pablo
 */
public class TelaDadosVeiculo extends JFrame implements ActionListener {

    private ControladorVeiculos controladorVeiculos;
    private JLabel lbPlaca;
    private JLabel lbMarca;
    private JLabel lbModelo;
    private JLabel lbAno;
    private JLabel lbKmAtual;
    private JLabel lbDisponível;
    
    private JFormattedTextField tfPlaca;
    private JTextField tfMarca;
    private JTextField tfModelo;
    private JFormattedTextField tfAno;
    private JFormattedTextField tfKmAtual;
    private JCheckBox cjDisponivel;
    
    public TelaDadosVeiculo(String tiulo){
        super(tiulo);
        this.controladorVeiculos = controladorVeiculos;
        inicia();
    }
    
    public void inicia(){
        
    lbPlaca = new JLabel();
    lbMarca = new JLabel();
    lbModelo = new JLabel();
    lbAno = new JLabel();
    lbKmAtual = new JLabel();
    lbDisponível = new JLabel();
    
    tfPlaca = new JFormattedTextField();
    tfMarca = new JTextField();
    tfModelo = new JTextField();
    tfAno = new JFormattedTextField();
    tfKmAtual = new JFormattedTextField();
    cjDisponivel = new JCheckBox();
        
    lbPlaca.setText("Placa: ");
    lbPlaca.setBounds(50, 20, 100, 50);
    
    lbMarca.setText("Marca: ");

    
    lbModelo.setText("Modelo: ");

    
    lbAno.setText("Ano: ");

    
    lbKmAtual.setText("KM Atual: ");


    lbDisponível.setText("Disponibilidade: ");
    
    
    tfPlaca = new JFormattedTextField();
    tfMarca = new JTextField();
    tfModelo = new JTextField();
    tfAno = new JFormattedTextField();
    tfKmAtual = new JFormattedTextField();
    cjDisponivel = new JCheckBox();
    
    Container container = getContentPane();
    container.add(lbPlaca);
        setSize(680,460);
        setLocationRelativeTo(null);
        setResizable(false);
    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
