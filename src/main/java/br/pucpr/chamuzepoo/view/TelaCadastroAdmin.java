package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Admin;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class TelaCadastroAdmin {

    public static Scene criarTela() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Título
        Label titulo = new Label("Cadastro de Administrador");
        titulo.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        grid.add(titulo, 0, 0, 2, 1);

        // Campos do formulário
        TextField txtNome = new TextField();
        TextField txtSobrenome = new TextField();
        TextField txtEmail = new TextField();
        PasswordField txtSenha = new PasswordField();
        TextField txtCpf = new TextField();
        TextField txtTelefone = new TextField();
        DatePicker dateDataNascimento = new DatePicker();
        TextField txtGenero = new TextField();

        // Adicionando campos ao grid
        grid.add(new Label("Nome:"), 0, 1);
        grid.add(txtNome, 1, 1);
        grid.add(new Label("Sobrenome:"), 0, 2);
        grid.add(txtSobrenome, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(txtEmail, 1, 3);
        grid.add(new Label("Senha:"), 0, 4);
        grid.add(txtSenha, 1, 4);
        grid.add(new Label("CPF:"), 0, 5);
        grid.add(txtCpf, 1, 5);
        grid.add(new Label("Telefone:"), 0, 6);
        grid.add(txtTelefone, 1, 6);
        grid.add(new Label("Data Nascimento:"), 0, 7);
        grid.add(dateDataNascimento, 1, 7);
        grid.add(new Label("Gênero:"), 0, 8);
        grid.add(txtGenero, 1, 8);

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setDefaultButton(true);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> Main.mudarCena(TelaPerfilADM.criarTela()));

        HBox botoes = new HBox(10, btnVoltar, btnCadastrar);
        grid.add(botoes, 1, 9);

        btnCadastrar.setOnAction(e -> {
            try {
                LocalDate dataNasc = dateDataNascimento.getValue() != null ?
                        dateDataNascimento.getValue() : LocalDate.now();

                Admin novoAdmin = new Admin(
                        txtNome.getText(),
                        txtSobrenome.getText(),
                        txtEmail.getText(),
                        txtSenha.getText(),
                        txtCpf.getText(),
                        txtTelefone.getText(),
                        dataNasc,
                        txtGenero.getText()
                );

                ControllerUsuario controller = new ControllerUsuario();
                if (controller.cadastrarAdmin(novoAdmin)) {
                    new Alert(Alert.AlertType.INFORMATION, "Administrador cadastrado com sucesso!").showAndWait();
                    Main.mudarCena(TelaPerfilADM.criarTela());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Apenas administradores podem cadastrar novos ADMs!").showAndWait();
                }
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao cadastrar: " + ex.getMessage()).showAndWait();
            }
        });

        return new Scene(grid, 600, 500);
    }
}