
package br.ufsc.ine5605.claviculario.telas;

import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pablo Vicente
 */
public class TelaPrincipal {
    //Atributos
    private final Scanner teclado;

    //Construtor
    public TelaPrincipal(ControladorPrincipal controladorPrincipal){
        teclado = new Scanner(System.in);
    }
    
    //Métodos Operacionais
    public void exibirMenu() { 
        int numeroAcao =-1;
        
        
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("=================== Bem-Vindo ao Claviculário Eletrônico ==================");
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("[1] Retirar Veiculo");
            System.out.println("[2] Devolver Veiculo");
            System.out.println("[3] Gerenciar Veiculos");
            System.out.println("[4] Gerenciar Funcionários");
            System.out.println("[5] Gerenciar Registros");
            System.out.println("[0] Encerrar\n");
            System.out.println("Por favor escolha umas das opções acima e digite o número correspondente.");

            //boolean repetir;
            do{
                numeroAcao = pedeInt();
                if(numeroAcao>5){
                    System.out.println(EntradaSaida.OPCAONAOEXISTE.getMensagem());
                }
            } while(!(numeroAcao >= 0 && numeroAcao <= 5)); //|| repetir);

            switch(numeroAcao){
                case 1:
                    exibirTelaRetirarVeiculo();
                    break;
                case 2:
                    exibirTelaDevolucaoVeiculo();
                    break;
                case 3:
                    ControladorPrincipal.getInstance().carregarMenuDevolucao();
                    break;
                case 4:
                    ControladorPrincipal.getInstance().carregarMenuGerenciamento();
                    break;
                case 5:
                    ControladorPrincipal.getInstance().carregarMenuRegistros();
                    break;
                case 0:
                    ControladorPrincipal.getInstance().encerrar();
                    break; 
            }
      
        }
    
    public void exibirTelaRetirarVeiculo(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Retirada De Veiculo ============================");
        System.out.println("---------------------------------------------------------------------------\n");
        
        System.out.println("Para retirar veículo é necessário informar a matricula e a placa do veiculo\n");
        //System.out.println(ControladorPrincipal.getInstance().retirarVeiculo().getMensagem());
        
        
        ControladorPrincipal.getInstance().carregarMenuPrincipal();
    }
    
    public void exibirTelaDevolucaoVeiculo(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("=========================== Devolução De Veiculo ==========================");
        System.out.println("---------------------------------------------------------------------------");
        
        int matricula = pedeMatricula();
        String placa = ControladorPrincipal.getInstance().pedirPlacaVeiculo();
        int kmAtual = pedeKMatualVeiculo();
        System.out.println(ControladorPrincipal.getInstance().devolverVeiculo(matricula, placa, kmAtual).getMensagem());
        
        this.exibirMenu();
    }
    
    public int pedeMatricula(){
        int matricula = 0;
        System.out.println("[A] Informa a matricula do funcionário: ");
        return pedeInt();
    }
    
    public int pedeKMatualVeiculo(){
        System.out.println("[C] Infomra quilometragem(KM) atual do veículo: ");
        return pedeInt();
    }
    
    public String pedeString(){
        teclado.nextLine();
        return teclado.nextLine();
    }
    
    public int pedeInt(){
        boolean repetir;
        int inteiro = 0;
        do {
            repetir = false;
            try {
                inteiro = teclado.nextInt();
            } catch(InputMismatchException e) {
                repetir = true;
                teclado.nextLine();
                System.out.println(EntradaSaida.VALORNUMERICOREQUERIDO.getMensagem());
            }
        } while(repetir);
        return inteiro;
    }    
    
    public void bloqueiaAcesso(int contador){
        contador  = 3 - contador; 
        System.out.println(EntradaSaida.VEICULONAOASSOCIADO.getMensagem());
        System.out.println("Restão "+ contador+" tentativas para matrícula ser bloqueada");
    }
    
    public boolean perguntarAoUsuario(String pergunta){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(pergunta +"(s/n)");
        System.out.println("---------------------------------------------------------------------------");
        
        boolean valorResposta;
        boolean repetir;
        do {
            repetir = false;
            String resposta = teclado.next();
            resposta = resposta.toLowerCase();
            valorResposta = true;
            switch (resposta) {
                case "s":
                case "sim":
                    valorResposta = true;
                    break;
                case "n":
                case "nao":
                case "não":
                    valorResposta = false;
                    break;
                default:
                    repetir = true;
                    break;
            }
        } while(repetir);
        return valorResposta;
    }
}
