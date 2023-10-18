package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception; 

public class LoginForm extends JFrame implements ActionListener{
    JButton login;
    JButton register;
    JPanel panel;
    JLabel email_label;
    JLabel pass_label;
    final JTextField EMAIL_FIELD;
    final JPasswordField PASS_FIELD;

    LoginForm(){
        //Email Insertion
        email_label = new JLabel();
        email_label.setText("Email: ");
        EMAIL_FIELD = new JTextField(20);

        //Password Insertion
        pass_label = new JLabel();
        pass_label.setText("Password: ");
        PASS_FIELD = new JPasswordField(20);

        //Login Button
        login = new JButton("Login");
        login.setActionCommand("login");
    
        //Register Button
        register = new JButton("Register");
        register.setActionCommand("register");

        //Panel
        panel = new JPanel(new GridLayout(3, 2));
        panel.add(email_label);
        panel.add(EMAIL_FIELD);
        panel.add(pass_label);
        panel.add(PASS_FIELD);
        panel.add(login);
        panel.add(register);

        //Panel Border
        add(panel, BorderLayout.CENTER);
        login.addActionListener(this);
        register.addActionListener(this);
        setTitle("Login Form");
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        //Login button
        if(ae.getActionCommand().equals("login")){
            String email = EMAIL_FIELD.getText();
            String pass = PASS_FIELD.getText();

            if(email.equals("test") && pass.equals("test")){
                Home newpage = new Home();
                newpage.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Wrong user/password.");
            }
        }
        //Register button
        if(ae.getActionCommand().equals("register")){
            Register newpage = new Register();
            newpage.setVisible(true);
            dispose();
        }
    }    
}
