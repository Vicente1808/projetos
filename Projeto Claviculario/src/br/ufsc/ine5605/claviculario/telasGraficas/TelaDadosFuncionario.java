/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author pablo
 */
public class TelaDadosFuncionario extends JFrame implements ActionListener{
    
    private ControladorFuncionarios controladorFuncionarios;
    private JLabel lbTitulo;
    private JLabel lbMatricula;
    private JLabel lbNome;
    private JLabel lbDataNascimento;
    private JLabel lbTelefone;
    private JLabel lbCargo;
    private JLabel lbBloqueado;
    private JLabel infoTela;
    private JLabel lbVeiculoPendente; 
        
    private JFormattedTextField tfMatricula;
    private JTextField tfNome;
    private JFormattedTextField tfDataNascimento;
    private JFormattedTextField tfTelefone;
    private JComboBox tfCargo;
    private JComboBox tfBloqueado;
    private JButton btCadastrar;
    private JTextField tfCargoOutro;
    
    public TelaDadosFuncionario(ControladorFuncionarios controladorFuncionarios){
        this.controladorFuncionarios = controladorFuncionarios;
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
        lbVeiculoPendente = new JLabel();
        infoTela = new JLabel();
        
        String[] cargos = {"Diretor","Outro"};
        String[] status = {"Liberado","Bloqueado"};
        
        tfMatricula = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        tfMatricula.setText("");
        tfNome = new JTextField();
        
        try {
            tfDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
            Logger.getLogger(TelaDadosFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tfTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
        } catch (ParseException ex) {
            Logger.getLogger(TelaDadosFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfCargo = new JComboBox(cargos);
        tfBloqueado = new JComboBox(status);
        btCadastrar = new JButton();
        tfCargoOutro = new JTextField();
        
        Container container = getContentPane();
        container.setLayout(null);
        //container.setBackground(Color.white);
        
        lbTitulo.setText(getTitle());
        lbTitulo.setForeground(Color.black);
        lbTitulo.setFont(new Font("Arial", Font.PLAIN, 20));
        lbTitulo.setBounds(50, 20, 100, 50);
        
        lbMatricula.setText("Matrícula:");
        lbMatricula.setBounds(50, 50, 100, 50);
        
        tfMatricula.setBounds(170, 60, 120, 25);
        
        lbNome.setText("Nome:");
        lbNome.setBounds(50, 100, 100, 50);
        
        tfNome.setBounds(170, 110, 120, 25);
        
        lbDataNascimento.setText("Data Nascimento:");
        lbDataNascimento.setBounds(50, 150, 100, 50);
        
        tfDataNascimento.setBounds(170, 160, 120, 25);
        
        lbTelefone.setText("Telefone:");
        lbTelefone.setBounds(50, 200, 100, 50);
        
        tfTelefone.setBounds(170, 210, 120, 25);
        
        lbCargo.setText("Cargo:");
        lbCargo.setBounds(50, 250, 100, 50);
        
        tfCargo.setBounds(170, 260, 120, 25);
        tfCargo.addActionListener(this);
        tfCargo.setActionCommand("TROCACARGO");
        tfCargoOutro.setBounds(170, 290, 120, 25);
        
        lbBloqueado.setText("Status:");      
        lbBloqueado.setBounds(50, 300, 100, 50);
        
        tfBloqueado.setBounds(170, 315, 120, 25);
        
        lbVeiculoPendente.setText("Veiculo Pendente:         AAA-1234");
        lbVeiculoPendente.setBounds(50, 340, 240, 25);
        lbVeiculoPendente.setEnabled(false);
        
        btCadastrar.setText("Cadastrar");
        btCadastrar.setBounds(50, 370, 120, 30);
        btCadastrar.addActionListener(this);
        
        infoTela.setBounds(320, 370, 330, 25);
        infoTela.setForeground(Color.red);
        
        //String[][] placas = ControladorFuncionarios.getInstance().getVeiculosSimples();
        JList painel = new JList();
        
        //painel.setLineWrap(rootPaneCheckingEnabled);
        
        JScrollPane jscroll = new JScrollPane(painel);
        
        jscroll.setBounds(320, 60, 320, 300);
        jscroll.setBackground(Color.white);
        
        container.add(lbTitulo);
        container.add(lbMatricula);
        container.add(lbNome);
        container.add(lbDataNascimento);
        container.add(lbTelefone);
        container.add(lbCargo);
        container.add(lbBloqueado);
        container.add(lbVeiculoPendente);
        
        container.add(tfMatricula);
        container.add(tfNome);
        container.add(tfDataNascimento);
        container.add(tfTelefone);
        container.add(tfCargo);
        container.add(tfBloqueado);
        container.add(btCadastrar);
        container.add(jscroll);
        container.add(infoTela);
        
        setSize(680,460);
        setLocationRelativeTo(null);
        
        if(tfCargo.getSelectedItem().toString().equals("Outro")){
                container.add(tfCargoOutro);
            }
    }   
            
    public void limparTela(){
        tfMatricula.setText("");
        tfNome.setText("");
        tfDataNascimento.setText("");        
        tfTelefone.setText("");        
        tfBloqueado.validate();
        lbVeiculoPendente.setText("");        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getActionCommand().equals("TROCACARGO")){
            if(tfCargo.getSelectedItem().toString().equals("Outro")){                
                getContentPane().add(tfCargoOutro);
            }else{
                getContentPane().remove(tfCargoOutro);
            }
        }
        
        if(ae.getSource() == btCadastrar) {
            if(tfMatricula.getText().equals("")){
                infoTela.setText("Campo Matrícula está em branco.");
                infoTela.setForeground(Color.BLUE);
                repaint();
            }else if(tfNome.getText().equals("")){
                infoTela.setText("Campo nome está em branco.");
                infoTela.setForeground(Color.green);
                repaint();               
            }else if(tfDataNascimento.getText().equals("  /  /    ")){
                infoTela.setText("Campo data nascimento está em branco.");
                infoTela.setForeground(Color.ORANGE);
                repaint();     
            }else if(tfTelefone.getText().equals("(  )     -    ")){
                infoTela.setText("Campo telefone está em branco.");
                infoTela.setForeground(Color.MAGENTA);
                repaint();                 
            }else if(tfCargo.getSelectedItem().equals("Outro")&&tfCargoOutro.getText().equals("")){
                infoTela.setText("Campo cargo está em branco.");
                infoTela.setForeground(Color.MAGENTA);
                repaint(); 
            }else{

                FuncionarioVO funcionarioVO = new FuncionarioVO();
                funcionarioVO.matricula = Integer.parseInt(tfMatricula.getText());
                funcionarioVO.nome = tfNome.getText();
                
                SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
 
                String data = tfDataNascimento.getText();
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(formatoData.parse(data));
                } catch (ParseException ex) {
                    Logger.getLogger(TelaDadosFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                    infoTela.setText("Data nascimento inválida.");
                    repaint();                     
                }
                
                funcionarioVO.telefone = tfTelefone.getText();
                
                if(tfCargo.getSelectedItem().equals("Outro")&&tfCargoOutro.getText().equals("")){
                    funcionarioVO.cargo = tfCargoOutro.getText();
                }else{
                    funcionarioVO.cargo = tfCargo.getSelectedItem().toString();
                }
                
                if(tfBloqueado.getSelectedItem().equals("Disponível")){
                    funcionarioVO.bloqueado =false;
                }else{
                        funcionarioVO.bloqueado =true;
                        }            
                //funcionarioVO.bloqueado = tfBloqueado.isBorderPaintedFlat();
                funcionarioVO.veiculoPendente = null;
                
                limparTela();
                infoTela.setForeground(Color.blue);
                infoTela.setText(controladorFuncionarios.cadastrarFuncionario(funcionarioVO));
                
                
            }
            
        }
    }
    
}
