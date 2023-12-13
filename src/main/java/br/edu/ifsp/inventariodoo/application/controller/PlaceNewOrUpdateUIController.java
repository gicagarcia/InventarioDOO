package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class PlaceNewOrUpdateUIController {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtBlock;
    @FXML
    private TextField txtNumber;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    Place place;

    private Place getEntityToView(){
        if(place == null){
            place = new Place();
        }
        place.setBlock(txtBlock.getText());
        place.setNumber(Integer.parseInt(txtNumber.getText()));
        return place;
    }

    private void setEntityIntoView(Place place){
        txtID.setText(place.getId().toString());
        txtBlock.setText(place.getBlock());
        txtNumber.setText(place.getNumber().toString());
    }

    public void goToBackScene(ActionEvent actionEvent) throws Exception {
        PlaceManagementUIView view = new PlaceManagementUIView();
        view.show();
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws Exception {
        place = getEntityToView();
        if(place.getId() == null){
            createPlaceUseCase.insert(place);
        }else{
            updatePlaceUseCase.update(place);
        }
        PlaceManagementUIView view = new PlaceManagementUIView();
        view.show();
    }

    public void setPlace(Place place, UIMode mode) {
        if(place == null){
            throw new IllegalArgumentException("Place can not be null");
        }
        //this.place = selectedPlace;
        setEntityIntoView(place);

        if(mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnBack.setLayoutX(btnSave.getLayoutX());
        btnBack.setLayoutY(btnSave.getLayoutY());
        btnBack.setText("Fechar");

        btnSave.setVisible(false);

        txtNumber.setDisable(true);
        txtBlock.setDisable(true);
    }
}
