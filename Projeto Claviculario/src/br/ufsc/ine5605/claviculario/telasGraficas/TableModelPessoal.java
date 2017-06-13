/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import java.util.HashMap;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


/**
 *
 * @author 05169430906
 */
public class TableModelPessoal implements TableModel {
    
    private final String[] nomeColunas = {"Matricula", "Nome", "Nascimento", "Telefone", "Cargo", "Status"};
    private final Object[][] data;
    
    
    
    TableModelPessoal(HashMap<Integer, Funcionario> lista) {
        data = new Object[lista.keySet().size()][5];
        int index = 0;
        for(Integer chave : lista.keySet()) {
            lista.get(chave);
            data[index][0] = lista.get(chave).getMatricula();
            data[index][1] = lista.get(chave).getNome();
            data[index][2] = lista.get(chave).getDataNascimento();
            data[index][3] = lista.get(chave).getTelefone();
            data[index][4] = lista.get(chave).getCargo();
            data[index][5] = lista.get(chave).isBloqueado();
            
        }
    }

    @Override
    public int getRowCount() {
        return nomeColunas.length;
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }

    @Override
    public String getColumnName(int col) {
        return nomeColunas[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return data[0][col].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        data[row][col] = aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        //this.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //this.removeTableModelListener(l);
    }
    
}
