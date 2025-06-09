package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Solicitante;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;

public class TelaCadastroSolicitante {

    public static Scene criarTela() {
        GridPane telaCadastroSolicitante = new GridPane();
        telaCadastroSolicitante.setAlignment(Pos.CENTER);

        // Título da tela
        Label titulotelaCadastroSolicitante = new Label("Cadastro Solicitante");
        telaCadastroSolicitante.add(titulotelaCadastroSolicitante, 1, 1);
        titulotelaCadastroSolicitante.setStyle("-fx-font-size:26px; -fx-font-weight:bold;");


        // Nome
        Label labelEntradaNome = new Label("Nome: ");
        TextField entradaNome = new TextField();
        telaCadastroSolicitante.add(labelEntradaNome, 1, 2);
        telaCadastroSolicitante.add(entradaNome, 1, 3);

        // Sobrenome
        Label labelEntradaSobrenome = new Label("Sobrenome: ");
        TextField entradaSobrenome = new TextField();
        telaCadastroSolicitante.add(labelEntradaSobrenome, 1, 4);
        telaCadastroSolicitante.add(entradaSobrenome, 1, 5);

        // E-mail
        Label labelEntradaEmail = new Label("E-mail: ");
        TextField entradaEmail = new TextField();
        telaCadastroSolicitante.add(labelEntradaEmail, 1, 6);
        telaCadastroSolicitante.add(entradaEmail, 1, 7);

        // Senha
        Label labelEntradaSenha = new Label("Senha: ");
        PasswordField entradaSenha = new PasswordField();
        telaCadastroSolicitante.add(labelEntradaSenha, 1, 8);
        telaCadastroSolicitante.add(entradaSenha, 1, 9);

        // CPF
        Label labelEntradaCPF = new Label("CPF: ");
        TextField entradaCPF = new TextField();
        telaCadastroSolicitante.add(labelEntradaCPF, 1, 10);
        telaCadastroSolicitante.add(entradaCPF, 1, 11);

        // Telefone
        Label labelEntradaTelefone = new Label("Telefone: ");
        TextField entradaTelefone = new TextField();
        telaCadastroSolicitante.add(labelEntradaTelefone, 1, 12);
        telaCadastroSolicitante.add(entradaTelefone, 1, 13);

        // Data de nascimento
        Label labelentradaDataNascimento = new Label("Data de nascimento: ");
        DatePicker entradaDataNascimento = new DatePicker();
        telaCadastroSolicitante.add(labelentradaDataNascimento, 1, 14);
        telaCadastroSolicitante.add(entradaDataNascimento, 1, 15);

        // Gênero
        Label labelGenero = new Label("Gênero: ");
        RadioButton escolhaGeneroM = new RadioButton("M");
        RadioButton escolhaGeneroF = new RadioButton("F");
        RadioButton escolhaGeneroO = new RadioButton("O");

        ToggleGroup grupoGenero = new ToggleGroup();
        escolhaGeneroM.setToggleGroup(grupoGenero);
        escolhaGeneroF.setToggleGroup(grupoGenero);
        escolhaGeneroO.setToggleGroup(grupoGenero);

        telaCadastroSolicitante.add(labelGenero, 1, 16);
        telaCadastroSolicitante.add(escolhaGeneroM, 1, 17);
        telaCadastroSolicitante.add(escolhaGeneroF, 1, 18);
        telaCadastroSolicitante.add(escolhaGeneroO, 1, 19);

        // Botão Cadastrar
        Button botaoCadastrar = new Button("Cadastrar");
        telaCadastroSolicitante.add(botaoCadastrar, 1, 20);
        botaoCadastrar.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");


        botaoCadastrar.setOnAction(evento -> {
            // Pegando os dados do input
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

            Solicitante solicitante = new Solicitante(
                    nome, sobrenome, email, senha, cpf, telefone, dataNascimento, genero
            );

            ControllerUsuario controllerUsuario = new ControllerUsuario(solicitante);
            controllerUsuario.cadastrarUsuario();
            System.out.println("Solicitante cadastrado com Sucesso!");
            Main.mudarCena(TelaLogin.criarTela());
        });

        // Botão Voltar
        Button botaoVoltarParaLogin = new Button("Voltar");
        telaCadastroSolicitante.add(botaoVoltarParaLogin, 1, 21);
        botaoVoltarParaLogin.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");


        botaoVoltarParaLogin.setOnAction(evento -> {
            Main.mudarCena(TelaEscolhaCadastro.criarTela());
        });

        return new Scene(telaCadastroSolicitante, 1100, 700);
    }
}
