package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.findCategoryUseCase;
import static br.edu.ifsp.inventariodoo.application.main.Main.findPersonUseCase;

public class MainUIController {

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnChangePass;
    @FXML
    private Button btnLogin;


    public void login(ActionEvent actionEvent){
        String email = txtEmail.getText();
        String password = txtPass.getText();
        Optional<Person> optionalPerson = findPersonUseCase.findOneByEmail(email);
        optionalPerson.ifPresent(foundPerson -> {
            Person person = foundPerson;
            if(person.verifyPassword(password, person.getPassword())){
                if(person.hasRole(TypeWorker.WAREHOUSEMAN)){
                    WarehousemanUIView view = new WarehousemanUIView();
                    try {
                        view.show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (person.hasRole(TypeWorker.PREMIER)) {
                    InventoryManagementUIView view = new InventoryManagementUIView();
                    try {
                        view.show();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });



    }

    public void changePassword(ActionEvent actionEvent) {
    }
}
