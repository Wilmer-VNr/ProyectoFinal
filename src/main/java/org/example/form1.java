package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form1 {
    private JTextField usuario;
    public JPanel mainPanel;
    private JTextField password;
    private JButton iniciarSesion;
    private JButton crearCuenta;

    public form1() {
        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                String pass = password.getText();

                String url = "jdbc:mysql://127.0.0.1:3306/aulaEsfot";
                String user = "root";
                String password = "1234";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conexión con la base de datos exitosa");




                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Credenciales incorrectos.");
                }
            }
        });
        crearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Login");
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(480, 350);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }


}
