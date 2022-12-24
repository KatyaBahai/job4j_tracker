package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        Character[] charsFirst = new Character[left.length()];
        for (int i = 0; i < charsFirst.length; i++) {
            charsFirst[i] = left.charAt(i);
        }
        Character[] charsSecond = new Character[right.length()];
        for (int i = 0; i < charsSecond.length; i++) {
            charsSecond[i] = right.charAt(i);
        }
        int size = Math.min(left.length(), right.length());
        int rsl = 0;
        for (int i = 0; i < size; i++) {
            rsl = Character.compare(charsFirst[i], charsSecond[i]);
            if (rsl != 0) {
                return rsl;
            }
        }
        return Integer.compare(left.length(), right.length());
    }
}