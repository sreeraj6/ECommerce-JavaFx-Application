package com.example.ecomm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {

    private final int width = 500, height = 400, headerLine = 50;

    ProductList productList = new ProductList();
    Pane bodyPane;

    Button signInButton = new Button("Sign in");
    Label welcomeLabel = new Label("Welcome Customer");

    Customer loggedInCustomer = null;

    private GridPane headerBar(){
        GridPane header = new GridPane();

        //put search area and button
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");
        searchBar.setPromptText("Search here");

        header.setHgap(10);
        searchButton.setOnAction(new EventHandler <ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productList.getAllProducts());
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPanel());
            }
        });

        header.setVgap(10);

        //set position in the grid
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(signInButton,2,0);
        header.add(welcomeLabel,3,0);
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
                loggedInCustomer = Login.customerLogin(user,pass);

                if(loggedInCustomer != null){
                    messageLabel.setText("Valid User");
                    welcomeLabel.setText("Welcome" + loggedInCustomer.getName());
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

    private GridPane signup() {

        Label userLabel = new Label("Enter name");
        Label passLabel = new Label("Password");
        Label emailLabel = new Label("Email");
        Label mobileLabel = new Label("Mobile");
        Label addressLabel = new Label("Address");

        TextField userName = new TextField();
        userName.setPromptText("Enter you name");

        TextField emailFied = new TextField();
        emailFied.setPromptText("Enter email id");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter mobile number");

        TextField addressField = new TextField();
        addressField.setPromptText("Enter your address");


        Button signupButton = new Button("Signup");
        signupButton.setStyle("-fx-background-color: MediumSeaGreen");


        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userName.getText();
                String password = passwordField.getText();
                String email = emailFied.getText();
                String mobile = mobileField.getText();
                String address = addressField.getText();

                boolean user = Signup.customerSignup(username,email,password,mobile,address);
                if(user){
                    showDialoge("user Signed up");
                }
                else {
                    showDialoge("You are existing user");
                }
            }
        });


        GridPane signupPane = new GridPane();
        signupPane.setTranslateY(50);
        signupPane.setVgap(10);
        signupPane.setHgap(10);

        //add Label and input field
        signupPane.add(userLabel,0,0);
        signupPane.add(userName,1,0);
        signupPane.add(emailLabel,0,1);
        signupPane.add(emailFied,1,1);
        signupPane.add(passLabel,0,2);
        signupPane.add(passwordField,1,2);
        signupPane.add(mobileLabel,0,3);
        signupPane.add(mobileField,1,3);
        signupPane.add(addressLabel,0,4);
        signupPane.add(addressField,1,4);
        signupPane.add(signupButton,1,5);
        return signupPane;
    }
    private GridPane footerBar() {
        Button buyNowButton = new Button("Buy Now");

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                boolean orderStatus = false;
                Order order = new Order();
                if(product != null && loggedInCustomer != null){
                    order.placeOrder(loggedInCustomer, product);
                    orderStatus = true;
                }
                if(orderStatus == true){
                    showDialoge("Order Placed");
                }
                else{
                    showDialoge("Error Occured");
                }
            }
        });

        GridPane footer = new GridPane();

        footer.setTranslateY(headerLine+height);
        footer.add(buyNowButton,0,0);

        return footer;
    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width + 2 * headerLine,height+2 * headerLine);  //set Size of the window
        root.setStyle("-fx-background-color: #96D4E7;");
        bodyPane = new Pane();

        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);

        bodyPane.getChildren().add(signup());
        //add header to pane
        root.getChildren().addAll(
                headerBar(),
//                loginPanel(),
//                productList.getAllProducts()
                bodyPane,
                footerBar()
        );
        return root;
    }

    private void showDialoge(String message){
        Dialog<String> dialog = new Dialog<>();

        dialog.setTitle("Confirmation");

        ButtonType type = new ButtonType("Ok",ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);

        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
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