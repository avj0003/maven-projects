package com.company;

import javax.swing.*;
import java.awt.*;

public class AddCustomerView extends JFrame {
    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);

    public JButton btnAdd = new JButton("Add Customer");
    public JButton btnCancel = new JButton("Cancel");

    public AddCustomerView() {
        this.setTitle("Add Customer");
        this.setSize(600, 400);

        Container pane = this.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID:"));
        line1.add(txtCustomerID);
        pane.add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name:"));
        line2.add(txtName);
        pane.add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Address:"));
        line3.add(txtAddress);
        pane.add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Phone:"));
        line4.add(txtPhone);
        pane.add(line4);

        JPanel buttonPane = new JPanel(new FlowLayout());
        buttonPane.add(btnAdd);
        buttonPane.add(btnCancel);
        pane.add(buttonPane);

    }
}
