package com.company;

import javax.swing.*;
import java.awt.*;

public class AddProductView extends JFrame {
    public JTextField txtProductID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtPrice = new JTextField(20);
    public JTextField txtQuantity = new JTextField(20);

    public JButton btnAdd = new JButton("Add Product");
    public JButton btnCancel = new JButton("Cancel");

    public AddProductView() {
        this.setTitle("Add Product");
        this.setSize(600, 400);

        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("ProductID:"));
        line1.add(txtProductID);
        pane.add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name:"));
        line2.add(txtName);
        pane.add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Price:"));
        line3.add(txtPrice);
        pane.add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Quantity:"));
        line4.add(txtQuantity);
        pane.add(line4);

        JPanel buttonPane = new JPanel(new FlowLayout());
        buttonPane.add(btnAdd);
        buttonPane.add(btnCancel);
        pane.add(buttonPane);

    }
}
