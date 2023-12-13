package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    private UIMode mode;

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

    public void backScene(ActionEvent actionEvent) throws Exception {
        WarehousemanUIView view = new WarehousemanUIView();
        view.show();
    }

    public void insertPerson(ActionEvent actionEvent) throws Exception {
        PersonNewOrUpdateView view = new PersonNewOrUpdateView();
        view.show();
    }

    public void editPerson(ActionEvent actionEvent) throws Exception {
        showPersonInMode(UIMode.UPDATE);
    }

    public void deletePerson(ActionEvent actionEvent) {
        Person selectedItem = tablePerson.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            deletePersonUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void detailPerson(ActionEvent actionEvent) throws Exception {
        showPersonInMode(UIMode.VIEW);
    }

    public void showPersonInMode(UIMode mode) throws Exception {
        Person selectedPerson = tablePerson.getSelectionModel().getSelectedItem();
        if(selectedPerson != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/PersonNewOrUpdateUI.fxml"));
            Parent root = loader.load();
            PersonNewOrUpdateUIController controller = loader.getController();

            // Show the view
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.setPerson(selectedPerson, mode);
        }
    }
}
