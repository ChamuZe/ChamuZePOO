package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.model.Admin;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Usuario;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TelaEdicaoAdmin {
    private Usuario admin;

    public TelaEdicaoAdmin() {
        this.admin = ControllerUsuario.usuarioLogado;
    }

    public static Scene criarTela() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setVgap(20);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setStyle("-fx-background-color: #f8f9fa;");

        // Título principal
        Label titulo = new Label("Alterando Dados");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        grid.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);

        // Campos para editar dados do Admin
        TextField nomeField = new TextField(ControllerUsuario.usuarioLogado.getNome());
        nomeField.setStyle("-fx-font-size: 14px;");

        TextField emailField = new TextField(ControllerUsuario.usuarioLogado.getEmail());
        emailField.setStyle("-fx-font-size: 14px;");

        TextField cpfField = new TextField(ControllerUsuario.usuarioLogado.getCpf());
        cpfField.setStyle("-fx-font-size: 14px;");

        TextField telefoneField = new TextField(ControllerUsuario.usuarioLogado.getTelefone());
        telefoneField.setStyle("-fx-font-size: 14px;");

        // Botão de Salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
        btnSalvar.setPrefWidth(300);
        btnSalvar.setOnAction(event -> {
            // Atualizar dados do admin
            ControllerUsuario.usuarioLogado.setNome(nomeField.getText());
            ControllerUsuario.usuarioLogado.setEmail(emailField.getText());
            ControllerUsuario.usuarioLogado.setCpf(cpfField.getText());
            ControllerUsuario.usuarioLogado.setTelefone(telefoneField.getText());

            // Controller para atualizar admin
            ControllerUsuario controller = new ControllerUsuario();
            Admin usuarioadmin = (Admin) ControllerUsuario.usuarioLogado;
            boolean sucesso = controller.atualizarAdmin(usuarioadmin);

            if (sucesso) {
                Alert sucessoAlert = new Alert(Alert.AlertType.INFORMATION);
                sucessoAlert.setContentText("Dados atualizados com sucesso!");
                sucessoAlert.show();
                Main.mudarCena(TelaPerfilADM.criarTela());
            } else {
                Alert erroAlert = new Alert(Alert.AlertType.ERROR);
                erroAlert.setContentText("Erro ao atualizar os dados.");
                erroAlert.show();
            }
        });

        // Botão de Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        btnVoltar.setPrefWidth(300);
        btnVoltar.setOnAction(event -> {
            Main.mudarCena(TelaPerfilADM.criarTela());
        });

        // Adicionando os campos no layout
        grid.add(new Label("Nome:"), 0, 1);
        grid.add(nomeField, 1, 1);

        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);

        grid.add(new Label("CPF:"), 0, 3);
        grid.add(cpfField, 1, 3);

        grid.add(new Label("Telefone:"), 0, 4);
        grid.add(telefoneField, 1, 4);

        // Adicionando os botões
        HBox botoesBox = new HBox(10, btnSalvar, btnVoltar);
        botoesBox.setAlignment(Pos.CENTER);
        grid.add(botoesBox, 0, 5, 2, 1);

        return new Scene(grid, 800, 600);
    }
}
