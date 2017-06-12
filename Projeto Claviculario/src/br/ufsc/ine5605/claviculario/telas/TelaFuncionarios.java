
package br.ufsc.ine5605.claviculario.telas;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pablo Vicente
 */
public class TelaFuncionarios {
    //Atributos
    private final Scanner teclado;
    
    //Construtor
    public TelaFuncionarios(){
        teclado = new Scanner(System.in);
    }
    
    //Metodos Operacionais
    public void exibirMenu(){
        int numeroAcao = -1;
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("=================== Bem Vindo ao Cadastro De Funcionarios =================");
        System.out.println("---------------------------------------------------------------------------\n");
        
        System.out.println("[1] Cadastrar Funcionário");
        System.out.println("[2] Excluir Funcionario");
        System.out.println("[3] Consultar Informações do Funcionario");
        System.out.println("[4] Atualizar Cadastro De Funcionario");
        System.out.println("[5] Cadastrar Veiculos Para Funcionario");
        System.out.println("[6] Listar Funcionarios Cadastrados");
        System.out.println("[0] Voltar ao Maneu Pricipal\n");
        System.out.println("Por favor escolha umas das opções acima e digite o número correspondente.");
        boolean repetir;
        do {
            repetir = false;
            numeroAcao = pedeInt();
            if(numeroAcao>6){
                System.out.println(EntradaSaida.OPCAONAOEXISTE.getMensagem());
            }
        } while(!(numeroAcao >= 0 && numeroAcao <= 6) || repetir);

        switch(numeroAcao){
            case 1:
                this.exibirTelaCadastro();
                break;
            case 2:
                this.exibirTelaExclusao();
                break;
            case 3:
                this.exibirTelaConsultaFuncionario();
                break;
            case 4:
                this.exibirTelaAtualizacao();
                break;
            case 5:
                this.cadastrarVeiculoFuncionario();
                break;
            case 6:
                this.exibirTelaListaFuncionarios();
                break;                    
            case 0:
                ControladorFuncionarios.getInstance().carregarMenuPrincipal();
                break;    
        }
    }

    private void exibirTelaCadastro() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================= Cadastro de Funcionario =========================");
        System.out.println("---------------------------------------------------------------------------");
                
