package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void info() {
        System.out.println("Active: " + active);
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        error.info();
        Error errorOne = new Error(true, 10, "pending");
        errorOne.info();
        Error errorTwo = new Error(true, 1, "undefined");
        errorTwo.info();
        Error errorThree = new Error(false, 0, "No error found");
        errorThree.info();
    }

}
