package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class aula {
    public JPanel AulaPanel;
    private JPanel tabla;
    public JTable informacionAula;
    private JTextField IdLab;
    private JTextField capacidadAula;
    private JTextField reservadoAula;
    private JTextField fechaAula;
    private JButton reservarAula;
    private JButton limpiarAula;
    private JButton buscarAula;
    private JTextField busquedaAula;
    private JTextField totalAulas;
    private JTextField disponibleAula;

    // Información de la conexión
    private final String url = "jdbc:mysql://localhost:3306/aulaEsfot";
    private final String username = "root";
    private final String password = "";

    public aula() {
        // Cargar totales al iniciar
        cargarTotales();


        reservarAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateReservaInputs()) {
                    reservarAula();
                }
            }
        });

        limpiarAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        buscarAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (busquedaAula.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del aula.");
                } else {
                    buscarAula();
                }
            }
        });
    }

    // Validar entradas antes de la reserva
    private boolean validateReservaInputs() {
        if (IdLab.getText().isEmpty() || reservadoAula.getText().isEmpty() ||
                capacidadAula.getText().isEmpty() || disponibleAula.getText().isEmpty() || fechaAula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return false;
        }

        try {
            Integer.parseInt(capacidadAula.getText());
            Integer.parseInt(disponibleAula.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Capacidad y disponibles deben ser números válidos.");
            return false;
        }

        try {
            java.sql.Date.valueOf(fechaAula.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Fecha debe estar en formato YYYY-MM-DD.");
            return false;
        }

        return true;
    }

    // Método para reservar un aula
    private void reservarAula() {
        String idAula = IdLab.getText();
        String reservadoPor = reservadoAula.getText();
        int capacidad = Integer.parseInt(capacidadAula.getText());
        int disponibles = Integer.parseInt(disponibleAula.getText());
        String fechaReserva = fechaAula.getText();

        String sql = "INSERT INTO aulas (idAula, reservadoPor, capacidad, disponibles, fechaReserva) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, idAula);
                preparedStatement.setString(2, reservadoPor);
                preparedStatement.setInt(3, capacidad);
                preparedStatement.setInt(4, disponibles);
                preparedStatement.setDate(5, java.sql.Date.valueOf(fechaReserva)); // Convertir String a Date

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Aula reservada exitosamente.");

                // Actualizar totales después de la reserva
                cargarTotales();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reservar aula: " + ex.getMessage());
        }
    }

    // Método para buscar un aula
    private void buscarAula() {
        String idAula = busquedaAula.getText();

        String sql = "SELECT * FROM aulas WHERE idAula = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, idAula);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        IdLab.setText(resultSet.getString("idAula"));
                        reservadoAula.setText(resultSet.getString("reservadoPor"));
                        capacidadAula.setText(String.valueOf(resultSet.getInt("capacidad")));
                        disponibleAula.setText(String.valueOf(resultSet.getInt("disponibles")));
                        fechaAula.setText(resultSet.getDate("fechaReserva").toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Aula no encontrada.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar aula: " + ex.getMessage());
        }
    }

    // Método para cargar el total de aulas
    private void cargarTotales() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Total de aulas
            String sqlAula = "SELECT COUNT(*) AS total FROM aulas";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlAula)) {
                if (resultSet.next()) {
                    totalAulas.setText(String.valueOf(resultSet.getInt("total")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar totales de aulas: " + ex.getMessage());
        }
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        IdLab.setText("");
        reservadoAula.setText("");
        capacidadAula.setText("");
        disponibleAula.setText("");
        fechaAula.setText("");
        busquedaAula.setText("");
    }
}

