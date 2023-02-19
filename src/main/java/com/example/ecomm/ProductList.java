package com.example.ecomm;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductList {

    public TableView<Product> productTable;

    public Pane getAllProducts(){

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> data = FXCollections.observableArrayList();
        data.addAll(new Product(123,"Laptop",(double)2342.05),
                new Product(123,"Laptop",(double)234.4),
                new Product(223,"Laptop",(double)2342.05),
                new Product(323,"Laptop",(double)2342.05),
                new Product(423,"Laptop",(double)2342.05)

                );


        productTable = new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);

        Pane TablePane = new Pane();
        TablePane.getChildren().add(productTable);

        return TablePane;
    }

}
