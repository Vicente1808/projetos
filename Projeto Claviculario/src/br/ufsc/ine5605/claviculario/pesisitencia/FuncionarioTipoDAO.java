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
public class FuncionarioTipoDAO {
    private final HashMap<Integer, Funcionario> cacheFuncionarios;
    private final String filename = "funcionario.cla";
    
    public FuncionarioTipoDAO(){
        cacheFuncionarios = new HashMap<>();
        load();
    }  
    
    public void put(Funcionario funcionario){
        //testar null
        cacheFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }

    private void persist() {
        
    }

    public Funcionario get(Integer matricula) {
        return cacheFuncionarios.get(matricula);
    }

    public void remove(Integer i) {
        
    }

    public Collection<Funcionario> getList() {
        return cacheFuncionarios.values();
    }

    private void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
