/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.pesisitencia;

import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author pablo
 */
public class MapeadorFuncionario {
    private final HashMap<Integer, Funcionario> listFuncionarios;
    private final String filename = "funcionarios.rtm";
    
    public MapeadorFuncionario(){
        listFuncionarios = new HashMap<>();
        load();
    }  
    
    public Funcionario get(Integer matricula) {
        return listFuncionarios.get(matricula);
    }
    
    public void put(Funcionario funcionario){
        listFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }
    
    private void load() {
        
    }


    private void persist(){
        try{
        FileOutputStream saida = new FileOutputStream(filename);
        ObjectOutputStream objetoSaida = new ObjectOutputStream(saida);
        objetoSaida.writeObject(listFuncionarios);
        
        objetoSaida.flush();
        saida.flush();
        
        objetoSaida=null;
        saida=null;
        
        }catch(FileNotFoundException ex){
            
        }catch(IOException ex){
            
        }
    }



    public void remove(Integer i) {
        
    }

    public Collection<Funcionario> getList() {
        return listFuncionarios.values();
    }


    
}
