package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form2 {
    public JPanel mainPanel;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField fechaNac;
    private JTextField nacionalidad;
    private JTextField ocupacion;
    private JTextField premios;
    private JButton verHobby;
    private JButton button1;

    public form2() {


        verHobby.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }
}
