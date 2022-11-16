package ru.job4j.oop;

public class Train implements Vehicle {
    public void move() {
        System.out.println("The train goes on tracks.");
    }
    public void startEngine() {
        System.out.println("Put some wood into the furnace.");
    }
}
