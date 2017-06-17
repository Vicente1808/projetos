/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.pesisitencia;

import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author pablo
 */
public class MapeadorFuncionario {
    private final HashMap<Integer, Funcionario> listFuncionarios;
    private final String filename = "funcionarios.cla";
    
    public MapeadorFuncionario(){
        listFuncionarios = new HashMap<>();
        load();
    }  
    
    public Funcionario get(Integer matricula) {
        return listFuncionarios.get(matricula);
    }
    
    public void put(Funcionario funcionario){
        //testar null
        listFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }

    private void persist() {
        
    }

    public void remove(Integer i) {
        
    }

    public Collection<Funcionario> getList() {
        return listFuncionarios.values();
    }

    private void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
