package org.seating_arrangement_system.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
public class Welcome extends CenteredLayout{

    public Welcome() {
        this.setTitle("Welcome");

//        topLabel.setText(" Are you??");
//        topLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
//        topLabel.setForeground(Color.BLACK);


        // Load icons
        ImageIcon adminIcon = resizeIcon(new ImageIcon("setting.png"), 64, 64); // Resize to 64x64 pixels
        ImageIcon studentIcon = resizeIcon(new ImageIcon("user.png"), 64, 64);
        JButton adminButton = new JButton("Admin", adminIcon);
        adminButton.setHorizontalTextPosition(SwingConstants.CENTER);
        adminButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        adminButton.addActionListener(action -> {
            this.dispose();
            new AdminLoginForm();
        });

        JButton studentButton = new JButton("Student", studentIcon);
        studentButton.setHorizontalTextPosition(SwingConstants.CENTER);
        studentButton.setVerticalTextPosition(SwingConstants.BOTTOM);

        studentButton.addActionListener(action -> {
            this.dispose();
            new StudentView();
        });


        compList.add(adminButton);
        compList.add(studentButton);
        this.render();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
