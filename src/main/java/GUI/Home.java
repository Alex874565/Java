package GUI;

import javax.swing.*;

public class Home extends JFrame{
    Home(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Home");

        JLabel greet = new JLabel();
        greet.setText("Hi, tester");
        this.add(greet);
    }

    Home(String name){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Home");

        JLabel greet = new JLabel();
        greet.setText("Hi, " + name + "!");
        this.add(greet);
    }
}
