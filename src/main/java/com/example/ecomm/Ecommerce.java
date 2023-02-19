package com.example.ecomm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {

    ProductList productList = new ProductList();
    private GridPane headerBar(){
        GridPane header = new GridPane();

        //put search area and button
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");

        //set position in the grid
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);

        return header;
    }

    private GridPane loginPanel() {
        Label userLabel = new Label("User Name");
        Label passLabel = new Label("Password");

        TextField username = new TextField();
        username.setPromptText("Enter user name");

        PasswordField password = new PasswordField();
        password.setPromptText("Enter password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label("Login message");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = username.getText();
                String pass = password.getText();

                if(Login.customerLogin(user,pass)){
                    messageLabel.setText("Valid User");
                }
                else{
                    messageLabel.setText("Invalid User");
                }

            }
        });


        GridPane loginPane = new GridPane();
        loginPane.setTranslateY(50);
        loginPane.setHgap(10);
        loginPane.setVgap(10);
        loginPane.add(userLabel,0,0);
        loginPane.add(username,1,0);
        loginPane.add(passLabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messageLabel,1,2);
        return loginPane;
    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(500,300);  //set Size of the window

        //add header to pane
        root.getChildren().addAll(headerBar(),loginPanel(),productList.getAllProducts() );
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