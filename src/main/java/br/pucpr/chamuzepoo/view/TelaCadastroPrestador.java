package br.pucpr.chamuzepoo.view;

import br.pucpr.chamuzepoo.Main;
import br.pucpr.chamuzepoo.controller.ControllerUsuario;
import br.pucpr.chamuzepoo.model.Prestador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class TelaCadastroPrestador{
    public static Scene criarTela() {
    // Tela principal (fundo suave)
    GridPane telaCadastroPrestador = new GridPane();
    telaCadastroPrestador.setPadding(new Insets(20));
    telaCadastroPrestador.setAlignment(Pos.CENTER);
    telaCadastroPrestador.setStyle(
            "-fx-background-color: #f8f9fa;" // fundo suave parecido com Bootstrap
    );

    // Caixa branca centralizada, onde ficará o formulário
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

    // Título da tela de cadastro
    Label titulotelaCadastroPrestador = new Label("Cadastro Prestador");
    titulotelaCadastroPrestador.setStyle("-fx-font-size:26px; -fx-font-weight:bold;");
    caixaBranca.add(titulotelaCadastroPrestador, 0, 0, 2, 1);
    GridPane.setHalignment(titulotelaCadastroPrestador, javafx.geometry.HPos.CENTER);

    // Inputs adicionados na caixa branca, coluna 0, linha variável
    int linha = 1; // Começa da linha 1 (linha 0 é título)

    // Nome
    caixaBranca.add(new Label("Nome:"), 0, linha++);
    TextField entradaNome = new TextField();
    caixaBranca.add(entradaNome, 0, linha++);

    // Sobrenome
    caixaBranca.add(new Label("Sobrenome:"), 0, linha++);
    TextField entradaSobrenome = new TextField();
    caixaBranca.add(entradaSobrenome, 0, linha++);

    // E-mail
    caixaBranca.add(new Label("E-mail:"), 0, linha++);
    TextField entradaEmail = new TextField();
    caixaBranca.add(entradaEmail, 0, linha++);

    // Senha
    caixaBranca.add(new Label("Senha:"), 0, linha++);
    PasswordField entradaSenha = new PasswordField();
    caixaBranca.add(entradaSenha, 0, linha++);

    // CPF
    caixaBranca.add(new Label("CPF:"), 0, linha++);
    TextField entradaCPF = new TextField();
    caixaBranca.add(entradaCPF, 0, linha++);

    // Telefone
    caixaBranca.add(new Label("Telefone:"), 0, linha++);
    TextField entradaTelefone = new TextField();
    caixaBranca.add(entradaTelefone, 0, linha++);

    // Data de nascimento
    caixaBranca.add(new Label("Data de nascimento:"), 0, linha++);
    DatePicker entradaDataNascimento = new DatePicker();
    caixaBranca.add(entradaDataNascimento, 0, linha++);

    // Gênero (RadioButtons em HBox)
    caixaBranca.add(new Label("Gênero:"), 0, linha++);
    RadioButton escolhaGeneroM = new RadioButton("M");
    RadioButton escolhaGeneroF = new RadioButton("F");
    RadioButton escolhaGeneroO = new RadioButton("O");
    ToggleGroup grupoGenero = new ToggleGroup();
    escolhaGeneroM.setToggleGroup(grupoGenero);
    escolhaGeneroF.setToggleGroup(grupoGenero);
    escolhaGeneroO.setToggleGroup(grupoGenero);
    HBox boxGenero = new HBox(15, escolhaGeneroM, escolhaGeneroF, escolhaGeneroO);
    caixaBranca.add(boxGenero, 0, linha++);

    // CNPJ
    caixaBranca.add(new Label("CNPJ:"), 0, linha++);
    TextField entradaCNPJ = new TextField();
    caixaBranca.add(entradaCNPJ, 0, linha++);

    // Chave Pix
    caixaBranca.add(new Label("Chave Pix:"), 0, linha++);
    TextField entradaChavePix = new TextField();
    caixaBranca.add(entradaChavePix, 0, linha++);

    // Botão cadastrar
    Button botaoCadastrar = new Button("Cadastrar");
    botaoCadastrar.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-weight: bold;");
    botaoCadastrar.setPrefWidth(200);
    GridPane.setMargin(botaoCadastrar, new Insets(0, 0, 10, 0)); // margem superior de 20px
    caixaBranca.add(botaoCadastrar, 0, linha++);

    // Botão voltar
    Button botaoVoltarParaLogin = new Button("Voltar");
    botaoVoltarParaLogin.setStyle("-fx-background-color: #6C757D; -fx-text-fill: white; -fx-font-weight: bold;");
    botaoVoltarParaLogin.setPrefWidth(100); // largura menor
    caixaBranca.add(botaoVoltarParaLogin, 0, linha++);
    GridPane.setHalignment(botaoVoltarParaLogin, javafx.geometry.HPos.RIGHT); // alinha à direita

    // Cadastra o usuário com validações
    botaoCadastrar.setOnAction(evento -> {
        // Pegando os dados do input
        String nome = entradaNome.getText().trim();
        String sobrenome = entradaSobrenome.getText().trim();
        String email = entradaEmail.getText().trim();
        String senha = entradaSenha.getText().trim();
        String cpf = entradaCPF.getText().trim();
        String telefone = entradaTelefone.getText().trim();
        LocalDate dataNascimento = entradaDataNascimento.getValue();
        Toggle selecionado = grupoGenero.getSelectedToggle();
        String genero = null;
        if (selecionado != null) {
            RadioButton opcaoSelecionada = (RadioButton) selecionado;
            genero = opcaoSelecionada.getText();
        }

        String cnpj = entradaCNPJ.getText().trim();
        String chavePix = entradaChavePix.getText().trim();

        // Validação básica
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos obrigatórios");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, preencha os campos obrigatórios: Nome, E-mail e Senha.");
            alerta.showAndWait();
            return;
        }

        if (genero == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Gênero não selecionado");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, selecione um gênero.");
            alerta.showAndWait();
            return;
        }

        Prestador prestador = new Prestador(nome, sobrenome, email, senha, cpf, telefone, dataNascimento, genero, cnpj, chavePix);
        ControllerUsuario controllerUsuario = new ControllerUsuario(prestador);
        controllerUsuario.cadastrarUsuario();

        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Cadastro realizado");
        sucesso.setHeaderText(null);
        sucesso.setContentText("Prestador cadastrado com sucesso!");
        sucesso.showAndWait();

        System.out.println("Prestador cadastrado com Sucesso!");
        Main.mudarCena(TelaLogin.criarTela());
    });

    // Lógica para voltar para tela de escolha cadastro
    botaoVoltarParaLogin.setOnAction(evento -> {
        Main.mudarCena(TelaEscolhaCadastro.criarTela());
    });

    // Adiciona a caixa branca à tela principal
    telaCadastroPrestador.add(caixaBranca, 0, 0);

    return new Scene(telaCadastroPrestador, 1100, 700);
}

}