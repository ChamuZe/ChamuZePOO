package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Admin;
import br.pucpr.chamuzejava.model.Prestador;
import br.pucpr.chamuzejava.model.Solicitante;
import br.pucpr.chamuzejava.model.Usuario;
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

        telaLogin.setPadding(new Insets(40));
        telaLogin.setVgap(15);
        telaLogin.setHgap(10);
        telaLogin.setAlignment(Pos.CENTER);

        // Fundo branco com borda arredondada e sombra
        telaLogin.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 40;" +
                        "-fx-background-radius: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0, 0, 2);"
        );

        Label tituloTelaLogin = new Label("Login");
        tituloTelaLogin.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #212529;");
        telaLogin.add(tituloTelaLogin, 0, 0, 2, 1);
        GridPane.setHalignment(tituloTelaLogin, HPos.CENTER);

        Label labelEntradaEmail = new Label("E-mail:");
        telaLogin.add(labelEntradaEmail, 0, 1);

        TextField entradaEmail = new TextField();
        entradaEmail.setPromptText("Digite seu e-mail");
        entradaEmail.setPrefWidth(300);
        entradaEmail.setStyle("-fx-font-size: 16px; -fx-pref-height: 35px;");
        telaLogin.add(entradaEmail, 1, 1);

        Label labelEntradaSenha = new Label("Senha:");
        telaLogin.add(labelEntradaSenha, 0, 2);

        PasswordField entradaSenha = new PasswordField();
        entradaSenha.setPromptText("Digite sua senha");
        entradaSenha.setStyle("-fx-font-size: 16px; -fx-pref-height: 35px;");
        telaLogin.add(entradaSenha, 1, 2);

        Button botaoEnviar = new Button("Entrar");
        botaoEnviar.setPrefWidth(300);
        botaoEnviar.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
        telaLogin.add(botaoEnviar, 1, 3);

        // Label para mensagens de erro/aviso na tela (invisível inicialmente)
        Label mensagemErro = new Label();
        mensagemErro.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        mensagemErro.setVisible(false);
        telaLogin.add(mensagemErro, 1, 4);

        botaoEnviar.setOnAction(evento -> {
            String email = entradaEmail.getText().trim();
            String senha = entradaSenha.getText().trim();

            if(email.isEmpty() || senha.isEmpty()) {
                mensagemErro.setText("Preencha todos os campos!");
                mensagemErro.setVisible(true);
                return;
            }

            ControllerUsuario controllerUsuario = new ControllerUsuario();
            Usuario usuario = controllerUsuario.realizarLogin(email, senha);

            if(usuario != null) {
                controllerUsuario.setUsuario(usuario);
                mensagemErro.setVisible(false); // limpa mensagem caso tivesse erro anterior

                if (usuario instanceof Admin) {
                    System.out.println("Login como ADMIN bem-sucedido!");
                    Main.mudarCena(TelaPerfilADM.criarTela());
                } else if (usuario instanceof Prestador) {
                    System.out.println("Usuário se logou como Prestador");
                    Main.mudarCena(TelaPrestadorInicial.criarTela());
                } else if (usuario instanceof Solicitante) {
                    System.out.println("Usuário se logou como Solicitante");
                    Main.mudarCena(TelaSolicitanteInicial.criarTela());
                }
            } else {
                mensagemErro.setText("E-mail ou senha inválidos!");
                mensagemErro.setVisible(true);
            }
        });

        HBox linhaSeCadastrar = new HBox(5);
        linhaSeCadastrar.setAlignment(Pos.CENTER_LEFT);
        Label labelLinkSeCadastrar = new Label("Não possui uma conta?");
        Hyperlink linkSeCadastrar = new Hyperlink("Cadastre-se");
        linhaSeCadastrar.getChildren().addAll(labelLinkSeCadastrar, linkSeCadastrar);
        telaLogin.add(linhaSeCadastrar, 1, 5);

        linkSeCadastrar.setOnMouseClicked(evento -> {
            Main.mudarCena(TelaEscolhaCadastro.criarTela());
        });

        return new Scene(telaLogin, 1100, 700);
    }
}
