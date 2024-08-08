package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home {
    public JPanel mainPanel;
    public JPanel mainPanel2;
    private JLabel fondo;
    private JPanel parrafo;
    private JButton signUpButton;
    private JButton signInButton;
    private JLabel parrafoLabel;


    public home() {

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new form2().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 550);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(mainPanel).dispose();

            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de Reservas de Aulas de la ESFOT");
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(mainPanel).dispose();
            }
        });
    }
}