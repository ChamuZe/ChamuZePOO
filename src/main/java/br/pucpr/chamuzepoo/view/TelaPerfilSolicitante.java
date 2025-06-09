package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TelaPerfilSolicitante {
    public static Scene criarTela(){
        GridPane telaPerfilSolicitante = new GridPane();

        telaPerfilSolicitante.setPadding(new Insets(40));
        telaPerfilSolicitante.setHgap(10);
        telaPerfilSolicitante.setVgap(15);
        telaPerfilSolicitante.setAlignment(Pos.CENTER);

        // Título
        Label titulo = new Label("Perfil do Solicitante");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        telaPerfilSolicitante.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);

        // Campos
        Label labelId = new Label("ID: " + ControllerUsuario.usuarioLogado.getId());
        labelId.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelId, 0, 12);

        Label labelNome = new Label("Nome: " + ControllerUsuario.usuarioLogado.getNome());
        labelNome.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelNome, 0, 1);

        Label labelSobreNome = new Label("Sobrenome: " + ControllerUsuario.usuarioLogado.getSobreNome());
        labelSobreNome.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelSobreNome, 0, 2);

        Label labelEmail = new Label("Email: " + ControllerUsuario.usuarioLogado.getEmail());
        labelEmail.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelEmail, 0, 3);

        Label labelSenha = new Label("Senha: " + ControllerUsuario.usuarioLogado.getSenha());
        labelSenha.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelSenha, 0, 4);

        Label labelCpf = new Label("CPF: " + ControllerUsuario.usuarioLogado.getCpf());
        labelCpf.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelCpf, 0, 5);

        Label labelTelefone = new Label("Telefone: " + ControllerUsuario.usuarioLogado.getTelefone());
        labelTelefone.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelTelefone, 0, 6);

        Label labelDataNascimento = new Label("Data de Nascimento: " + ControllerUsuario.usuarioLogado.getDataNascimento());
        labelDataNascimento.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelDataNascimento, 0, 7);

        Label labelGenero = new Label("Gênero: " + ControllerUsuario.usuarioLogado.getGenero());
        labelGenero.setStyle("-fx-font-size: 16px;");
        telaPerfilSolicitante.add(labelGenero, 0, 8);

        // Botão Editar
        Button botaoEditarSolicitante = new Button("Editar");
        botaoEditarSolicitante.setPrefWidth(150);
        botaoEditarSolicitante.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");
        telaPerfilSolicitante.add(botaoEditarSolicitante, 0, 10);

        botaoEditarSolicitante.setOnAction(e -> {
            System.out.println("Vai editar Usuário");
        });

        // Botão Excluir
        Button botaoExcluirSolicitante = new Button("Excluir");
        botaoExcluirSolicitante.setPrefWidth(150);
        botaoExcluirSolicitante.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        telaPerfilSolicitante.add(botaoExcluirSolicitante, 1, 10);

        botaoExcluirSolicitante.setOnAction(e -> {
            System.out.println("Vai excluir Usuário");
        });

        // Botão Voltar
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(150);
        botaoVoltar.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        telaPerfilSolicitante.add(botaoVoltar, 0, 11, 2, 1);
        GridPane.setHalignment(botaoVoltar, HPos.CENTER);

        botaoVoltar.setOnAction(e -> {
            Main.mudarCena(TelaSolicitanteInicial.criarTela());
        });

        return new Scene(telaPerfilSolicitante, 1100, 700);
    }
}
