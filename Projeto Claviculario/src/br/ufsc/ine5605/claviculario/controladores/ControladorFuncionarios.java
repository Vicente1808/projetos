package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.persistencia.MapeadorFuncionario;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaDadosFuncionario;
import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Pablo Vicente
 */

public class ControladorFuncionarios{
    //Atributos
    private static ControladorFuncionarios instance;
    private final TelaDadosFuncionario telaDadosFuncionario;
    private final MapeadorFuncionario mapeadorFuncionario;
    //private final TelaGerenciamentoFuncionario telaGraficaGerenciamentoFuncionario;
        
    //Contrutor
    private ControladorFuncionarios(){
        this.mapeadorFuncionario = new MapeadorFuncionario();
        this.telaDadosFuncionario = new TelaDadosFuncionario(this);
        adicionarCargos();
    }
    
     //Metodos Operacionais
    public void carregarTelaGraficaDadosFuncionarios(String titulo, int tipo){
        telaDadosFuncionario.setTitle(titulo);
        telaDadosFuncionario.definirBotoes(tipo);
        telaDadosFuncionario.limparTela();
        telaDadosFuncionario.setVisible(true);
    }
   
        public void adicionarCargos(){
        ArrayList<String> cargos = new ArrayList<>();
            for(Funcionario funcionario :mapeadorFuncionario.getList().values()){
            String cargoList = funcionario.getCargo();
            if(!(cargos.contains(cargoList))){
                cargos.add(cargoList);
            }
        }
        if(!(cargos.contains(EntradaSaida.DIRETOR.getMensagem()))){
            cargos.add(EntradaSaida.DIRETOR.getMensagem());
        }
        if(!(cargos.contains(EntradaSaida.OUTRO.getMensagem()))){
            cargos.add(EntradaSaida.OUTRO.getMensagem());
        }    
        
        int t = cargos.size();
        String[] listaCargos = new String[t];
        
        for(int i=0;i<t;i++){
            listaCargos[i]=cargos.get(i);
        }
            telaDadosFuncionario.setListaCargos(cargos);
    }

    public String[][] getVeiculosSimples(){
        return ControladorPrincipal.getInstance().getListaVeiculosSimples();
    }
    
    public boolean validarMatricula(int matricula){
        return mapeadorFuncionario.get(matricula)!=null;
    }
    
    public String cadastrarFuncionario(FuncionarioVO funcionarioVO){
        String retornoCadastroFuncionario = EntradaSaida.MATRICULADUPLICA.getMensagem();
        
        if(!validarMatricula(funcionarioVO.matricula)){
            Funcionario funcionario = new Funcionario(funcionarioVO.matricula, funcionarioVO.nome, funcionarioVO.dataNascimento, funcionarioVO.telefone, funcionarioVO.cargo);
            mapeadorFuncionario.put(funcionario);
            retornoCadastroFuncionario = EntradaSaida.FUNCIONARIOCADASTRADO.getMensagem();
        }
        return retornoCadastroFuncionario;
    }
    
    public String excluirFuncionario(int matricula){
        String retornoExclusao = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        
        if(validarMatricula(matricula)){
            if(mapeadorFuncionario.get(matricula).getVeiculoPendente()!=null){
                retornoExclusao = EntradaSaida.FUNCIONARIPENDENTE.getMensagem();
            }else{
                mapeadorFuncionario.remove(matricula);
                retornoExclusao = EntradaSaida.FUNCIONARIOEXCLUIDO.getMensagem();
            }
        }
        return retornoExclusao;
    }
    
    /*public String pesquisarFuncionario(int matricula){
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
    */
    public String atualizarCadastroFuncionario(FuncionarioVO funcionarioVO){
        
        String retornoCadastro = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        
        int matricula = funcionarioVO.matricula;
        String nome = funcionarioVO.nome;
        Calendar dataNascimento = funcionarioVO.dataNascimento;
        String telefone = funcionarioVO.telefone;
        String cargo = funcionarioVO.cargo;
        
        if(validarMatricula(matricula)){
           
           Funcionario funcionario = mapeadorFuncionario.get(matricula);
           funcionario.setNome(nome);
           funcionario.setDataNascimento(dataNascimento);
           funcionario.setTelefone(telefone);
           funcionario.setCargo(cargo);
           funcionario.setBloqueado(true);
           retornoCadastro = EntradaSaida.FUNCIONARIOATUALIZADO.getMensagem();
        } 
        return retornoCadastro;
    }
    
