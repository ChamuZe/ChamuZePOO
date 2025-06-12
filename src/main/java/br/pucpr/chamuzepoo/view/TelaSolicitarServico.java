package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerServico;
import br.pucpr.chamuzejava.file.Categoria;
import br.pucpr.chamuzejava.file.LocalServico;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Servico;
import br.pucpr.chamuzepoo.model.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;

import java.util.UUID;

public class TelaSolicitarServico {
    public static Scene criarTela() {
        // Tela principal (fundo cinza suave)
        GridPane telaSolicitarServico = new GridPane();
        telaSolicitarServico.setPadding(new Insets(20));
        telaSolicitarServico.setAlignment(Pos.CENTER);
        telaSolicitarServico.setStyle("-fx-background-color: #f8f9fa;");

        // Caixa branca centralizada
        GridPane caixaBranca = new GridPane();
        caixaBranca.setPadding(new Insets(40));
        caixaBranca.setVgap(5);
        caixaBranca.setHgap(12);
        caixaBranca.setAlignment(Pos.CENTER);
        caixaBranca.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 2);"
        );

        // Título
        Label tituloTelaSolicitarServico = new Label("Solicitar Serviço");
        tituloTelaSolicitarServico.setStyle("-fx-font-size:24px; -fx-font-weight:bold;");
        caixaBranca.add(tituloTelaSolicitarServico, 0, 0, 2, 1);
        GridPane.setHalignment(tituloTelaSolicitarServico, javafx.geometry.HPos.CENTER);

        int linha = 1;

        // Título do serviço
        caixaBranca.add(new Label("Título:"), 0, linha++);
        TextField entradaTitulo = new TextField();
        caixaBranca.add(entradaTitulo, 0, linha++);

        // Descrição
        caixaBranca.add(new Label("Descrição:"), 0, linha++);
        TextField entradaDescricao = new TextField();
        caixaBranca.add(entradaDescricao, 0, linha++);

        // Categoria
        caixaBranca.add(new Label("Categoria:"), 0, linha++);
        ComboBox<Categoria> categoriaBox = new ComboBox<>();
        categoriaBox.getItems().addAll(Categoria.values());
        caixaBranca.add(categoriaBox, 0, linha++);

        // Local do serviço
        caixaBranca.add(new Label("Local do serviço:"), 0, linha++);
        ComboBox<LocalServico> localServicoBox = new ComboBox<>();
        localServicoBox.getItems().addAll(LocalServico.values());
        caixaBranca.add(localServicoBox, 0, linha++);

        // Preço
        caixaBranca.add(new Label("Preço:"), 0, linha++);
        TextField entradaPreco = new TextField();
        entradaPreco.setPromptText("0.00");
        caixaBranca.add(entradaPreco, 0, linha++);

        // Botão solicitar
        Button botaoSolicitarServico = new Button("Solicitar");
        botaoSolicitarServico.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
        botaoSolicitarServico.setPrefWidth(200);
        GridPane.setMargin(botaoSolicitarServico, new Insets(10, 0, 10, 0));
        caixaBranca.add(botaoSolicitarServico, 0, linha++);

        // Botão voltar
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-weight: bold;");
        botaoVoltar.setPrefWidth(100);
        caixaBranca.add(botaoVoltar, 0, linha++);
        GridPane.setHalignment(botaoVoltar, javafx.geometry.HPos.RIGHT);

        // Adiciona a caixa branca na tela principal
        telaSolicitarServico.add(caixaBranca, 0, 0);

        // Ações dos botões
        botaoSolicitarServico.setOnAction(evento -> {
            String titulo = entradaTitulo.getText();
            String descricao = entradaDescricao.getText();
            Categoria categoria = categoriaBox.getValue();
            LocalServico localServico = localServicoBox.getValue();
            double preco = 0.00;

            try {
                preco = Double.parseDouble(entradaPreco.getText());
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Por favor, insira um valor numérico válido para o preço.").show();
                return;
            }

            if (titulo.isEmpty() || descricao.isEmpty() || categoria == null || localServico == null || preco == 0.0) {
                new Alert(Alert.AlertType.WARNING, "Preencha todos os campos corretamente.").show();
                return;
            }

            UUID usuarioId = ControllerUsuario.usuarioLogado.getId();
            String usuarioIdString = usuarioId.toString();
            Servico servico = new Servico(usuarioIdString, descricao, titulo, categoria, localServico, preco);
            ControllerServico controllerServico = new ControllerServico(servico);
            controllerServico.solicitarServico();

            new Alert(Alert.AlertType.INFORMATION, "Serviço solicitado com sucesso!").showAndWait();
        });

        botaoVoltar.setOnAction(e -> Main.mudarCena(TelaSolicitanteInicial.criarTela()));

        return new Scene(telaSolicitarServico, 1100, 700);
    }
}