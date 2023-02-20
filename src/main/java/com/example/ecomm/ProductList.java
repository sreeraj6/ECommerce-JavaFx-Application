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


        ObservableList<Product> productList = Product.getAllProducts();
        data.addAll(new Product(123,"Laptop",(double)2342.05),
                new Product(123,"Laptop",(double)234.4)
        );


        productTable = new TableView<>();
        productTable.setItems(productList);
        productTable.getColumns().addAll(id,name,price);

        Pane TablePane = new Pane();
        TablePane.getChildren().add(productTable);

        return TablePane;
    }

    public Product getSelectedProduct(){

        return productTable.getSelectionModel().getSelectedItem();
    }

}
