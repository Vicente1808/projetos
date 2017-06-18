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
    private JButton btExluir;
    private JButton btAtualizar;
    private JButton btLiberarFuncionario;
    private JTable  tabelaVeiculos;
    private JScrollPane scroll;
    
    public TelaGerenciamentoVeiculos(ControladorPrincipal controladorPrincipal){
        super("Gerenciamento De Veículos");
        this.controladorPrincipal = controladorPrincipal;
        inicia();
    }
    
    public void inicia(){
        lbTitulo = new JLabel();
        btNovo = new JButton();
        btExluir = new JButton();
        btAtualizar = new JButton();
        btLiberarFuncionario = new JButton();
        tabelaVeiculos = new JTable(7,8);
        scroll = new JScrollPane(tabelaVeiculos);
    
        lbTitulo.setText("Gerenciamento De Veículos");
        lbTitulo.setFont(new Font ("perpetura", Font.PLAIN, 20));
        lbTitulo.setBounds(250, 20, 400, 40);
        
        btNovo.setText("Novo");
        btNovo.setBounds(50, 503, 130, 50);
        btNovo.addActionListener(this);
        
        btExluir.setText("Excluir");
        btExluir.setBounds(200, 503, 130, 50);
        btExluir.addActionListener(this);        
        
        btAtualizar.setText("Atualizar");
        btAtualizar.setBounds(350, 503, 130, 50);
        btAtualizar.addActionListener(this);   

        btLiberarFuncionario.setText("Liberar Funcionario");
        btLiberarFuncionario.setBounds(500, 503, 135, 50);
        btLiberarFuncionario.addActionListener(this); 
        
        tabelaVeiculos.setFillsViewportHeight(true);
        
        scroll.setToolTipText("Tabela Veiculos");
        scroll.setBounds(50, 70, 590, 410);
        
        Container container = getContentPane();
        container.setLayout(null);
        
        container.add(lbTitulo);
        container.add(btNovo);
        container.add(btExluir);
        container.add(btAtualizar);
        container.add(btLiberarFuncionario);
        container.add(scroll);
        
        setSize(700, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        
    }
    
    public void updateData(){
        DefaultTableModel modeloPadrao = new DefaultTableModel();
        modeloPadrao.addColumn("Placa");
        modeloPadrao.addColumn("Modelo");
        modeloPadrao.addColumn("Marca");
        modeloPadrao.addColumn("Ano");
        modeloPadrao.addColumn("KM Atual");
        modeloPadrao.addColumn("Disponível");
        
        //for(VeiculoVO veiculo:){
            
        //}
        
    }   
            

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btNovo){
            ControladorVeiculos.getInstance().carregarTelaDadosVeiculo("Novo Veiculo");
        }
    }
    
}
