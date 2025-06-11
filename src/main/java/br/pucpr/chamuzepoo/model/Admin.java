package br.pucpr.chamuzepoo.model;

import java.time.LocalDate;

public class Admin extends Usuario {

    public Admin(String nome, String sobreNome, String email, String senha,
                 String cpf, String telefone, LocalDate dataNascimento, String genero) {
        super(nome, sobreNome, email, senha, cpf, telefone, dataNascimento, genero);
    }
}