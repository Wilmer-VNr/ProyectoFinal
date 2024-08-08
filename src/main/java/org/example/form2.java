package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form2 {
    public JPanel mainPanel;
    public JPanel mainPanel2;
    private JTextField apellidos;
    private JButton iniciarSesión;
    private JPasswordField passwordField1;
    private JComboBox<String> perfil;
    private JLabel ver;
    private JTextField names;
    private JTextField users;

    public form2() {

        iniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombresText = names.getText();
                String apellidosText = apellidos.getText();
                String usuariosText = users.getText();
                String passwordText = new String(passwordField1.getPassword());
                String role = perfil.getSelectedItem().toString();

                // Verifica que todos los campos estén completos
                if (nombresText.isEmpty() || apellidosText.isEmpty() || usuariosText.isEmpty() || passwordText.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Todos los campos deben ser completados");
                    return;
                }

                try {
                    String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                    String user = "root";
                    String password = "";

                    Connection conn = DriverManager.getConnection(url, user, password);

                    // Inserta un valor único para userid (puede ser generado de otra manera)
                    String sql = "INSERT INTO usuarios (userid, nombre, apellido, username, password, perfil) VALUES (NULL, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, nombresText);
                    pstmt.setString(2, apellidosText);
                    pstmt.setString(3, usuariosText);
                    pstmt.setString(4, passwordText);
                    pstmt.setString(5, role);

                    pstmt.executeUpdate();

                    // Cierra la conexión
                    pstmt.close();
                    conn.close();

                    JOptionPane.showMessageDialog(mainPanel, "Usuario insertado correctamente");

                    // Limpia los campos después de insertar
                    names.setText("");
                    apellidos.setText("");
                    users.setText("");
                    passwordField1.setText("");
                    perfil.setSelectedIndex(0);

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error al insertar el usuario");
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
