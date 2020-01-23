package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_menu extends JDialog {
    private JPanel contentPane;
    private JButton altexButton;
    private JButton emagButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Main_menu() {
        setContentPane(contentPane);
        setModal(true);

        emagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEmag();
            }

            private void onEmag() {
                Shop emag = new Shop(1);
                emag.pack();
                emag.setVisible(true);
                setVisible(false);
            }
        });

        altexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAltex();
            }

            private void onAltex() {
                Shop emag = new Shop(2);
                emag.pack();
                emag.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        Main_menu dialog = new Main_menu();
        dialog.pack();
        dialog.setVisible(true);
    }
}
