/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.persistencia;

import br.ufsc.ine5605.claviculario.entidades.Veiculo;
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
public class MapeadorVeiculo {
    private HashMap<String, Veiculo> listaVeiculos;
    private String filename = "src/br/ufsc/ine5605/claviculario/persistencia/files/veiculos.rtm";
    
    public MapeadorVeiculo(){
        listaVeiculos = new HashMap<>();
        load();
    }
    
    public Veiculo get(String placa){
        return listaVeiculos.get(placa);
    }
    
    public void put(Veiculo veiculo){
        listaVeiculos.put(veiculo.getPlaca(),veiculo);
        persist();
    }
    
    private void load(){
        try{
            FileInputStream entrada = new FileInputStream(filename);
            ObjectInputStream objetoEntrada = new ObjectInputStream(entrada);

            this.listaVeiculos = (HashMap<String,Veiculo>) objetoEntrada.readObject();

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
    
    public void persist(){
        try{
            FileOutputStream saida = new FileOutputStream(filename);
            ObjectOutputStream objetoSaida = new ObjectOutputStream(saida);
            objetoSaida.writeObject(listaVeiculos);
            
            objetoSaida.flush();
            saida.flush();
            
            objetoSaida=null;
            saida=null;
            
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException ex){
            System.out.println(ex);
        }
        
    }
    
    public void remove(String placa){
        listaVeiculos.remove(placa);
        persist();
    }
        
    public Collection<Veiculo> getList(){
        return listaVeiculos.values();
    }
}
