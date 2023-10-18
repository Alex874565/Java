package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener{
    JLabel name_label, email_label, password_label, confirm_password_label;
    JButton register;
    JPanel panel;
    final JTextField EMAIL_FIELD, NAME_FIELD;
    final JPasswordField PASS_FIELD, CONFIRM_PASS_FIELD;

    
    Register(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Registration");
        
        //Name Insertion
        name_label = new JLabel("Name: ");
        NAME_FIELD = new JTextField();

        //Email Insertion
        email_label = new JLabel("Email: ");
        EMAIL_FIELD = new JTextField();

        //Password Insertion
        password_label = new JLabel("Password: ");
        PASS_FIELD = new JPasswordField();

        //Confirm Password
        confirm_password_label = new JLabel("Confirm password: ");
        CONFIRM_PASS_FIELD = new JPasswordField();

        //Register Button
        register = new JButton("Register");

        //Panel
        panel = new JPanel(new GridLayout(5, 2));
        panel.add(name_label);
        panel.add(NAME_FIELD);
        panel.add(email_label);
        panel.add(EMAIL_FIELD);
        panel.add(password_label);
        panel.add(PASS_FIELD);
        panel.add(confirm_password_label);
        panel.add(CONFIRM_PASS_FIELD);
        panel.add(register);

        //Panel Border
        add(panel, BorderLayout.CENTER);
        register.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String name = NAME_FIELD.getText();
        String email = EMAIL_FIELD.getText();
        String pass = PASS_FIELD.getText();
        String pass_confirmation = CONFIRM_PASS_FIELD.getText();
    }
}
