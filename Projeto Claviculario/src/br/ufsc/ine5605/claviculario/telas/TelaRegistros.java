
package br.ufsc.ine5605.claviculario.telas;

import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.controladores.ControladorRegistros;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.enums.MensagemAcessoNegacao;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pablo Vicente
 */
public class TelaRegistros {
    //Atributos
    private final Scanner teclado;
    
    //Construtor
    public TelaRegistros(){
        teclado = new Scanner(System.in);
    }
    
    public void exibirMenu(){
        int numeroAcao =-1;
        
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("=================== Bem Vindo ao Histórico De Registros ===================");
            System.out.println("---------------------------------------------------------------------------\n");
            System.out.println("[1] Pesquisar registros de uso de veiculo");
            System.out.println("[2] Pesquisar registros de uso por funcionarios");
            System.out.println("[3] Pesquisar registro de acessos e negacoes");
            System.out.println("[0] Voltar ao menu principal");
            
            boolean repetir;
            do{
                repetir = false;
                try{         
                    numeroAcao = teclado.nextInt();
                } catch(InputMismatchException e) {
                        repetir = true;
                        teclado.nextLine();
                        System.out.println(EntradaSaida.OPCAONAOEXISTE.getMensagem());
                }
                if(numeroAcao>3){
                    System.out.println(EntradaSaida.OPCAONAOEXISTE.getMensagem());
                }
            } while(!(numeroAcao >= 0 && numeroAcao <= 3) || repetir);
            
            switch(numeroAcao){
                case 1:
                    this.exibirTelaPesquisaPlaca();
                    break;
                case 2:
                    this.exibirTelaPesquisaMatricula();
                    break;
                case 3:
                    this.exibirTelaPesquisaAcesso();
                    break;
                case 0:
                    ControladorPrincipal.getInstance().carregarMenuPrincipal();
                    break;    
            
        }    
    }
    
    private void exibirTelaPesquisaMatricula() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Pesquisa de Registros ==========================");
        System.out.println("---------------------------------------------------------------------------");
        
        System.out.println("Insira a matricula do funcionario: ");
        int matricula = 0;
        boolean repetir;
        do {
            repetir = false;
            try {
                matricula = teclado.nextInt();
            } catch(InputMismatchException e) {
                repetir = true;
                teclado.nextLine();
                System.out.println(EntradaSaida.VALORNUMERICOREQUERIDO.getMensagem());
            }
        } while(repetir);
        
