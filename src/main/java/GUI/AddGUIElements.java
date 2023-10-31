package GUI;

import javax.swing.*;
import java.awt.*;
import main.*;

public interface AddGUIElements {
    default void addLabelText(JPanel panel, JLabel label, JTextField field){
        JPanel wrapper = new JPanel(new FlowLayout());
        wrapper.add(label);
        wrapper.add(field);
        panel.add(wrapper);
    }

    default void addLabelPass(JPanel panel, JLabel label, JPasswordField field){
        JPanel wrapper = new JPanel(new FlowLayout());
        wrapper.add(label);
        wrapper.add(field);
        panel.add(wrapper);
    }

    default void addButtons(JPanel panel, JButton[] buttons){
        JPanel wrapper = new JPanel(new FlowLayout());
        for(JButton button : buttons){
            wrapper.add(button);
        }
        panel.add(wrapper);
    }

    default void addWorkout(JPanel panel, Workout workout){
        //TODO
    }
}
