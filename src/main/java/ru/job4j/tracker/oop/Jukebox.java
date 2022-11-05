package ru.job4j.tracker.oop;

public class Jukebox {
    public void music(int position) {
        if (position == 1) {
            System.out.println("Пусть бегут неуклюже");
        } else if (position == 2) {
            System.out.println("Спокойной ночи");
        }
        else {
            System.out.println("Песня не найдена");
        }
    }
    public static void main(String[] args) {
        Jukebox jukebox1 = new Jukebox();
        jukebox1.music(1);
        jukebox1.music(2);
        jukebox1.music(3);

    }
}
