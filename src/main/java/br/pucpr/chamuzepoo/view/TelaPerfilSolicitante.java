package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaPerfilSolicitante {
    public static Scene criarTela() {
        GridPane telaPerfilSolicitante = new GridPane();

        telaPerfilSolicitante.setPadding(new Insets(40));
        telaPerfilSolicitante.setHgap(10);
        telaPerfilSolicitante.setVgap(15);
        telaPerfilSolicitante.setAlignment(Pos.CENTER);
        telaPerfilSolicitante.setStyle("-fx-background-color: #f2f2f2;");

        // Card container
        VBox card = new VBox(15);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 4);"
        );
        card.setPrefWidth(500);

        // Título
        Label titulo = new Label("Perfil do Solicitante");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333;");
        card.getChildren().add(titulo);

        // Campos
        Label labelId = new Label("ID: " + ControllerUsuario.usuarioLogado.getId());
        labelId.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelId);

        Label labelNome = new Label("Nome: " + ControllerUsuario.usuarioLogado.getNome());
        labelNome.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelNome);

        Label labelSobreNome = new Label("Sobrenome: " + ControllerUsuario.usuarioLogado.getSobreNome());
        labelSobreNome.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelSobreNome);

        Label labelEmail = new Label("Email: " + ControllerUsuario.usuarioLogado.getEmail());
        labelEmail.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelEmail);

        Label labelSenha = new Label("Senha: " + ControllerUsuario.usuarioLogado.getSenha());
        labelSenha.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelSenha);

        Label labelTelefone = new Label("Telefone: " + ControllerUsuario.usuarioLogado.getTelefone());
        labelTelefone.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelTelefone);

        //Formatando data antes de mostrar
        LocalDate dataNascimentoBruta = ControllerUsuario.usuarioLogado.getDataNascimento();
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNascimentoFormatadaBR = dataNascimentoBruta.format(formatoBR);
        Label labelDataNascimento = new Label("Data de Nascimento: " + dataNascimentoFormatadaBR);
        labelDataNascimento.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelDataNascimento);

        Label labelGenero = new Label("Gênero: " + ControllerUsuario.usuarioLogado.getGenero());
        labelGenero.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelGenero);

        // Botões
        HBox botoes = new HBox(15);
        botoes.setAlignment(Pos.CENTER);

        Button botaoEditarSolicitante = new Button("Editar dados");
        botaoEditarSolicitante.setPrefWidth(150);
        botaoEditarSolicitante.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size:16px;");
        botaoEditarSolicitante.setOnAction(e -> System.out.println("Vai editar Usuário"));

        Button botaoExcluirSolicitante = new Button("Excluir conta");
        botaoExcluirSolicitante.setPrefWidth(150);
        botaoExcluirSolicitante.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size:16px;");
        botaoExcluirSolicitante.setOnAction(e -> {
            Alert confirmacaoExcluirConta = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacaoExcluirConta.setTitle("Confirmação de exclusão");
            confirmacaoExcluirConta.setHeaderText(null);

            confirmacaoExcluirConta.setContentText("Tem certeza que deseja excluir sua conta?");
            // Exibe o alert e espera o usuário responder (Ok ou Cancelar)
            confirmacaoExcluirConta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ControllerUsuario controllerUsuario= new ControllerUsuario();
                    Boolean excluiu = controllerUsuario.excluirUsuario();

                    if (excluiu) {
                        Alert usuarioExcluido = new Alert(Alert.AlertType.INFORMATION);
                        usuarioExcluido.setTitle("Usuário Excluído");
                        usuarioExcluido.setHeaderText(null);
                        usuarioExcluido.setContentText("Sua conta foi excluída com sucesso!");
                        usuarioExcluido.showAndWait();
                        Main.mudarCena(TelaLogin.criarTela());
                    } else {
                        Alert usuarioNaoExcluido = new Alert(Alert.AlertType.ERROR);
                        usuarioNaoExcluido.setTitle("Erro");
                        usuarioNaoExcluido.setHeaderText(null);
                        usuarioNaoExcluido.setContentText("Não foi possível excluir sua conta.");
                        usuarioNaoExcluido.showAndWait();
                    }

                    System.out.println("Usuário confirmou a exclusão");
                } else {
                    System.out.println("Usuário cancelou a exclusão");
                }
            });
        });

        botoes.getChildren().addAll(botaoEditarSolicitante, botaoExcluirSolicitante);
        card.getChildren().add(botoes);

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(200);
        botaoVoltar.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size:12px; -fx-font-weight:bold;");
        botaoVoltar.setOnAction(e -> Main.mudarCena(TelaSolicitanteInicial.criarTela()));
        card.getChildren().add(botaoVoltar);

        telaPerfilSolicitante.add(card, 0, 0);
        GridPane.setHalignment(card, HPos.CENTER);

        return new Scene(telaPerfilSolicitante, 1100, 700);
    }
}
