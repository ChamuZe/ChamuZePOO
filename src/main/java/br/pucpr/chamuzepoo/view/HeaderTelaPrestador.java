package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HeaderTelaPrestador {

    public static HBox criarNavbarPrestador() {
        HBox barraDeNavegacao = new HBox();
        barraDeNavegacao.setStyle("-fx-background-color: #343a40; -fx-font-size:14px;");
        barraDeNavegacao.setPadding(new Insets(25));
        barraDeNavegacao.setSpacing(10);
        barraDeNavegacao.setAlignment(Pos.CENTER_LEFT);

        // Logo
        ImageView logo = new ImageView(new Image("file:src/assets/chamuzeLogoComFundoR.png"));
        logo.setFitHeight(50);
        logo.setPreserveRatio(true);

        // Botões
        Button btnHome = criarBotaoEstilizado("Home", "outline-warning");
        btnHome.setOnAction(e -> Main.mudarCena(TelaPrestadorInicial.criarTela()));

        Button btnServicos = criarBotaoEstilizado("Meus Serviços", "outline-warning");

        Button btnPerfil = criarBotaoEstilizado("Perfil", "warning");
        btnPerfil.setOnAction(e -> Main.mudarCena(TelaPerfilPrestador.criarTela()));

        Button btnSair = criarBotaoEstilizado("Sair", "danger");
        btnSair.setOnAction(evento -> {
            Main.mudarCena(TelaLogin.criarTela());
        });

        // Espaçamento e saudação
        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);
        Label saudacao = new Label("Bem-vindo, Prestador");
        saudacao.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        barraDeNavegacao.getChildren().addAll(
                logo, btnHome, btnServicos, espaco, saudacao, btnPerfil, btnSair
        );

        return barraDeNavegacao;
    }

    private static Button criarBotaoEstilizado(String texto, String tipo) {
        Button btn = new Button(texto);
        btn.setStyle(estiloBootstrap(tipo));
        return btn;
    }

    private static String estiloBootstrap(String tipo) {
        return switch (tipo) {
            case "warning" -> "-fx-background-color: #ffc107; -fx-text-fill: black;";
            case "outline-warning" -> "-fx-background-color: transparent; -fx-border-color: #ffc107; -fx-text-fill: #ffc107;";
            case "danger" -> "-fx-background-color: #dc3545; -fx-text-fill: white;";
            case "success" -> "-fx-background-color: #28a745; -fx-text-fill: white;";
            default -> "";
        };
    }
}
