package ru.job4j.oop;

public class Plane implements Vehicle {
    public void move() {
        System.out.println("The plane takes off and flies into the air.");
    }

    public void startEngine() {
        System.out.println("Press the start button");
    }
}
