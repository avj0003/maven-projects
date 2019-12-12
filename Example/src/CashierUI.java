import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierUI {
    public JFrame view;

    public JButton btnAddPurchase = new JButton("Add New Purchase");
    public JButton btnUpdatePurchase = new JButton("Update Purchase");
    public JButton btnNewCustomer = new JButton("Add New Customer");
    public JButton btnUpdateCustomer = new JButton("Update Customer");
    public JButton btnUpdate = new JButton("Update Information");
    public JButton btnLogout = new JButton("Logout");

    public CashierUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Cashier View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System", SwingConstants.CENTER);

        title.setFont(title.getFont().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddPurchase);
        panelButtons.add(btnUpdatePurchase);
        panelButtons.add(btnNewCustomer);
        panelButtons.add(btnUpdateCustomer);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnLogout);

        view.getContentPane().add(panelButtons);


        btnAddPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ap = new AddPurchaseUI(null);
                ap.run();
            }
        });

        btnUpdatePurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UpdatePurchaseUI ap = new UpdatePurchaseUI();
                ap.run();
            }
        });

        btnNewCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomerUI ac = new AddCustomerUI(null);
                ac.run();
            }
        });

        btnUpdateCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCustomerUI ac = new UpdateCustomerUI(null);
                ac.run();
            }
        });
    }
}