package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    JFrame frame = new JFrame("FulkopingLibrary");

    JButton Login = new JButton("Login");


        Gui () {
            frame.setSize(500,500);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(2,1));
            frame.add(Login);

            frame.setVisible(true);
        }

        void Loginwindow() {

            JFrame loginframe = new JFrame("Log in");
            JTextField Username = new JTextField("Username");
            JButton Confirm = new JButton("Confirm");

            loginframe.add(Username);
            loginframe.add(Confirm);
            Confirm.addActionListener(this);
            loginframe.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();

        if (btn.matches("Login")){
            Loginwindow();
        }

        if (btn.matches("Confirm")) {

        }
    }
}
