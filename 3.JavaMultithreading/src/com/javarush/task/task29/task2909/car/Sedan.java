package com.javarush.task.task29.task2909.car;

public class Sedan extends Car {
    public Sedan(int numberOfPassengers) {
        super(Car.SEDAN, numberOfPassengers);
    }

    public static final int MAX_SEDAN_SPEED = 120;

    @Override
    public int getMaxSpeed() {

        return MAX_SEDAN_SPEED;
    }
}
