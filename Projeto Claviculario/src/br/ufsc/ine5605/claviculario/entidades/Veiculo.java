package br.ufsc.ine5605.claviculario.entidades;

import java.io.Serializable;



public class Veiculo implements Serializable{
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private int kmAtual;
    private boolean chaveClaviculario;

    //Contrutores

    public Veiculo(String placa, String modelo, String marca, int ano, int kmAtual){
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.kmAtual = kmAtual;
        this.chaveClaviculario = true;
    }
    
    //Metodos Modificaores

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public boolean isChaveClaviculario() {
        return chaveClaviculario;
    }

    public void setChaveClaviculario(boolean chaveClaviculario) {
        this.chaveClaviculario = chaveClaviculario;
    }

    
}