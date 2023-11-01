module br.edu.ifsp.inventariodoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.ifsp.inventariodoo to javafx.fxml;
    exports br.edu.ifsp.inventariodoo;
}