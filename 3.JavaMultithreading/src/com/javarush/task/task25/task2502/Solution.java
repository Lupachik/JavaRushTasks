package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car(){

                wheels = new ArrayList<>();
                if(loadWheelNamesFromDB().length != Wheel.values().length) throw new IllegalArgumentException();
                else
                    for (int i = 0; i < loadWheelNamesFromDB().length; i++)
                    wheels.add(Wheel.valueOf(loadWheelNamesFromDB()[i]));



        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}
