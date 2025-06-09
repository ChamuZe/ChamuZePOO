package br.pucpr.chamuzejava.view;
import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerServico;
import br.pucpr.chamuzejava.file.Categoria;
import br.pucpr.chamuzejava.file.LocalServico;
import br.pucpr.chamuzejava.model.Servico;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;

public class TelaSolicitarServico {
    public static int solicitanteId = 1;
    public static Scene criarTela() {
        GridPane telaSolicitarServico = new GridPane();

        Label titulotelaSolicitarServico = new Label("Solicitar Serviço");
        telaSolicitarServico.add(titulotelaSolicitarServico, 1, 1);

        // titulo
        Label labelEntradaTitulo = new Label("Titulo: ");
        TextField entradaTitulo = new TextField();
        telaSolicitarServico.add(labelEntradaTitulo, 1, 2);
        telaSolicitarServico.add(entradaTitulo, 1, 3);

        // descricao
        Label labelEntradaDescricao= new Label("Descrição: ");
        TextField entradaDescricao = new TextField();
        telaSolicitarServico.add(labelEntradaDescricao, 1, 4);
        telaSolicitarServico.add(entradaDescricao, 1, 5);

        // categoria
        Label labelEntradaCategoria = new Label("Categoria: ");
        ComboBox<Categoria> categoriaBox = new ComboBox<>();
        categoriaBox.getItems().addAll(Categoria.values());
        telaSolicitarServico.add(labelEntradaCategoria, 1, 6);
        telaSolicitarServico.add(categoriaBox, 1, 7);

        // localServico
        Label labelEntradaLocalServico = new Label("Local do serviço: ");
        ComboBox<LocalServico> localServicoBox = new ComboBox<>();
        localServicoBox.getItems().addAll(LocalServico.values());

        telaSolicitarServico.add(labelEntradaLocalServico, 1, 8);
        telaSolicitarServico.add(localServicoBox, 1, 9);

        // preco
        Label labelEntradaPreco = new Label("Preço: ");
        TextField entradaPreco = new TextField();
        entradaPreco.setPromptText("0.00");
        telaSolicitarServico.add(labelEntradaPreco, 1, 10);
        telaSolicitarServico.add(entradaPreco, 1, 11);



        // Botão Cadastrar
        Button botaoSolicitarServico = new Button("Solicitar");
        telaSolicitarServico.add(botaoSolicitarServico, 1, 20);

        botaoSolicitarServico.setOnAction(evento -> {
            // Pegando os dados do input
            String titulo = entradaTitulo.getText();
            String descricao = entradaDescricao.getText();
            Categoria categoria = categoriaBox.getValue();
            LocalServico localServico = localServicoBox.getValue();
            double preco = 0.00;

            try {
                preco = Double.parseDouble(entradaPreco.getText());
            } catch (NumberFormatException ex) {
                Alert alertaErro = new Alert(Alert.AlertType.ERROR);
                alertaErro.setTitle("Erro de Preço");
                alertaErro.setHeaderText("Preço inválido");
                alertaErro.setContentText("Por favor, digite um valor numérico válido no campo preço.");
                alertaErro.showAndWait();
            }
            if(preco == 0.00 || titulo.isEmpty() || descricao.isEmpty() || categoria == null || localServico == null) {
                new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!").show();
                return;
            }

            Servico servico = new Servico(
                    solicitanteId, descricao, titulo, categoria, localServico, preco
            );

            ControllerServico controllerServico = new ControllerServico(servico);
            controllerServico.solicitarServico();
        });

        // Botão Voltar
        Button botaoVoltarParaLogin = new Button("Voltar");
        telaSolicitarServico.add(botaoVoltarParaLogin, 1, 21);

        botaoVoltarParaLogin.setOnAction(evento -> {
            Main.mudarCena(TelaLogin.criarTela());
        });

        return new Scene(telaSolicitarServico, 1000, 500);
    }
}
