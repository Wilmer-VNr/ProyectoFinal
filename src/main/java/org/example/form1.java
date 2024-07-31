package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class form1 {
    public JPanel mainPanel;
    public JPanel mainPanel2;
    private JTextField user;
    private JButton iniciarSesión;
    private JPasswordField passwordField1;


    public form1() {

        iniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = user.getText();
                String password = new String(passwordField1.getPassword());

                String url = "jdbc:mysql://127.0.0.1:3306/language";
                String userDb = "root";
                String passwordDb = "";

                try (Connection connection = DriverManager.getConnection(url, userDb, passwordDb)) {
                    System.out.println("Conexión con la base de datos exitosa");
                    JFrame frame = new JFrame("Bienvenido Administrador");
                    frame.setContentPane(new form2().mainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 450);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    //https://stackoverflow.com/questions/12224431/create-a-root-password-for-phpmyadmin

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error al conectar con la base de datos.");
                }
            }
        });
    }


}

