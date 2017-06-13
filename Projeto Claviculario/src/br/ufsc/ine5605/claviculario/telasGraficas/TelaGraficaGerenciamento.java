/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
    private JButton botaoNovo;
    private JButton botaoExcluir;
    private JButton botaoAtualizar;
    private JButton botaoLiberarVeiculos;
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
        botaoNovo = new JButton();
        botaoExcluir = new JButton();
        botaoAtualizar = new JButton();
        botaoLiberarVeiculos = new JButton(); 
        //String[] colunas = new String[]{"Matricula","Nome","Data Nascimento","Telefone","Cargo","Status Bloqueio","VeiculoPendente"};
        listaFuncionarios = new JTable() {
            @Override
            public boolean isCellEditable(int row, int clolumn) {
                return false;
            }
        };
       
        
        //tableModel = new TableModelPessoal(ControladorFuncionarios.getInstance().getFuncionarios());
        
        //listaFuncionarios.setModel(tableModel);
        scroll = new JScrollPane(listaFuncionarios);
        
        Insets insets = container.getInsets();
        
        titulo.setText("Gerenciamento de Funcionarios");
        titulo.setFont(new Font ("perpetura", Font.PLAIN, 20));
        titulo.setForeground(Color.black);
        titulo.setBounds(150, 20, 500, 50);//Margem esquerda, margem topo, tamanho
        
        listaFuncionarios.setFillsViewportHeight(true);
        
        scroll.setBounds(50, 70, 600, 400);
        
        
        botaoNovo.setText("Novo");
        botaoNovo.addActionListener(this);
        
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(this);
        
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(this);
        
        botaoLiberarVeiculos.setText("Liberar veiculos");
        botaoLiberarVeiculos.addActionListener(this);
        
        
        Dimension size = botaoNovo.getPreferredSize();
        botaoNovo.setBounds(insets.left + 50, insets. top + 500, size.width + 75, size.height + 30);
        
        size = botaoExcluir.getPreferredSize();
        botaoExcluir.setBounds(insets.left + 190, insets. top + 500, size.width + 75, size.height + 30);
        
        size = botaoAtualizar.getPreferredSize();
        botaoAtualizar.setBounds(insets.left + 330, insets. top + 500, size.width + 75, size.height + 30);
        
        size = botaoLiberarVeiculos.getPreferredSize();
        botaoLiberarVeiculos.setBounds(insets.left + 500, insets.top + 500, size.width + 75, size.height + 30);
        
        container.add(titulo);
        container.add(botaoNovo);
        container.add(botaoExcluir);
        container.add(botaoAtualizar);
        container.add(botaoLiberarVeiculos);
        container.add(scroll);
        
        setSize(720, 620);
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botaoNovo){
            ControladorFuncionarios.getInstance().carregarTelaGraficaDadosFuncionarios("Novo Funcionario");
        }else{
            
        }
    }


    
    
    }
    
    



