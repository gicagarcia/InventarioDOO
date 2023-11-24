module br.edu.ifsp.inventariodoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.ifsp.inventariodoo.application.view to javafx.fxml;
    opens br.edu.ifsp.inventariodoo.application.controller to javafx.fxml;
    exports br.edu.ifsp.inventariodoo.application.view;
    exports br.edu.ifsp.inventariodoo.application.controller;
}