        System.out.println(ControladorFuncionarios.getInstance().cadastrarFuncionario());
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }

    private void exibirTelaExclusao() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("=========================== Excluir Funcionario ===========================");
        System.out.println("---------------------------------------------------------------------------");
        
        System.out.println(ControladorFuncionarios.getInstance().excluirFuncionario());
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();  
    }

    private void exibirTelaConsultaFuncionario(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Consulta Funcionario ===========================");
        System.out.println("---------------------------------------------------------------------------");
        int matricula = pedirMatriculaFuncionario();
        if(!(ControladorFuncionarios.getInstance().consultaFuncionarios(matricula))){
            System.out.println(EntradaSaida.MATRICULAINVALIDA.getMensagem());
        }
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }

    private void exibirTelaAtualizacao() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("=========================== Atualizar Cadastro ============================");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(ControladorFuncionarios.getInstance().atualizarCadastroFuncionario());
        ///int matricula = pedirMatriculaFuncionario();
        //exibiInformacaoVeiculoCadastrado(matricula);
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();  
    }
    
    public void exibirTelaCadastroVeiculosFuncionario() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("================== Cadastrar Veiculos Para Funcionário ====================");
        System.out.println("---------------------------------------------------------------------------");
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }
    
    public void cadastrarVeiculoFuncionario(){
        exibirTelaCadastroVeiculosFuncionario();
        int matricula = pedirMatriculaFuncionario();
        exibiInformacaoVeiculoCadastrado(matricula);
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }
    
    public void exibiInformacaoVeiculoCadastrado(int matricula){
        exibirTelaCadastroVeiculosFuncionario();
        do{
            System.out.println(ControladorFuncionarios.getInstance().cadastrarVeiculosNoFuncionario(matricula));
        }while(ControladorPrincipal.getInstance().perguntarAoUsuario(EntradaSaida.PERGUNTA.getMensagem()));
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }
 
    public void exibirDadosDoFuncionario(String infoFuncionario){
    
        System.out.println("---------------------------------------------------------------------------");
        String[] dadosFuncionarios = infoFuncionario.split(",");
        System.out.println("Matricula: " + dadosFuncionarios[0]);
        System.out.println("Nome: " + dadosFuncionarios[1]);
        String data = dadosFuncionarios[2];
        if(0==0){
            
        }   else{
            
        }         
        System.out.println("Data Nascimento: " + dadosFuncionarios[2]);
        System.out.println("Telefone: " + dadosFuncionarios[3]);
        System.out.println("Cargo: "+dadosFuncionarios[4]);
        System.out.println("Está bloqueado: " + dadosFuncionarios[5]);
        String veiculoPedennte =null;
        if(dadosFuncionarios[6].equals("null")){
            veiculoPedennte = "";
        }else{
            veiculoPedennte = dadosFuncionarios[6];
        }
        System.out.println("Veículo pendente de entrega: " + veiculoPedennte);        
    }
    
    private void exibirTelaListaFuncionarios() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================= Funcionario Cadastrados =========================");
        System.out.println("---------------------------------------------------------------------------");
        ControladorFuncionarios.getInstance().exibirDadosDeTodosFuncionarios();
        //ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }
    
    public void exibirTelaVeiculosAutorizados(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("========================== Veiculos Autorizados ===========================");
        System.out.println("---------------------------------------------------------------------------");
    }
    
    public int pedirMatriculaFuncionario(){
        System.out.print("[A] Informe a matricula: ");
        return pedeInt();
    }
    
    public String pedirNomeFuncionario(){
        System.out.print("[B] Informe o nome do funcionario: ");
        return pedirString();
    }
    
    public Calendar pedirDataNascimento(){
        DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
        Date date2 = null;
        
        
            System.out.print("[C] Informe a data de nascimento(dd/mm/aaaa): ");
            boolean repetir;
            Calendar date = Calendar.getInstance();
            do {
                repetir = false;
                try {
                    String dataTexto = teclado.next();
                    if(dataTexto.length()==10){
                        String[] data = dataTexto.split("/");
                        if(data.length == 3){
                            int[] dataNumerica = new int[3];
                            for(int i = 0; i < data.length; i++) {
                                dataNumerica[i] = Integer.parseInt(data[i]);
                            }
                            date = Calendar.getInstance();
                            date.set(dataNumerica[2], dataNumerica[1] - 1, dataNumerica[0]);
                        }
                    }else {
                        repetir = true;
                    }
                } catch(NumberFormatException e) {
                    repetir = true;
                    teclado.nextLine();
                }
                if(repetir) {
                    System.out.println(EntradaSaida.DATAINCORRETA.getMensagem());
                }
            } while(repetir);
        return date;
    }
    
    public String pedirTelefone(){
        String mensagem ="Telefone formato inválido";
        String telefone = null;
        boolean repetir= true;
        do{
            System.out.print("[D] Informe o telefone (XX)9XXXX-XXXX: ");
            telefone = pedirString();
            if(telefone.length()==14){
                if((telefone.charAt(0)=='(')&&(telefone.charAt(3)==')')&&(telefone.charAt(9)=='-')){
                    for(int i =0; i <telefone.length();i++){
                        if(!(i==0||i==3||i==9)){
                            int n = Character.getNumericValue(telefone.charAt(i));
                            if(n>=0&&n<=9){
                               repetir = false;
                            }else{
                                repetir = true;
                                break;
                            }
                        }
                    }
                }
            }
            if(repetir){
                System.out.println(mensagem);
            }   
        }while(repetir);
        
        return telefone;
    }
    
    public String pedirCargo(){
        String cargo = null;
        int codCargo = -1;
        do{
            System.out.println("[E] Informe o cargo: ");
            System.out.println("        [0] Para Diretor");
            System.out.println("        [1] Para outro cargo");

            codCargo = pedeInt();
            switch (codCargo) {
                case 0:
                    cargo = EntradaSaida.DIRETOR.getMensagem();
                    break;
                case 1:
                    System.out.println("Digite o cargo:");
                    cargo = pedirString();
                    //System.out.println(cargo+" cadastrado com sucesso.");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }while(codCargo!=0&&codCargo!=1);
        return cargo;
    }
    
    public String pedirString(){
        teclado.nextLine();
        //teclado.nextLine();
        return teclado.nextLine();

    }
    
    public int pedeInt(){
        boolean repetir;
        int inteiro=-1;
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
}
