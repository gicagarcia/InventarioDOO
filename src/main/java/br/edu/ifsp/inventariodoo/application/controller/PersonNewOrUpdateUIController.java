package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import br.edu.ifsp.inventariodoo.domain.usecases.person.CreatePersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.FindPersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.person.UpdatePersonUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.List;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class PersonNewOrUpdateUIController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtRegistration;
    @FXML
    private TextField txtSecretPhrase;
    @FXML
    private TextField txtSecretAnswer;
    @FXML
    private TextField txtPassword;
    @FXML
    private CheckBox cbPresident;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    private Person person;

    private Person getEntitytoView(){
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String id = txtRegistration.getText();

        if(person == null){
            if(cbPresident.isSelected()){
                String password = txtPassword.getText();
                person = Person.asPremier(id, name, email, phone, password);
                person.registerSecretPhrase(txtSecretPhrase.getText(), txtSecretAnswer.getText());
            }else{
                person = Person.asPerson(id, name, email, phone);
            }
        }
        return person;
    }

    private void setEntityIntoView(Person person){
        System.out.println(person.getName());
        txtName.setText(person.getName());
        txtEmail.setText(person.getEmail());
        txtPhone.setText(person.getPhone());
        txtRegistration.setText(person.getRegistrationId());
        if(person.hasRole(TypeWorker.PREMIER))
            cbPresident.isSelected();
        else
            cbPresident.isDisabled();
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        PersonManagementUIView view = new PersonManagementUIView();
        view.show();
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws Exception {
        person = getEntitytoView();
        if(findPersonUseCase.findOne(person.getRegistrationId()).isEmpty()){
            createPersonUseCase.insert(person);
            System.out.println(person.toString());
        }else{
            System.out.println("existe");
            updatePersonUseCase.update(person);
        }
        PersonManagementUIView view = new PersonManagementUIView();
        view.show();
    }

    public void setPerson(Person person, UIMode mode) {
        if(person == null){
            throw new IllegalArgumentException("Person can not be null");
        }
        this.person = person;
        setEntityIntoView(person);

        if(mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnBack.setLayoutX(btnSave.getLayoutX());
        btnBack.setLayoutY(btnSave.getLayoutY());
        btnBack.setText("Fechar");

        btnSave.setVisible(false);

        txtName.setDisable(true);
        txtEmail.setDisable(true);
        txtPhone.setDisable(true);;
        txtRegistration.setDisable(true);
        txtPassword.setDisable(true);
        txtSecretPhrase.setDisable(true);
        txtSecretAnswer.setDisable(true);
    }
}
