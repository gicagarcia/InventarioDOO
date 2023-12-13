package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class WarehousemanUIController {
    public void goToPersonScene(ActionEvent actionEvent) throws Exception {
        PersonManagementUIView personView = new PersonManagementUIView();
        personView.show();
    }

    public void goToPlaceScene(ActionEvent actionEvent) throws Exception {
        PlaceManagementUIView place = new PlaceManagementUIView();
        place.show();
    }

    public void goToItemScene(ActionEvent actionEvent) throws Exception {
        ItemManagementUIView item = new ItemManagementUIView();
        item.show();
    }

    public void goToInventoryScene(ActionEvent actionEvent) throws Exception {
        InventoryManagementUIView view = new InventoryManagementUIView();
        view.show();
    }

    public void goToCategory(ActionEvent actionEvent) throws Exception {
        CategoryManagementUIView view = new CategoryManagementUIView();
        view.show();
    }

    public void goToGoods(ActionEvent actionEvent) throws Exception {
        GoodsManagementUIView view = new GoodsManagementUIView();
        view.show();
    }
}
