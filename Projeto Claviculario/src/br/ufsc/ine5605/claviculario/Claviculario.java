    
package br.ufsc.ine5605.claviculario;

import br.ufsc.ine5605.claviculario.controladores.ControladorFuncionarios;
import br.ufsc.ine5605.claviculario.controladores.ControladorPrincipal;
import br.ufsc.ine5605.claviculario.controladores.ControladorVeiculos;
import br.ufsc.ine5605.claviculario.entidades.Funcionario;
import br.ufsc.ine5605.claviculario.entidades.Veiculo;
import br.ufsc.ine5605.claviculario.telasGraficas.TelaPrincipalGrafica;
import java.util.Calendar;
/**
 *
 * @author Pablo Vicente
 */
public class Claviculario {
    
    //batata doce
    public static void main(String[] args) {
        
        Funcionario funcionario = new Funcionario(595,"Pablo", Calendar.getInstance(), "48998288680","Diretor");   
        ControladorFuncionarios.getInstance().getFuncionarios().add(funcionario);
        Funcionario funcionario2 = new Funcionario(596,"Pedro", Calendar.getInstance(), "48998288680","assistente");   
        ControladorFuncionarios.getInstance().getFuncionarios().add(funcionario2);
        Funcionario funcionario3 = new Funcionario(597,"Vinicius", Calendar.getInstance(), "48998288680","diretor");   
        ControladorFuncionarios.getInstance().getFuncionarios().add(funcionario3);        
         
        Veiculo veiculo = new Veiculo("aaa-1234", "GTO", "Pontiac", 1969, 100000);
        Veiculo veiculo2 = new Veiculo("bbb-1234", "RAM", "DODGE", 2016, 1000);
        Veiculo veiculo3 = new Veiculo("ccc-1234", "Gallardo", "Lamborguini", 2017, 0);
        Veiculo veiculo4 = new Veiculo("ddd-1234", "MAVERICK", "FORD", 1969, 50000);
        
        ControladorVeiculos.getInstance().getVeiculos().add(veiculo);
        ControladorVeiculos.getInstance().getVeiculos().add(veiculo2);
        ControladorVeiculos.getInstance().getVeiculos().add(veiculo3);
        ControladorVeiculos.getInstance().getVeiculos().add(veiculo4);
        
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
        ctrl.carregarMenuPrincipal();
    }
    
}
