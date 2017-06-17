package br.ufsc.ine5605.claviculario.controladores;

import br.ufsc.ine5605.claviculario.entidades.Registro;
import br.ufsc.ine5605.claviculario.entidades.RegistroDevolucao;
import br.ufsc.ine5605.claviculario.enums.MensagemAcessoNegacao;
import br.ufsc.ine5605.claviculario.entidades.RegistroRetirada;
import br.ufsc.ine5605.claviculario.zzantigos.telas.TelaRegistros;
import java.util.ArrayList;
import java.util.Date;

public class ControladorRegistros{
    
    private static ControladorRegistros instance;
    private final ArrayList<Registro> registrosUso;
    private final TelaRegistros telaRegistros;
    
    private ControladorRegistros(){
        registrosUso = new ArrayList<>();
        telaRegistros = new TelaRegistros();
    }
        
    public void registrarRetirada(int matricula, String placa, Date data, boolean statusAcesso, MensagemAcessoNegacao motivoNegacaoAcesso){
        RegistroRetirada registroRetirada = new RegistroRetirada(matricula, placa, data, statusAcesso, motivoNegacaoAcesso);
        registrosUso.add(registroRetirada);
    }
    
    public void registrarDevolucao(int matricula, String placa, Date data) {
        RegistroDevolucao registroDevolucao = new RegistroDevolucao(matricula, placa, data);
        registrosUso.add(registroDevolucao);
    }
    
    public ArrayList<Registro> pesquisarRegistros(int matricula){
        ArrayList<Registro> registrosRetorno = new ArrayList<>();
        if(ControladorPrincipal.getInstance().validarMatricula(matricula)) {
            for(Registro registro : registrosUso) {
                if(registro != null && registro.getMatricula() == matricula) {
                    registrosRetorno.add(registro);
                }
            }
        }
        return registrosRetorno;
    }
    
    public ArrayList<Registro> pesquisarRegistros(String placa){
        ArrayList<Registro> registrosRetorno = new ArrayList<>();
        if(ControladorPrincipal.getInstance().validarPlaca(placa)) {
            for(Registro registro : registrosUso) {
                if(registro != null && registro.getPlaca().equals(placa)) {
                    registrosRetorno.add(registro);
                }
            }
        }
        return registrosRetorno;
    }
    
    public ArrayList<RegistroRetirada> pesquisarRegistrosRetirada(boolean acessoPermitido, MensagemAcessoNegacao motivoNegacao) {
        ArrayList<RegistroRetirada> registrosRetorno = new ArrayList<>();
        for(Registro registro : registrosUso) {
            if(registro.getClass().equals(RegistroRetirada.class)) {
                RegistroRetirada registroRetirada = (RegistroRetirada) registro;
                if(registroRetirada.isRetiradaPermitida() == acessoPermitido && registroRetirada.getMotivoAcessoNegacao().equals(motivoNegacao)) {
                    registrosRetorno.add(registroRetirada);
                }
            }
        }
        return registrosRetorno;
    }
    
    public ArrayList<Registro> getListaRegistros() {
        return this.registrosUso;
    }
    
    public void carregaMenuRegistros(){
        telaRegistros.exibirMenu();
    }
    public static ControladorRegistros getInstance() {
        if(instance == null){
            instance = new ControladorRegistros();
        }
        return instance;
    }
    
}