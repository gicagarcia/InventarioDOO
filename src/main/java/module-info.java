module br.edu.ifsp.inventariodoo {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.inventariodoo.application.view to javafx.fxml;
    opens br.edu.ifsp.inventariodoo.application.controller to javafx.fxml;
    opens br.edu.ifsp.inventariodoo.domain.entities.user to javafx.base;
    opens br.edu.ifsp.inventariodoo.domain.entities.item to javafx.base;
    exports br.edu.ifsp.inventariodoo.application.controller;
}