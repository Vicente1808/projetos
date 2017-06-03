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
public enum EntradaSaida {
    VALORNUMERICOREQUERIDO("Voçe não inseriu um valor numérico, tente novamente."),
    OPCAONAOEXISTE("Opcão inválida, tente novamente."),
    DATAINCORRETA("Voçe digitou a data incorretamente, tente novamente."),
    PLACAINCORRETA("Voçe digitou a placa no formato incorreto, tente novamente."),
    PLACADUPLICADA("Já exite um veículo cadastrado com esta placa."),
    PLACAINEXISTENTE("Não existe veiculo cadastrado com está placa."),
    VEICULOCADASTRADO("Veículo cadastrado com sucesso."),
    VEICULOPENDENTE("Veiculo não pode ser excluido, pois ainda não foi devolvido."),
    VEICULOEXCLUIDO("Veículo excluido com sucesso."),
    VEICULOATUALIZADO("Dados do veículo atualizado com sucesso."),
    MATRICULADUPLICA("Já exite um funcionário cadastrado com essa matricula."),
    FUNCIONARIOCADASTRADO("Funcionário cadastrado com sucesso."),
    MATRICULAINVALIDA("Não existem funcionário cadastrado com essa matricula."),
    FUNCIONARIPENDENTE("Não é possivel exlcuir funcionário, pois ainda não devolveu veículo"),
    FUNCIONARIOEXCLUIDO("Funcionário excluido com sucesso."),
    FUNCIONARIOATUALIZADO("Dados do funcioanrio atualizado com sucesso."),
    VEICULOASSOCIADO("Funcionario já possui permisso para retirar este veículo"),
    VEICULOLIBERADO("Permissão de retirar veículo cadastrada com sucesso."),
    PERGUNTA("Deseja permitir que o usuário utilize um veículo?"),
    FUNCIONARIOEHDIRETOR("O diretor cadastrado tem acesso a todos os veiculos!"),
    VEICULONAOASSOCIADO("Funcionário não possui veículo associado"),
    DIRETOR("DIRETOR"),
    PERGUNTA3("Deseja retirar permissão deste funcionário retirar um determinado veiculo");
    
    private final String mensagem;
    
    EntradaSaida(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getMensagem() {
        return this.mensagem;
    }
}
