package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class GoodsManagementUIController {

    @FXML
    private TableView<Goods> tableGoods;
    @FXML
    private TableColumn<Goods, String> cID;
    @FXML
    private TableColumn<Goods, String> cName;
    @FXML
    private TableColumn<Goods, String> cOrigin;
    @FXML
    private TableColumn<Goods, String> cCaract;
    @FXML
    private TableColumn<Goods, String> cCategory;

    private ObservableList<Goods> tableData;

    private UIMode mode;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableGoods.setItems(tableData);
    }

    private void bindColumnToValueSources() {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cOrigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
        cCaract.setCellValueFactory(new PropertyValueFactory<>("characteristics"));
        cCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

    }

    private void loadDataAndShow() {
        List<Goods> goods = findGoodsUseCase.findAll();
        tableData.clear();
        tableData.addAll(goods);
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        WarehousemanUIView view = new WarehousemanUIView();
        view.show();
    }

    public void insertGoods(ActionEvent actionEvent) throws Exception {
        GoodsNewOrUpdateUIView view = new GoodsNewOrUpdateUIView();
        view.show();
    }

    public void editGoods(ActionEvent actionEvent) throws Exception {
        showGoodsInMode(UIMode.UPDATE);
    }

    private void showGoodsInMode(UIMode uiMode) throws Exception {
        Goods selectedGoods = tableGoods.getSelectionModel().getSelectedItem();
        if(selectedGoods != null){
            GoodsNewOrUpdateUIController controller = new GoodsNewOrUpdateUIController();
            GoodsNewOrUpdateUIView view = new GoodsNewOrUpdateUIView();
            view.show();
            controller.setGoods(selectedGoods, mode);
        }
    }

    public void deleteGoods(ActionEvent actionEvent) {
        Goods selectedGoods = tableGoods.getSelectionModel().getSelectedItem();
        if(selectedGoods != null){
            deleteGoodsUseCase.delete(selectedGoods);
            loadDataAndShow();
        }
    }

    public void detailGoods(ActionEvent actionEvent) throws Exception {
        showGoodsInMode(UIMode.VIEW);
    }
}
