package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TelaPrestadorInicial {
    public static Scene criarTela() {
        // Tela do prestador
        GridPane telaPrestador = new GridPane();
        telaPrestador.setHgap(10);
        telaPrestador.setVgap(20);
        telaPrestador.setPadding(new Insets(20));
        telaPrestador.setAlignment(Pos.TOP_CENTER);

        // Barra de navegação
        HBox barraDeNavegacao = new HBox();
        barraDeNavegacao.setSpacing(15);
        barraDeNavegacao.setPadding(new Insets(10));
        barraDeNavegacao.setAlignment(Pos.CENTER_LEFT);

        // Botão de perfil
        Button botaoPerfil = new Button("Perfil");
        botaoPerfil.setOnAction(e -> {
            Main.mudarCena(TelaPerfilPrestador.criarTela());
        });

        // Inclusão dos ícones do NAVBAR
        barraDeNavegacao.getChildren().addAll(botaoPerfil);

        // Adição do NAVBAR na tela
        telaPrestador.add(barraDeNavegacao, 0, 0);

        // Título da tela
        Label tituloTelaPrestador = new Label("Bem-vindo(a), Prestador!");
        tituloTelaPrestador.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        telaPrestador.add(tituloTelaPrestador, 0, 1);

        return new Scene(telaPrestador, 1000, 500);
    }
}
