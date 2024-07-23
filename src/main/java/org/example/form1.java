package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form1 {
    private JTextField usuario;
    public JPanel mainPanel;
    private JTextField password;
    private JButton iniciarSesion;

    public form1() {
        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usuario.getText();
                String pass = password.getText();

                if (user.equals("admin") && pass.equals("admin2024")) {
                    JFrame frame = new JFrame();
                    frame.setContentPane(new admin().mainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                    SwingUtilities.getWindowAncestor(mainPanel).dispose();
                }if (user.equals("profe") && pass.equals("profe2024")) {
                    JFrame frame = new JFrame();
                    frame.setContentPane(new profesor().mainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                    SwingUtilities.getWindowAncestor(mainPanel).dispose();
                }  if (user.equals("estudiante") && pass.equals("estudiante")) {
                    JFrame frame = new JFrame();
                    frame.setContentPane(new estudiante().mainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 400);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    SwingUtilities.getWindowAncestor(mainPanel).dispose();

                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Credenciales incorrectos.");
                }
            }
        });
    }


}
