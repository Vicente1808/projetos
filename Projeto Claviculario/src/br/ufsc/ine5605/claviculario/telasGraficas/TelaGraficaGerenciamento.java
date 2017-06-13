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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author pablo
 */
public class TelaGraficaGerenciamento extends JFrame implements ActionListener{
    
    private final ControladorPrincipal ctrl;
    
    private JLabel titulo;
    private JButton btNovo;
    private JButton btExcluir;
    private JButton btAtualizar;
    private JButton btLiberarVeiculos;
    private JScrollPane scroll;
    private JTable listaFuncionarios;
    private TableModelPessoal tableModel;
    
    public TelaGraficaGerenciamento(ControladorPrincipal owner){
        super("Gerenciamento De Funcionários");
        ctrl = owner;
        inicia();
    }
    
    private void inicia(){
        Container container = getContentPane();
        container.setLayout(null);
        
        titulo = new JLabel();
        btNovo = new JButton();
        btExcluir = new JButton();
        btAtualizar = new JButton();
        btLiberarVeiculos = new JButton(); 
        listaFuncionarios = new JTable(); 
        tableModel = new TableModelPessoal(ControladorFuncionarios.getInstance().getFuncionarios());
        scroll = new JScrollPane(listaFuncionarios);
        
        Insets insets = container.getInsets();
        
        Dimension size = titulo.getPreferredSize();
        titulo.setBounds(insets.left + 175, insets.top + 20, size.width + 400, size.height + 40);
        
        size = btNovo.getPreferredSize();
        btNovo.setBounds(insets.left + 50, insets.top + 500, size.width + 95, size.height + 40);
        
        size = btExcluir.getPreferredSize();
        btExcluir.setBounds(insets.left + 200, insets. top + 500, size.width + 95, size.height + 40);
        
        size = btAtualizar.getPreferredSize();
        btAtualizar.setBounds(insets.left + 350, insets. top + 500, size.width + 95, size.height + 40);
        
        size = btLiberarVeiculos.getPreferredSize();
        btLiberarVeiculos.setBounds(insets.left + 500, insets.top + 500, size.width + 100, size.height + 40);
        
        size = scroll.getPreferredSize();
        scroll.setBounds(insets.left + 50, insets.top + 70, size.width + 133, size.height);

        titulo.setText("Gerenciamento de Funcionarios");
        titulo.setFont(new Font ("perpetura", Font.PLAIN, 20));
        
        btNovo.setText("Novo");
        btNovo.addActionListener(this);
        
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(this);
        
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(this);
        
        btLiberarVeiculos.setText("Liberar veiculos");
        btLiberarVeiculos.addActionListener(this);
        
        listaFuncionarios.setModel(tableModel);
        listaFuncionarios.setFillsViewportHeight(true);
        
        container.add(titulo);
        container.add(btNovo);
        container.add(btExcluir);
        container.add(btAtualizar);
        container.add(btLiberarVeiculos);
        container.add(scroll);
        
        setSize(720, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btNovo){
            ControladorFuncionarios.getInstance().carregarTelaGraficaDadosFuncionarios("Novo Funcionario");
        }else{
            
        }
    }


    
    
    }
    
    



