package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.entidades.Veiculo;
import br.ufsc.ine5605.claviculario.enums.EntradaSaida;
import br.ufsc.ine5605.claviculario.zzantigos.telas.TelaVeiculos;
import java.util.ArrayList;

public class ControladorVeiculos{
    //Atributos
    private static ControladorVeiculos instance;
    private final ArrayList<Veiculo> veiculos;
    private final TelaVeiculos telaVeiculos;
    
    private ControladorVeiculos(){
        veiculos = new ArrayList<>();
        telaVeiculos = new TelaVeiculos(this);
    }
    
    public String cadastrarVeiculo(){//String placa, String modelo, String marca, int ano, int kmAtual){
        
        String placa = pedirPlacaVeiculo();
        String retornoCadastro =EntradaSaida.PLACADUPLICADA.getMensagem();
        if(!validarPlaca(placa)){
            String modelo = telaVeiculos.pedeModeloVeiculo();
            String marca = telaVeiculos.pedeMarcaVeiculo();
            int ano = telaVeiculos.pedeAnoVeiculo();
            int kmAtual = telaVeiculos.pedeKMAtualVeiculo();
            Veiculo veiculo = new Veiculo(placa, modelo, marca, ano, kmAtual);
            veiculos.add(veiculo);
            retornoCadastro = EntradaSaida.VEICULOCADASTRADO.getMensagem();
            
        }
        return retornoCadastro;
    }

    public String excluirVeiculo(){//String placa){
        String retornoVeiculoExcluido = EntradaSaida.PLACAINEXISTENTE.getMensagem();
        String placa = pedirPlacaVeiculo();
        if(validarPlaca(placa)){
            if(!getVeiculo(placa).isChaveClaviculario()){
                retornoVeiculoExcluido = EntradaSaida.VEICULOPENDENTE.getMensagem();
            }else{
                veiculos.remove(getVeiculo(placa));
                ControladorPrincipal.getInstance().excluirVeiculoTodosFuncionarios(placa);
                retornoVeiculoExcluido = EntradaSaida.VEICULOEXCLUIDO.getMensagem();
            }
        }    
        return retornoVeiculoExcluido;
    }
    
    public String atualizarCadastroVeiculo(){
        String retornoAtualizacao = EntradaSaida.PLACAINEXISTENTE.getMensagem();
        String placa = pedirPlacaVeiculo();
        if(validarPlaca(placa)){
            String modelo = telaVeiculos.pedeModeloVeiculo();
            String marca = telaVeiculos.pedeMarcaVeiculo();
            int ano = telaVeiculos.pedeAnoVeiculo();
            int kmAtual = telaVeiculos.pedeKMAtualVeiculo();
            
            Veiculo veiculo = getVeiculo(placa);
            veiculo.setMarca(marca);
            veiculo.setModelo(modelo);
            veiculo.setAno(ano);
            veiculo.setKmAtual(kmAtual);
            retornoAtualizacao = EntradaSaida.VEICULOATUALIZADO.getMensagem();
        }
        return retornoAtualizacao;
    }
    
    public String pesquisarVeiculo(String placa){
        String retornoPesquisa = EntradaSaida.PLACAINEXISTENTE.getMensagem();
        //String placa = telaVeiculos.pedePlacaVeiculo();
        if(validarPlaca(placa)){
            Veiculo veiculo = getVeiculo(placa);
            String marca = veiculo.getMarca();
            String modelo = veiculo.getModelo();
            int ano = veiculo.getAno();
            int kmAtual = veiculo.getKmAtual();
            boolean chave = veiculo.isChaveClaviculario();
            retornoPesquisa = placa+","+marca+","+modelo+","+ano+","+kmAtual+","+chave;
        }
        return retornoPesquisa;
    }
    
    public boolean exibirDadosVeiculo(String placa){
        boolean retorno = false;
        if(validarPlaca(placa)){
            telaVeiculos.exibirDadosDeVeiculos(pesquisarVeiculo(placa));
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
            telaVeiculos.exibirDadosDeVeiculos(pesquisarVeiculo(placa));
        }
    }
    
    public boolean validarPlaca(String placa){
        return (getVeiculo(placa)!=null);
    }
        
    public Veiculo getVeiculo(String placa){
        Veiculo veiculoPlaca = null;
        for(Veiculo veiculo : veiculos){
            if(veiculo.getPlaca().equals(placa)){
                veiculoPlaca = veiculo;
                break;
            }
        }
        return veiculoPlaca;
    }
    public String pedirPlacaVeiculo(){
        return telaVeiculos.pedePlacaVeiculo();
    }
    
    public  void carregarMenuVeiculos(){
        telaVeiculos.exibeMenu();
    }
    public void carregarMenuPrincipal(){
        ControladorPrincipal.getInstance().carregarMenuPrincipal();
    }    

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }
    

    public static ControladorVeiculos getInstance() {
        if(instance == null){
            instance = new ControladorVeiculos();
        }
        return instance;
    }
    
}