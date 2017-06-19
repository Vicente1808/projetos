/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author 05169430906
 */
public class TableModelFuncionarios extends AbstractTableModel{
    
    private HashMap<Integer, FuncionarioVO> lista;
    private final String[] nomeColunas = {"Matricula", "Nome", "Nascimento", "Telefone", "Cargo", "Bloqueado"};
    private Object[][] data;
    
    TableModelFuncionarios() {
        atualizarTabela();
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(data.length > 0) {
            return data[rowIndex][columnIndex];
        } else {
            return -1;
        }
    }
    
    @Override
    public String getColumnName(int col) {
        return nomeColunas[col];
    }
    
    public final void atualizarTabela() {
        lista = ControladorFuncionarios.getInstance().getFuncionarios();
        data = new Object[lista.keySet().size()][nomeColunas.length];
        int index = 0;
        for(Integer chave : lista.keySet()) {
            lista.get(chave);
            data[index][0] = lista.get(chave).matricula;
            data[index][1] = lista.get(chave).nome;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataNascimento1 = sdf.format(lista.get(chave).dataNascimento.getTime());
            data[index][2] = dataNascimento1;
            data[index][3] = lista.get(chave).telefone;
            data[index][4] = lista.get(chave).cargo;
            data[index][5] = lista.get(chave).bloqueado;
            index++;
        }
        
    }
    
}
