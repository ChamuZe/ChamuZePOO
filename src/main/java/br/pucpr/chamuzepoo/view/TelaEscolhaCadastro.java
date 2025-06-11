package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TelaEscolhaCadastro {
    public static Scene criarTela(){
        GridPane telaEscolhaCadastro = new GridPane();

        telaEscolhaCadastro.setPadding(new Insets(40));
        telaEscolhaCadastro.setVgap(20);
        telaEscolhaCadastro.setHgap(10);
        telaEscolhaCadastro.setAlignment(Pos.CENTER);

        telaEscolhaCadastro.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0, 0, 2);"
        );

        // Título
        Label titulo = new Label("Escolha o tipo de cadastro");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212529;");
        telaEscolhaCadastro.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);

        // Botão para cadastro como Prestador
        Button botaoPrestador = new Button("Prestador");
        botaoPrestador.setPrefWidth(300);
        botaoPrestador.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        telaEscolhaCadastro.add(botaoPrestador, 0, 1);

        botaoPrestador.setOnAction(evento -> {
            Main.mudarCena(TelaCadastroPrestador.criarTela());
        });

        // Botão para cadastro como Solicitante
        Button botaoSolicitante = new Button("Solicitante");
        botaoSolicitante.setPrefWidth(300);
        botaoSolicitante.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
        telaEscolhaCadastro.add(botaoSolicitante, 0, 2);

        botaoSolicitante.setOnAction(evento -> {
            Main.mudarCena(TelaCadastroSolicitante.criarTela());
        });

        // Botão voltar para Login
        Button botaoVoltarLogin = new Button("Voltar");
        botaoVoltarLogin.setPrefWidth(100); // largura menor
        botaoVoltarLogin.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        telaEscolhaCadastro.add(botaoVoltarLogin, 0, 3);

        // Alinhar à direita e dar margem superior
        GridPane.setHalignment(botaoVoltarLogin, HPos.RIGHT);
        GridPane.setMargin(botaoVoltarLogin, new Insets(20, 0, 0, 0)); // margem de cima


        botaoVoltarLogin.setOnAction(evento -> {
            Main.mudarCena(TelaLogin.criarTela());
        });

        return new Scene(telaEscolhaCadastro, 1100, 700);
    }
}
