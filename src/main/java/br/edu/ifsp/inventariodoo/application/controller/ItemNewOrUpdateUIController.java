package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class ItemNewOrUpdateUIController {
    @FXML
    private TextField txtTag;
    @FXML
    private TextField txtGoods;
    @FXML
    private TextField txtResp;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtDesc;
    @FXML
    private ChoiceBox<StatusItem> cbStatus;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    Item item;

    @FXML
    private void initialize(){
        StatusItem[] valores = StatusItem.values();
        ObservableList<StatusItem> opcoes = FXCollections.observableArrayList(valores);
        cbStatus.setItems(opcoes);
    }

    private Item getEntityToView(){
        if(item == null){
            item = new Item();
        }
        item.setTag(txtTag.getText());
        item.setDescription(txtDesc.getText());
        item.setStatus(cbStatus.getValue());

        Optional<Goods> optionalGoods = findGoodsUseCase.findOneByName(txtGoods.getText());
        optionalGoods.ifPresent(item::setGoods);

        Optional<Person> optionalPerson = findPersonUseCase.findOneByEmail(txtResp.getText());
        optionalPerson.ifPresent(item::setResponsible);

        Optional<Place> optionalPlace = findPlaceUseCase.findOne(Integer.parseInt(txtPlace.getText()));
        optionalPlace.ifPresent(item::setPlace);

        return item;
    }

    private void setEntityIntoView(Item item){
        txtTag.setText(item.getTag());
        txtResp.setText(item.getResponsible().getEmail());
        txtDesc.setText(item.getDescription());
        txtGoods.setText(item.getGoods().getName());
        txtPlace.setText(item.getPlace().getId().toString());
        cbStatus.setValue(item.getStatus());
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        ItemManagementUIView view = new ItemManagementUIView();
        view.show();
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws Exception {
        item = getEntityToView();
        if(findItemUseCase.findOne(item.getTag()).isEmpty()){
            createItemUseCase.insert(item);
        }else{
            updateItemUseCase.update(item);
        }
        ItemManagementUIView view = new ItemManagementUIView();
        view.show();;
    }

    public void setItem(Item item, UIMode mode) {
        if(item == null){
            throw new IllegalArgumentException("Goods can not be null");
        }
        //this.goods = selectedGoods;
        setEntityIntoView(item);

        if(mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnBack.setLayoutX(btnSave.getLayoutX());
        btnBack.setLayoutY(btnSave.getLayoutY());
        btnBack.setText("Fechar");

        btnSave.setVisible(false);

        txtTag.setDisable(true);
        txtPlace.setDisable(true);
        txtGoods.setDisable(true);
        txtDesc.setDisable(true);
        txtResp.setDisable(true);
    }
}
