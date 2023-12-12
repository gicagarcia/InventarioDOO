package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.place.FindPlaceUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static br.edu.ifsp.inventariodoo.application.main.Main.deletePlaceUseCase;
import static br.edu.ifsp.inventariodoo.application.main.Main.findPlaceUseCase;

public class PlaceManagementUIController {

    @FXML
    private TableView<Place> tablePlace;
    @FXML
    private TableColumn<Place, Integer> cID;
    @FXML
    private TableColumn<Place, String> cBlock;
    @FXML
    private TableColumn<Place, String> cNumber;

    private ObservableList<Place> tableData;


    @FXML
    private void initialize(){
        bindTableViewToItemList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemList() {
        tableData = FXCollections.observableArrayList();
        tablePlace.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cBlock.setCellValueFactory(new PropertyValueFactory<>("block"));
        cNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
    }

    private void loadDataAndShow() {
        List<Place> places = findPlaceUseCase.findAll();
        tableData.clear();
        tableData.addAll(places);
    }

    private void showPlaceInMode(UIMode mode) throws Exception {
        Place selectedPlace = tablePlace.getSelectionModel().getSelectedItem();
        if(selectedPlace != null){
            PlaceNewOrUpdateUIController controller = new PlaceNewOrUpdateUIController();
            PlaceNewOrUpdateUIView view = new PlaceNewOrUpdateUIView();
            view.show();
            controller.setPlace(selectedPlace, mode);
        }
    }

    public void goToBackScene(ActionEvent actionEvent) throws Exception {
        WarehousemanUIView view = new WarehousemanUIView();
        view.show();
    }

    public void insertPlace(ActionEvent actionEvent) throws Exception {
        PlaceNewOrUpdateUIView newPlace = new PlaceNewOrUpdateUIView();
        newPlace.show();
    }

    public void editPlace(ActionEvent actionEvent) throws Exception {
        showPlaceInMode(UIMode.UPDATE);
    }

    public void detailPlace(ActionEvent actionEvent) throws Exception {
        showPlaceInMode(UIMode.VIEW);
    }

    public void deletePlace(ActionEvent actionEvent) {
        Place place = tablePlace.getSelectionModel().getSelectedItem();
        if(place != null){
            deletePlaceUseCase.delete(place);
            loadDataAndShow();
        }
    }
}
