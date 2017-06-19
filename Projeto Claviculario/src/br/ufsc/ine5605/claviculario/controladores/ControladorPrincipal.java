
package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.enums.MensagemAcessoNegacao;
import br.ufsc.ine5605.claviculario.enums.RetiradaEDevolucao;
import br.ufsc.ine5605.claviculario.telasGraficas.principal.TelaGerenciamentos;
import br.ufsc.ine5605.claviculario.telasGraficas.principal.TelaGraficaDevolucao;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaGerenciamentoFuncionario;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaGerenciamentoVeiculos;
import br.ufsc.ine5605.claviculario.telasGraficas.principal.TelaPrincipalGrafica;
import br.ufsc.ine5605.claviculario.telasGraficas.principal.TelaRetiradaVeiculo;
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
    private ControladorFuncionarios controladorFuncionarios;
    private ControladorVeiculos controladorVeiculos;
    private final TelaPrincipalGrafica telaPrincipal;
    private final TelaGraficaDevolucao telaDevolucao;
    private final TelaRetiradaVeiculo telaRetirada;
    private final TelaGerenciamentos telaGerenciamentos;
    private final TelaGerenciamentoFuncionario telaGereciamentoFuncionarios;
    private final TelaGerenciamentoVeiculos telaGerenciamentoVeiculos;
    
    //Construtor
    private ControladorPrincipal(){
        this.controladorFuncionarios = ControladorFuncionarios.getInstance();
        this.controladorVeiculos = ControladorVeiculos.getInstance();
        telaPrincipal = new TelaPrincipalGrafica(this);
        telaDevolucao = new TelaGraficaDevolucao(this);
        telaRetirada = new TelaRetiradaVeiculo(this);
        telaGerenciamentos = new TelaGerenciamentos(this);
        this.telaGereciamentoFuncionarios = new TelaGerenciamentoFuncionario(this);
        this.telaGerenciamentoVeiculos = new TelaGerenciamentoVeiculos(this);
    }
    
    //Métodos Operacionais
    public RetiradaEDevolucao retirarVeiculo(int matricula, String placa){
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
        String date = f.format(data);
        
        RetiradaEDevolucao retornoRetirada = RetiradaEDevolucao.MATRICULAINCORRETA;
        
        if(!ControladorFuncionarios.getInstance().validarMatricula(matricula)){
            placa = null;
            ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.MATRICULAINVALIDA);
            retornoRetirada = RetiradaEDevolucao.MATRICULAINCORRETA;
        }else if(ControladorFuncionarios.getInstance().getFuncionario(matricula).bloqueado){
                    placa = null;
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.FUNCIONARIOBLOQUEADO);
                    retornoRetirada = RetiradaEDevolucao.USUARIOBLOQUEADO;
        }else if(ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculoPendente!= null){
            placa = ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculoPendente;
            ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.VEICULOPENDENTE);
            retornoRetirada = RetiradaEDevolucao.VEICULOPENDENTE;

        }else if((ControladorFuncionarios.getInstance().quantidadeVeiculosFuncionarios(matricula)==0)&& !(ControladorFuncionarios.getInstance().getFuncionario(matricula).cargo.equals(EntradaSaida.DIRETOR.getMensagem()))){
                placa =null;
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.SEMVEICULOSASSOCIADOS);
                retornoRetirada = RetiradaEDevolucao.SEMVEICULOS;
                
        }else if(ControladorFuncionarios.getInstance().quantidadeVeiculosFuncionarios(matricula)==1){
                placa = ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculos.get(0);
                if(ControladorVeiculos.getInstance().getVeiculo(placa).chaveClaviculario){
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.ACESSOPERMITIDO);
                    ControladorVeiculos.getInstance().alterarChaveClaviculario(placa,true);
                    ControladorFuncionarios.getInstance().alterarVeiculoPendente(matricula, placa);
                                    
                }else{
                    retornoRetirada = RetiradaEDevolucao.VEICULOINDISPONIVEL;
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.VEICULOINDISPONIVEL);
                }
        }else{
            int contador = 0;
            boolean resposta = false;
            do{
                if(!validarVeiculoDeFuncionario(matricula, placa) && !(ControladorFuncionarios.getInstance().getFuncionario(matricula).cargo.equals(EntradaSaida.DIRETOR.getMensagem()))){
                    contador++;
                    ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.ACESSONEGADOAOVEICULO);
                }
            }while(contador < 3 && (resposta));
            if(contador == 3){
                ControladorFuncionarios.getInstance().bloquearFuncionario(matricula);
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.FUNCIONARIOBLOQUEADO);
                retornoRetirada = RetiradaEDevolucao.USUARIOBLOQUEADO;
            }
            if(ControladorVeiculos.getInstance().getVeiculo(placa).chaveClaviculario){
                ControladorRegistros.getInstance().registrarRetirada(matricula, placa, data, true, MensagemAcessoNegacao.ACESSOPERMITIDO);
                ControladorVeiculos.getInstance().alterarChaveClaviculario(placa,false);
                ControladorFuncionarios.getInstance().alterarVeiculoPendente(matricula, placa);
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
            if(ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculoPendente.equals(placa)){
                ControladorVeiculos.getInstance().alterarChaveClaviculario(placa, true);
                ControladorFuncionarios.getInstance().alterarVeiculoPendente(matricula,null);
                ControladorRegistros.getInstance().registrarDevolucao(matricula, placa, Calendar.getInstance().getTime());
            } else {
                retorno = RetiradaEDevolucao.VEICULONAOASSOCIADO;
            }
        } else {
            retorno = RetiradaEDevolucao.MATRICULAOUPLACAINVALIDA;
        }
        return retorno;
    }
    
    public void carregarTelaPrincipal(){
        telaGerenciamentos.setVisible(false);
        telaDevolucao.setVisible(false);
        telaRetirada.setVisible(false);
        telaPrincipal.setVisible(true);
    }
    
    public void carregarMenuRetirada() {
        telaPrincipal.setVisible(false);
        telaGerenciamentos.setVisible(false);
        telaDevolucao.setVisible(false);
        telaRetirada.setVisible(true);
    }
    
    public void carregarMenuDevolucao() {
        telaPrincipal.setVisible(false);
        telaGerenciamentos.setVisible(false);
        telaRetirada.setVisible(false);
        telaDevolucao.setVisible(true);
    }
    
    public void carregarMenuGerenciamento(){
        telaRetirada.setVisible(false);
        telaPrincipal.setVisible(true);
        telaGerenciamentos.setVisible(true);
    }
    
    public void carregarTelaGerenciamentoFuncionarios(){
        telaGereciamentoFuncionarios.setVisible(true);
    }
    
    public void carregarTelaGerenciamentoVeiculos(){
        telaGerenciamentoVeiculos.setVisible(true);
    }
    
    public String[][] getListaVeiculosSimples(){
        return ControladorVeiculos.getInstance().getVeiculosSimples();
    }
    
    public void carregarMenuRegistros(){
        ControladorRegistros.getInstance().carregaMenuRegistros();
    }
    
    public void atualizarDados() {
        telaGereciamentoFuncionarios.updateData();
        telaGerenciamentoVeiculos.updateData();
    }
    
    public boolean validarMatricula(int matricula){
        return ControladorFuncionarios.getInstance().validarMatricula(matricula);
    }
    
    public boolean validarVeiculoDeFuncionario(int matricula, String placa){
        return ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculos.contains(placa);
    }
    
    //public void exibirDadosVeiculo(String placa){
      //  ControladorVeiculos.getInstance().exibirDadosVeiculo(placa);
   // }
    
    //public void getVeiculosAutorizados(int matricula){
        //ControladorVeiculos.getInstance().exbirListaVeiculosFuncionario(ControladorFuncionarios.getInstance().getFuncionario(matricula).veiculos);
   //}
    
    public boolean validarPlaca(String placa){
        return ControladorVeiculos.getInstance().validarPlaca(placa);
    }
    
    public void excluirVeiculoTodosFuncionarios(String placa){
        ControladorFuncionarios.getInstance().excluirVeiculoTodosFuncionarios(placa);
    }
    
    //public String pedirPlacaVeiculo(){
        // ControladorVeiculos.getInstance().pedirPlacaVeiculo();
    //}//
    
    //public boolean perguntarAoUsuario(String mensagem){
        //return false;
    //}
    
    //public void encerrar() {
        
    //}
    //Métodos Para Pegar instancia do controlador
    public static ControladorPrincipal getInstance() {
        if(instance == null){
            instance = new ControladorPrincipal();
        }
        return instance;
    }

}
