package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class CategoryNewOrUpdateController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtApplication;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    Category category;

    private Category getEntityToView(){
        if(category == null){
            category = new Category();
        }
        category.setName(txtName.getText());
        category.setArea(txtArea.getText());
        category.setApplication(txtApplication.getText());
        return category;
    }

    private void setEntityIntoView(){
        txtName.setText(category.getName());
        txtArea.setText(category.getArea());
        txtApplication.setText(category.getApplication());
    }


    public void setCategory(Category selectedCategory, UIMode mode) {
        if(category == null){
            throw new IllegalArgumentException("Category can not be null");
        }
        this.category = selectedCategory;
        setEntityIntoView();

        if(mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnBack.setLayoutX(btnSave.getLayoutX());
        btnBack.setLayoutY(btnSave.getLayoutY());
        btnBack.setText("Fechar");

        btnSave.setVisible(false);

        txtName.isDisabled();
        txtApplication.isDisabled();
        txtArea.isDisabled();
    }

    public void saveOrUpdate(ActionEvent actionEvent) {
        category = getEntityToView();
        if(category.getId() == null){
            createCategoryUseCase.insert(category);
        }else{
            updateCategoryUseCase.update(category);
        }
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        CategoryManagementUIView view = new CategoryManagementUIView();
        view.show();
    }
}
