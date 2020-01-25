package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class VerifyConnection {
    public static void run(JProgressBar progressBar1, String site) throws IOException {
        if (sendPingRequest(site)) {
            progressBar1.setVisible(true);
            progressBar1.setIndeterminate(false);
            progressBar1.setBackground(Color.decode("#00ff00"));
        } else {
            progressBar1.setBackground(Color.decode("#ff0000"));
        }
    }

    public static Boolean sendPingRequest(String ipAddress)
            throws IOException {
        InetAddress geek = InetAddress.getByName(ipAddress);

        return geek.isReachable(5000);
    }
}
