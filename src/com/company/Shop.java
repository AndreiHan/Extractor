package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shop extends JFrame {
    private JPanel panel1;
    private JCheckBox laptopuriCheckBox;
    private JCheckBox telefoaneCheckBox;
    private JCheckBox televizoareCheckBox;
    private JCheckBox PCUriCheckBox;
    private JButton backButton;
    private JButton startButton;

    public Shop(int shop) {
        int currentShop = shop;
        setContentPane(panel1);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPress();
            }

            private void onPress() {
                if (currentShop == 1) {
                    if (laptopuriCheckBox.isSelected()) {
                        Emag.getLaptop();
                    }
                    if (telefoaneCheckBox.isSelected()) {
                        Emag.getTelefoane();
                    }
                    if (PCUriCheckBox.isSelected()) {
                        Emag.getPc();
                    }
                    if (televizoareCheckBox.isSelected()) {
                        Emag.getTelevizoare();
                    }
                } else if (currentShop == 2) {
                    if (laptopuriCheckBox.isSelected()) {
                        Altex.getLaptop();
                    }
                    if (telefoaneCheckBox.isSelected()) {
                        Altex.getTelefoane();
                    }
                    if (PCUriCheckBox.isSelected()) {
                        Altex.getPc();
                    }
                    if (telefoaneCheckBox.isSelected()) {
                        Altex.getTelevizoare();
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBack();
            }

            private void onBack() {
                setVisible(false);
                Main_menu dialog = new Main_menu();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }
}