    public void alterarVeiculoPendente(int matricula, String placa ){
        mapeadorFuncionario.get(matricula).setVeiculoPendente(placa);
    }
    
    
    public void bloquearFuncionario(int matricula){
        mapeadorFuncionario.get(matricula).setBloqueado(true);
    }
    
    //Tem que ser atualiado
    public String cadastrarVeiculosNoFuncionario(int matricula, String placa){
        String retornoCadastroVeiculoNoFuncionario = EntradaSaida.MATRICULAINVALIDA.getMensagem();
        if(validarMatricula(matricula)){
            if(!(mapeadorFuncionario.get(matricula).getCargo().equals(EntradaSaida.DIRETOR.getMensagem()))) {
                if(!ControladorPrincipal.getInstance().validarPlaca(placa)){
                    retornoCadastroVeiculoNoFuncionario = EntradaSaida.PLACAINEXISTENTE.getMensagem();
                }else{
                    Funcionario funcionario = mapeadorFuncionario.get(matricula);
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
    

    
    
    public FuncionarioVO getFuncionario(int matricula){
        FuncionarioVO funcionarioVO = new FuncionarioVO();
        if(validarMatricula(matricula)){

            Funcionario funcionario = mapeadorFuncionario.get(matricula);
            funcionarioVO.matricula = funcionario.getMatricula();
            funcionarioVO.nome = funcionario.getNome();
            funcionarioVO.dataNascimento = funcionario.getDataNascimento();
            funcionarioVO.telefone = funcionario.getTelefone();
            funcionarioVO.cargo = funcionario.getCargo();
            funcionarioVO.bloqueado = funcionario.isBloqueado();
            funcionarioVO.veiculoPendente = funcionario.getVeiculoPendente();
            funcionarioVO.veiculos = funcionario.getVeiculos();
        }
        return funcionarioVO;
    }
    
    
    //public String pedirPlacaVeiculo(){
      //  return ControladorPrincipal.getInstance().pedirPlacaVeiculo();
    //}
    
    public void excluirVeiculoTodosFuncionarios(String placa){
        for(Integer matricula : mapeadorFuncionario.getList().keySet()){
            if(mapeadorFuncionario.get(matricula).getVeiculos().contains(placa)){
                mapeadorFuncionario.get(matricula).getVeiculos().remove(placa);
            }
        }
    }
    
    public int quantidadeVeiculosFuncionarios(int matricula){
        return getFuncionario(matricula).veiculos.size();
    }
    
    public void carregarMenuPrincipal(){
        ControladorPrincipal.getInstance().carregarTelaPrincipal();
    }
    
    /*public void exibirListaVeiculosFuncionario(int matricula){
        telaFuncionarios.exibirTelaVeiculosAutorizados();
        ControladorPrincipal.getInstance().exibirVeiculosAutorizados(matricula);
    */

    
    /*
    public void exibirDadosDeTodosFuncionarios(){
        for(Funcionario funcionario:funcionarios.values()){
            telaFuncionarios.exibirDadosDoFuncionario(pesquisarFuncionario(funcionario.getMatricula()));
        }
    }
    */

    
    public HashMap getFuncionarios() {
        HashMap<Integer, FuncionarioVO> funcionariosVO = new HashMap<>();
        
        for(Integer matricula : mapeadorFuncionario.getList().keySet()){
            funcionariosVO.put(matricula, getFuncionario(matricula));
        }
        return funcionariosVO;
    }

    public static ControladorFuncionarios getInstance() {
        if(instance == null){
            instance = new ControladorFuncionarios();
        }
        return instance;
    }

    
}