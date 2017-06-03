/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.entidades;

import br.ufsc.ine5605.claviculario.enums.MensagemAcessoNegacao;
import java.util.Date;

/**
 *
 * @author Adri
 */
public abstract class Registro {
        
        protected String tipo;
        protected int matricula;
        protected String placa;
	protected Date data;

    public Registro(int matricula, String placa, Date data) {
        this.matricula = matricula;
        this.placa = placa;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }
    
    public int getMatricula() {
        return matricula;
    }

    public String getPlaca() {
        return placa;
    }

    public Date getData() {
        return data;
    }

    public MensagemAcessoNegacao getMotivoAcessoNegacao() {
        if(this.getClass().equals(RegistroRetirada.class)) {
            return this.getMotivoAcessoNegacao();
        } else {
            return null;
        }
    }

    
    
}
