package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerServico;
import br.pucpr.chamuzepoo.model.Servico;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class TelaPrestadorInicial {
    public static Scene criarTela() {

        BorderPane layoutPrincipal = new BorderPane();
        layoutPrincipal.setTop(HeaderTelaPrestador.criarNavbarPrestador());

        VBox conteudo = new VBox(20);
        conteudo.setPadding(new Insets(20));
        conteudo.setAlignment(Pos.TOP_CENTER);

        Label tituloTelaPrestador = new Label("Bem-vindo(a) à plataforma ChamuZé!");
        tituloTelaPrestador.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        conteudo.getChildren().add(tituloTelaPrestador);

        // Grid de cards
        GridPane gridServicos = new GridPane();
        gridServicos.setHgap(20);
        gridServicos.setVgap(20);
        gridServicos.setPadding(new Insets(10));
        gridServicos.setAlignment(Pos.TOP_CENTER);

        ArrayList<Servico> servicos = ControllerServico.lerLista();
        int coluna = 0;
        int linha = 0;
        for (Servico servico : servicos) {
            VBox card = new VBox(10);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9; -fx-background-radius: 8; -fx-border-radius: 8;");
            card.setPrefWidth(450);

            Label titulo = new Label("Título: " + servico.getTitulo());
            Label descricao = new Label("Descrição: " + servico.getDescricao());
            Label categoria = new Label("Categoria: " + (servico.getCategoria() != null ? servico.getCategoria() : "Sem categoria"));
            Label local = new Label("Local: " + (servico.getLocalServico() != null ? servico.getLocalServico() : "Sem local"));
            Label status = new Label("Status: " + (servico.getStatusServico() != null ? servico.getStatusServico().name() : "Desconhecido"));
            Label preco = new Label("Preço: R$ " + String.format("%.2f", servico.getPreco()));

            Button btnAceitar = new Button("Aceitar Serviço");
            btnAceitar.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-weight: bold;");
            btnAceitar.setOnAction(e -> {
                System.out.println("Serviço aceito: " + servico.getTitulo());
                // lógica de aceitação aqui
            });

            Button btnProposta = new Button("Enviar Proposta");
            btnProposta.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
            btnProposta.setOnAction(e -> {
                System.out.println("Serviço aceito: " + servico.getTitulo());
                // lógica de aceitação aqui
            });

            HBox botoes = new HBox(10); // 10px de espaço entre os botões
            botoes.setAlignment(Pos.CENTER_LEFT);
            botoes.getChildren().addAll(btnAceitar, btnProposta);

            card.getChildren().addAll(titulo, descricao, categoria, local, status, preco, botoes);
            gridServicos.add(card, coluna, linha);

            coluna++;
            if (coluna == 2) {
                coluna = 0;
                linha++;
            }
        }

        conteudo.getChildren().add(gridServicos);

        ScrollPane scrollPane = new ScrollPane(conteudo);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        layoutPrincipal.setCenter(scrollPane);

        return new Scene(layoutPrincipal, 1100, 700);
    }
}
