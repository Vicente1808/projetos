/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.enums;

/**
 *
 * @author Adri
 */
public enum ErroDeEntrada {
    VALORNUMERICOREQUERIDO("Voce não inseriu um valor numerico, tente novamente."),
    OPCAONAOEXISTE("Opcao invalida, tente novamente."),
    DATAINCORRETA("Voce digitou a data incorretamente, tente novamente."),
    PLACAINCORRETA("Voce digitou a placa no formato incorreto, tente novamente.");
    
    private final String mensagem;
    
    ErroDeEntrada(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getMensagem() {
        return this.mensagem;
    }
}
