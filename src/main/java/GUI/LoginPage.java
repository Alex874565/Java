package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import Database.*;
import main.*;

public class LoginPage extends JFrame implements ActionListener, ManageUsers, AddGUIElements{
    final JTextField EMAIL_FIELD;
    final JPasswordField PASS_FIELD;

    public LoginPage(){
        JButton login;
        JButton register;
        JPanel panel;
        JLabel email_label;
        JLabel pass_label;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Login");

        //Email Insertion
        email_label = new JLabel();
        email_label.setText("Email: ");
        EMAIL_FIELD = new JTextField(30);
        //EMAIL_FIELD.setFont(new Font());

        //Password Insertion
        pass_label = new JLabel();
        pass_label.setText("Password: ");
        PASS_FIELD = new JPasswordField(20);
        
        //Login Button
        login = new JButton("Login");
        login.setActionCommand("login");
        login.addActionListener(this);
    
        //Register Button
        register = new JButton("Register");
        register.setActionCommand("register");
        register.addActionListener(this);

        JButton[] buttons = {login, register};

        //Panel
        panel = new JPanel(new GridLayout(4,1));

        panel.add(new JPanel());

        addLabelText(panel, email_label, EMAIL_FIELD);
        addLabelPass(panel, pass_label, PASS_FIELD);
        addButtons(panel, buttons);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        //Login button
        if(ae.getActionCommand().equals("login")){


            String email = EMAIL_FIELD.getText();
            String pass = String.valueOf(PASS_FIELD.getPassword());

            if(email.equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(this, "Please complete all fields.");
            }else if(userExists(Main.CREDS, email, pass)){
                HomePage newpage = new HomePage(email);
                newpage.setVisible(true);
                dispose();   
            }else{
                JOptionPane.showMessageDialog(this, "Wrong user/password.");
            }
        }
        //Register button
        if(ae.getActionCommand().equals("register")){
            RegistrationPage newpage = new RegistrationPage();
            newpage.setVisible(true);
            dispose();
        }
    }    
}
