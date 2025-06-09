package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Prestador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class TelaCadastroPrestador {
    public static Scene criarTela(){
        //Tela cadastro
        GridPane telaCadastroPrestador = new GridPane();
        telaCadastroPrestador.setAlignment(Pos.CENTER);

        //Título da tela de cadastro
        Label titulotelaCadastroPrestador = new Label("Cadastro Prestador");
        telaCadastroPrestador.add(titulotelaCadastroPrestador, 1, 0);
        titulotelaCadastroPrestador.setStyle("-fx-font-size:26px; -fx-font-weight:bold;");
        //Input de Nome
        Label labelEntradaNome = new Label("Nome: ");
        telaCadastroPrestador.add(labelEntradaNome, 1, 2);
        TextField entradaNome = new TextField();
        telaCadastroPrestador.add(entradaNome, 1, 3);

        //Input de Sobrenome
        Label labelEntradaSobrenome = new Label("Sobrenome: ");
        telaCadastroPrestador.add(labelEntradaSobrenome, 1, 4);
        TextField entradaSobrenome = new TextField();
        telaCadastroPrestador.add(entradaSobrenome, 1, 5);

        //Input de E-mail
        Label labelEntradaEmail = new Label("E-mail: ");
        telaCadastroPrestador.add(labelEntradaEmail, 1, 6);
        TextField entradaEmail = new TextField();
        telaCadastroPrestador.add(entradaEmail, 1, 7);

        //Input de Senha
        Label labelEntradaSenha = new Label("Senha: ");
        telaCadastroPrestador.add(labelEntradaSenha, 1, 8);
        PasswordField entradaSenha = new PasswordField();
        telaCadastroPrestador.add(entradaSenha, 1, 9);

        //Input de CPF
        Label labelEntradaCPF = new Label("CPF: ");
        telaCadastroPrestador.add(labelEntradaCPF, 1, 10);
        TextField entradaCPF = new TextField();
        telaCadastroPrestador.add(entradaCPF, 1, 11);

        //Input de Telefone
        Label labelEntradaTelefone = new Label("Telefone: ");
        telaCadastroPrestador.add(labelEntradaTelefone, 1, 12);
        TextField entradaTelefone = new TextField();
        telaCadastroPrestador.add(entradaTelefone, 1, 13);

        //Input de DataDeNascimento
        Label labelentradaDataNascimento = new Label("Data de nascimento: ");
        telaCadastroPrestador.add(labelentradaDataNascimento, 1, 14);
        DatePicker entradaDataNascimento = new DatePicker();
        telaCadastroPrestador.add(entradaDataNascimento, 1,15);

        //Input de Gênero
        Label labelGenero = new Label("Gênero: ");
        telaCadastroPrestador.add(labelGenero, 1, 16);

        RadioButton escolhaGeneroM = new RadioButton("M");
        RadioButton escolhaGeneroF = new RadioButton("F");
        RadioButton escolhaGeneroO = new RadioButton("O");

        ToggleGroup grupoGenero = new ToggleGroup();

        escolhaGeneroM.setToggleGroup(grupoGenero);
        escolhaGeneroF.setToggleGroup(grupoGenero);
        escolhaGeneroO.setToggleGroup(grupoGenero);

        telaCadastroPrestador.add(escolhaGeneroM, 1, 17);
        telaCadastroPrestador.add(escolhaGeneroF, 1,18);
        telaCadastroPrestador.add(escolhaGeneroO, 1, 19);

        //Input de CNPJ
        Label labelEntradaCNPJ = new Label("CNPJ: ");
        telaCadastroPrestador.add(labelEntradaCNPJ, 1, 20);
        TextField entradaCNPJ = new TextField();
        telaCadastroPrestador.add(entradaCNPJ, 1, 21);

        //Input de ChavePix
        Label labelEntradaChavePix = new Label("Chave Pix: ");
        telaCadastroPrestador.add(labelEntradaChavePix, 1, 22);
        TextField entradaChavePix = new TextField();
        telaCadastroPrestador.add(entradaChavePix, 1, 23);

        //Botão para cadastrar
        Button botaoCadastrar = new Button("Cadastrar");
        telaCadastroPrestador.add(botaoCadastrar,1 ,24 );
        botaoCadastrar.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
        //Cadastra o usuário
        botaoCadastrar.setOnAction(evento->{
            //Pegando os dados do input
            String nome = entradaNome.getText();
            String sobrenome = entradaSobrenome.getText();
            String email = entradaEmail.getText();
            String senha = entradaSenha.getText();
            String cpf = entradaCPF.getText();
            String telefone = entradaTelefone.getText();
            LocalDate dataNascimento = entradaDataNascimento.getValue();
            Toggle selecionado = grupoGenero.getSelectedToggle();
            RadioButton opcaoSelecionada = (RadioButton) selecionado;
            String genero = opcaoSelecionada.getText();
            String cnpj = entradaCNPJ.getText();
            String chavePix = entradaChavePix.getText();


            Prestador prestador = new Prestador(nome, sobrenome, email,  senha,  cpf,  telefone,  dataNascimento,  genero
            ,cnpj, chavePix);
            ControllerUsuario controllerUsuario = new ControllerUsuario(prestador);
            controllerUsuario.cadastrarUsuario();
            System.out.println("Prestador cadastrado com Sucesso!");
            Main.mudarCena(TelaLogin.criarTela());

        });

        //Botão voltar para Login
        Button botaoVoltarParaLogin = new Button("Voltar");
        telaCadastroPrestador.add(botaoVoltarParaLogin, 1, 25);
        botaoVoltarParaLogin.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        //Lógica para voltar para tela de login
        botaoVoltarParaLogin.setOnAction(evento->{
            Main.mudarCena(TelaEscolhaCadastro.criarTela());
        });

       return new Scene(telaCadastroPrestador,1100, 700);
    }
}
