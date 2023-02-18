module com.example.ecomm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ecomm to javafx.fxml;
    exports com.example.ecomm;
}