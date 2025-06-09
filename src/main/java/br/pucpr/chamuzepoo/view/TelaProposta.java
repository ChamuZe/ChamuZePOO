package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzejava.controller.ControllerProposta;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Proposta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;




public class TelaProposta {

    public static Scene criarTela() {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));

        GridPane formGrid = new GridPane();
        formGrid.setVgap(15);
        formGrid.setHgap(10);
        formGrid.setPadding(new Insets(10));

        Label servicoLabel = new Label("Serviço: Teste");
        Label precoAtualLabel = new Label("Preço Atual: R$teste");

        Label novoPrecoLabel = new Label("Novo Preço:");
        TextField novoPrecoField = new TextField();
        novoPrecoField.setPromptText("Digite o novo valor");

        Label descricaoLabel = new Label("Descrição da Proposta:");
        TextArea descricaoField = new TextArea();
        descricaoField.setPromptText("Explique sua proposta...");
        descricaoField.setPrefRowCount(3);

        Button btnEnviar = new Button("Enviar Proposta");

        formGrid.add(servicoLabel, 0, 0, 2, 1);
        formGrid.add(precoAtualLabel, 0, 1, 2, 1);
        formGrid.add(novoPrecoLabel, 0, 2);
        formGrid.add(novoPrecoField, 1, 2);
        formGrid.add(descricaoLabel, 0, 3);
        formGrid.add(descricaoField, 0, 4, 2, 1);
        formGrid.add(btnEnviar, 0, 5, 2, 1);

        VBox secaoPropostas = new VBox(10);
        Label propostasTitulo = new Label("Propostas Existentes");

        ListView<Proposta> propostasLista = new ListView<>();
        propostasLista.setPrefHeight(200);
        ObservableList<Proposta> propostaData = FXCollections.observableArrayList();
        propostasLista.setItems(propostaData);

        Button btnCarregasPropostas = new Button("Carregar Propostas");
        btnCarregasPropostas.setOnAction(e -> {
            propostaData.setAll(new ControllerProposta().lerPropostas());
        });

        propostasLista.setCellFactory(_ -> new ListCell<Proposta>() {
            @Override
            protected void updateItem(Proposta proposta, boolean empty) {
                super.updateItem(proposta, empty);
                if (empty || proposta == null) {
                    setText(null);
                } else {
                    setText(String.format("%s: R$%.2f Solicitante: %s, Prestador: %s",
                            proposta.getServico(),
                            proposta.getPreco(),
                            proposta.getSolicitante(),
                            proposta.getPrestador()));
                }
            }
        });

        secaoPropostas.getChildren().addAll(propostasTitulo, btnCarregasPropostas, propostasLista);

        mainLayout.getChildren().addAll(formGrid, secaoPropostas);

        btnEnviar.setOnAction(e -> {
            try {
                double novoPreco = Double.parseDouble(novoPrecoField.getText());
                String descricao = descricaoField.getText();

                String solicitante = "SolicitanteTeste";
                String teste = "testeTitulo";


                if (descricao.isBlank()) {
                    throw new IllegalArgumentException("A descrição é obrigatória");
                }

                Proposta proposta = new Proposta(novoPreco, descricao, teste,ControllerUsuario.usuarioLogado.getNome(),solicitante);

                new ControllerProposta().cadastrarProposta(proposta);


                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Sucesso");
                success.setHeaderText("Proposta enviada!");
                success.setContentText("Sua proposta foi registrada com sucesso.");
                success.showAndWait();

            } catch (NumberFormatException ex) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Erro de Formato");
                error.setHeaderText("Valor inválido");
                error.setContentText("Digite um valor numérico válido para o preço");
                error.showAndWait();
            } catch (Exception ex) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Erro");
                error.setHeaderText("Falha ao enviar proposta");
                error.setContentText(ex.getMessage());
                error.showAndWait();
            }
        });

        return new Scene(mainLayout, 1000, 500);
    }
}