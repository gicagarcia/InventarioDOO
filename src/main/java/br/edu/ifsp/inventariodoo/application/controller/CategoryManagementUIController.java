package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
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

import java.util.List;

import static br.edu.ifsp.inventariodoo.application.main.Main.deleteCategoryUseCase;
import static br.edu.ifsp.inventariodoo.application.main.Main.findCategoryUseCase;

public class CategoryManagementUIController {
    @FXML
    private TableView<Category> tableCategory;
    @FXML
    private TableColumn<Category, String> cID;
    @FXML
    private TableColumn<Category, String> cName;
    @FXML
    private TableColumn<Category, String> cArea;
    @FXML
    private TableColumn<Category, String> cApplication;

    private ObservableList<Category> tableData;

    private UIMode mode;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableCategory.setItems(tableData);
    }

    private void bindColumnToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        cApplication.setCellValueFactory(new PropertyValueFactory<>("application"));
    }

    private void loadDataAndShow() {
        List<Category> categories = findCategoryUseCase.findAll();
        tableData.clear();
        tableData.addAll(categories);
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        WarehousemanUIView view = new WarehousemanUIView();
        view.show();
    }

    public void insertCategory(ActionEvent actionEvent) throws Exception {
        CategoryNewOrUpdateView view = new CategoryNewOrUpdateView();
        view.show();
    }

    public void editCategory(ActionEvent actionEvent) throws Exception {
        showCategoryInMode(UIMode.UPDATE);
    }

    private void showCategoryInMode(UIMode uiMode) throws Exception {
        Category selectedCategory = tableCategory.getSelectionModel().getSelectedItem();
        if(selectedCategory != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/CategoryNewOrUpdateUI.fxml"));
            Parent root = loader.load();
            CategoryNewOrUpdateController controller = loader.getController();

            // Show the view
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.setCategory(selectedCategory, mode);
        }
    }

    public void deleteCategory(ActionEvent actionEvent) {
        Category selectedCategory = tableCategory.getSelectionModel().getSelectedItem();
        if(selectedCategory != null){
            deleteCategoryUseCase.delete(selectedCategory);
            loadDataAndShow();
        }
    }

    public void detailCategory(ActionEvent actionEvent) throws Exception {
        showCategoryInMode(UIMode.VIEW);
    }
}
