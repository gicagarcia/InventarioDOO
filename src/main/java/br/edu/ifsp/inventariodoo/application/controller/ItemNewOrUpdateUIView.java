package br.edu.ifsp.inventariodoo.application.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class ItemNewOrUpdateUIView  {
    public void show() throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/ItemNewOrUpdateUI.fxml")));
        final Scene scene = new Scene(graph, 600, 400);
        final Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
