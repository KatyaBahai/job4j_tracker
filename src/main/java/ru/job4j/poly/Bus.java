package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("Off we go!");
    }

    @Override
    public void passengers(int number) {
        System.out.println("Number of passengers: " + number);
    }

    @Override
    public double fuel(double gasAmount) {
        return gasAmount * 1.98;
    }
}
