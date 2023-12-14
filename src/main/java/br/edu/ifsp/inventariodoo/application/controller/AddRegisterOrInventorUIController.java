package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class AddRegisterOrInventorUIController {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtTag;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtResp;
    @FXML
    private TextField txtDesc;
    @FXML
    private ChoiceBox<StatusItem> cbStatus;
    @FXML
    private TextField txtInventor;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnInventor;

    Register register;
    Person person;
    Inventory inventory;

    @FXML
    private void initialize(){
        StatusItem[] valores = StatusItem.values();
        ObservableList<StatusItem> opcoes = FXCollections.observableArrayList(valores);
        cbStatus.setItems(opcoes);
    }

    private Register getEntityToView(){
        if(register == null){
            register = new Register();
        }
        register.setDescription(txtDesc.getText());
        register.setStatus(cbStatus.getValue());

        Optional<Item> optionalItem = findItemUseCase.findOne(txtTag.getText());
        optionalItem.ifPresent(register::setItem);

        Optional<Place> optionalPlace = findPlaceUseCase.findOne(Integer.parseInt(txtPlace.getText()));
        optionalPlace.ifPresent(register::setPlace);

        Optional<Person> optionalPerson = findPersonUseCase.findOneByEmail(txtResp.getText());
        optionalPerson.ifPresent(register::setInventor);

        return register;
    }

    private void setEntityToView(Register register){
        txtDate.setText(register.getRegisterDate().toString());
        txtID.setText(register.getId().toString());
        txtPlace.setText(register.getPlace().getId().toString());
        txtDesc.setText(register.getDescription());
        txtResp.setText(register.getInventor().getEmail());
        txtTag.setText(register.getItem().getTag());
        cbStatus.setValue(register.getStatus());
    }

    public void addRegister(ActionEvent actionEvent) throws Exception {
        register = getEntityToView();
        register.setRegisterDate(LocalDate.now());
        createRegisterUseCase.insert(register);
        List<Register> registerList = findRegisterUseCase.findAll();
        Integer tamanho = registerList.size();
        Register register1 = registerList.get(tamanho - 1);
        this.inventory.addRegister(register1);

        updateInventoryUseCase.update(inventory);

        InventoryManagementUIView view = new InventoryManagementUIView();
        view.show();
    }

    public void addInventor(ActionEvent actionEvent) throws Exception {
        String email = txtInventor.getText();

        Optional<Person> optionalPerson = findPersonUseCase.findOneByEmail(email);
        optionalPerson.ifPresent(person ->{
            Person newPerson = person;
            this.inventory.addInventor(newPerson);
        });

        InventoryManagementUIView view = new InventoryManagementUIView();
        view.show();
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        InventoryManagementUIView view = new InventoryManagementUIView();
        view.show();
    }

    public void setInventory(Inventory selectedInventory, UIMode mode) {
        if(selectedInventory == null){
            throw new IllegalArgumentException("Inventory can not be null");
        }
        this.inventory = selectedInventory;
        configureViewMode(mode);
    }

    public void setRegister(Register selectedRegister, UIMode mode) {
        if(selectedRegister == null){
            throw new IllegalArgumentException("Register can not be null");
        }
        setEntityToView(selectedRegister);

        configureViewMode(mode);
    }

    private void configureViewMode(UIMode mode) {
        if(mode == UIMode.REGISTER){
            txtInventor.setDisable(true);
            btnInventor.setVisible(false);
            txtDate.setDisable(true);
        } else if (mode == UIMode.INVENTOR) {
            txtTag.setDisable(true);
            txtResp.setDisable(true);
            txtDesc.setDisable(true);
            txtID.setDisable(true);
            txtDate.setDisable(true);
            txtPlace.setDisable(true);
            btnRegister.setVisible(false);
        } else if (mode == UIMode.VIEW) {
            txtTag.setDisable(true);
            txtResp.setDisable(true);
            txtDesc.setDisable(true);
            txtID.setDisable(true);
            txtDate.setDisable(true);
            txtPlace.setDisable(true);
            btnRegister.setVisible(false);
            txtInventor.setDisable(true);
            btnInventor.setVisible(false);;
        }

    }


}
