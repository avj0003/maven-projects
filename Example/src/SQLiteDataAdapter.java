
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDataAdapter implements IDataAdapter {

    Connection conn = null;
    public static String path = "C:\\Users\\abhii\\IdeaProjects\\maven-projects\\Example\\src\\store.db";

    public int connect(String dbfile) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + dbfile;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_OPEN_FAILED;
        }

        return CONNECTION_OPEN_OK;
    }

    @Override
    public int disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return CONNECTION_CLOSE_FAILED;
        }
        return CONNECTION_CLOSE_OK;
    }

    public ProductModel loadProduct(int productID) {
        connect(path);
        ProductModel product = null;
        try {
            String sql = "SELECT * FROM Products WHERE ProductId = " + productID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                product = new ProductModel();
                product.mProductID = rs.getInt("ProductId");
                product.mName = rs.getString("Name");
                product.mPrice = rs.getDouble("Price");
                product.mQuantity = rs.getDouble("Quantity");
                product.mVendor = rs.getString("Vendor");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return product;
    }

    public PurchaseModel loadPurchase(int purchaseId) {
        connect(path);
        PurchaseModel purchase = null;
        try {

            String sql = "SELECT * FROM Orders WHERE OrderId = " + purchaseId;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                purchase = new PurchaseModel();
                purchase.mPurchaseID = rs.getInt("OrderId");
                purchase.mCustomerID = rs.getInt("CustomerId");
                purchase.mProductID = rs.getInt("ProductId");
                purchase.mTax = rs.getDouble("TotalTax");
                purchase.mPrice = rs.getDouble("Price");
                purchase.mQuantity = rs.getDouble("Quantity");
                purchase.mTotal = rs.getDouble("TotalCost");
                purchase.mDate = rs.getString("Date");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return purchase;
    }


    public int saveProduct(ProductModel product) {
        try {
            connect(path);
            Statement stmt = conn.createStatement();
            ProductModel p = loadProduct(product.mProductID); // check if this product exists
            if (p != null) {
                stmt.executeUpdate("DELETE FROM Products WHERE ProductID = " + product.mProductID);
            }

            String sql = "INSERT INTO Products(ProductId, Name, Price, Quantity, Vendor) VALUES " + product;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PRODUCT_SAVE_FAILED;
        } finally {
            disconnect();
        }

        return PRODUCT_SAVE_OK;
    }

    @Override
    public int savePurchase(PurchaseModel purchase) {
        try {
            connect(path);
            String sql = "INSERT INTO Orders VALUES " + purchase;
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PURCHASE_SAVE_FAILED;
        } finally {
            disconnect();
        }
        return PURCHASE_SAVE_OK;
    }

    @Override
    public int updatePurchase(PurchaseModel purchase) {
        try {
            connect(path);
            Statement stmt = conn.createStatement();
            PurchaseModel p = loadPurchase(purchase.mPurchaseID); // check if this product exists
            if (p != null) {
                stmt.executeUpdate("DELETE FROM Orders WHERE OrderId = " + purchase.mPurchaseID);
            }
            String sql = "INSERT INTO Orders VALUES " + purchase;
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return PURCHASE_SAVE_FAILED;
        } finally {
            disconnect();
        }
        return PURCHASE_SAVE_OK;
    }


    @Override
    public int updateCustomer(CustomerModel customerModel) {
        try {
            connect(path);
            Statement stmt = conn.createStatement();
            CustomerModel p = loadCustomer(customerModel.mCustomerID); // check if this product exists
            if (p != null) {
                stmt.executeUpdate("DELETE FROM Customers WHERE CustomerId = " + customerModel.mCustomerID);
            }
            String sql = "INSERT INTO Customers VALUES " + customerModel;
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return CUSTOMER_SAVE_FAILED;
        } finally {
            disconnect();
        }
        return CUSTOMER_SAVE_OK;
    }

    @Override
    public int updateUserInfo(UserModel userModel) {
        int response = USER_SAVE_FAILED;
        try {
            connect(path);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE users SET Fullname='"+ userModel.mFullname+"', Password='"+ userModel.mPassword +"' WHERE Username = '"+ userModel.mUsername +"'";
            stmt.executeUpdate(sql);
            response = USER_SAVE_OK;
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            response = USER_SAVE_FAILED;
        } finally {
            disconnect();
        }
        return response;
    }

    @Override
    public int updateUser(UserModel userModel) {
        int response = USER_SAVE_FAILED;
        try {
            connect(path);
            Statement stmt = conn.createStatement();
            UserModel p = loadUser(userModel.mUsername); // check if this product exists
            if (p != null) {
                stmt.executeUpdate("DELETE FROM Users WHERE Username = '"+p.mUsername+"'");
            }
            ResultSet rs;
            if (userModel.mUserType == UserModel.CUSTOMER) {
                do {
                    int randomUserId = (int) (Math.random()*(9999 - 1)) + 1;
                    String checkUserId = "SELECT CustomerId from Users where CustomerId = '"+ randomUserId +"'";
                    rs = stmt.executeQuery(checkUserId);
                    userModel.mCustomerID = randomUserId;
                } while(rs.next());
            }
            String sql = "INSERT INTO Users VALUES " + userModel;
            stmt.executeUpdate(sql);
            response = USER_SAVE_OK;
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            response = USER_SAVE_FAILED;
        } finally {
            disconnect();
        }
        return response;
    }

    @Override
    public PurchaseListModel loadPurchaseHistory(int id) {
        PurchaseListModel res = new PurchaseListModel();
        try {
            connect(path);
            String sql = "SELECT * FROM Orders WHERE CustomerId = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PurchaseModel purchase = new PurchaseModel();
                purchase.mCustomerID = id;
                purchase.mPurchaseID = rs.getInt("OrderId");
                purchase.mProductID = rs.getInt("ProductId");
                purchase.mPrice = rs.getDouble("Price");
                purchase.mQuantity = rs.getDouble("Quantity");
                purchase.mCost = rs.getDouble("TotalCost");
                purchase.mTax = rs.getDouble("TotalTax");
                purchase.mDate = rs.getString("Date");
                res.purchases.add(purchase);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return res;
    }

    @Override
    public PurchaseListModel loadPurchaseSummary() {
        PurchaseListModel res = new PurchaseListModel();
        try {
            connect(path);
            String sql = "SELECT * FROM Orders";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PurchaseModel purchase = new PurchaseModel();
                purchase.mPurchaseID = rs.getInt("OrderId");
                purchase.mCustomerID = rs.getInt("CustomerId");
                purchase.mProductID = rs.getInt("ProductId");
                purchase.mPrice = rs.getDouble("Price");
                purchase.mQuantity = rs.getDouble("Quantity");
                purchase.mCost = rs.getDouble("TotalCost");
                purchase.mTax = rs.getDouble("TotalTax");
                purchase.mDate = rs.getString("Date");
                res.purchases.add(purchase);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            disconnect();
        }
        return res;
    }

    @Override
    public ProductListModel searchProduct(String name, double minPrice, double maxPrice) {
        ProductListModel res = new ProductListModel();
        try {
            connect(path);
            String sql = "SELECT * FROM Products WHERE Name LIKE \'%" + name + "%\' "
                    + "AND Price >= " + minPrice + " AND Price <= " + maxPrice;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.mProductID = rs.getInt("ProductID");
                product.mName = rs.getString("Name");
                product.mPrice = rs.getDouble("Price");
                product.mQuantity = rs.getDouble("Quantity");
                res.products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return res;
    }

    public CustomerModel loadCustomer(int id) {
        CustomerModel customer = null;
        try {
            connect(path);
            String sql = "SELECT * FROM Customers WHERE CustomerId = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                customer = new CustomerModel();
                customer.mCustomerID = id;
                customer.mName = rs.getString("Name");
                customer.mPhone = rs.getString("Phone");
                customer.mAddress = rs.getString("Address");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            disconnect();
        }
        return customer;
    }

    @Override
    public int saveCustomer(CustomerModel model)  {
        connect(path);
        try {
        String sql = "INSERT INTO Customers VALUES " + model;
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    } catch (Exception e) {
        String msg = e.getMessage();
        System.out.println(msg);
        if (msg.contains("UNIQUE constraint failed"))
            return CUSTOMER_SAVE_FAILED;
    } finally {
        disconnect();
    }
        return CUSTOMER_SAVE_OK;
    }

    @Override
    public int saveUser(UserModel userModel) {
        try {
            connect(path);
            ResultSet rs;
            if (userModel.mUserType == UserModel.CUSTOMER) {
                do {
                    int randomUserId = (int) (Math.random()*(9999 - 1)) + 1;
                    String checkUserId = "SELECT CustomerId from Users where CustomerId = '"+ randomUserId +"'";
                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(checkUserId);
                    userModel.mCustomerID = randomUserId;
                } while(rs.next());
            }
            String sql = "INSERT INTO Users VALUES " + userModel;
            Statement stmt1 = conn.createStatement();
            stmt1.executeUpdate(sql);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg);
            if (msg.contains("UNIQUE constraint failed"))
                return USER_SAVE_FAILED;
        } finally {
            disconnect();
        }
        return USER_SAVE_OK;
    }

    public UserModel loadUser(String username) {
        UserModel user = null;
        try {
            connect(path);
            String sql = "SELECT * FROM Users WHERE Username = '"+ username +"' ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new UserModel();
                user.mUsername = username;
                user.mPassword = rs.getString("Password");
                user.mFullname = rs.getString("Fullname");
                user.mUserType = rs.getInt("Usertype");
                if (user.mUserType == UserModel.CUSTOMER)
                    user.mCustomerID = rs.getInt("CustomerId");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            disconnect();
        }
        return user;
    }



}
