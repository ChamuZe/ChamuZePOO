package br.pucpr.chamuzejava.model;

import java.io.Serializable;

public class Proposta implements Serializable  {
    private static final long serialVersionUID = 1L;
    private double preco;
    private String justificativa;
    private String servico;
    private String prestador;
    private String solicitante;
//    private int id_solicitante;
//    private int id_servico;
//    private int id_prestador;

//    public Proposta(double preco, int id_solicitante, int id_servico, int id_prestador) {
//        this.preco = preco;
//        this.id_solicitante = id_solicitante;
//        this.id_servico = id_servico;
//        this.id_prestador = id_prestador;
//    }


    public Proposta(double preco, String justificativa, String servico, String prestador, String solicitante) {
        this.preco = preco;
        this.justificativa = justificativa;
        this.servico = servico;
        this.prestador = prestador;
        this.solicitante = solicitante;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    };

    public double getPreco() {return preco;}

    public void setPreco(double preco) {this.preco = preco;}

    public String getJustificativa() {return justificativa;}

    public void setJustificativa(String justificativa) {this.justificativa = justificativa;}

    public String getServico() {return servico;}

    public void setServico(String servico) {this.servico = servico;}

    public String getPrestador() {return prestador;}

    public void setPrestador(String prestador) {this.prestador = prestador;}

    public String getSolicitante() {return solicitante;}

    public void setSolicitante(String solicitante) {this.solicitante = solicitante;}

    //    public int getId_solicitante() {return id_solicitante;}

//    public void setId_solicitante(int id_solicitante) {this.id_solicitante = id_solicitante;}
//
//    public int getId_servico() {return id_servico;}
//
//    public void setId_servico(int id_servico) {this.id_servico = id_servico;}
//
//    public int getId_prestador() {return id_prestador;}
//
//    public void setId_prestador(int id_prestador) {this.id_prestador = id_prestador;}


}
