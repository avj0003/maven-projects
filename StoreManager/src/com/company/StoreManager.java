package com.company;

public class StoreManager {

    public static void main(String[] args) {
        System.out.println("Hello class!");
        IDataAccess adapter = new SQLiteDataAdapter();
        adapter.connect();
        ProductModel product = adapter.loadProduct(1);
        CustomerModel customer = adapter.loadCustomer(1);
        OrderModel order = adapter.loadOrder(1);
        System.out.println("Product: ID = " + product.mProductID + " Name = " + product.mName);
        AddProductView addProductView = new AddProductView();
        AddProductController addProductController = new AddProductController(addProductView, adapter);

        System.out.println("Customer: ID = " + customer.mCustomerID + " Name = " + customer.mName);
        AddCustomerView addCustomerView = new AddCustomerView();
        AddCustomerController addCustomerController = new AddCustomerController(addCustomerView, adapter);

        System.out.println("Customer: ID = " + order.mCustomerID + " Product ID= " + order.mProductID);
        AddOrderView addOrderView = new AddOrderView();
        AddOrderController addOrderController = new AddOrderController(addOrderView, adapter);

        addProductView.setVisible(true);
        addCustomerView.setVisible(true);
        addOrderView.setVisible(true);

    }
}
