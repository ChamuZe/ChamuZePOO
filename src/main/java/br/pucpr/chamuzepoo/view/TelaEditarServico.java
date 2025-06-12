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


public class TelaEditarServico {
    public static Scene criarTela(UUID idServico){
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
        Label tituloTelaSolicitarServico = new Label("Editar Serviço");
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
        Button botaoEditarServico = new Button("Editar");
            botaoEditarServico.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
            botaoEditarServico.setPrefWidth(200);
            GridPane.setMargin(botaoEditarServico, new Insets(10, 0, 10, 0));
            caixaBranca.add(botaoEditarServico, 0, linha++);

        // Botão voltar
        Button botaoVoltar = new Button("Voltar");
            botaoVoltar.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-weight: bold;");
            botaoVoltar.setPrefWidth(100);
            caixaBranca.add(botaoVoltar, 0, linha++);
            GridPane.setHalignment(botaoVoltar, javafx.geometry.HPos.RIGHT);

        // Adiciona a caixa branca na tela principal
            telaSolicitarServico.add(caixaBranca, 0, 0);

        // Ações dos botões
            botaoEditarServico.setOnAction(evento -> {
            String titulo = entradaTitulo.getText();
            if (titulo.isEmpty()){
                titulo = null;
            }
            String descricao = entradaDescricao.getText();
            if (descricao.isEmpty()){
                descricao = null;
            }
            Categoria categoria = categoriaBox.getValue();
            LocalServico localServico = localServicoBox.getValue();
            String preco = entradaPreco.getText();
            if ( preco.isEmpty()){
                preco = null;
            }
            String idString = idServico.toString();

            if (!(ControllerServico.editarServicos(idString, descricao, titulo, categoria, localServico, preco))){
                new Alert(Alert.AlertType.INFORMATION, "Nenhuma informação para editar").showAndWait();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION, "edição feita com sucesso!").showAndWait();
            }

        });

            botaoVoltar.setOnAction(e -> Main.mudarCena(TelaSolicitanteInicial.criarTela()));

            return new Scene(telaSolicitarServico, 1100, 700);
    }
}
