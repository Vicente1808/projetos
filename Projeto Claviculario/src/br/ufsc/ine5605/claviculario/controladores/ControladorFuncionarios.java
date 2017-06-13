package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.telas.TelaFuncionarios;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaGraficaDadosFuncionario;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaGraficaGerenciamento;
import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pablo Vicente
 */

public class ControladorFuncionarios{
    //Atributos
    private static ControladorFuncionarios instance;
    private final TelaFuncionarios telaFuncionarios;
    private final HashMap<Integer, Funcionario> funcionarios;
    private final TelaGraficaDadosFuncionario telaGraficaDadosFuncionario;
        
    //Contrutor
    private ControladorFuncionarios(){
        funcionarios = new HashMap<>();
        telaFuncionarios = new TelaFuncionarios();
        telaGraficaDadosFuncionario = new TelaGraficaDadosFuncionario();
    }

    
    public void carregarTelaGraficaDadosFuncionarios(String titulo){
        telaGraficaDadosFuncionario.setTitle(titulo);
        telaGraficaDadosFuncionario.setVisible(true);
    }
    //Metodos Operacionais
    public String cadastrarFuncionario(){
        String retornoCadastroFuncionario = EntradaSaida.MATRICULADUPLICA.getMensagem();
        int matricula = telaFuncionarios.pedirMatriculaFuncionario();
        
        if(!validarMatricula(matricula)){
            
            String nome = telaFuncionarios.pedirNomeFuncionario();
            Calendar dataNascimento = telaFuncionarios.pedirDataNascimento();
            String telefone =telaFuncionarios.pedirTelefone();
            String cargo = telaFuncionarios.pedirCargo();
            Funcionario funcionario = new Funcionario(matricula, nome, dataNascimento, telefone, cargo);
            funcionarios.put(funcionario.getMatricula(), funcionario);
            
            if(!funcionario.getCargo().equals(EntradaSaida.DIRETOR.getMensagem())){
                boolean resposta = ControladorPrincipal.getInstance().perguntarAoUsuario(EntradaSaida.PERGUNTA.getMensagem());
                    if(resposta){    
                    telaFuncionarios.exibiInformacaoVeiculoCadastrado(matricula);
                }
             }
            retornoCadastroFuncionario = EntradaSaida.FUNCIONARIOCADASTRADO.getMensagem();
        }
        
        return retornoCadastroFuncionario;
    }
    
    public String excluirFuncionario(){
        String retornoExclusao = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        int matricula = telaFuncionarios.pedirMatriculaFuncionario();
        if(validarMatricula(matricula)){
            if(getFuncionario(matricula).getVeiculoPendente()!=null){
                retornoExclusao = EntradaSaida.FUNCIONARIPENDENTE.getMensagem();
            }else{
                funcionarios.remove(getFuncionario(matricula));
                retornoExclusao = EntradaSaida.FUNCIONARIOEXCLUIDO.getMensagem();
            }
        }
        return retornoExclusao;
    }
    
    public String pesquisarFuncionario(int matricula){
        String retornoPesquisa = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        if(validarMatricula(matricula)){
            Funcionario funcionario = getFuncionario(matricula);
            String nome = funcionario.getNome();
            
            Calendar nasc = funcionario.getDataNascimento();
            Date dataNascimento = nasc.getTime();
            SimpleDateFormat dateF = new SimpleDateFormat();
            
            String telefone = funcionario.getTelefone();
            String cargo = funcionario.getCargo();
            boolean bloqueado = funcionario.isBloqueado();
            String veiculoPendente = funcionario.getVeiculoPendente();
            retornoPesquisa = matricula+","+nome+","+dataNascimento+","+telefone+","+cargo+","+bloqueado+","+veiculoPendente;
            
    }
        return retornoPesquisa;
    }
    
    public String atualizarCadastroFuncionario(){
        String retornoCadastro = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        int matricula = telaFuncionarios.pedirMatriculaFuncionario();
        if(validarMatricula(matricula)){
           String nome = telaFuncionarios.pedirNomeFuncionario();
           Calendar dataNascimento = telaFuncionarios.pedirDataNascimento();
           String telefone = telaFuncionarios.pedirTelefone();
           String cargo = telaFuncionarios.pedirCargo();
           
           Funcionario funcionario = getFuncionario(matricula);
           funcionario.setNome(nome);
           funcionario.setDataNascimento(dataNascimento);
           funcionario.setTelefone(telefone);
           funcionario.setCargo(cargo);
           retornoCadastro = EntradaSaida.FUNCIONARIOATUALIZADO.getMensagem();
        } 
        return retornoCadastro;
    }
    
