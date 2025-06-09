package br.pucpr.chamuzejava.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzejava.controller.ControllerUsuario;
import br.pucpr.chamuzejava.model.Prestador;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TelaPerfilPrestador {
    public static Scene criarTela() {
        GridPane telaPerfilPrestador = new GridPane();

        telaPerfilPrestador.setPadding(new Insets(40));
        telaPerfilPrestador.setHgap(10);
        telaPerfilPrestador.setVgap(15);
        telaPerfilPrestador.setAlignment(Pos.CENTER);

        // Título
        Label titulo = new Label("Perfil do Prestador");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        telaPerfilPrestador.add(titulo, 0, 0, 2, 1);
        GridPane.setHalignment(titulo, HPos.CENTER);

        // Campos
        Label labelId = new Label("Id: " + ControllerUsuario.usuarioLogado.getId());
        labelId.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelId, 0, 12);

        Label labelNome = new Label("Nome: " + ControllerUsuario.usuarioLogado.getNome());
        labelNome.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelNome, 0, 1);

        Label labelSobrenome = new Label("Sobrenome: " + ControllerUsuario.usuarioLogado.getSobreNome());
        labelSobrenome.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelSobrenome, 0, 2);

        Label labelEmail = new Label("Email: " + ControllerUsuario.usuarioLogado.getEmail());
        labelEmail.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelEmail, 0, 3);

        Label labelSenha = new Label("Senha: " + ControllerUsuario.usuarioLogado.getSenha());
        labelSenha.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelSenha, 0, 4);

        Label labelCpf = new Label("CPF: " + ControllerUsuario.usuarioLogado.getCpf());
        labelCpf.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelCpf, 0, 5);

        Label labelTelefone = new Label("Telefone: " + ControllerUsuario.usuarioLogado.getTelefone());
        labelTelefone.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelTelefone, 0, 6);

        Label labelDataNascimento = new Label("Data de Nascimento: " + ControllerUsuario.usuarioLogado.getDataNascimento());
        labelDataNascimento.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelDataNascimento, 0, 7);

        Label labelGenero = new Label("Gênero: " + ControllerUsuario.usuarioLogado.getGenero());
        labelGenero.setStyle("-fx-font-size: 16px;");
        telaPerfilPrestador.add(labelGenero, 0, 8);

        if (ControllerUsuario.usuarioLogado instanceof Prestador) {
            Prestador prestador = (Prestador) ControllerUsuario.usuarioLogado;
            String cnpj = prestador.getCnpj();
            String chavePix  = prestador.getChavePix();

            Label labelCnpj = new Label("CNPJ: " + cnpj);
            labelCnpj.setStyle("-fx-font-size: 16px;");
            telaPerfilPrestador.add(labelCnpj, 0, 9);

            Label labelChavePix = new Label("Chave Pix: " + chavePix);
            labelChavePix.setStyle("-fx-font-size: 16px;");
            telaPerfilPrestador.add(labelChavePix, 0, 10);
        }


        // Botões
        int linhaBotoes = 11;

        Button botaoEditar = new Button("Editar");
        botaoEditar.setPrefWidth(150);
        botaoEditar.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");
        telaPerfilPrestador.add(botaoEditar, 0, linhaBotoes);
        botaoEditar.setOnAction(e -> {
            System.out.println("Vai editar Usuário");
        });

        Button botaoExcluir = new Button("Excluir");
        botaoExcluir.setPrefWidth(150);
        botaoExcluir.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        telaPerfilPrestador.add(botaoExcluir, 1, linhaBotoes);
        botaoExcluir.setOnAction(e -> {
            System.out.println("Vai excluir Usuário");
        });

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setPrefWidth(150);
        botaoVoltar.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        telaPerfilPrestador.add(botaoVoltar, 0, linhaBotoes + 1, 2, 1);
        GridPane.setHalignment(botaoVoltar, HPos.CENTER);
        botaoVoltar.setOnAction(e -> {
            Main.mudarCena(TelaPrestadorInicial.criarTela());
        });

        return new Scene(telaPerfilPrestador, 1100, 700);
    }
}
