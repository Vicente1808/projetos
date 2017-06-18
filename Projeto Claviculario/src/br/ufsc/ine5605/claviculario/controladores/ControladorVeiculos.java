package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.entidades.Veiculo;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.persistencia.MapeadorVeiculo;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaDadosVeiculo;
import br.ufsc.ine5605.claviculario.valueObjects.VeiculoVO;
import java.util.HashMap;

public class ControladorVeiculos{
    //Atributos
    private static ControladorVeiculos instance;
    private TelaDadosVeiculo telaDadosVeiculo;
    private final MapeadorVeiculo mapeadorVeiculo;
    
    
    private ControladorVeiculos(){
        this.mapeadorVeiculo = new MapeadorVeiculo();
        this.telaDadosVeiculo = new TelaDadosVeiculo();
    }
    
    public void carregarTelaCadastroVeiculo(){
        telaDadosVeiculo.setTitle("Novo Veiculo");
        telaDadosVeiculo.trocarBotao("btCadastrar");
        telaDadosVeiculo.limparTela();
        telaDadosVeiculo.alterarEdicao(true);
        telaDadosVeiculo.setVisible(true);

    }
   
    public void carregarTelaExclusãoVeiculo(){
        telaDadosVeiculo.setTitle("Exclusão De Veiculo");
        telaDadosVeiculo.trocarBotao("btExcluir");
        telaDadosVeiculo.limparTela();
        telaDadosVeiculo.alterarEdicao(false);
        telaDadosVeiculo.setVisible(true);
    }
    
    public void carregarTelaAtualizacaoVeiculo(){
        telaDadosVeiculo.setTitle("Atualização Cadastro De Veiculo");
        telaDadosVeiculo.trocarBotao("Atualizar");
        telaDadosVeiculo.limparTela();
        telaDadosVeiculo.alterarEdicao(true);
        telaDadosVeiculo.setVisible(true);
    }
    
    public boolean validarPlaca(String placa){
        return (mapeadorVeiculo.get(placa)!=null);
    }
    
    public String cadastrarVeiculo(VeiculoVO veiculoVO){
        String retornoCadastro =EntradaSaida.PLACADUPLICADA.getMensagem();
        
        if(!validarPlaca(veiculoVO.placa)){
            Veiculo veiculo = new Veiculo(veiculoVO.placa, veiculoVO.modelo, veiculoVO.marca, veiculoVO.ano, veiculoVO.kmAtual);
            mapeadorVeiculo.put(veiculo);
            retornoCadastro = EntradaSaida.VEICULOCADASTRADO.getMensagem();
        }
        return retornoCadastro;
    }

    public String excluirVeiculo(String placa){
        String retornoVeiculoExcluido = EntradaSaida.PLACAINEXISTENTE.getMensagem();
        
        if(validarPlaca(placa)){
            
            if(!mapeadorVeiculo.get(placa).isChaveClaviculario()){
                retornoVeiculoExcluido = EntradaSaida.VEICULOPENDENTE.getMensagem();
            }else{
                mapeadorVeiculo.remove(placa);
                ControladorPrincipal.getInstance().excluirVeiculoTodosFuncionarios(placa);
                retornoVeiculoExcluido = EntradaSaida.VEICULOEXCLUIDO.getMensagem();
            }
        }    
        return retornoVeiculoExcluido;
    }
   
    public String atualizarCadastroVeiculo(VeiculoVO veiculoVO){
        String retornoAtualizacao = EntradaSaida.PLACAINEXISTENTE.getMensagem();
        
        if(validarPlaca(veiculoVO.placa)){
            Veiculo veiculo =mapeadorVeiculo.get(veiculoVO.placa);
            veiculo.setPlaca(veiculoVO.placa);
            veiculo.setMarca(veiculoVO.marca);
            veiculo.setModelo(veiculoVO.modelo);
            veiculo.setAno(veiculoVO.ano);
            veiculo.setKmAtual(veiculoVO.kmAtual);
            retornoAtualizacao = EntradaSaida.VEICULOATUALIZADO.getMensagem();
        }
        return retornoAtualizacao;
    }
    
    public void alterarChaveClaviculario(String placa, boolean chave){
        mapeadorVeiculo.get(placa).setChaveClaviculario(chave);
    }
    
    public VeiculoVO getVeiculo(String placa){
        VeiculoVO veiculoVO = new VeiculoVO();
        if(validarPlaca(placa)){
            Veiculo veiculo = mapeadorVeiculo.get(placa);
            
            veiculoVO.placa = veiculo.getPlaca();
            veiculoVO.marca = veiculo.getMarca();
            veiculoVO.modelo = veiculo.getModelo();
            veiculoVO.ano = veiculo.getAno();
            veiculoVO.kmAtual = veiculo.getKmAtual();
            veiculoVO.chaveClaviculario = veiculo.isChaveClaviculario();
            
        }
        return veiculoVO;
    }

/*    
    public boolean exibirDadosVeiculo(String placa){
        boolean retorno = false;
        if(validarPlaca(placa)){
            telaVeiculos.exibirDadosDeVeiculos(getVeiculo(placa));
        }
        return retorno;
    }

    public void exibirDadosTodosVeiculos(){
        for(Veiculo veiculo : veiculos){
            telaVeiculos.exibirDadosDeVeiculos(veiculo.getPlaca());
            
        }
    }
    
    public void exbirListaVeiculosFuncionario(ArrayList<String> veiculosFuncionario){
        for(String placa : veiculosFuncionario){
            telaVeiculos.exibirDadosDeVeiculos(getVeiculo(placa));
        }
    }
      
*/
    
    public void carregarMenuPrincipal(){
        ControladorPrincipal.getInstance().carregarTelaPrincipal();
    }    

    public HashMap getVeiculos() {
        HashMap<String, VeiculoVO> veiculosVO = new HashMap<>();
        
        for(Veiculo veiculo: mapeadorVeiculo.getList()){
            
            veiculosVO.put(veiculo.getPlaca(), getVeiculo(veiculo.getPlaca()));            
        }
        

        return veiculosVO;
    }
  
    

    public static ControladorVeiculos getInstance() {
        if(instance == null){
            instance = new ControladorVeiculos();
        }
        return instance;
    }
    
}