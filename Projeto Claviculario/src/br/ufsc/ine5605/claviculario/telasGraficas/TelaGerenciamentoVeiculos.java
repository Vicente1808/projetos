/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.controladores.ControladorVeiculos;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class TelaGerenciamentoVeiculos extends JFrame implements ActionListener{

    private ControladorPrincipal controladorPrincipal;
    private JLabel lbTitulo;
    private JButton btNovo;
    private JButton btExcluir;
    private JButton btAtualizar;
    private JButton btLiberarFuncionario;
    private JTable  tabelaVeiculos;
    private TableModelVeiculos tableModel;
    private JScrollPane scroll;
    
    public TelaGerenciamentoVeiculos(ControladorPrincipal controladorPrincipal){
        super("Gerenciamento De Veículos");
        this.controladorPrincipal = controladorPrincipal;
        inicia();
    }
    
    private void inicia(){
        lbTitulo = new JLabel();
        btNovo = new JButton();
        btExcluir = new JButton();
        btAtualizar = new JButton();
        btLiberarFuncionario = new JButton();
        tabelaVeiculos = new JTable();
        tableModel = new TableModelVeiculos();
        scroll = new JScrollPane(tabelaVeiculos);
    
        lbTitulo.setText("Gerenciamento De Veículos");
        lbTitulo.setFont(new Font ("perpetura", Font.PLAIN, 20));
        lbTitulo.setBounds(250, 20, 400, 40);
        
        btNovo.setText("Novo");
        btNovo.setBounds(50, 503, 130, 50);
        btNovo.addActionListener(this);
        
        btExcluir.setText("Excluir");
        btExcluir.setBounds(200, 503, 130, 50);
        btExcluir.addActionListener(this);        
        
        btAtualizar.setText("Atualizar");
        btAtualizar.setBounds(350, 503, 130, 50);
        btAtualizar.addActionListener(this);   

        //btLiberarFuncionario.setText("Liberar Funcionario");
        btLiberarFuncionario.setText("Não aperte aqui!!!");
        btLiberarFuncionario.setBounds(500, 503, 135, 50);
        btLiberarFuncionario.addActionListener(this); 
        
        tabelaVeiculos.setModel(tableModel);
        tabelaVeiculos.setFillsViewportHeight(true);
        
        scroll.setToolTipText("Tabela Veiculos");
        scroll.setBounds(50, 70, 590, 410);
        
        Container container = getContentPane();
        container.setLayout(null);
        
        container.add(lbTitulo);
        container.add(btNovo);
        container.add(btExcluir);
        container.add(btAtualizar);
        container.add(btLiberarFuncionario);
        container.add(scroll);
        
        setSize(700, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        
    }
    
    public void updateData(){
        tableModel.atualizarDados();
        repaint();
    }   
            

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btNovo){
            ControladorVeiculos.getInstance().carregarTelaDadosVeiculo("Cadastro", 0);
        }else if(ae.getSource()==btExcluir){
            if(tabelaVeiculos.getSelectedRow() != -1) {
                int index = tabelaVeiculos.getSelectedRow();
                ControladorVeiculos.getInstance().excluirVeiculo( (String) tableModel.getValueAt(index, 0));
                updateData();
            }
        }else if(ae.getSource()==btAtualizar){
            ControladorVeiculos.getInstance().carregarTelaDadosVeiculo("Atualização", 1);
        }
    }
    
}
