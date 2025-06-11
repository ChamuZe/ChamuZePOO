package br.pucpr.chamuzepoo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import br.pucpr.chamuzepoo.view.TelaLogin;

public class Main extends Application{
    private static Stage palco;

    @Override
    public void start(Stage primeiraCena) throws Exception{
        palco = primeiraCena;
        palco.setTitle("ChamuZé");
        mudarCena(TelaLogin.criarTela());
        palco.show();

    }

    public static void mudarCena(Scene cena){
        palco.setScene(cena);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
