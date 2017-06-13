/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author pablo
 */
public class TelaGraficaDadosFuncionario extends JFrame implements ActionListener{
    
    private JLabel lbTitulo;
    private JLabel lbMatricula;
    private JLabel lbNome;
    private JLabel lbDataNascimento;
    private JLabel lbTelefone;
    private JLabel lbCargo;
    private JLabel  lbBloqueado;
    private JLabel telinha;
        
    private JFormattedTextField matricula;
    private JTextField nome;
    private JFormattedTextField dataNascimento;
    private JFormattedTextField telefone;
    private JComboBox cargo;
    private JCheckBox bloqueado;
    
    public TelaGraficaDadosFuncionario(){
        inicia();
    }
    
    private void inicia(){
        lbTitulo = new JLabel();
        lbMatricula = new JLabel();
        lbNome = new JLabel();
        lbDataNascimento = new JLabel();
        lbTelefone = new JLabel();
        lbCargo = new JLabel();
        lbBloqueado = new JLabel();
        telinha = new JLabel();
        
        matricula = new JFormattedTextField();
        nome = new JTextField();
        dataNascimento = new JFormattedTextField();
        telefone = new JFormattedTextField();
        cargo = new JComboBox();
        bloqueado = new JCheckBox();
        
        Container container = getContentPane();
        container.setLayout(null);
        //container.setBackground(Color.white);
        
        lbTitulo.setText(getTitle());
        lbTitulo.setForeground(Color.black);
        lbTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
        lbTitulo.setBounds(50, 20, 100, 50);
        
        lbMatricula.setText("Matrícula:");
        lbMatricula.setBounds(50, 50, 100, 50);
        
        matricula.setBounds(170, 60, 120, 25);
        
        lbNome.setText("Nome:");
        lbNome.setBounds(50, 100, 100, 50);
        
        nome.setBounds(170, 110, 120, 25);
        
        lbDataNascimento.setText("Data Nascimento:");
        lbDataNascimento.setBounds(50, 150, 100, 50);
        
        dataNascimento.setBounds(170, 160, 120, 25);
        
        lbTelefone.setText("Telefone:");
        lbTelefone.setBounds(50, 200, 100, 50);
        
        telefone.setBounds(170, 210, 120, 25);
        
        lbCargo.setText("Cargo:");
        lbCargo.setBounds(50, 250, 100, 50);
        
        cargo.setBounds(170, 260, 120, 25);
        
        lbBloqueado.setText("Status:");      
        lbBloqueado.setBounds(50, 300, 100, 50);
        
        bloqueado.setText("Bloqueado");
        bloqueado.setBounds(170, 310, 120, 25);
        
        JPanel painel = new JPanel();
        
        
        painel.setBounds(50, 400, 290, 200);
        painel.setBackground(Color.white);
        
        JScrollPane jscroll = new JScrollPane(painel);
        
        container.add(lbTitulo);
        container.add(lbMatricula);
        container.add(lbNome);
        container.add(lbDataNascimento);
        container.add(lbTelefone);
        container.add(lbCargo);
        container.add(lbBloqueado);
        
        container.add(matricula);
        container.add(nome);
        container.add(dataNascimento);
        container.add(telefone);
        container.add(cargo);
        container.add(bloqueado);
        container.add(painel);
        
        setSize(700,600);
        setLocationRelativeTo(null);
    }   
            
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
