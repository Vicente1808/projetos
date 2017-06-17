/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.persistencia;

import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author pablo
 */
public class MapeadorFuncionarios {
    
    private static MapeadorFuncionarios instance;
    private HashMap<Integer, Funcionario> cacheFuncionarios;
    private final String filename = "src/funcionario54.txt";
    
    private MapeadorFuncionarios(){
        cacheFuncionarios = new HashMap<>();
        load();
    }
    
    public static MapeadorFuncionarios getInstance() {
        if(instance == null) {
            instance = new MapeadorFuncionarios();
        }
        return instance;
    }
    
    public void put(Funcionario funcionario){
        if(funcionario != null) {
            cacheFuncionarios.put(funcionario.getMatricula(), funcionario);
        }
        persist();
    }

    private void persist() {
        FileOutputStream fos;
        ObjectOutputStream objout;
        try {
            fos = new FileOutputStream(filename);
            objout = new ObjectOutputStream(fos);
            
            objout.writeObject(cacheFuncionarios);
            
            fos.flush();
            objout.flush();
            
            fos.close();
            objout.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Funcionario get(Integer matricula) {
        return cacheFuncionarios.get(matricula);
    }

    public void remove(Integer matricula) {
        cacheFuncionarios.remove(matricula);
        persist();
    }

    public Collection<Funcionario> getList() {
        return cacheFuncionarios.values();
    }

    private void load() {
        FileInputStream fis;
        ObjectInputStream objin;
        try{
            fis = new FileInputStream(filename);
            objin = new ObjectInputStream(fis);
            
            cacheFuncionarios = (HashMap<Integer, Funcionario>) objin.readObject();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
