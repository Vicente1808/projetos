/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.entidades;

import java.util.Date;


/**
 *
 * @author Adri
 */
public class RegistroDevolucao extends Registro {
    
    public RegistroDevolucao(int matricula, String placa, Date data) {
        super(matricula, placa, data);
        this.tipo = "devolucao";
    }
    
}
