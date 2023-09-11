package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.dao.AdminDao;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AdminLoginForm extends FormLayout {
    AdminLoginForm() {
        setSize(400, 500);
        topLabel.setText("Admin Login Form");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 18));
        topLabel.setForeground(Color.black);

        JLabel enterName = new JLabel("Enter username:");
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(110,45));
        compList.add(makeGroup(enterName, nameField));

        JLabel enterPass = new JLabel("Enter Password:");
        JPasswordField passField = new JPasswordField(15);
        passField.setPreferredSize(new Dimension(110,45));


        compList.add(makeGroup(enterPass, passField));

        RoundedPanel btnPane = new RoundedPanel(10);
        btnPane.setPreferredSize(new Dimension(110,45));

        RoundedButton login = new RoundedButton("Login",10);


        login.addActionListener((java.awt.event.ActionEvent event) -> {
            String username = nameField.getText();
            String password = passField.getText();
            AdminDao adminDao = new AdminDao();
            if (adminDao.checkAdmin(username, password)) {
                this.dispose();
                new AdminView();
            }
            else {
                JOptionPane.showMessageDialog(null, "invalid username or password!");
            }
        });

        RoundedButton back = new RoundedButton("Back",10);

        back.addActionListener(action -> {
            this.dispose();
            new Welcome();
        });

        btnPane.add(login);
        btnPane.add(back);

        compList.add(btnPane);
        render();
        setVisible(true);
        setLocationRelativeTo(null);

    }
}