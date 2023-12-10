package br.edu.ifsp.inventariodoo.application.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class PlaceDetailUIView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/br/edu/ifsp/inventariodoo/application/view/PlaceDetailUI.fxml")));
        final Scene scene = new Scene(graph, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
