package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main_menu extends JDialog {
    private JPanel contentPane;
    private JButton altexButton;
    private JButton emagButton;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JButton refreshButton;

    public Main_menu() {
        setContentPane(contentPane);
        setModal(true);
        try {
            VerifyConnection.run(progressBar1, "www.altex.ro");
            VerifyConnection.run(progressBar2, "www.emag.ro");
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onRefresh();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            private void onRefresh() throws IOException {
                progressBar1.setIndeterminate(true);
                VerifyConnection.run(progressBar1, "www.altex.ro");
                VerifyConnection.run(progressBar1, "www.emag.ro");
            }
        });

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
        dispose();
    }

    public static void main(String[] args) {
        Main_menu dialog = new Main_menu();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


}


