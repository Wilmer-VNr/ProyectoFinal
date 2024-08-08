package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.MessageFormat;

public class usuarios {
    private JButton añadir;
    private JButton actualizarButton;
    private JButton Limpiarbutton1;
    private JButton buscarbutton2;
    private JTextField buscarUser;
    private JTable table1;
    private JTextField TotalUsuarios;
    private JComboBox<String> perfiles;
    private JPasswordField passwordField;
    private JTextField usernames;
    private JTextField lastnames;
    private JTextField names;
    private JTextField id_users;
    private JButton eliminarButton;
    private JButton imprimirButton;
    private JPanel totalUsuarios;
    public JPanel mainPanel;

    public usuarios() {
        // Configurar la tabla
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Usuario", "Perfil"}, 0);
        table1.setModel(model);

        // Acción para añadir un usuario
        añadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = id_users.getText();
                    int idInt = validarYConvertirId(id); // Validar y convertir el ID

                    String nombre = names.getText();
                    String apellido = lastnames.getText();
                    String usuario = usernames.getText();
                    String perfil = (String) perfiles.getSelectedItem();
                    String password = new String(passwordField.getPassword());

                    String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                    String user = "root";
                    String passwordDb = "";

                    String sql = "INSERT INTO usuarios (userid, nombre, apellido, username, password, perfil) VALUES (?, ?, ?, ?, ?, ?)";

                    try (Connection connection = DriverManager.getConnection(url, user, passwordDb)) {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, idInt);
                        preparedStatement.setString(2, nombre);
                        preparedStatement.setString(3, apellido);
                        preparedStatement.setString(4, usuario);
                        preparedStatement.setString(5, password);
                        preparedStatement.setString(6, perfil);
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(totalUsuarios, "Usuario añadido con éxito.");
                            limpiarCampos();
                            actualizarTabla(); // Actualizar la tabla después de añadir un usuario
                        } else {
                            JOptionPane.showMessageDialog(totalUsuarios, "No se pudo añadir el usuario.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(totalUsuarios, "Error al conectar con la base de datos.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(totalUsuarios, "El ID debe ser un número entero.");
                }
            }
        });

        // Acción para actualizar un usuario
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = id_users.getText();
                    int idInt = validarYConvertirId(id); // Validar y convertir el ID

                    String nombre = names.getText();
                    String apellido = lastnames.getText();
                    String usuario = usernames.getText();
                    String perfil = (String) perfiles.getSelectedItem();
                    String password = new String(passwordField.getPassword());

                    String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                    String user = "root";
                    String passwordDb = "";

                    String sql = "UPDATE usuarios SET nombre=?, apellido=?, username=?, perfil=?, password=? WHERE userid=?";

                    try (Connection connection = DriverManager.getConnection(url, user, passwordDb)) {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, nombre);
                        preparedStatement.setString(2, apellido);
                        preparedStatement.setString(3, usuario);
                        preparedStatement.setString(4, perfil);
                        preparedStatement.setString(5, password);
                        preparedStatement.setInt(6, idInt);
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(totalUsuarios, "Usuario actualizado con éxito.");
                            limpiarCampos();
                            actualizarTabla(); // Actualizar la tabla después de actualizar un usuario
                        } else {
                            JOptionPane.showMessageDialog(totalUsuarios, "No se pudo actualizar el usuario.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(totalUsuarios, "Error al conectar con la base de datos.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(totalUsuarios, "El ID debe ser un número entero.");
                }
            }
        });

        // Acción para eliminar un usuario
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = id_users.getText();
                    int idInt = validarYConvertirId(id); // Validar y convertir el ID

                    String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                    String user = "root";
                    String passwordDb = "";

                    String sql = "DELETE FROM usuarios WHERE userid=?";

                    try (Connection connection = DriverManager.getConnection(url, user, passwordDb)) {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, idInt);
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(totalUsuarios, "Usuario eliminado con éxito.");
                            limpiarCampos();
                            actualizarTabla(); // Actualizar la tabla después de eliminar un usuario
                        } else {
                            JOptionPane.showMessageDialog(totalUsuarios, "No se pudo eliminar el usuario.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(totalUsuarios, "Error al conectar con la base de datos.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(totalUsuarios, "El ID debe ser un número entero.");
                }
            }
        });

        // Acción para limpiar los campos
        Limpiarbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // Acción para buscar un usuario
        buscarbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = buscarUser.getText();

                String url = "jdbc:mysql://localhost:3306/aulaEsfot";
                String user = "root";
                String passwordDb = "";

                String sql = "SELECT * FROM usuarios WHERE userid LIKE ? OR nombre LIKE ? OR apellido LIKE ? OR username LIKE ?";

                try (Connection connection = DriverManager.getConnection(url, user, passwordDb)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    String wildcardSearch = "%" + searchTerm + "%";
                    preparedStatement.setString(1, wildcardSearch);
                    preparedStatement.setString(2, wildcardSearch);
                    preparedStatement.setString(3, wildcardSearch);
                    preparedStatement.setString(4, wildcardSearch);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    while (resultSet.next()) {
                        String id = resultSet.getString("userid");
                        String nombre = resultSet.getString("nombre");
                        String apellido = resultSet.getString("apellido");
                        String usuario = resultSet.getString("username");
                        String perfil = resultSet.getString("perfil");
                        model.addRow(new Object[]{id, nombre, apellido, usuario, perfil});
                    }

                    TotalUsuarios.setText(String.valueOf(model.getRowCount()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(totalUsuarios, "Error al conectar con la base de datos.");
                }
            }
        });

        // Acción para imprimir los datos de los usuarios
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Crear un documento de impresión
                    JTable.PrintMode printMode = JTable.PrintMode.NORMAL;
                    boolean printResult = table1.print(printMode, new MessageFormat("Usuarios"), new MessageFormat("Page {0}"));

                    if (printResult) {
                        JOptionPane.showMessageDialog(totalUsuarios, "Impresión exitosa.");
                    } else {
                        JOptionPane.showMessageDialog(totalUsuarios, "Error durante la impresión.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(totalUsuarios, "Error al intentar imprimir.");
                }
            }
        });

        // Inicializar la tabla con datos
        actualizarTabla(); // Llamar a este método para inicializar la tabla con los datos de la base de datos
    }

    private void limpiarCampos() {
        id_users.setText("");
        names.setText("");
        lastnames.setText("");
        usernames.setText("");
        passwordField.setText("");
        perfiles.setSelectedIndex(0);
    }

    private int validarYConvertirId(String id) throws NumberFormatException {
        if (id == null || id.trim().isEmpty()) {
            throw new NumberFormatException("El ID no puede estar vacío.");
        }
        return Integer.parseInt(id);
    }

    private void actualizarTabla() {
        String url = "jdbc:mysql://localhost:3306/aulaEsfot";
        String user = "root";
        String passwordDb = "";

        String sql = "SELECT * FROM usuarios";

        try (Connection connection = DriverManager.getConnection(url, user, passwordDb)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                String id = resultSet.getString("userid");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String usuario = resultSet.getString("username");
                String perfil = resultSet.getString("perfil");
                model.addRow(new Object[]{id, nombre, apellido, usuario, perfil});
            }

            TotalUsuarios.setText(String.valueOf(model.getRowCount()));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(totalUsuarios, "Error al conectar con la base de datos.");
        }
    }

}
