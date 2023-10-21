package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import Database.*;

public class LoginForm extends JFrame implements ActionListener, ManageDB{
    JButton login;
    JButton register;
    JPanel panel;
    JLabel email_label;
    JLabel pass_label;
    final JTextField EMAIL_FIELD;
    final JPasswordField PASS_FIELD;
    static final String URL = "jdbc:mysql://localhost:3306/GymWorkoutPlanner",
                        DB_USER = "gymworkoutplanner",
                        DB_PASS = "GymWorkoutPlanner1.",
                        CREDS[] = {URL, DB_USER, DB_PASS};

    LoginForm(){

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
    
        //Register Button
        register = new JButton("Register");
        register.setActionCommand("register");

        //Panel
        JPanel wrapper_email = new JPanel(new FlowLayout());
        wrapper_email.add(email_label);
        wrapper_email.add(EMAIL_FIELD);

        JPanel wrapper_pass = new JPanel(new FlowLayout());
        wrapper_pass.add(pass_label);
        wrapper_pass.add(PASS_FIELD);

        JPanel wrapper_buttons = new JPanel(new FlowLayout());
        wrapper_buttons.add(login);
        wrapper_buttons.add(register);

        panel = new JPanel(new GridLayout(4,1));
        panel.add(new JPanel());
        panel.add(wrapper_email);
        panel.add(wrapper_pass);
        panel.add(wrapper_buttons);

        //Panel Border
        add(panel);
        login.addActionListener(this);
        register.addActionListener(this);
        setTitle("Login Form");
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        //Login button
        if(ae.getActionCommand().equals("login")){


            String email = EMAIL_FIELD.getText();
            String pass = String.valueOf(PASS_FIELD.getPassword());

            if(email.equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(this, "Please complete all fields.");
            }else if(userExists(CREDS, email, pass)){
                Home newpage = new Home(getName(CREDS, email));
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
