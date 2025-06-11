package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Admin;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class TelaCadastroAdmin {
    // Estilos consistentes com a TelaLogin
    private static final String ESTILO_TITULO = "-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212529;";
    private static final String ESTILO_CAMPO = "-fx-font-size: 14px; -fx-pref-height: 35px; -fx-background-radius: 5;";
    private static final String ESTILO_BOTAO_PRIMARIO = "-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14px; -fx-pref-height: 35px;";
    private static final String ESTILO_BOTAO_SECUNDARIO = "-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-pref-height: 35px;";
    private static final String ESTILO_LABEL = "-fx-font-size: 14px; -fx-text-fill: #495057;";

    public static Scene criarTela() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #f8f9fa;");

        // Container principal com sombra (como na TelaLogin)
        VBox containerPrincipal = new VBox(20);
        containerPrincipal.setPadding(new Insets(30));
        containerPrincipal.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);"
        );
        containerPrincipal.setMaxWidth(800);

        // Título
        Label titulo = new Label("Cadastro de Administrador");
        titulo.setStyle(ESTILO_TITULO);
        VBox.setMargin(titulo, new Insets(0, 0, 20, 0));

        // Formulário
        GridPane formulario = new GridPane();
        formulario.setVgap(15);
        formulario.setHgap(15);
        formulario.setPadding(new Insets(10));

        // Campos do formulário
        TextField txtNome = criarCampoTexto("Digite o nome");
        TextField txtSobrenome = criarCampoTexto("Digite o sobrenome");
        TextField txtEmail = criarCampoTexto("Digite o e-mail");
        PasswordField txtSenha = criarCampoSenha("Digite a senha");
        TextField txtCpf = criarCampoTexto("Digite o CPF");
        TextField txtTelefone = criarCampoTexto("Digite o telefone");
        DatePicker dateDataNascimento = criarDatePicker();
        TextField txtGenero = criarCampoTexto("Digite o gênero");

        // Adicionando campos ao formulário
        adicionarCampo(formulario, 0, "Nome:", txtNome);
        adicionarCampo(formulario, 1, "Sobrenome:", txtSobrenome);
        adicionarCampo(formulario, 2, "Email:", txtEmail);
        adicionarCampo(formulario, 3, "Senha:", txtSenha);
        adicionarCampo(formulario, 4, "CPF:", txtCpf);
        adicionarCampo(formulario, 5, "Telefone:", txtTelefone);
        adicionarCampo(formulario, 6, "Data Nascimento:", dateDataNascimento);
        adicionarCampo(formulario, 7, "Gênero:", txtGenero);

        // Botões
        HBox botoes = new HBox(15);
        botoes.setAlignment(Pos.CENTER_RIGHT);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle(ESTILO_BOTAO_SECUNDARIO);
        btnVoltar.setPrefWidth(120);
        btnVoltar.setOnAction(e -> Main.mudarCena(TelaPerfilADM.criarTela()));

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setStyle(ESTILO_BOTAO_PRIMARIO);
        btnCadastrar.setPrefWidth(120);
        btnCadastrar.setDefaultButton(true);

        botoes.getChildren().addAll(btnVoltar, btnCadastrar);

        // Ação do botão Cadastrar
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
                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                    sucesso.setTitle("Sucesso");
                    sucesso.setHeaderText(null);
                    sucesso.setContentText("Administrador cadastrado com sucesso!");
                    sucesso.showAndWait();
                    Main.mudarCena(TelaPerfilADM.criarTela());
                } else {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setTitle("Erro");
                    erro.setHeaderText(null);
                    erro.setContentText("Apenas administradores podem cadastrar novos ADMs!");
                    erro.showAndWait();
                }
            } catch (Exception ex) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro");
                erro.setHeaderText(null);
                erro.setContentText("Erro ao cadastrar: " + ex.getMessage());
                erro.showAndWait();
            }
        });

        // Adiciona todos os componentes ao container
        containerPrincipal.getChildren().addAll(titulo, formulario, botoes);
        grid.add(containerPrincipal, 0, 0);

        return new Scene(grid, 1100, 700); // Mesmo tamanho da TelaLogin
    }

    // Métodos auxiliares para criação de componentes estilizados
    private static TextField criarCampoTexto(String prompt) {
        TextField campo = new TextField();
        campo.setPromptText(prompt);
        campo.setStyle(ESTILO_CAMPO);
        campo.setPrefWidth(300);
        return campo;
    }

    private static PasswordField criarCampoSenha(String prompt) {
        PasswordField campo = new PasswordField();
        campo.setPromptText(prompt);
        campo.setStyle(ESTILO_CAMPO);
        campo.setPrefWidth(300);
        return campo;
    }

    private static DatePicker criarDatePicker() {
        DatePicker picker = new DatePicker();
        picker.setStyle(ESTILO_CAMPO);
        picker.setPrefWidth(300);
        return picker;
    }

    private static void adicionarCampo(GridPane grid, int linha, String label, Control campo) {
        Label lbl = new Label(label);
        lbl.setStyle(ESTILO_LABEL);
        grid.add(lbl, 0, linha);
        grid.add(campo, 1, linha);
    }
}