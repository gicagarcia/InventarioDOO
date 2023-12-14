package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;
import static br.edu.ifsp.inventariodoo.application.main.Main.createPersonUseCase;

public class InventoryNewOrViewUIController {

    @FXML
    private TableView<Register> tableRegister;
    @FXML
    private TableColumn<Register, String> cItem;
    @FXML
    private TableColumn<Register, String> cResp;
    @FXML
    private TableColumn<Register,String> cStatus;
    @FXML
    private TableColumn<Register, String> cData;
    ObservableList<Register> tableDataRegister;

    @FXML
    private TableView<Person> tableInventors;
    @FXML
    private TableColumn<Person, String> cInventors;
    ObservableList<Person> tableDataPerson;

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDetailRegister;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtEmail;

    Inventory inventory;

    @FXML
    private void initialize(){
        bindTableRegisterToItemsList();
        bindTableInventorToItemsList();
        bindColumnToValueSourcesRegister();
        bindColumnToValueSourcesPerson();
    }

    private void bindTableRegisterToItemsList() {
        tableDataRegister = FXCollections.observableArrayList();
        tableRegister.setItems(tableDataRegister);
    }

    private void bindTableInventorToItemsList() {
        tableDataPerson = FXCollections.observableArrayList();
        tableInventors.setItems(tableDataPerson);
    }

    private void bindColumnToValueSourcesRegister() {
        cItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        cResp.setCellValueFactory(new PropertyValueFactory<>("inventor"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cData.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
    }

    private void bindColumnToValueSourcesPerson() {
        cInventors.setCellValueFactory(new PropertyValueFactory<>("email"));
    }


    private Inventory getEntitytoView(){
        String email = txtEmail.getText();

        if(inventory == null){
            Optional<Person> person = findPersonUseCase.findOneByEmail(email);
            person.ifPresent(foundPerson ->{
                Person person1 = foundPerson;
                inventory = Inventory.withoutLists(person1);
            });
        }
        return inventory;
    }

    private void setEntityIntoView(Inventory inventory){
        System.out.println(inventory.toString());
        txtID.setText(inventory.getId().toString());
        txtEmail.setText(inventory.getPresident().getEmail());
        List<Register> registers = inventory.getItensInventoried();
        List<Person> inventors = inventory.getInventors();


        ObservableList<Register> observableRegister = FXCollections.observableList(registers);
        ObservableList<Person> observablePerson = FXCollections.observableList(inventors);

        tableDataRegister.clear();
        tableDataRegister.addAll(observableRegister);

        tableDataPerson.clear();
        tableDataPerson.addAll(observablePerson);
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        InventoryManagementUIView view = new InventoryManagementUIView();
        view.show();
    }

    public void detailRegister(ActionEvent actionEvent) throws Exception {
        showAditionInMode(UIMode.VIEW);
    }

    public void saveInventory(ActionEvent actionEvent) throws Exception {
        inventory = getEntitytoView();

        createInventoryUseCase.insert(inventory);

        InventoryManagementUIView view = new InventoryManagementUIView();
        view.show();
    }

    public void setInventory(Inventory inventory, UIMode mode) {
        if(inventory == null){
            throw new IllegalArgumentException("Inventory can not be null");
        }
        setEntityIntoView(inventory);

        if(mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnBack.setText("Fechar");

        btnSave.setVisible(false);
        btnDetailRegister.setVisible(false);

        txtID.setDisable(true);
        txtEmail.setDisable(true);
    }

    private void showAditionInMode(UIMode mode) throws Exception{
        Register selectedRegister = tableRegister.getSelectionModel().getSelectedItem();
        if(selectedRegister != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/AddRegisterOrInventorUI.fxml"));
            Parent root = loader.load();
            AddRegisterOrInventorUIController controller = loader.getController();

            // Show the view
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.setRegister(selectedRegister, mode);
        }
    }
}
