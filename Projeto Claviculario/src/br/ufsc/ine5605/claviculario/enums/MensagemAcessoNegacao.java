/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.claviculario.enums;

/**
 *
 * @author 05169430906
 */
public enum MensagemAcessoNegacao {
    MATRICULAINVALIDA("Matricula invalida"),
    NAOASSOCIADO("Funcionário não possui veículos associados."),
    FUNCIONARIOBLOQUEADO("Seu acesso esta bloqueado"),
    VEICULOINDISPONIVEL("Este veiculo nao esta disponivel"),
    ACESSONEGADOAOVEICULO("Voce nao tem acesso a este veiculo"),
    VEICULOPENDENTE("O funcionário informado possui um veículo pendente de entrega."),
    SEMVEICULOSASSOCIADOS("O funcionários não possui veículos associados."),
    PLACAINVALIDA("Placa invalida"),
    ACESSOPERMITIDO("Acesso permitido ao veículo."),
    PERMITIDO("Acesso permitido");
    
    private MensagemAcessoNegacao(String mensagem) {
        this.mensagem = mensagem;
    }
    private final String mensagem;
    
    public String getMensagem() {
        return this.mensagem;
    }
}
