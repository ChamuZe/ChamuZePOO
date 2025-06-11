package br.pucpr.chamuzepoo.model;

import java.time.LocalDate;

public class Prestador extends Usuario{
    private String cnpj;
    private String chavePix;

    public Prestador(){
        super();
        System.out.println("Def - Prestador cadastrado! \nID: " + Usuario.getSerialVersionUID() + "Nome: " + super.getNome() + " Email: " + super.getEmail());

    }

    public Prestador(String nome, String sobreNome, String email, String senha, String cpf, String telefone, LocalDate dataNascimento, String genero, String cnpj, String chavePix) {
        super(nome, sobreNome, email, senha, cpf, telefone, dataNascimento, genero);
        this.cnpj = cnpj;
        this.chavePix = chavePix;
        System.out.println("Prestador cadastrado! \nID: " + Usuario.getSerialVersionUID() + "Nome: " + super.getNome() + " Email: " + super.getEmail());

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}
