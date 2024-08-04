package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class form1 {
    public JPanel mainPanel;
    public JPanel mainPanel2;
    private JTextField usuario;
    private JButton iniciarSesión;
    private JPasswordField passwordField1;
    private JComboBox perfil;

    public form1() {
        iniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioText = usuario.getText();
                String passwordText = new String(passwordField1.getPassword());
                String role = perfil.getSelectedItem().toString();

                String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                String user = "root";
                String password = ""; // Asegúrate de que esta contraseña sea la correcta

                String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND perfil = ?";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conexión con la base de datos exitosa");

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, usuarioText);
                    preparedStatement.setString(2, passwordText);
                    preparedStatement.setString(3, role);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(mainPanel, "Inicio de sesión exitoso.");

                        JFrame frame = new JFrame();


                        switch (role) {
                            case "Administrador":
                                frame.setContentPane(new admin().mainPanel);
                                frame.setTitle("Administrador");
                                break;
                            case "Profesor":
                                frame.setContentPane(new profesor().mainPanel);
                                frame.setTitle("Profesor");
                                break;
                            case "Estudiante":
                                frame.setContentPane(new estudiante().mainPanel);
                                frame.setTitle("Estudiante");
                                break;
                            default:
                                JOptionPane.showMessageDialog(mainPanel, "Perfil no reconocido.");
                                return;
                        }

                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(400, 500);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Usuario, contraseña o perfil incorrectos.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error al conectar con la base de datos.");
                }
            }
        });
    }
}
