package com.example.ecomm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;


public class Login {

    //Encrpyt password
    private static byte[] getSha(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    //Encrypt password
    protected static String getEcryptedPassword(String password){

        try {
            BigInteger num = new BigInteger(1,getSha(password));
            StringBuilder hexString = new StringBuilder(num.toString(16));
            return hexString.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public static Customer customerLogin(String userId, String password) {
        String encryptedPass = getEcryptedPassword(password);
        String query = "select * from customer where email = "+'"'+userId+'"'+" and password = "+'"' + encryptedPass+'"'+"";
        DatabaseConnection dbConn = new DatabaseConnection();

        try {
            ResultSet rs = dbConn.getQueryTable(query);
            if(rs != null && rs.next()){
                return new Customer(
                        rs.getInt("cid"),
                        rs.getString("email"),
                        rs.getString("email")
                );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }



}
