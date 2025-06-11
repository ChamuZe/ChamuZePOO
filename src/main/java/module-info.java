module br.pucpr.chamuzepoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens br.pucpr.chamuzepoo to javafx.fxml;
    exports br.pucpr.chamuzepoo;
}