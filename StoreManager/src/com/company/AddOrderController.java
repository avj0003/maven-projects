package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrderController {
    public AddOrderView view;
    public IDataAccess dataAdapter;

    public AddOrderController(AddOrderView view, IDataAccess adapter) {
        this.view = view;
        this.dataAdapter = adapter;

        view.btnAdd.addActionListener(new AddButtonController());
        view.btnCancel.addActionListener(new CancelButtonController());

    }

    class AddButtonController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            OrderModel order = new OrderModel();
            order.mCustomerID = Integer.parseInt(view.txtCustomerID.getText());
            order.mProductID = Integer.parseInt(view.txtProductID.getText());
            order.mCost = Integer.parseInt(view.txtCost.getText());
            order.mTax = Integer.parseInt(view.txtTax.getText());
            order.mPrice = Integer.parseInt(view.txtPrice.getText());
            order.mQuantity = Integer.parseInt(view.txtQuantity.getText());

            dataAdapter.saveOrder(order);
            JOptionPane.showMessageDialog(null, "Product Id = " + order.mProductID);
        }
    }

    class CancelButtonController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.setVisible(false);
        }
    }

}