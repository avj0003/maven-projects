package com.company;

import javax.swing.*;
import java.awt.*;

public class AddOrderView extends JFrame {
    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtProductID = new JTextField(20);
    public JTextField txtPrice = new JTextField(20);
    public JTextField txtQuantity = new JTextField(20);
    public JTextField txtTax = new JTextField(20);
    public JTextField txtCost = new JTextField(20);

    public JButton btnAdd = new JButton("Add Customer");
    public JButton btnCancel = new JButton("Cancel");

    public AddOrderView() {
        this.setTitle("Add Order");
        this.setSize(600, 400);

        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID:"));
        line1.add(txtCustomerID);
        pane.add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("ProductID:"));
        line2.add(txtProductID);
        pane.add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Price:"));
        line3.add(txtPrice);
        pane.add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Quantity:"));
        line4.add(txtQuantity);
        pane.add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("Total Tax:"));
        line5.add(txtTax);
        pane.add(line5);

        JPanel line6 = new JPanel(new FlowLayout());
        line6.add(new JLabel("Total Cost:"));
        line6.add(txtCost);
        pane.add(line6);

        JPanel buttonPane = new JPanel(new FlowLayout());
        buttonPane.add(btnAdd);
        buttonPane.add(btnCancel);
        pane.add(buttonPane);

    }
}
