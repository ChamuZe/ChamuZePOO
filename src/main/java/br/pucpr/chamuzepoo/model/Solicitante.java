package br.pucpr.chamuzejava.model;

import java.time.LocalDate;

public class Solicitante extends Usuario{

    public Solicitante(){
        super();
        System.out.println("Def - Solicitante cadastrado! \nID: " + Usuario.getSerialVersionUID() + "Nome: " + super.getNome() + " Email: " + super.getEmail());
    }

    public Solicitante(String nome, String sobreNome, String email, String senha, String cpf, String telefone, LocalDate dataNascimento, String genero) {
        super(nome, sobreNome, email, senha, cpf, telefone, dataNascimento, genero);
        System.out.println("Solicitante cadastrado! \nID: " + Usuario.getSerialVersionUID() + "Nome: " + super.getNome() + " Email: " + super.getEmail());

    }
}
