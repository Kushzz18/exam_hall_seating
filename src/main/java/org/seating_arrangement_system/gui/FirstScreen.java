package org.seating_arrangement_system.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FirstScreen extends CenteredLayout {

    private JLabel bgImageLabel;

    public FirstScreen() {
        this.setTitle("Welcome");
        this.getContentPane().setBackground(new Color(95, 111, 146));

        bgImageLabel = new JLabel();

        // Load the background image
        ImageIcon bgImageIcon = new ImageIcon("hall.png");
        Image bgImage = bgImageIcon.getImage();

        // Create a panel to hold the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the size of the panel (frame)
                Dimension panelSize = getSize();

                // Scale the background image to fit the panel (frame) size
                Image scaledBgImage = bgImage.getScaledInstance(panelSize.width, panelSize.height, Image.SCALE_SMOOTH);
                ImageIcon scaledBgImageIcon = new ImageIcon(scaledBgImage);

                // Draw the scaled background image
                scaledBgImageIcon.paintIcon(this, g, 0, 0);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        topLabel.setText("<html><p style='font-size: 25px; line-height: 1.5; text-align: center;'>Welcome to Exam Seat Plan Management System</p></html>");
        topLabel.setFont(new Font("SansSerif", Font.BOLD, 27));
        topLabel.setForeground(Color.BLACK);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        centerPanel.add(topLabel, BorderLayout.NORTH);

        JButton continueButton = new JButton("CONTINUE");
        Font buttonFont = continueButton.getFont();
        continueButton.setFont(new Font(buttonFont.getName(), Font.BOLD, 27));
        continueButton.setPreferredSize(new Dimension(150, 50));
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);

        continueButton.addActionListener(action -> {
            this.dispose();
            new Welcome();
        });

        getRootPane().setDefaultButton(continueButton);

        centerPanel.add(continueButton, BorderLayout.CENTER);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        this.setContentPane(backgroundPanel);
        this.render();
        this.setSize(500, 500);

        this.setLayout(new BorderLayout());

        this.add(topLabel, BorderLayout.NORTH);
        this.add(continueButton,BorderLayout.CENTER);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        // Add a ComponentListener to handle resizing
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Repaint the background image when the component (frame) is resized
                backgroundPanel.repaint();
            }
        });
    }
}
