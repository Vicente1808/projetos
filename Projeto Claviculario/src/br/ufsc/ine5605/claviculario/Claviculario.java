    
package br.ufsc.ine5605.claviculario;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.controladores.ControladorVeiculos;
import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import br.ufsc.ine5605.claviculario.entidades.Veiculo;
import br.ufsc.ine5605.claviculario.persistencia.MapeadorFuncionario;
import br.ufsc.ine5605.claviculario.valueObjects.FuncionarioVO;
import br.ufsc.ine5605.claviculario.valueObjects.VeiculoVO;
import java.util.Calendar;
/**
 *
 * @author Pablo Vicente
 */
public class Claviculario {
    
    //batata doce
    public static void main(String[] args) {
        
        FuncionarioVO funcionario = new FuncionarioVO();
        funcionario.matricula =595;
        funcionario.nome ="Pablo";
        funcionario.dataNascimento = Calendar.getInstance();
        funcionario.telefone ="48998288680";
        funcionario.cargo="Diretor";   
        
        ControladorFuncionarios.getInstance().cadastrarFuncionario(funcionario);
        
        Funcionario funcionario2 = new Funcionario(596,"Pedro", Calendar.getInstance(), "48998288680","assistente");   
        Funcionario funcionario3 = new Funcionario(597,"Vinicius", Calendar.getInstance(), "48998288680","diretor");   
         
        VeiculoVO veiculoVO = new VeiculoVO();
        veiculoVO.placa = "AAA-1234";
        veiculoVO.marca = "RAM";
        veiculoVO.modelo = "dodge";
        veiculoVO.ano = 1998;
        veiculoVO.kmAtual = 1234;
        ControladorVeiculos.getInstance().cadastrarVeiculo(veiculoVO);
        //Veiculo veiculo2 = new Veiculo("bbb-1234", "RAM", "DODGE", 2016, 1000);
        //Veiculo veiculo3 = new Veiculo("ccc-1234", "Gallardo", "Lamborguini", 2017, 0);
        //Veiculo veiculo4 = new Veiculo("ddd-1234", "MAVERICK", "FORD", 1969, 50000);
        
        //ControladorVeiculos.getInstance().getVeiculos().add(veiculo2);
        //ControladorVeiculos.getInstance().getVeiculos().add(veiculo3);
        //ControladorVeiculos.getInstance().getVeiculos().add(veiculo4);
        
        /*Calendar c = Calendar.getInstance();
        Date data = c.getTime();
		
	DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM); //Data COmpleta
	System.out.println("Data brasileira: "+f.format(data));
        
        Calendar teste = ControladorFuncionarios.getInstance().pedeDataNascimento();
        //teste 
        
        System.out.println("teste"+ f.format(teste));
        //System.out.println(data);
        */
        ControladorPrincipal ctrl = ControladorPrincipal.getInstance();
        ctrl.carregarTelaPrincipal();
    }
    
}
