/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.valueObjects;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author 05169430906
 */
public class FuncionarioVO {
    
    public int matricula;
    public String nome;
    public Calendar dataNascimento;
    public String telefone;
    public String cargo;
    public boolean bloqueado;
    public String veiculoPendente;
    public ArrayList<String> funcionarios;
    
    public FuncionarioVO() {
        funcionarios = new ArrayList<>();
    }
}
