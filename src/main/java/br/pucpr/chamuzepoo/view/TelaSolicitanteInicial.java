package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TelaSolicitanteInicial {
    public static Scene criarTela() {
        // Tela do solicitante
        GridPane telaSolicitante = new GridPane();
        telaSolicitante.setHgap(10);
        telaSolicitante.setVgap(20);
        telaSolicitante.setPadding(new Insets(20));
        telaSolicitante.setAlignment(Pos.TOP_CENTER);

        // Barra de navegação
        HBox barraDeNavegacao = new HBox();
        barraDeNavegacao.setSpacing(15);
        barraDeNavegacao.setPadding(new Insets(10));
        barraDeNavegacao.setAlignment(Pos.CENTER_LEFT);

        // Botões do NAVBAR
        Button botaoPerfil = new Button("Perfil");
        botaoPerfil.setOnAction(e -> Main.mudarCena(TelaPerfilSolicitante.criarTela()));

        Button botaoSolicitarServico = new Button("Solicitar Serviço");
        botaoSolicitarServico.setOnAction(e -> Main.mudarCena(TelaSolicitarServico.criarTela()));

        // Adiciona os botões à barra de navegação
        barraDeNavegacao.getChildren().addAll(botaoPerfil, botaoSolicitarServico);

        // Adição da barra de navegação na tela
        telaSolicitante.add(barraDeNavegacao, 0, 0);

        // Título da tela
        Label tituloTelaSolicitante = new Label("Bem-vindo(a), Solicitante!");
        tituloTelaSolicitante.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        telaSolicitante.add(tituloTelaSolicitante, 0, 1);

        return new Scene(telaSolicitante, 1000, 500);
    }
}
