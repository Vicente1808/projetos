
package br.ufsc.ine5605.claviculario.zzantigos.telas;

import br.ufsc.ine5605.claviculario.controladores.ControladorVeiculos;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pablo Vicente
 */
public class TelaVeiculos {
    //Atributos
    private final Scanner teclado; 

    //Contrutor
    public TelaVeiculos(ControladorVeiculos controladorVeiculos){
        teclado = new Scanner(System.in);
    }
    
    //Métodos Operacionais
    public void exibeMenu(){ 
        int numeroAcao = -1;
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("===================== Bem Vindo ao Cadastro de Veiculos ===================");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("[1] Cadastrar Veiculo");
        System.out.println("[2] Excluir Veiculo");
        System.out.println("[3] Atualizar dados Veiculo");
        System.out.println("[4] Consultar Informações do Veiculo");
        System.out.println("[5] Listar Veiculos Cadastrados");
        System.out.println("[0] Voltar ao Maneu Pricipal");
        System.out.println("");
        System.out.println("Por favor escolha umas das opções acima e digite o número correspondente.");
        
        boolean repetir;
        do {
            numeroAcao = pedeInt();
            if(numeroAcao>5 || numeroAcao < 0){
                System.out.println(EntradaSaida.OPCAONAOEXISTE.getMensagem());
            }
        } while(!(numeroAcao >= 0 && numeroAcao <= 5));

        switch(numeroAcao){
            case 1:
                exibirTelaCadastro();
                break;
            case 2:
                exibirTelaExclusaoVeiculo();
                break;
            case 3: 
                exibirTelaAtualizacaoVeiculo();
                break;
            case 4:
                exibirTelaIConsultaVeiculo();
                break;
            case 5:
                exibirTelaListaVeiculos();
                break;                    
            case 0:
                ControladorVeiculos.getInstance().carregarMenuPrincipal();
                break;
        }
    }
    
    public void exibirTelaCadastro(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("=========================== Cadastrando Veiculos ==========================");
        System.out.println("---------------------------------------------------------------------------");
        
        System.out.println(ControladorVeiculos.getInstance().cadastrarVeiculo());
        ControladorVeiculos.getInstance().carregarMenuVeiculos();
    }

    private void exibirTelaExclusaoVeiculo(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("============================ Excluindo Veiculo ============================");
        System.out.println("---------------------------------------------------------------------------");
        
        //String placa = pedePlacaVeiculo();
        System.out.println(ControladorVeiculos.getInstance().excluirVeiculo());
        ControladorVeiculos.getInstance().carregarMenuVeiculos();
    }

    private void exibirTelaAtualizacaoVeiculo() {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("======================= Atualizar Cadastro Veculo =========================");
            System.out.println("---------------------------------------------------------------------------");
            //pedePlacaVeiculo();
            System.out.println(ControladorVeiculos.getInstance().atualizarCadastroVeiculo());
            ControladorVeiculos.getInstance().carregarMenuVeiculos();
    }
    
    private void exibirTelaIConsultaVeiculo() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Pesquisando Veiculo ============================");
        System.out.println("---------------------------------------------------------------------------");
        String placa = pedePlacaVeiculo();
        if(ControladorVeiculos.getInstance().exibirDadosVeiculo(placa)){
            System.out.println(EntradaSaida.PLACAINEXISTENTE.getMensagem());
        }
        ControladorVeiculos.getInstance().carregarMenuVeiculos();
    }
    
    public void exibirDadosDeVeiculos(String infoVeiculo){
        System.out.println("---------------------------------------------------------------------------");
        String[] dadosVeiculo = infoVeiculo.split(",");
        System.out.println("Placa: " + dadosVeiculo[0]);
        System.out.println("Marca: " + dadosVeiculo[1]);
        System.out.println("Modelo: " + dadosVeiculo[2]);
        System.out.println("Ano: " + dadosVeiculo[3]);
        System.out.println("Quilometragem Atual: " + dadosVeiculo[4]);
        System.out.println("Veiculo disponível: " + dadosVeiculo[5]);
    }

    private void exibirTelaListaVeiculos() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Veiculos Cadastrados ===========================");
        System.out.println("---------------------------------------------------------------------------");
        ControladorVeiculos.getInstance().exibirDadosTodosVeiculos();
        ControladorVeiculos.getInstance().carregarMenuVeiculos();
    }

    public String pedePlacaVeiculo(){
        boolean repetir;
        String placa = "";
        System.out.print("[A] Informe a placa do Veiculo(aaa-0000): ");
        do {
            repetir = false;
            try {
                placa = pedeString();//teclado.next();
                String[] caracteresDivididos = placa.split("-");
                if(caracteresDivididos.length == 2) {
                    //System.out.println((caracteresDivididos.length == 2) );
                    if(caracteresDivididos[0].length() == 3 && caracteresDivididos[1].length() == 4) {
                        //System.out.println(caracteresDivididos[0].length() == 3 && caracteresDivididos[1].length() == 4);
                        char[] parteAlfabetica = caracteresDivididos[0].toCharArray();
                        int parteNumerica = Integer.parseInt(caracteresDivididos[1]);
                        for(int i = 0; i < parteAlfabetica.length; i++) {
                            if(!Character.isLetter(parteAlfabetica[i])) {
                                repetir = true;
                                break;
                            }
                        }
                    } else {
                        repetir = true;
                    }
                } else {
                    repetir = true;
                }
                if(repetir)
                    System.out.println(EntradaSaida.PLACAINCORRETA.getMensagem());
            } catch(NumberFormatException e) {
                repetir = true;
                teclado.nextLine();
                System.out.println(EntradaSaida.PLACAINCORRETA.getMensagem());
            }
        } while(repetir);
        return placa;
    }
    
    public String pedeModeloVeiculo(){
        System.out.print("[B] Informe o modelo do Veiculo: ");
        return pedeString();
        //String modeloVeiculo = teclado.next();
        //return modeloVeiculo;
    }    
    
    public String pedeMarcaVeiculo(){
        System.out.print("[C] Informe a marca do Veiculo: ");
        return pedeString();
        //String marcaVeiculo = teclado.next();
        //return marcaVeiculo;
    }
    
    public int pedeAnoVeiculo(){
        System.out.print("[D] Informe o ano do Veiculo: ");
        return pedeInt();
    }
    
    public int pedeKMAtualVeiculo(){
        System.out.print("[E] Informe a Quilometragem Atual do Veiculo: ");
        return pedeInt();
    }
    
    public int pedeInt(){
        boolean repetir;  
        int inteiro = 0;
        do {
            repetir = false;
            try {
                inteiro = teclado.nextInt();
            } catch (InputMismatchException e) {
                repetir = true;
                teclado.nextLine();
                System.out.println(EntradaSaida.VALORNUMERICOREQUERIDO.getMensagem());
            }
        } while(repetir);
        return inteiro;
    }
    
    public String pedeString(){
        return teclado.next();
    }
    


}