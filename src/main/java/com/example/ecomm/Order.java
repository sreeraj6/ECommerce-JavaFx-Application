package com.example.ecomm;

public class Order {

    public boolean placeOrder(Customer customer,Product product){

        try {
//            insert orders(customer_id,product_id,status) values (1,1,'Ordered');
            String placeOrder = "insert into orders(customer_id,product_id,status) values ("+customer.getId()+","+product.getId()+",'Ordered')";
            System.out.println(placeOrder);
            DatabaseConnection dbconn = new DatabaseConnection();
            return dbconn.insertUpdate(placeOrder);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
