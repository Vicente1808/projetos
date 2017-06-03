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
public enum RetiradaEDevolucao {
    VEICULODEVOLVIDO("Veiculo devolvido com sucesso!"),
    VEICULONAOASSOCIADO("Voce não possui a chave desse veiculo"),
    MATRICULAOUPLACAINVALIDA("A matricula ou placa inserida eh invalida"),
    MATRICULAINCORRETA("Voçe digitou uma matricula inválida, tente novamnte."),
    USUARIOBLOQUEADO("A matricula para você digitou está com acesso bloqueado!"),
    VEICULOPENDENTE("O usuário informado já retirou um veículo, devolva-o primeiro."),
    VEICULOINDISPONIVEL("O veiculo informado está indisponível no momento :(, tente outro."),
    VEICULOINDISPONIVEL2("O veiculo disponibilizado para este funcionário já está emprestado."),
    VEICULORETIRADO("Veiculo retirado com sucesso."),
    SEMVEICULOS("Funcionário não tem permissão para retirar nenhum veiculo.");
    
    private final String mensagem;
    
    RetiradaEDevolucao(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getMensagem() {
        return this.mensagem;
    }
}
