package ru.job4j.io;

import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Я великий Оракул. Что ты хочешь узнать? ");
        String question = input.nextLine();
        int random = new Random().nextInt(3);
        String answer;
        switch(random) {
            case 0 -> answer = "Да";
            case 1 -> answer = "Нет";
            default -> answer = "Может быть";
        }
        System.out.println(answer);
    }
}
