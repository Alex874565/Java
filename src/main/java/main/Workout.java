package main;

import java.util.*;

public class Workout {
    String name, date = null;
    Integer ex_num;
    List<Exercise> exercises;

    public Workout(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getDate(){
        return this.date;
    }

    public Exercise getExercise(Integer i){
        return exercises.get(i);
    }

    public Integer getExNum(){
        return this.ex_num;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setExNum(Integer ex_num){
        this.ex_num = ex_num;
    }

    public void addExercise(Exercise exercise){
        ex_num += 1;
        exercises.add(exercise);
    }
}
