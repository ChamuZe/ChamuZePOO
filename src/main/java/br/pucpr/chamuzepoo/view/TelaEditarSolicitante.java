package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TelaEditarSolicitante {
    public static Scene criarTela() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setVgap(20);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setStyle("-fx-background-color: #f8f9fa;");

        // Título principal
        Label titulo = new Label("Editar dados do solicitante");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        grid.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);

        // Campos para editar dados dos usuáriosa
        TextField nomeField = new TextField(ControllerUsuario.usuarioLogado.getNome());
        nomeField.setStyle("-fx-font-size: 14px;");

        TextField sobrenomeField = new TextField(ControllerUsuario.usuarioLogado.getSobreNome());
        sobrenomeField.setStyle("-fx-font-size: 14px;");

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
            ControllerUsuario.usuarioLogado.setSobreNome(sobrenomeField.getText());
            ControllerUsuario.usuarioLogado.setEmail(emailField.getText());
            ControllerUsuario.usuarioLogado.setCpf(cpfField.getText());
            ControllerUsuario.usuarioLogado.setTelefone(telefoneField.getText());

            // Controller para atualizar usuários
            ControllerUsuario controller = new ControllerUsuario();
            boolean sucesso = controller.atualizarUsuario(ControllerUsuario.usuarioLogado);

            if (sucesso) {
                Alert sucessoAlert = new Alert(Alert.AlertType.INFORMATION);
                sucessoAlert.setContentText("Dados atualizados com sucesso!");
                sucessoAlert.show();
                Main.mudarCena(TelaPerfilSolicitante.criarTela());
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
            Main.mudarCena(TelaPerfilSolicitante.criarTela());
        });

        // Adicionando os campos no layout
        grid.add(new Label("Nome:"), 0, 1);
        grid.add(nomeField, 1, 1);

        grid.add(new Label("Sobrenome:"), 0, 2);
        grid.add(sobrenomeField, 1, 2);

        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);

        grid.add(new Label("CPF:"), 0, 4);
        grid.add(cpfField, 1, 4);

        grid.add(new Label("Telefone:"), 0, 5);
        grid.add(telefoneField, 1, 5);

        // Adicionando os botões
        HBox botoesBox = new HBox(10, btnSalvar, btnVoltar);
        botoesBox.setAlignment(Pos.CENTER);
        grid.add(botoesBox, 0, 6, 2, 1);

        return new Scene(grid, 1100, 700);
    }
}