        if(ControladorRegistros.getInstance().pesquisarRegistros(matricula).size() > 0) {
            System.out.println(ControladorRegistros.getInstance().pesquisarRegistros(matricula).size() + " registro(s) foi(foram) encontrado(s)");
            for(int i = 0; i < ControladorRegistros.getInstance().pesquisarRegistros(matricula).size(); i++) {
                System.out.println("Evento: " + ControladorRegistros.getInstance().pesquisarRegistros(matricula).get(i).getTipo());
                System.out.println("Matricula: " + ControladorRegistros.getInstance().pesquisarRegistros(matricula).get(i).getMatricula());
                System.out.println("Placa: " + ControladorRegistros.getInstance().pesquisarRegistros(matricula).get(i).getPlaca());
                System.out.println("Data: " + ControladorRegistros.getInstance().pesquisarRegistros(matricula).get(i).getData());
                if(ControladorRegistros.getInstance().pesquisarRegistros(matricula).get(i).getTipo().equals("retirada")) {
                    System.out.println("Status: " + ControladorRegistros.getInstance().pesquisarRegistros(matricula).get(i).getMotivoAcessoNegacao().getMensagem());
                }
            }
        } else {
            System.out.println("Nenhum registro foi encontrado");
        }
        this.exibirMenu();
    }

    private void exibirTelaPesquisaPlaca() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Pesquisa de Registros ==========================");
        System.out.println("---------------------------------------------------------------------------");
        
        System.out.println("Insira a placa do veiculo: ");
        String placa = teclado.next();
        if(ControladorRegistros.getInstance().pesquisarRegistros(placa).size() > 0) {
            System.out.println(ControladorRegistros.getInstance().pesquisarRegistros(placa).size() + " registro(s) foi(foram) encontrado(s)");
            for(int i = 0; i < ControladorRegistros.getInstance().pesquisarRegistros(placa).size(); i++) {
                System.out.println("Evento: " + ControladorRegistros.getInstance().pesquisarRegistros(placa).get(i).getTipo());
                System.out.println("Matricula: " + ControladorRegistros.getInstance().pesquisarRegistros(placa).get(i).getMatricula());
                System.out.println("Placa: " + ControladorRegistros.getInstance().pesquisarRegistros(placa).get(i).getPlaca());
                System.out.println("Data: " + ControladorRegistros.getInstance().pesquisarRegistros(placa).get(i).getData());
                if(ControladorRegistros.getInstance().pesquisarRegistros(placa).get(i).getTipo().equals("retirada")) {
                    System.out.println("Status: " + ControladorRegistros.getInstance().pesquisarRegistros(placa).get(i).getMotivoAcessoNegacao().getMensagem());
                }
                System.out.println("\n--------------------------------------------------------------------\n");
            }
        } else {
            System.out.println("Nenhum registro foi encontrado");
        }
        this.exibirMenu();
        
    }
    
    private void exibirTelaPesquisaAcesso() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Pesquisa de Registros ==========================");
        System.out.println("---------------------------------------------------------------------------");
        
        System.out.println("Insira o motivo da negacao ou permissao");
        
        System.out.println("[1] Acesso permitido ao veiculo");
        System.out.println("[2] Seu acesso esta bloqueado");
        System.out.println("[3] Este veiculo nao esta disponivel");
        System.out.println("[4] Voce nao tem acesso a este veiculo");
        System.out.println("[5] Matricula invalida");
        System.out.println("[6] Placa invalida");
        
        int numeroAcao = -1;
        
        boolean repetir;
        do {
            repetir = false;
                try {
                    numeroAcao = teclado.nextInt();
                } catch(InputMismatchException e) {
                    repetir = true;
                    teclado.nextLine();
                    System.out.println(EntradaSaida.OPCAONAOEXISTE.getMensagem());
                }
        } while(!(numeroAcao >= 1 && numeroAcao <= 6) || repetir);
        
        boolean acessou = false;
        MensagemAcessoNegacao mensagemNegacao = MensagemAcessoNegacao.PERMITIDO;
        
        switch(numeroAcao) {
            case 1:
                acessou = true;
                break;
            case 2:
                mensagemNegacao = MensagemAcessoNegacao.FUNCIONARIOBLOQUEADO;
                break;
            case 3:
                mensagemNegacao = MensagemAcessoNegacao.VEICULOINDISPONIVEL;
                break;
            case 4:
                mensagemNegacao = MensagemAcessoNegacao.ACESSONEGADOAOVEICULO;
                break;
            case 5:
                mensagemNegacao = MensagemAcessoNegacao.MATRICULAINVALIDA;
                break;
            case 6:
                mensagemNegacao = MensagemAcessoNegacao.PLACAINVALIDA;
                break;
        }
        
        if(ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).size() > 0) {
            System.out.println(ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).size() + " registro(s) foi(foram) encontrado(s)");
            for(int i = 0; i < ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).size(); i++) {
                System.out.println("Evento: " + ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).get(i).getTipo());
                System.out.println("Matricula: " + ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).get(i).getMatricula());
                System.out.println("Placa: " + ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).get(i).getPlaca());
                System.out.println("Data: " + ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).get(i).getData());
                if(ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).get(i).getTipo().equals("retirada")) {
                    System.out.println("Status: " + ControladorRegistros.getInstance().pesquisarRegistrosRetirada(acessou, mensagemNegacao).get(i).getMotivoAcessoNegacao().getMensagem());
                }
            }
            
        }
        this.exibirMenu();
    }
    
    private void exibirTodosRegistros() {
        System.out.println(ControladorRegistros.getInstance().getListaRegistros().size() + " registro(s) foi(foram) encontrado(s)");
        for(int i = 0; i < ControladorRegistros.getInstance().getListaRegistros().size(); i++) {
            System.out.println("Matricula: " + ControladorRegistros.getInstance().getListaRegistros().get(i).getMatricula());
            System.out.println("Placa: " + ControladorRegistros.getInstance().getListaRegistros().get(i).getPlaca());
            System.out.println("Data: " + ControladorRegistros.getInstance().getListaRegistros().get(i).getData());
            String status = ControladorRegistros.getInstance().getListaRegistros().get(i).getMotivoAcessoNegacao().getMensagem();
            if(status != null) {
                System.out.println("Status: " + status);
            }
        }
        this.exibirMenu();
    }

}
