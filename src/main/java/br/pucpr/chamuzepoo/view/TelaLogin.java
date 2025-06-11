package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Admin;
import br.pucpr.chamuzepoo.model.Prestador;
import br.pucpr.chamuzepoo.model.Solicitante;
import br.pucpr.chamuzepoo.model.Usuario;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TelaLogin {

    public static Scene criarTela() {
        GridPane telaLogin = new GridPane();

        telaLogin.setPadding(new Insets(20));
        telaLogin.setVgap(15);
        telaLogin.setHgap(10);
        telaLogin.setAlignment(Pos.CENTER);

        // Estilo da caixa (imitando Bootstrap)
        telaLogin.setStyle(
                "-fx-background-color: #f8f9fa;" + // tom de fundo suave
                        "-fx-padding: 20;"
        );

        // Caixa branca centralizada
        GridPane caixaBranca = new GridPane();
        caixaBranca.setPadding(new Insets(40));
        caixaBranca.setVgap(15);
        caixaBranca.setHgap(10);
        caixaBranca.setAlignment(Pos.CENTER);
        caixaBranca.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 2);"
        );

        Label tituloTelaLogin = new Label("Login");
        tituloTelaLogin.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212529;");
        caixaBranca.add(tituloTelaLogin, 0, 0, 2, 1);
        GridPane.setHalignment(tituloTelaLogin, HPos.CENTER);

        Label labelEntradaEmail = new Label("E-mail");
        labelEntradaEmail.setStyle("-fx-font-size: 14px; -fx-text-fill: #495057;");
        caixaBranca.add(labelEntradaEmail, 0, 1);

        TextField entradaEmail = new TextField();
        entradaEmail.setPromptText("Digite seu e-mail");
        entradaEmail.setPrefWidth(300);
        entradaEmail.setStyle("-fx-font-size: 16px; -fx-pref-height: 38px;");
        caixaBranca.add(entradaEmail, 1, 1);

        Label labelEntradaSenha = new Label("Senha");
        labelEntradaSenha.setStyle("-fx-font-size: 14px; -fx-text-fill: #495057;");
        caixaBranca.add(labelEntradaSenha, 0, 2);

        PasswordField entradaSenha = new PasswordField();
        entradaSenha.setPromptText("Digite sua senha");
        entradaSenha.setStyle("-fx-font-size: 16px; -fx-pref-height: 38px;");
        caixaBranca.add(entradaSenha, 1, 2);

        Button botaoEnviar = new Button("Entrar");
        botaoEnviar.setPrefWidth(300);
        botaoEnviar.setStyle(
                "-fx-background-color: #ffc107;" +
                        "-fx-text-fill: #212529;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 16px;" +
                        "-fx-pref-height: 40px;" +
                        "-fx-background-radius: 6;"
        );
        caixaBranca.add(botaoEnviar, 1, 3);

        Label mensagemErro = new Label();
        mensagemErro.setStyle("-fx-text-fill: #dc3545; -fx-font-weight: bold; -fx-font-size: 14px;");
        mensagemErro.setVisible(false);
        caixaBranca.add(mensagemErro, 1, 4);

        botaoEnviar.setOnAction(evento -> {
            String email = entradaEmail.getText().trim();
            String senha = entradaSenha.getText().trim();

            if(email.isEmpty() || senha.isEmpty()) {
                mensagemErro.setText("Preencha todos os campos!");
                mensagemErro.setVisible(true);
                return;
            }

            try{
                ControllerUsuario controllerUsuario = new ControllerUsuario();
                Usuario usuario = controllerUsuario.realizarLogin(email, senha);

                if (usuario != null) {
                    controllerUsuario.setUsuario(usuario);
                    mensagemErro.setVisible(false);

                    if (usuario instanceof Admin) {
                        Main.mudarCena(TelaPerfilADM.criarTela());
                    } else if (usuario instanceof Prestador) {
                        Main.mudarCena(TelaPrestadorInicial.criarTela());
                    } else if (usuario instanceof Solicitante) {
                        Main.mudarCena(TelaSolicitanteInicial.criarTela());
                    }
                } else {
                    mensagemErro.setText("E-mail ou senha inválidos!");
                    mensagemErro.setVisible(true);
                }
            } catch (Exception e){
                mensagemErro.setText("Ocorreu um erro ao realizar Login, tente novamente mais tarde!");
                mensagemErro.setVisible(true);
            }
        });

        HBox linhaSeCadastrar = new HBox(5);
        linhaSeCadastrar.setAlignment(Pos.CENTER_LEFT);
        Label labelLinkSeCadastrar = new Label("Não possui uma conta?");
        Hyperlink linkSeCadastrar = new Hyperlink("Cadastre-se");
        linkSeCadastrar.setStyle("-fx-text-fill: #0d6efd; -fx-font-size: 14px;");
        linhaSeCadastrar.getChildren().addAll(labelLinkSeCadastrar, linkSeCadastrar);
        caixaBranca.add(linhaSeCadastrar, 1, 5);

        linkSeCadastrar.setOnMouseClicked(evento -> {
            Main.mudarCena(TelaEscolhaCadastro.criarTela());
        });

        telaLogin.add(caixaBranca, 0, 0);

        return new Scene(telaLogin, 1100, 700);
    }

}
