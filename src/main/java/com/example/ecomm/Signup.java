package com.example.ecomm;

public class Signup extends Login {

    public static boolean customerSignup(String userName, String email, String password, String mobile,String address){

        String encryptedPassword = getEcryptedPassword(password);
        try {
            String insertCustomer = "insert into customer(name,password,email,mobile,address) values ('"+userName+"','"+encryptedPassword+"','"+email+"','"+mobile+"','"+address+"')";
            System.out.println(insertCustomer);
            DatabaseConnection dbConn = new DatabaseConnection();
            return  dbConn.insertUpdate(insertCustomer);
        }
        catch (Exception e){
            e.printStackTrace();
        }
       return false;
    }
}
