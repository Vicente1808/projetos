/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.telasGraficas;

import br.ufsc.ine5605.claviculario.controladores.ControladorVeiculos;
import br.ufsc.ine5605.claviculario.valueObjects.VeiculoVO;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adri
 */
public class TableModelVeiculos extends AbstractTableModel {
    
    private HashMap<String, VeiculoVO> lista;
    private Object data[][];
    private final String nomeColunas[] = {"Placa", "Modelo", "Marca", "ano", "kmAtual", "Disponivel"};
    
    public TableModelVeiculos() {
        atualizarDados();
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
    
    public final void atualizarDados() {
        lista = ControladorVeiculos.getInstance().getVeiculos();
        data = new Object[lista.keySet().size()][nomeColunas.length];
        
        int index = 0;
        for(String placa : lista.keySet()) {
            data[index][0] = lista.get(placa).placa;
            data[index][1] = lista.get(placa).modelo;
            data[index][2] = lista.get(placa).marca;
            data[index][3] = lista.get(placa).ano;
            data[index][4] = lista.get(placa).kmAtual;
            data[index][5] = lista.get(placa).chaveClaviculario;
            index++;
        }
    }
    
}
