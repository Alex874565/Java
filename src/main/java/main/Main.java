package main;

import GUI.LoginPage;

public class Main {

    static final public String URL = "jdbc:mysql://localhost:3306/GymWorkoutPlanner",
                        DB_USER = "gymworkoutplanner",
                        DB_PASS = "GymWorkoutPlanner1.",
                        CREDS[] = {URL, DB_USER, DB_PASS};
    public static void main(String[] args) {
        
        LoginPage form = new LoginPage();
        form.setVisible(true);
        //Home home = new Home();
        //home.setVisible(true);
    }
}