package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Prestador;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaPerfilPrestador {
    public static Scene criarTela() {

        BorderPane layoutPrincipal = new BorderPane();
        // Usa o navbar da fábrica
        layoutPrincipal.setTop(HeaderTelaPrestador.criarNavbarPrestador());

        GridPane telaPerfilPrestador = new GridPane();

        telaPerfilPrestador.setPadding(new Insets(40));
        telaPerfilPrestador.setHgap(10);
        telaPerfilPrestador.setVgap(15);
        telaPerfilPrestador.setAlignment(Pos.CENTER);
        telaPerfilPrestador.setStyle("-fx-background-color: #f2f2f2;");

        // Card container com VBox e estilo igual ao TelaPerfilSolicitante
        VBox card = new VBox(15);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 4);"
        );
        card.setPrefWidth(500);

        // Título
        Label titulo = new Label("Perfil do Prestador");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333;");
        card.getChildren().add(titulo);

        // Campos - todos com mesmo estilo
        Label labelId = new Label("Id: " + ControllerUsuario.usuarioLogado.getId());
        labelId.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelId);

        Label labelNome = new Label("Nome: " + ControllerUsuario.usuarioLogado.getNome());
        labelNome.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelNome);

        Label labelSobrenome = new Label("Sobrenome: " + ControllerUsuario.usuarioLogado.getSobreNome());
        labelSobrenome.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelSobrenome);

        Label labelEmail = new Label("Email: " + ControllerUsuario.usuarioLogado.getEmail());
        labelEmail.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelEmail);

        Label labelSenha = new Label("Senha: " + ControllerUsuario.usuarioLogado.getSenha());
        labelSenha.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelSenha);

        Label labelCpf = new Label("CPF: " + ControllerUsuario.usuarioLogado.getCpf());
        labelCpf.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelCpf);

        Label labelTelefone = new Label("Telefone: " + ControllerUsuario.usuarioLogado.getTelefone());
        labelTelefone.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelTelefone);

        //Formatando antes de mostrar
        LocalDate dataNascimentoBruta = ControllerUsuario.usuarioLogado.getDataNascimento();
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNascimentoFormatada = dataNascimentoBruta.format(formatoBR);
        Label labelDataNascimento = new Label("Data de Nascimento: " + dataNascimentoFormatada);
        labelDataNascimento.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelDataNascimento);

        Label labelGenero = new Label("Gênero: " + ControllerUsuario.usuarioLogado.getGenero());
        labelGenero.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        card.getChildren().add(labelGenero);

        if (ControllerUsuario.usuarioLogado instanceof Prestador) {
            Prestador prestador = (Prestador) ControllerUsuario.usuarioLogado;
            String cnpj = prestador.getCnpj();
            String chavePix  = prestador.getChavePix();

            Label labelCnpj = new Label("CNPJ: " + cnpj);
            labelCnpj.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
            card.getChildren().add(labelCnpj);

            Label labelChavePix = new Label("Chave Pix: " + chavePix);
            labelChavePix.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
            card.getChildren().add(labelChavePix);
        }

        // Botões em HBox com espaçamento igual
        HBox botoes = new HBox(15);
        botoes.setAlignment(Pos.CENTER);

        Button botaoEditar = new Button("Editar");
        botaoEditar.setPrefWidth(150);
        botaoEditar.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size:16px;");
        botaoEditar.setOnAction(e -> System.out.println("Vai editar Usuário"));

        Button botaoExcluir = new Button("Excluir");
        botaoExcluir.setPrefWidth(150);
        botaoExcluir.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-font-size:16px;");
        botaoExcluir.setOnAction(e -> {
                Alert confirmacaoExcluirConta = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacaoExcluirConta.setTitle("Confirmação de exclusão");
                confirmacaoExcluirConta.setHeaderText(null);

                confirmacaoExcluirConta.setContentText("Tem certeza que deseja excluir sua conta?");
                // Exibe o alert e espera o usuário responder (Ok ou Cancelar)
                confirmacaoExcluirConta.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        ControllerUsuario controllerUsuario= new ControllerUsuario();
                        Boolean excluiu = controllerUsuario.excluirUsuario();

                        if (excluiu) {
                            Alert usuarioExcluido = new Alert(Alert.AlertType.INFORMATION);
                            usuarioExcluido.setTitle("Usuário Excluído");
                            usuarioExcluido.setHeaderText(null);
                            usuarioExcluido.setContentText("Sua conta foi excluída com sucesso!");
                            usuarioExcluido.showAndWait();
                            Main.mudarCena(TelaLogin.criarTela());
                        } else {
                            Alert usuarioNaoExcluido = new Alert(Alert.AlertType.ERROR);
                            usuarioNaoExcluido.setTitle("Erro");
                            usuarioNaoExcluido.setHeaderText(null);
                            usuarioNaoExcluido.setContentText("Não foi possível excluir sua conta.");
                            usuarioNaoExcluido.showAndWait();
                        }


                        System.out.println("Usuário confirmou a exclusão");
                    } else {
                        System.out.println("Usuário cancelou a exclusão");
                    }
                });
        });

        botoes.getChildren().addAll(botaoEditar, botaoExcluir);
        card.getChildren().add(botoes);

        // Botão Voltar
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(200);
        botaoVoltar.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size:12px; -fx-font-weight:bold;");
        botaoVoltar.setOnAction(e -> Main.mudarCena(TelaPrestadorInicial.criarTela()));
        card.getChildren().add(botaoVoltar);

        telaPerfilPrestador.add(card, 0, 0);
        GridPane.setHalignment(card, HPos.CENTER);

        layoutPrincipal.setCenter(telaPerfilPrestador);

        return new Scene(layoutPrincipal, 1100, 700);
    }
}
