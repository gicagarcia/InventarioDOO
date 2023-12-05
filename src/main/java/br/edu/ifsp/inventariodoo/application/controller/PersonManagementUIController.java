package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.application.view.WindowLoader;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.DeletePersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.FindPersonUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.inventariodoo.application.main.Main.deletePersonUseCase;
import static br.edu.ifsp.inventariodoo.application.main.Main.findPersonUseCase;

public class PersonManagementUIController {

    @FXML
    private TableView<Person> tablePerson;
    @FXML
    private TableColumn<Person, String> cName;
    @FXML
    private TableColumn<Person, String> cRegistration;
    @FXML
    private TableColumn<Person, String> cEmail;
    @FXML
    private TableColumn<Person, String> cPhone;

    private ObservableList<Person> tableData;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnToValueSources();
        loadDataAndShow();
    }

    private void loadDataAndShow() {
        List<Person> people = findPersonUseCase.findAll();
        tableData.clear();
        tableData.addAll(people);
    }

    private void bindColumnToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cRegistration.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tablePerson.setItems(tableData);
    }

    public void backScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("WarehousemanUI");
    }

    public void insertPerson(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PersonNewOrUpdateUI");
    }

    public void editPerson(ActionEvent actionEvent) throws IOException {
        showBookInMode(UIMode.UPDATE);
    }

    public void deletePerson(ActionEvent actionEvent) {
        Person selectedItem = tablePerson.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            deletePersonUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void detailPerson(ActionEvent actionEvent) throws IOException {
        showBookInMode(UIMode.VIEW);
    }

    public void showBookInMode(UIMode mode) throws IOException{
        Person selectedPerson = tablePerson.getSelectionModel().getSelectedItem();
        if(selectedPerson != null){
            WindowLoader.setRoot("PersonNewOrUpdateUI");
            PersonNewOrUpdateUIController controller = (PersonNewOrUpdateUIController) WindowLoader.getController();
            controller.setPerson(selectedPerson, mode);
        }
    }
}
