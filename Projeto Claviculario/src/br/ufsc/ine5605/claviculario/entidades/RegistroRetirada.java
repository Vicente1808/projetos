package br.ufsc.ine5605.claviculario.entidades;

import br.ufsc.ine5605.claviculario.enums.MensagemAcessoNegacao;
import java.util.Date;

public class RegistroRetirada extends Registro {
    
    private boolean retiradaPermitida;
    private MensagemAcessoNegacao motivoAcessoNegacao;

    public RegistroRetirada(int matricula, String placa, Date data, boolean retiradaPermitida, MensagemAcessoNegacao motivoAcessoNegacao) {
        super(matricula, placa, data);
        tipo = "retirada";
        this.retiradaPermitida = retiradaPermitida;
        this.motivoAcessoNegacao = motivoAcessoNegacao;
    }

    public boolean isRetiradaPermitida() {
        return retiradaPermitida;
    }

    @Override
    public MensagemAcessoNegacao getMotivoAcessoNegacao() {
        return motivoAcessoNegacao;
    }


}