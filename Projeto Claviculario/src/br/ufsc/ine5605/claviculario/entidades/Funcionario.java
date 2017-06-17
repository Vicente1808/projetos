package br.ufsc.ine5605.claviculario.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *  
 * @author pablo
 */
public class Funcionario implements Serializable{
    	private final int matricula;
	private String nome;
	private Calendar dataNascimento;
	private String telefone;
        private String cargo;
	private boolean estaBloqueado;
        private String veiculoPendente;
	private final ArrayList<String> veiculos;

        //Construtor
        
        public Funcionario(int matricula, String nome, Calendar dataNascimento, String telefone, String cargo){
            this.matricula = matricula;
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.telefone = telefone;
            this.cargo = cargo;
            this.estaBloqueado = false;
            this.veiculoPendente = null;
            veiculos = new ArrayList<>();
        }

    public int getMatricula() {
        return matricula;
    }

	
    public String getNome(){
    return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isBloqueado() {
        return estaBloqueado;
    }

    //Metodos Modificadores
    public void setBloqueado(boolean estaBloqueado) {
        this.estaBloqueado = estaBloqueado;
    }
    
    public String getVeiculoPendente() {
        return veiculoPendente;
    }

    public void setVeiculoPendente(String placa) {
        this.veiculoPendente = placa;
    }

    public ArrayList<String> getVeiculos(){
            return veiculos;
    }

    

        
}
