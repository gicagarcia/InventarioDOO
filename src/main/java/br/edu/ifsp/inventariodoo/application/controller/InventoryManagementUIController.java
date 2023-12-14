package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
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

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.inventariodoo.application.main.Main.findInventoryUseCase;
import static br.edu.ifsp.inventariodoo.application.main.Main.findPersonUseCase;

public class InventoryManagementUIController {

    @FXML
    private TableView<Inventory> tableInventory;
    @FXML
    private TableColumn<Inventory, String> cID;
    @FXML
    private TableColumn<Inventory, String> cPresident;

    private ObservableList<Inventory> tableData;

    Inventory inventory;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableInventory.setItems(tableData);
    }

    private void bindColumnToValueSources() {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cPresident.setCellValueFactory(new PropertyValueFactory<>("president"));
    }

    private void loadDataAndShow() {
        List<Inventory> inventories = findInventoryUseCase.findAll();
        System.out.println(inventories);
        tableData.clear();
        tableData.addAll(inventories);
    }

    public void newInventory(ActionEvent actionEvent) throws Exception {
        InventoryNewOrViewUIView view = new InventoryNewOrViewUIView();
        view.show();
    }

    public void detail(ActionEvent actionEvent) throws Exception {
        showInventoryInMode(UIMode.VIEW);
    }

    public void addRegister(ActionEvent actionEvent) throws Exception {
        showAditionInMode(UIMode.REGISTER);
    }

    public void export(ActionEvent actionEvent) {
        Inventory selectedInventory = tableInventory.getSelectionModel().getSelectedItem();
        if(selectedInventory != null){
            String caminho = "../br.edu.ifsp.inventariodoo/MeuInventario.csv";

            try (FileWriter writer = new FileWriter(caminho)) {
                writer.append("id,president,itensInventoried,inventors\n");
                writer.append(inventory.toCSV() + "\n");
                System.out.println("Objeto exportado para CSV com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addInventor(ActionEvent actionEvent) throws Exception {
        showAditionInMode(UIMode.INVENTOR);

    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        WarehousemanUIView view = new WarehousemanUIView();
        view.show();
    }

    private void showAditionInMode(UIMode mode) throws Exception{
        Inventory selectedInventory = tableInventory.getSelectionModel().getSelectedItem();
        if(selectedInventory != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/AddRegisterOrInventorUI.fxml"));
            Parent root = loader.load();
            AddRegisterOrInventorUIController controller = loader.getController();


            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.setInventory(selectedInventory, mode);
        }
    }

    private void showInventoryInMode(UIMode mode) throws Exception {
        Inventory selectedInventory = tableInventory.getSelectionModel().getSelectedItem();
        if(selectedInventory != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/InventoryNewOrViewUI.fxml"));
            Parent root = loader.load();
            InventoryNewOrViewUIController controller = loader.getController();

            Inventory inventory1 = findInventoryUseCase.findOne(selectedInventory.getId()).get();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            controller.setInventory(inventory1, mode);
        }
    }


}
