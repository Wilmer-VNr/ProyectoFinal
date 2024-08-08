package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class laboratorio {
    public JPanel labPanel;
    private JTabbedPane tabbedPane1;
    private JTextField id_lab;
    private JTextField capacidad_lab;
    private JTextField reserva_lab;
    private JTextField fecha_lab;
    private JButton reservarLab;
    private JButton limpiarlab;
    private JButton buscarLab;
    private JTextField BarraBusqueda;
    private JTable tableLab;
    private JTextField totalLab;

    private JTextField disponibleLab;

    private final String url = "jdbc:mysql://localhost:3306/aulaEsfot";
    private final String user = "root";
    private final String password = "";

    public laboratorio() {
        // Verifica que totalLab esté inicializado correctamente
        if (totalLab == null) {
            JOptionPane.showMessageDialog(null, "Error: totalLab no está inicializado.");
            return;
        }

        cargarTotales();

        reservarLab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateReservaInputs()) {
                    reservarLab();
                }
            }
        });

        limpiarlab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        buscarLab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BarraBusqueda.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del laboratorio.");
                } else {
                    buscarLab();
                }
            }
        });
    }

    // Validar entradas antes de la reserva
    private boolean validateReservaInputs() {
        if (id_lab.getText().isEmpty() || reserva_lab.getText().isEmpty() ||
                capacidad_lab.getText().isEmpty() || disponibleLab.getText().isEmpty() || fecha_lab.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return false;
        }

        try {
            Integer.parseInt(capacidad_lab.getText());
            Integer.parseInt(disponibleLab.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Capacidad y disponibles deben ser números válidos.");
            return false;
        }

        try {
            java.sql.Date.valueOf(fecha_lab.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Fecha debe estar en formato YYYY-MM-DD.");
            return false;
        }

        return true;
    }

    // Método para reservar un laboratorio
    private void reservarLab() {
        String idLab = id_lab.getText();
        String reservadoPor = reserva_lab.getText();
        int capacidad = Integer.parseInt(capacidad_lab.getText());
        int disponibles = Integer.parseInt(disponibleLab.getText());
        String fechaReserva = fecha_lab.getText();

        String sql = "INSERT INTO laboratorios (idLab, reservadoPor, totalLab, capacidad, disponibles, fechaReserva) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, idLab);
                preparedStatement.setString(2, reservadoPor);
                preparedStatement.setInt(3, capacidad); // totalLab se establece igual que capacidad
                preparedStatement.setInt(4, capacidad);
                preparedStatement.setInt(5, disponibles);
                preparedStatement.setDate(6, java.sql.Date.valueOf(fechaReserva)); // Convertir String a Date

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Laboratorio reservado exitosamente.");

                cargarTotales();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reservar laboratorio: " + ex.getMessage());
        }
    }

    // Método para buscar un laboratorio
    private void buscarLab() {
        String idLab = BarraBusqueda.getText();

        String sql = "SELECT * FROM laboratorios WHERE idLab = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, idLab);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id_lab.setText(resultSet.getString("idLab"));
                        reserva_lab.setText(resultSet.getString("reservadoPor"));
                        capacidad_lab.setText(String.valueOf(resultSet.getInt("capacidad")));
                        disponibleLab.setText(String.valueOf(resultSet.getInt("disponibles")));
                        fecha_lab.setText(resultSet.getDate("fechaReserva").toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Laboratorio no encontrado.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar laboratorio: " + ex.getMessage());
        }
    }

    // Método para cargar el total de laboratorios y aulas
    private void cargarTotales() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Total de laboratorios
            String sqlLab = "SELECT COUNT(*) AS total FROM laboratorios";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlLab)) {
                if (resultSet.next()) {
                    totalLab.setText(String.valueOf(resultSet.getInt("total")));
                }
            }

            // Total de aulas
            String sqlAula = "SELECT COUNT(*) AS total FROM aulas";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlAula)) {
                if (resultSet.next()) {
                    // Puede ser que debas usar otro campo para mostrar el total de aulas
                    // Si solo tienes un campo para mostrar el total de laboratorios, asegúrate de actualizarlo correctamente.
                    // totalLab.setText(String.valueOf(resultSet.getInt("total")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar totales: " + ex.getMessage());
        }
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        id_lab.setText("");
        reserva_lab.setText("");
        capacidad_lab.setText("");
        disponibleLab.setText("");
        fecha_lab.setText("");
        BarraBusqueda.setText("");
    }
}
