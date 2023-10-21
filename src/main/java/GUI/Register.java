package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Register extends JFrame implements ActionListener{
    JLabel name_label, email_label, password_label, confirm_password_label;
    JButton register, back;
    JPanel panel;
    final JTextField EMAIL_FIELD, NAME_FIELD;
    final JPasswordField PASS_FIELD, CONFIRM_PASS_FIELD;
    static final String URL = "jdbc:mysql://localhost:3306/GymWorkoutPlanner",
                        DB_USER = "gymworkoutplanner",
                        DB_PASS = "GymWorkoutPlanner1.";

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.matches();
    }

    
    Register(){
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
        
        //Back Button
        back = new JButton("Back");
        back.setActionCommand("back");

        //Panel
        panel = new JPanel(new GridLayout(6, 2));

        JPanel name_wrapper = new JPanel(new FlowLayout());
        name_wrapper.add(name_label);
        name_wrapper.add(NAME_FIELD);

        JPanel email_wrapper = new JPanel(new FlowLayout());
        email_wrapper.add(email_label);
        email_wrapper.add(EMAIL_FIELD);

        JPanel pass_wrapper = new JPanel(new FlowLayout());
        pass_wrapper.add(password_label);
        pass_wrapper.add(PASS_FIELD);

        JPanel c_pass_wrapper = new JPanel(new FlowLayout());
        c_pass_wrapper.add(confirm_password_label);
        c_pass_wrapper.add(CONFIRM_PASS_FIELD);

        JPanel buttons_wrapper = new JPanel(new FlowLayout());
        buttons_wrapper.add(register);
        buttons_wrapper.add(back);

        panel.add(new JPanel());
        panel.add(name_wrapper);
        panel.add(email_wrapper);
        panel.add(pass_wrapper);
        panel.add(c_pass_wrapper);
        panel.add(buttons_wrapper);

        //Panel Border
        add(panel);
        register.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("register")){

            System.out.println("Connecting database...");
            
            String name = NAME_FIELD.getText();
            String email = EMAIL_FIELD.getText();
            String pass = String.valueOf(PASS_FIELD.getPassword());
            String pass_confirmation = String.valueOf(CONFIRM_PASS_FIELD.getPassword());

            if(!pass_confirmation.equals(pass)){
                JOptionPane.showMessageDialog(this, "Passwords do not match. :(");
            }else if (name.equals("") || email.equals("") || pass.equals("") || pass_confirmation.equals("")){
                JOptionPane.showMessageDialog(this, "Please complete every field. :<");
            }else if(!validate(email)){
                JOptionPane.showMessageDialog(this, "Please insert a valid email. >:(");
            }else{
                try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASS)) {
                    System.out.println("Database connected!");
                    PreparedStatement query = conn.prepareStatement("SELECT COUNT(*) FROM GymWorkoutPlanner.users WHERE email = ?");
                    query.setString(1, email);
                    ResultSet rs = query.executeQuery();
                    rs.next();
                    if(rs.getInt(1) != 0){
                        JOptionPane.showMessageDialog(this, "Email already in use.");
                    }else{
                        query = conn.prepareStatement("INSERT INTO GymWorkoutPlanner.users VALUES (?, ?, ?);");
                        query.setString(1, name);
                        query.setString(2, email);
                        query.setString(3, pass);
                        query.executeUpdate();
                        LoginForm newpage = new LoginForm();
                        newpage.setVisible(true);
                        dispose();
                    } 
                } catch (SQLException e) {
                    throw new IllegalStateException("Cannot connect the database!", e);
                }
            }
        }else if(ae.getActionCommand().equals("back")){
            LoginForm newpage = new LoginForm();
            newpage.setVisible(true);
            dispose();
        }
    }
}
