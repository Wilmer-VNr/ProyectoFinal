package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin {
    public JPanel adminPanel;
    private JButton homeButton;
    private JButton aulasButton;
    private JButton estudiantesButton;
    private JButton singOutButton; // Corregido "singOut" a "signOut"
    private JButton laboratoriosButton;
    private JPanel opciones;

    public admin() {
        // Acción para el botón de inicio
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el panel de inicio
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new home().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(2000, 872);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        // Acción para el botón de estudiantes
        estudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el panel de gestión de usuarios
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new usuarios().mainPanel); // Asegúrate de que 'usuarios' tenga un panel 'mainPanel'
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(2000, 872);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        // Acción para el botón de aulas
        aulasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el panel de aulas
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new aula().AulaPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(2000, 872);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        // Acción para el botón de laboratorios
        laboratoriosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el panel de laboratorios
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new laboratorio().labPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(2000, 872);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        // Acción para el botón de cerrar sesión
        singOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el panel de inicio de sesión
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new form1().mainPanel); // Asegúrate de que 'form1' tenga un panel 'mainPanel'
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                // Cerrar la ventana actual
                SwingUtilities.getWindowAncestor(adminPanel).dispose();
            }
        });
    }
}
