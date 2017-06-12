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
    private JButton botaoNovo;
    private JButton botaoExcluir;
    private JButton botaoAtualizar;
    private JButton botaoLiberarVeiculos;
    private JScrollPane scroll;
    private JTable listaFuncionarios;
    
    public TelaGraficaGerenciamento(ControladorPrincipal owner){
        super("Gerenciamento De Funcionários");//Colona nome a janela
        ctrl = owner;
        inicia();
    }
    
    private void inicia(){
        
        Container container = getContentPane(); // Onde os oobjetos da tela serão adicionados
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
        scroll = new JScrollPane(listaFuncionarios);
        
                
        titulo.setText("Gerenciamento de Funcionarios");
        titulo.setFont(new Font ("perpetura", Font.PLAIN, 20));
        titulo.setForeground(Color.black);
        titulo.setBounds(150, 20, 500, 50);//Margem esquerda, margem topo, tamanho
        
        listaFuncionarios.setFillsViewportHeight(true);
        
        scroll.setBounds(50, 70, 600, 400);
        
        botaoNovo.setText("NOVO");
        botaoNovo.addActionListener(this);
        botaoNovo.setBackground(Color.GREEN);
        botaoNovo.setBounds(50, 500, 120, 50);//Margem esquerda, margem topo, lagura, altura
        
        botaoExcluir.setText("EXCLUIR");
        botaoExcluir.addActionListener(this);
        botaoExcluir.setBounds(190, 500, 120, 50);
        
        botaoAtualizar.setText("ATUALIZAR  CADASTRO");
        botaoAtualizar.addActionListener(this);
        botaoAtualizar.setBounds(330, 500, 120, 50);
        
        botaoLiberarVeiculos.setText("LIBERAR VEICULOS");
        botaoLiberarVeiculos.addActionListener(this);
        botaoLiberarVeiculos.setBounds(470, 500, 120, 50);
        
        
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
    
    



