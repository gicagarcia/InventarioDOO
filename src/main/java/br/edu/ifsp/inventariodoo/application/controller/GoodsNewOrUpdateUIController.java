package br.edu.ifsp.inventariodoo.application.controller;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class GoodsNewOrUpdateUIController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtOrigin;
    @FXML
    private TextField txtCaract;
    @FXML
    private TextField txtCategory;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    Goods goods;

    private Goods getEntityToView(){
        if(goods == null){
            goods = new Goods();
        }
        goods.setName(txtName.getText());
        goods.setOrigin(txtOrigin.getText());
        goods.setCharacteristics(txtCaract.getText());
        Optional<Category> category = findCategoryUseCase.findOneByName(txtCategory.getText());
        category.ifPresent(goods::setCategory);
        if(category.isEmpty()){
            alerta("Erro de categoria", "", "Informe uma categoria válida");
        }
        return goods;
    }

    private void setEntityIntoView(Goods goods){
        txtName.setText(goods.getName());
        txtOrigin.setText(goods.getOrigin());
        txtCaract.setText(goods.getCharacteristics());
        txtCategory.setText(goods.getCategory().getName());
    }

    public void backScene(ActionEvent actionEvent) throws Exception {
        GoodsManagementUIView view = new GoodsManagementUIView();
        view.show();
    }

    public void setGoods(Goods goods, UIMode mode) {
        if(goods == null){
            throw new IllegalArgumentException("Goods can not be null");
        }
        //this.goods = selectedGoods;
        setEntityIntoView(goods);

        if(mode == UIMode.VIEW)
            configureViewMode();
    }

    private void configureViewMode() {
        btnBack.setLayoutX(btnSave.getLayoutX());
        btnBack.setLayoutY(btnSave.getLayoutY());
        btnBack.setText("Fechar");

        btnSave.setVisible(false);

        txtName.setDisable(true);
        txtCaract.setDisable(true);
        txtOrigin.setDisable(true);
        txtCategory.setDisable(true);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws Exception {
        goods = getEntityToView();
        if(goods.getId() == null){
            createGoodsUseCase.insert(goods);
        }else{
            updateGoodsUseCase.update(goods);
        }
        GoodsManagementUIView view = new GoodsManagementUIView();
        view.show();
    }

    private void alerta(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }
}
