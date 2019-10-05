package com.company;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDataAdapter implements IDataAccess {
    Connection conn = null;

    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/ttn0007/Documents/store.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ProductModel loadProduct(int productID) {
        ProductModel product = new ProductModel();

        try {
            String sql = "SELECT ProductId, Name, Price, Quantity FROM Products WHERE ProductId = " + productID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            product.mProductID = rs.getInt("ProductId");
            product.mName = rs.getString("Name");
            product.mPrice = rs.getDouble("Price");
            product.mQuantity = rs.getDouble("Quantity");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
    public boolean saveProduct(ProductModel product) {

        return true;
    }

    @Override
    public CustomerModel loadCustomer(int id) {
        CustomerModel customer = new CustomerModel();

        try {
            String sql = "SELECT customerID, name, address, phone FROM Customers WHERE customerID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            customer.mCustomerID = rs.getInt("customerID");
            customer.mName = rs.getString("name");
            customer.mAddress = rs.getString("address");
            customer.mPhone = rs.getString("phone");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    @Override
    public boolean saveCustomer(CustomerModel customer) {
        return false;
    }


    @Override
    public OrderModel loadOrder(int id) {
        OrderModel order = new OrderModel();

        try {
            String sql = "SELECT * FROM Orders WHERE orderID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            order.mCustomerID = rs.getInt("customerID");
            order.mProductID = rs.getInt("productID");
            order.mTax = Double.parseDouble(rs.getString("tax"));
            order.mCost = Double.parseDouble(rs.getString("cost"));
            order.mPrice = Double.parseDouble(rs.getString("price"));
            order.mQuantity = Double.parseDouble(rs.getString("quantity"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    public boolean saveOrder(OrderModel order) {
        return false;
    }

}
