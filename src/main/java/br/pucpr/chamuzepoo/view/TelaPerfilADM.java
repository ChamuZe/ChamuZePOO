package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Admin;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TelaPerfilADM {

    // Estilos consistentes com a TelaLogin
    private static final String ESTILO_TITULO = "-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212529;";
    private static final String ESTILO_SUBTITULO = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #495057;";
    private static final String ESTILO_BOTAO_PRIMARIO = "-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14px;";
    private static final String ESTILO_BOTAO_PERIGO = "-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;";
    private static final String ESTILO_CARD = "-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2); -fx-padding: 15;";

    public static Scene criarTela() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setVgap(20);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setStyle("-fx-background-color: #f8f9fa;");

        // Título principal
        Label titulo = new Label("Painel do Administrador");
        titulo.setStyle(ESTILO_TITULO);
        grid.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);

        // Seção do ADM logado
        Admin admLogado = (Admin) ControllerUsuario.usuarioLogado;
        VBox cardPerfilLogado = criarCardPerfil(admLogado, true);
        cardPerfilLogado.setStyle(ESTILO_CARD);
        grid.add(cardPerfilLogado, 0, 1, 2, 1);

        // Seção de todos ADMs cadastrados
        Label subTituloAdmins = new Label("ADMINISTRADORES CADASTRADOS");
        subTituloAdmins.setStyle(ESTILO_SUBTITULO);
        grid.add(subTituloAdmins, 0, 2);

        // Lista de ADMs com ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f8f9fa; -fx-border-color: transparent;");

        VBox listaAdminsBox = new VBox(15);
        listaAdminsBox.setPadding(new Insets(10));

        for (Admin admin : ControllerUsuario.listarAdmins()) {
            if (!admin.getEmail().equals(admLogado.getEmail())) {
                VBox card = criarCardPerfil(admin, false);
                card.setStyle(ESTILO_CARD);
                listaAdminsBox.getChildren().add(card);
            }
        }

        scrollPane.setContent(listaAdminsBox);
        scrollPane.setPrefHeight(300);
        grid.add(scrollPane, 0, 3, 2, 1);

        // Botão de cadastro
        Button btnNovoAdmin = new Button("Cadastrar Novo ADM");
        btnNovoAdmin.setStyle(ESTILO_BOTAO_PRIMARIO);
        btnNovoAdmin.setPrefWidth(300);
        btnNovoAdmin.setOnAction(e -> Main.mudarCena(TelaCadastroAdmin.criarTela()));

        HBox containerBotao = new HBox(btnNovoAdmin);
        containerBotao.setAlignment(Pos.CENTER);
        grid.add(containerBotao, 0, 4, 2, 1);

        // Botão Voltar
        Button botaoVoltarLogin = new Button("Voltar");
        botaoVoltarLogin.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        botaoVoltarLogin.setPrefWidth(300);
        botaoVoltarLogin.setOnAction(evento -> {
            Main.mudarCena(TelaLogin.criarTela());
        });

        HBox containerBotaoVoltar = new HBox(botaoVoltarLogin);
        containerBotaoVoltar.setAlignment(Pos.CENTER);
        grid.add(containerBotaoVoltar, 0, 5, 2, 1);

        return new Scene(grid, 1100, 700); // Mesmo tamanho da TelaLogin
    }

    private static VBox criarCardPerfil(Admin admin, boolean isPerfilLogado) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.TOP_LEFT);

        GridPane dados = new GridPane();
        dados.setHgap(15);
        dados.setVgap(10);
        dados.setAlignment(Pos.TOP_LEFT);

        // Formatação dos campos
        String estiloLabel = "-fx-font-size: 14px; -fx-text-fill: #495057;";
        String estiloValor = "-fx-font-size: 14px; -fx-text-fill: #212529; -fx-font-weight: bold;";

        adicionarCampo(dados, 0, "Nome:", admin.getNome() + " " + admin.getSobreNome(), estiloLabel, estiloValor);
        adicionarCampo(dados, 1, "Email:", admin.getEmail(), estiloLabel, estiloValor);
        adicionarCampo(dados, 2, "CPF:", admin.getCpf(), estiloLabel, estiloValor);
        adicionarCampo(dados, 3, "Telefone:", admin.getTelefone(), estiloLabel, estiloValor);
        adicionarCampo(dados, 4, "Gênero:", admin.getGenero(), estiloLabel, estiloValor);

        card.getChildren().add(dados);

        // Botão de Editar
        if (isPerfilLogado) {
            Button btnEditar = new Button("Editar");
            btnEditar.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 14px;");
            btnEditar.setPrefWidth(200);
            btnEditar.setOnAction(e -> {
                System.out.println("Editar perfil de: " + admin.getNome());
                Main.mudarCena(TelaEdicaoAdmin.criarTela());
            });

            HBox botoesBox = new HBox(btnEditar);
            botoesBox.setAlignment(Pos.CENTER_RIGHT);
            card.getChildren().add(botoesBox);
        }

        // Botão de excluir
        if (!isPerfilLogado) {
            Button btnExcluir = new Button("Excluir Administrador");
            btnExcluir.setStyle(ESTILO_BOTAO_PERIGO);
            btnExcluir.setPrefWidth(200);

            btnExcluir.setOnAction(e -> {
                Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmação");
                confirmacao.setHeaderText("Excluir Administrador");
                confirmacao.setContentText("Tem certeza que deseja excluir " + admin.getNome() + "?");

                // Estilização
                DialogPane dialogPane = confirmacao.getDialogPane();
                dialogPane.setStyle("-fx-font-size: 14px;");

                if (confirmacao.showAndWait().get() == ButtonType.OK) {
                    ControllerUsuario controller = new ControllerUsuario();
                    if (controller.excluirAdmin(admin)) {
                        Main.mudarCena(TelaPerfilADM.criarTela());
                    } else {
                        Alert erro = new Alert(Alert.AlertType.ERROR);
                        erro.setContentText("Não foi possível excluir o administrador.");
                        erro.show();
                    }
                }
            });

            HBox botoesBox = new HBox(btnExcluir);
            botoesBox.setAlignment(Pos.CENTER_RIGHT);
            card.getChildren().add(botoesBox);
        }

        return card;
    }

    private static void adicionarCampo(GridPane grid, int linha, String label, String valor, String estiloLabel, String estiloValor) {
        Label lbl = new Label(label);
        lbl.setStyle(estiloLabel);

        Label val = new Label(valor);
        val.setStyle(estiloValor);

        grid.add(lbl, 0, linha);
        grid.add(val, 1, linha);
    }
}
