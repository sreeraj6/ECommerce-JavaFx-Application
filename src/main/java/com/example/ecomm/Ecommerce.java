package com.example.ecomm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {
    private GridPane headerBar(){
        GridPane header = new GridPane();

        //put search area and button
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");

        header.add(searchBar,0,0);
        header.add(searchButton,1,0);

        return header;
    }
    private Pane createContent(){
        Pane root = new Pane();
        //add header to pane
        root.getChildren().add(headerBar());

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}