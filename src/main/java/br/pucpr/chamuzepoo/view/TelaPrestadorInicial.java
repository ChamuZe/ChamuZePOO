package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TelaPrestadorInicial {
    public static Scene criarTela() {

        BorderPane layoutPrincipal = new BorderPane();
        // Usa o navbar da fábrica
        layoutPrincipal.setTop(HeaderTelaPrestador.criarNavbarPrestador());

        // Tela do prestador
        GridPane telaPrestador = new GridPane();
        telaPrestador.setHgap(10);
        telaPrestador.setVgap(20);
        telaPrestador.setPadding(new Insets(20));
        telaPrestador.setAlignment(Pos.TOP_CENTER);

        // Título da tela
        Label tituloTelaPrestador = new Label("Bem-vindo(a), Prestador!");
        tituloTelaPrestador.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        telaPrestador.add(tituloTelaPrestador, 0, 1);

        layoutPrincipal.setCenter(telaPrestador);

        return new Scene(layoutPrincipal, 1100, 700);
    }
}
