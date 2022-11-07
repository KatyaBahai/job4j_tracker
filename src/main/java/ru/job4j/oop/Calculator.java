package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int b) {
        return x - b;
    }

    public int divide(int b) {
        return b / x;
    }

    public int sumAllOperation(int b) {
        return sum(b) + multiply(b) + minus(b) + divide(b);
    }

    public static void main(String[] args) {
        int plus = Calculator.sum(10);
        int minus = Calculator.minus(5);
        Calculator calculator = new Calculator();
        int multi = calculator.multiply(5);
        int division = calculator.divide(10);
        int all = calculator.sumAllOperation(20);
    }
}
