package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
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

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class ItemManagementUIController {

    @FXML
    private TableView<Item> tableItem;
    @FXML
    private TableColumn<Item, String> cTag;
    @FXML
    private TableColumn<Item, String> cDesc;
    @FXML
    private TableColumn<Item, String> cStatus;
    @FXML
    private TableColumn<Item, String> cGoods;
    @FXML
    private TableColumn<Item, String> cResp;
    @FXML
    private TableColumn<Item, String> cPlace;

    private ObservableList<Item> tableData;

    private UIMode mode;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableItem.setItems(tableData);
    }

    private void bindColumnToValueSources() {
        cTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        cDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cGoods.setCellValueFactory(new PropertyValueFactory<>("goods"));
        cResp.setCellValueFactory(new PropertyValueFactory<>("responsible"));
        cPlace.setCellValueFactory(new PropertyValueFactory<>("place"));
    }


    private void loadDataAndShow() {
        List<Item> items = findItemUseCase.findAll();
        tableData.clear();
        tableData.addAll(items);
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        WarehousemanUIView view = new WarehousemanUIView();
        view.show();
    }

    public void insertItem(ActionEvent actionEvent) throws Exception {
        ItemNewOrUpdateUIView view = new ItemNewOrUpdateUIView();
        view.show();
    }

    public void editItem(ActionEvent actionEvent) throws Exception {
        showItemInMode(UIMode.UPDATE);
    }

    public void deleteItem(ActionEvent actionEvent) {
        Item selectedItem = tableItem.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            deleteItemUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void detailItem(ActionEvent actionEvent) throws Exception {
        showItemInMode(UIMode.VIEW);
    }

    private void showItemInMode(UIMode mode) throws Exception {
        Item selectedItem = tableItem.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/ItemNewOrUpdateUI.fxml"));
            Parent root = loader.load();
            ItemNewOrUpdateUIController controller = loader.getController();

            // Show the view
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.setItem(selectedItem, mode);
        }
    }
}
