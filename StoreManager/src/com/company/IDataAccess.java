package com.company;

public interface IDataAccess {
    public void connect();
    public ProductModel loadProduct(int id);
    public boolean saveProduct(ProductModel product);

    public CustomerModel loadCustomer(int id);
    public boolean saveCustomer(CustomerModel customer);

    public OrderModel loadOrder(int id);
    public boolean saveOrder(OrderModel order);
}
