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
public class MapeadorFuncionario {
    private HashMap<Integer, Funcionario> listaFuncionarios;
    private String filename = "src/br/ufsc/ine5605/claviculario/persistencia/files/funcionarios.rtm";
    
    public MapeadorFuncionario(){
        listaFuncionarios = new HashMap<>();
        load();
    }  
    
    public Funcionario get(Integer matricula) {
        return listaFuncionarios.get(matricula);
    }
    
    public void put(Funcionario funcionario){
        listaFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }
    
    private void load() {
        try{
        FileInputStream entrada = new FileInputStream(filename);
        ObjectInputStream objetoEntrada = new ObjectInputStream(entrada);
        
        this.listaFuncionarios = (HashMap<Integer, Funcionario>) objetoEntrada.readObject();
        
        objetoEntrada.close();
        entrada.close();
        
        objetoEntrada = null;
        entrada = null;
        
        }catch(ClassNotFoundException ae){
            System.out.println(ae);
        }catch(FileNotFoundException ae){
            System.out.println(ae);
        }catch(IOException ae){
            System.out.println(ae);
        }
    }


    private void persist(){
        try{
        FileOutputStream saida = new FileOutputStream(filename);
        ObjectOutputStream objetoSaida = new ObjectOutputStream(saida);
        objetoSaida.writeObject(listaFuncionarios);
        
        objetoSaida.flush();
        saida.flush();
        
        objetoSaida=null;
        saida=null;
        
        }catch(FileNotFoundException ex){
            System.out.println(ex);
            //ControladorFuncionarios.getInstance().mensagemErro(EntradaSaida.ARQUIVONAOENCONTRADO.getMensagem());
        }catch(IOException ex){
            System.out.println(ex);
            //ControladorFuncionarios.getInstance().mensagemErro(EntradaSaida.OBJECTO.getMensagem());
        }
    }

    public void remove(Integer matricula) {
        listaFuncionarios.remove(matricula);
    }

    public Collection<Funcionario> getList() {
        return listaFuncionarios.values();
    }

}
