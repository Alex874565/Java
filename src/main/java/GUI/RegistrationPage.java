package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import Database.*;
import main.*;

public class RegistrationPage extends JFrame implements ActionListener, ManageUsers, ValidateFields, AddGUIElements{
    final JTextField EMAIL_FIELD, NAME_FIELD;
    final JPasswordField PASS_FIELD, CONFIRM_PASS_FIELD;

    
    public RegistrationPage(){

        JLabel name_label, email_label, password_label, confirm_password_label;
        JButton register, back;
        JPanel panel;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Registration");
        
        //Name Insertion
        name_label = new JLabel("Name: ");
        NAME_FIELD = new JTextField(20);

        //Email Insertion
        email_label = new JLabel("Email: ");
        EMAIL_FIELD = new JTextField(30);

        //Password Insertion
        password_label = new JLabel("Password: ");
        PASS_FIELD = new JPasswordField(20);

        //Confirm Password
        confirm_password_label = new JLabel("Confirm password: ");
        CONFIRM_PASS_FIELD = new JPasswordField(20);

        //Register Button
        register = new JButton("Register");
        register.setActionCommand("register");
        register.addActionListener(this);
        
        //Back Button
        back = new JButton("Back");
        back.setActionCommand("back");
        back.addActionListener(this);

        JButton[] buttons = {register, back};

        //Panel
        panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JPanel());
        addLabelText(panel, name_label, NAME_FIELD);
        addLabelText(panel, email_label, EMAIL_FIELD);
        addLabelPass(panel, password_label, PASS_FIELD);
        addLabelPass(panel, confirm_password_label, CONFIRM_PASS_FIELD);
        addButtons(panel, buttons);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("register")){
            
            String name = NAME_FIELD.getText();
            String email = EMAIL_FIELD.getText();
            String pass = String.valueOf(PASS_FIELD.getPassword());
            String pass_confirmation = String.valueOf(CONFIRM_PASS_FIELD.getPassword());

            if (name.equals("") || email.equals("") || pass.equals("") || pass_confirmation.equals("")){
                JOptionPane.showMessageDialog(this, "Please complete every field. :<");
            }else if(!validateEmail(email)){
                JOptionPane.showMessageDialog(this, "Please insert a valid email. >:(");
            }else if(!validatePass(pass)){
                JOptionPane.showMessageDialog(this, "Password should contain a letter, a number and minimum 8 characters.");
            }else if(!pass_confirmation.equals(pass)){
                JOptionPane.showMessageDialog(this, "Passwords do not match. :(");
            }else{
                try (Connection conn = connect(Main.CREDS)) {
                    if(emailExists(Main.CREDS, email)){
                        JOptionPane.showMessageDialog(this, "Email already in use.");
                    }else{
                        addUser(Main.CREDS, name, email, pass);
                        LoginPage newpage = new LoginPage();
                        newpage.setVisible(true);
                        dispose();
                    }
                } catch (SQLException e) {
                    throw new IllegalStateException("Cannot connect the database!", e);
                }
            }
        }else if(ae.getActionCommand().equals("back")){
            LoginPage newpage = new LoginPage();
            newpage.setVisible(true);
            dispose();
        }
    }
}
