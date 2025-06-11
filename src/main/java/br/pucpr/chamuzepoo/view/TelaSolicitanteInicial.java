package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class TelaSolicitanteInicial {
    public static Scene criarTela() {
        BorderPane layoutPrincipal = new BorderPane();

        // Usa o navbar da fábrica
        layoutPrincipal.setTop(HeaderTelaSolicitante.criarNavbarSolicitante());

        // Conteúdo central
        VBox conteudo = new VBox(20);
        conteudo.setPadding(new Insets(30));
        conteudo.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Bem-vindo(a) à plataforma ChamuZé!");
        titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Button btnAdicionarServico = new Button("Solicitar Serviço");
        btnAdicionarServico.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-text-fill: black; -fx-font-size:16px;");
        btnAdicionarServico.setOnAction(e -> Main.mudarCena(TelaSolicitarServico.criarTela()));

        conteudo.getChildren().addAll(titulo, btnAdicionarServico);

        layoutPrincipal.setCenter(conteudo);

        return new Scene(layoutPrincipal, 1100, 700);
    }
}
