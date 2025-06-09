module br.pucpr.chamuzepoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.pucpr.chamuzepoo to javafx.fxml;
    exports br.pucpr.chamuzepoo;
}