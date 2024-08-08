package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profesor {
    public JPanel profesorPanel;
    private JButton homeButton;
    private JButton aulasButton;
    private JButton signOutButton;
    private JPanel informacion;
    private JButton laboratoriosButton;
    private JPanel aulasPanel;
    private JPanel labsPanel;

    public profesor() {
        // Inicializar el panel de información
        if (informacion == null) {
            informacion = new JPanel();
        }

        // Configurar el CardLayout para el panel de información
        CardLayout cardLayout = new CardLayout();
        informacion.setLayout(cardLayout);

        // Inicializar los paneles de aulas y laboratorios
        aulasPanel = new aula().AulaPanel;
        labsPanel = new laboratorio().labPanel;

        // Verificar que los paneles se han inicializado correctamente
        if (aulasPanel == null || labsPanel == null) {
            throw new IllegalStateException("Los paneles de aula o laboratorio no se inicializaron correctamente.");
        }

        // Agregar los paneles al panel de información con sus respectivas tarjetas
        informacion.add(aulasPanel, "Aulas");
        informacion.add(labsPanel, "Laboratorios");

        // Acción para el botón de cerrar sesión
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                SwingUtilities.getWindowAncestor(profesorPanel).dispose();
            }
        });

        // Acción para el botón de aulas
        aulasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(informacion, "Aulas");
            }
        });

        // Acción para el botón de laboratorios
        laboratoriosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(informacion, "Laboratorios");
            }
        });

        // Acción para el botón de inicio
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new home().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(2000, 872);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
