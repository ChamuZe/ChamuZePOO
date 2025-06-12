package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerServico;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Servico;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.UUID;

public class TelaVerMeusServicos {
    public static Scene criarTela() {

        BorderPane layoutPrincipal = new BorderPane();
        layoutPrincipal.setTop(HeaderTelaSolicitante.criarNavbarSolicitante());

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
            if (servico.getSolicitanteId().equals((ControllerUsuario.usuarioLogado.getId()).toString())) {

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

                Button btnEditar = new Button("Editar serviço");
                btnEditar.setStyle("-fx-background-color: #527afa; -fx-text-fill: white; -fx-font-weight: bold;");
                btnEditar.setOnAction(e -> Main.mudarCena(TelaEditarServico.criarTela(servico.getId())));

                Button btnExcluir = new Button("Excluir serviço");
                btnExcluir.setStyle("-fx-background-color: #b80000; -fx-text-fill: black; -fx-font-weight: bold;");
                btnExcluir.setOnAction(e -> {
                    UUID servicoID = servico.getId();
                    String servicoIdString = servicoID.toString();
                    ControllerServico.excluirServico(servicoIdString);
                    new Alert(Alert.AlertType.INFORMATION, "Serviço deletado com sucesso!").showAndWait();
                });

                HBox botoes = new HBox(10); // 10px de espaço entre os botões
                botoes.setAlignment(Pos.CENTER_LEFT);
                botoes.getChildren().addAll(btnEditar, btnExcluir);

                card.getChildren().addAll(titulo, descricao, categoria, local, status, preco, botoes);
                gridServicos.add(card, coluna, linha);

                coluna++;
                if (coluna == 2) {
                    coluna = 0;
                    linha++;
                }

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
