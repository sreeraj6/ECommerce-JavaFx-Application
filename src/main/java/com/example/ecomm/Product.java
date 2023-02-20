package com.example.ecomm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.ProtectionDomain;
import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId(){
        return id.get();
    }

    public String getName(){
        return name.get();
    }

    public Double getPrice(){
        return price.get();
    }

    public Product(int id, String name,Double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts(){
        String allProductList = "SELECT * FROM products";
        return getProducts(allProductList);
    }
    public static ObservableList<Product> getProducts(String query){

        DatabaseConnection dbconn = new DatabaseConnection();
        ResultSet res = dbconn.getQueryTable(query);
        ObservableList<Product> result = FXCollections.observableArrayList();

        try {
            if(res != null){
                while (res.next()){
                    //Taking value from ResultSet
                    result.add(new Product(
                            res.getInt("pid"),
                            res.getString("name"),
                            res.getDouble("price")

                    ));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
