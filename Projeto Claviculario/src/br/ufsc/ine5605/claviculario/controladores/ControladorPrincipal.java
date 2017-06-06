
package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.enums.MensagemAcessoNegacao;
import br.ufsc.ine5605.claviculario.telas.TelaPrincipal;
import br.ufsc.ine5605.claviculario.enums.RetiradaEDevolucao;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
/**
 *
 * @author Pablo Vicente
 */
public class ControladorPrincipal {
    //Atributos
    private static ControladorPrincipal instance; 

    private final TelaPrincipal telaPrincipal;
    
    //Construtor
    private ControladorPrincipal(){
        telaPrincipal = new TelaPrincipal(this);
    }
    
    //Métodos Operacionais
    public RetiradaEDevolucao retirarVeiculo(){
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
        String date = f.format(data);
        
        int matricula = telaPrincipal.pedeMatricula();
        RetiradaEDevolucao retornoRetirada = RetiradaEDevolucao.MATRICULAINCORRETA;
        
        if(!ControladorFuncionarios.getInstance().validarMatricula(matricula)){
            String placa = null;
            ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.MATRICULAINVALIDA);
            retornoRetirada = RetiradaEDevolucao.MATRICULAINCORRETA;
        }else if(ControladorFuncionarios.getInstance().getFuncionario(matricula).isEstaBloqueado()){
                    String placa = null;
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.FUNCIONARIOBLOQUEADO);
                    retornoRetirada = RetiradaEDevolucao.USUARIOBLOQUEADO;
        }else if(ControladorFuncionarios.getInstance().getFuncionario(matricula).getVeiculoPendente() != null){
            String placa = ControladorFuncionarios.getInstance().getFuncionario(matricula).getVeiculoPendente();
            ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.VEICULOPENDENTE);
            retornoRetirada = RetiradaEDevolucao.VEICULOPENDENTE;

        }else if((ControladorFuncionarios.getInstance().quantidadeVeiculosFuncionarios(matricula)==0)&& !(ControladorFuncionarios.getInstance().getFuncionario(matricula).getCargo().equals(EntradaSaida.DIRETOR.getMensagem()))){
                String placa =null;
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.SEMVEICULOSASSOCIADOS);
                retornoRetirada = RetiradaEDevolucao.SEMVEICULOS;
                
        }else if(ControladorFuncionarios.getInstance().quantidadeVeiculosFuncionarios(matricula)==1){
                String placa = ControladorFuncionarios.getInstance().getFuncionario(matricula).getVeiculos().get(0);
                if(ControladorVeiculos.getInstance().getVeiculo(placa).isChaveClaviculario()){
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.ACESSOPERMITIDO);
                    ControladorVeiculos.getInstance().getVeiculo(placa).setChaveClaviculario(false);
                    ControladorFuncionarios.getInstance().getFuncionario(matricula).setVeiculoPendente(placa);
                                    
                }else{
                    retornoRetirada = RetiradaEDevolucao.VEICULOINDISPONIVEL;
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.VEICULOINDISPONIVEL);
                }
        }else{
            int contador = 0;
            boolean resposta = false;
            String placa;
            do{
                placa = pedirPlacaVeiculo();
                if(!validarVeiculoDeFuncionario(matricula, placa) && !(ControladorFuncionarios.getInstance().getFuncionario(matricula).getCargo().equals(EntradaSaida.DIRETOR.getMensagem()))){
                    contador++;
                    telaPrincipal.bloqueiaAcesso(contador);
                    resposta = telaPrincipal.perguntarAoUsuario("Deseja retirar outro veículo?");
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.ACESSONEGADOAOVEICULO);
                }
            }while(contador < 3 && (resposta));
            if(contador == 3){
                ControladorFuncionarios.getInstance().getFuncionario(matricula).setEstaBloqueado(true);
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.FUNCIONARIOBLOQUEADO);
                retornoRetirada = RetiradaEDevolucao.USUARIOBLOQUEADO;
            }
            if(ControladorVeiculos.getInstance().getVeiculo(placa).isChaveClaviculario()){
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.ACESSOPERMITIDO);
                ControladorVeiculos.getInstance().getVeiculo(placa).setChaveClaviculario(false);
                ControladorFuncionarios.getInstance().getFuncionario(matricula).setVeiculoPendente(placa);
                retornoRetirada = RetiradaEDevolucao.VEICULORETIRADO;
            }else{
                retornoRetirada = RetiradaEDevolucao.VEICULOINDISPONIVEL;
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.VEICULOINDISPONIVEL);
            }
        }
        return retornoRetirada;
        
    }
    
    public RetiradaEDevolucao devolverVeiculo(int matricula, String placa, int kmAtual){
                
        RetiradaEDevolucao retorno = RetiradaEDevolucao.VEICULODEVOLVIDO;
        
        if(ControladorFuncionarios.getInstance().validarMatricula(matricula) && ControladorVeiculos.getInstance().validarPlaca(placa)) {
            if(ControladorFuncionarios.getInstance().getFuncionario(matricula).getVeiculoPendente().equals(placa)) {
                ControladorVeiculos.getInstance().getVeiculo(placa).setKmAtual(kmAtual);
                ControladorVeiculos.getInstance().getVeiculo(placa).setChaveClaviculario(true);
                ControladorFuncionarios.getInstance().getFuncionario(matricula).setVeiculoPendente(null);
                ControladorRegistros.getInstance().registrarDevolucao(matricula, placa, Calendar.getInstance().getTime());
            } else {
                retorno = RetiradaEDevolucao.VEICULONAOASSOCIADO;
            }
        } else {
            retorno = RetiradaEDevolucao.MATRICULAOUPLACAINVALIDA;
        }
        return retorno;
    }
    
    public void carregarMenuPrincipal(){
        telaPrincipal.exibirMenu();
    }
    
    public void carregarMenuVeiculos(){        
        ControladorVeiculos.getInstance().carregarMenuVeiculos();
    }
    
    public void carregarMenuFuncionarios(){
        ControladorFuncionarios.getInstance().carregarMenuFuncionario();
    }
    
    public void carregarMenuRegistros(){
        ControladorRegistros.getInstance().carregaMenuRegistros();
    }
    
    public boolean validarMatricula(int matricula){
        return ControladorFuncionarios.getInstance().validarMatricula(matricula);
    }
    
    public boolean validarVeiculoDeFuncionario(int matricula, String placa){
        return ControladorFuncionarios.getInstance().getFuncionario(matricula).getVeiculos().contains(placa);
    }
    
    //public void exibirDadosVeiculo(String placa){
      //  ControladorVeiculos.getInstance().exibirDadosVeiculo(placa);
   // }
    
    public void exibirVeiculosAutorizados(int matricula){
        ControladorVeiculos.getInstance().exbirListaVeiculosFuncionario(ControladorFuncionarios.getInstance().getFuncionario(matricula).getVeiculos());
   }
    
    public boolean validarPlaca(String placa){
        return ControladorVeiculos.getInstance().validarPlaca(placa);
    }
    
    public void excluirVeiculoTodosFuncionarios(String placa){
        ControladorFuncionarios.getInstance().excluirVeiculoTodosFuncionarios(placa);
    }
    
    public String pedirPlacaVeiculo(){
        return ControladorVeiculos.getInstance().pedirPlacaVeiculo();
    }
    
    public boolean perguntarAoUsuario(String mensagem){
        return telaPrincipal.perguntarAoUsuario(mensagem);
    }
    
    public void encerrar() {
        
    }
    //Métodos Para Pegar instancia do controlador
    public static ControladorPrincipal getInstance() {
        if(instance == null){
            instance = new ControladorPrincipal();
        }
        return instance;
    }
    
}