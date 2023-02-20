package com.example.ecomm;

import java.sql.*;
public class DatabaseConnection {

    String dbURL = "jdbc:mysql://localhost:3306/ecomm";
    String username = "root";
    String password = "root";

    //create connection
    private Statement getStatement(){
        try {

            Connection conn = DriverManager.getConnection(dbURL,username,password);
            return conn.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public ResultSet getQueryTable(String Query){
        Statement statement = getStatement();
        try {
            return statement.executeQuery(Query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUpdate(String query){
        Statement statement = getStatement();
        try {
            statement.executeUpdate(query);
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        String Query = "SELECT * FROM products";
//        DatabaseConnection db = new DatabaseConnection();
//        ResultSet rs = db.getQueryTable(Query);
//
//        if(rs != null) {
//            System.out.println("Connected to db");
//        }
//    }
}
