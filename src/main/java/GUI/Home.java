package GUI;

import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Home extends JFrame implements ActionListener, ManageWorkouts, ManageUsers{

    static final String URL = "jdbc:mysql://localhost:3306/GymWorkoutPlanner",
                        DB_USER = "gymworkoutplanner",
                        DB_PASS = "GymWorkoutPlanner1.",
                        CREDS[] = {URL, DB_USER, DB_PASS};
    final String EMAIL;

    @Override
    public Connection connect(String[] creds){
        try(Connection conn = DriverManager.getConnection(creds[0], creds[1], creds[2])){
            return DriverManager.getConnection(creds[0], creds[1], creds[2]);
        } catch (SQLException e) {
            throw new IllegalStateException("connect error", e);
        }
    }


    Home(String email){
        this.EMAIL = email;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Home");

        String name = getName(CREDS, EMAIL);

        JPanel panel = new JPanel(new GridLayout(2,2));

        JPanel greet_wrapper = new JPanel(new FlowLayout());
        JLabel greet = new JLabel();
        greet.setText("Hi, " + name + "!");
        greet_wrapper.add(greet);

        JPanel button_wrapper = new JPanel(new FlowLayout());
        JButton add_button = new JButton("Add Workout");
        add_button.setActionCommand("add_workout");
        add_button.addActionListener(this);
        button_wrapper.add(add_button);

        panel.add(greet_wrapper);
        panel.add(button_wrapper);

        JScrollPane scroll_pane = new JScrollPane(panel);

        add(scroll_pane);
    }

    Home(){
        this("tester");
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("add_workout")){
            String wname = (String)JOptionPane.showInputDialog(this, "Insert workout name:");
            if(wname != null && wname.length() > 0){
                addWorkout(CREDS, EMAIL, wname);
            }
        }
    }
}
