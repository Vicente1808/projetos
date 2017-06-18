/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author 05169430906
 */
public class TableModelPessoal extends AbstractTableModel{
    
    private final HashMap<Integer, FuncionarioVO> lista;
    private final String[] nomeColunas = {"Matricula", "Nome", "Nascimento", "Telefone", "Cargo", "Bloqueado"};
    private final Object[][] data;
    
    TableModelPessoal(HashMap<Integer, FuncionarioVO> lista) {
        this.lista = lista;
        data = new Object[lista.keySet().size()][6];
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
        return data[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int col) {
        return nomeColunas[col];
    }
    
    public final void atualizarTabela() {
        int index = 0;
        for(Integer chave : lista.keySet()) {
            lista.get(chave);
            data[index][0] = lista.get(chave).matricula;
            data[index][1] = lista.get(chave).nome;
            data[index][2] = lista.get(chave).dataNascimento;
            data[index][3] = lista.get(chave).telefone;
            data[index][4] = lista.get(chave).cargo;
            data[index][5] = lista.get(chave).bloqueado;
            index++;
        }
    }

}