    public String cadastrarVeiculosNoFuncionario(int matricula){
        String retornoCadastroVeiculoNoFuncionario = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        if(validarMatricula(matricula)){
            if(!(getFuncionario(matricula).getCargo().equals(EntradaSaida.DIRETOR.getMensagem()))) {
                String placa = pedirPlacaVeiculo();
                if(!ControladorPrincipal.getInstance().validarPlaca(placa)){
                    retornoCadastroVeiculoNoFuncionario = EntradaSaida.PLACAINEXISTENTE.getMensagem();
                }else{
                    Funcionario funcionario = getFuncionario(matricula);
                    if(funcionario.getVeiculos().contains(placa)){
                        retornoCadastroVeiculoNoFuncionario = EntradaSaida.VEICULOASSOCIADO.getMensagem();
                    }else{
                        funcionario.getVeiculos().add(placa);
                        retornoCadastroVeiculoNoFuncionario = EntradaSaida.VEICULOLIBERADO.getMensagem();
                    }
                }
            } else {
                retornoCadastroVeiculoNoFuncionario = EntradaSaida.FUNCIONARIOEHDIRETOR.getMensagem();
            }
        }
        return retornoCadastroVeiculoNoFuncionario;
    }
    
    public boolean consultaFuncionarios(int matricula){
        boolean retorno = false;
        if(validarMatricula(matricula)){
            telaFuncionarios.exibirDadosDoFuncionario(pesquisarFuncionario(matricula));
            telaFuncionarios.exibirTelaVeiculosAutorizados();
            ControladorPrincipal.getInstance().getVeiculosAutorizados(matricula);
            //exibirListaVeiculosFuncionario(matricula);
            retorno = true;
        }
        return retorno;
    }
    
    public boolean validarMatricula(int matricula){
        return getFuncionario(matricula)!=null;
    }
    
    public Funcionario getFuncionario(int matricula){
        Funcionario funcionarioMatricula = null;
        for(Funcionario funcionario : funcionarios.values()){
            if(funcionario != null && funcionario.getMatricula()==matricula){
                funcionarioMatricula = funcionario;
                break;
            }
       }
            return funcionarioMatricula;
    }
    
    public String pedirPlacaVeiculo(){
        return ControladorPrincipal.getInstance().pedirPlacaVeiculo();
    }
    
    public void excluirVeiculoTodosFuncionarios(String placa){
        for(Funcionario funcionario : funcionarios.values()){
            if(funcionario.getVeiculos().contains(placa)){
                funcionario.getVeiculos().remove(placa);
            }
        }
    }
    
    public int quantidadeVeiculosFuncionarios(int matricula){
        return getFuncionario(matricula).getVeiculos().size();
    }
    
    public void carregarMenuPrincipal(){
        ControladorPrincipal.getInstance().carregarMenuPrincipal();
    }
    
    //public void exibirListaVeiculosFuncionario(int matricula){
        //telaFuncionarios.exibirTelaVeiculosAutorizados();
        //ControladorPrincipal.getInstance().exibirVeiculosAutorizados(matricula);
    //}
    
    public void exibirDadosDeTodosFuncionarios(){
        for(Funcionario funcionario:funcionarios.values()){
            telaFuncionarios.exibirDadosDoFuncionario(pesquisarFuncionario(funcionario.getMatricula()));
        }
    }
    
    public HashMap getFuncionarios() {
        HashMap<Integer, FuncionarioVO> funcionariosVO = new HashMap<>();
        
        for(Integer chave : funcionarios.keySet()) {
            Funcionario funcionario = funcionarios.get(chave);
            FuncionarioVO funcionarioVO = new FuncionarioVO();
            funcionarioVO.matricula = funcionario.getMatricula();
            funcionarioVO.nome = funcionario.getNome();
            funcionarioVO.dataNascimento = funcionario.getDataNascimento();
            funcionarioVO.telefone = funcionario.getTelefone();
            funcionarioVO.cargo = funcionario.getCargo();
            funcionarioVO.bloqueado = funcionario.isBloqueado();
            funcionarioVO.veiculos = funcionario.getVeiculos();
            funcionariosVO.put(funcionarioVO.matricula, funcionarioVO);
        }
        return funcionariosVO;
    }
    
    public Calendar pedirDataNascimento(){
        return telaFuncionarios.pedirDataNascimento();
    }
    
    public static ControladorFuncionarios getInstance() {
        if(instance == null){
            instance = new ControladorFuncionarios();
        }
        return instance;
    }

    
}