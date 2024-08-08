package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel ver;

    public form1() {
        iniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioText = usuario.getText();
                String passwordText = new String(passwordField1.getPassword());
                String role = perfil.getSelectedItem().toString();

                String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                String user = "root";
                String password = "";

                String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND perfil = ?";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conexión con la base de datos exitosa");

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, usuarioText);
                    preparedStatement.setString(2, passwordText);
                    preparedStatement.setString(3, role);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        System.out.println("Inicio de sesión exitoso.");


                        switch (role) {
                            case "Administrador":
                                JFrame frame = new JFrame();

                                frame.setContentPane(new admin().adminPanel);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(2000, 872);
                                frame.setLocationRelativeTo(null);
                                frame.setVisible(true);
                                break;
                            case "Profesor":
                                JFrame frame2 = new JFrame();
                                //frame2.setUndecorated(true);

                                frame2.setContentPane(new profesor().profesorPanel);
                                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame2.setSize(2000, 872);
                                frame2.setLocationRelativeTo(null);
                                frame2.setVisible(true);
                                break;
                            case "Estudiante":
                                JFrame frame3 = new JFrame();
                                //frame3.setUndecorated(true);
                                frame3.setContentPane(new estudiante().estudiantePanel);
                                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame3.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                frame3.setLocationRelativeTo(null);
                                frame3.setVisible(true);
                                break;
                            default:
                                JOptionPane.showMessageDialog(mainPanel, "Perfil no reconocido.");
                                return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Usuario, contraseña o perfil incorrectos.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error al conectar con la base de datos.");
                }
            }
        });
        ver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cambiar el tipo del JPasswordField
                if (passwordField1.getEchoChar() == '\u0000') { // Contraseña visible
                    passwordField1.setEchoChar('⁎'); // Ocultar contraseña
                    ver.setText("");
                } else {
                    passwordField1.setEchoChar('\u0000'); // Mostrar contraseña
                    ver.setText("");
                }
            }
        });
    }
}