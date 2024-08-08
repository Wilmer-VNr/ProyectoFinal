package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
        frame.setContentPane(new home().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 872);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
