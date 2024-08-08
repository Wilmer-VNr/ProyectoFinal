package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class estudiante {
    public JPanel estudiantePanel;
    private JButton homeButton;
    private JButton aulasButton;
    private JButton signOutButton;

    public estudiante() {
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

        // Acción para el botón de cerrar sesión
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el panel de inicio de sesión
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                // Cerrar la ventana actual
                SwingUtilities.getWindowAncestor(estudiantePanel).dispose();
            }
        });
    }
}
