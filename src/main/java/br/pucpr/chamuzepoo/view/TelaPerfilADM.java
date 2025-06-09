package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Admin;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TelaPerfilADM {
    public static Scene criarTela() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(15);

        // Título
        Label titulo = new Label("Painel do Administrador");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(titulo, 0, 0, 2, 1);

        // Seção do ADM logado
        Admin admLogado = (Admin) ControllerUsuario.usuarioLogado;
        grid.add(criarCardPerfil(admLogado, true), 0, 1, 2, 1);

        // Seção de todos ADMs cadastrados
        Label subTituloAdmins = new Label("ADMINISTRADORES CADASTRADOS");
        subTituloAdmins.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(subTituloAdmins, 0, 2);

        // Lista de ADMs com ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        VBox listaAdminsBox = new VBox(10);

        for (Admin admin : ControllerUsuario.listarAdmins()) {
            // Não mostra o ADM logado na lista
            if (!admin.getEmail().equals(admLogado.getEmail())) {
                listaAdminsBox.getChildren().add(criarCardPerfil(admin, false));
            }
        }

        scrollPane.setContent(listaAdminsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200);
        grid.add(scrollPane, 0, 3, 2, 1);

        // Botão de cadastro
        Button btnNovoAdmin = new Button("Cadastrar Novo ADM");
        btnNovoAdmin.setOnAction(e -> Main.mudarCena(TelaCadastroAdmin.criarTela()));
        grid.add(btnNovoAdmin, 0, 4);

        return new Scene(grid, 800, 600);
    }

    private static VBox criarCardPerfil(Admin admin, boolean isPerfilLogado) {
        VBox card = new VBox(8);
        card.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-padding: 10;");

        GridPane dados = new GridPane();
        dados.setHgap(10);
        dados.setVgap(5);

        dados.add(new Label("Nome:"), 0, 0);
        dados.add(new Label(admin.getNome() + " " + admin.getSobreNome()), 1, 0);

        dados.add(new Label("Email:"), 0, 1);
        dados.add(new Label(admin.getEmail()), 1, 1);

        dados.add(new Label("CPF:"), 0, 2);
        dados.add(new Label(admin.getCpf()), 1, 2);

        dados.add(new Label("Telefone:"), 0, 3);
        dados.add(new Label(admin.getTelefone()), 1, 3);

        dados.add(new Label("Gênero:"), 0, 4);
        dados.add(new Label(admin.getGenero()), 1, 4);

        card.getChildren().add(dados);

        if (!isPerfilLogado) {
            Button btnExcluir = new Button("Excluir");
            btnExcluir.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");

            btnExcluir.setOnAction(e -> {
                // Confirmação antes de excluir
                Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmação");
                confirmacao.setHeaderText("Excluir Administrador");
                confirmacao.setContentText("Tem certeza que deseja excluir " + admin.getNome() + "?");

                if (confirmacao.showAndWait().get() == ButtonType.OK) {
                    ControllerUsuario controller = new ControllerUsuario();
                    if (controller.excluirAdmin(admin)) {
                        // Atualiza a tela após exclusão
                        Main.mudarCena(TelaPerfilADM.criarTela());
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Não foi possível excluir o administrador.").show();
                    }
                }
            });

            HBox botoesBox = new HBox(10);
            botoesBox.getChildren().add(btnExcluir);
            card.getChildren().add(botoesBox);
        }

        return card;
    }
}