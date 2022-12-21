package ru.job4j.tracker;

import java.util.Comparator;

public class ItemAscByName implements Comparator<Item> {

    @Override
    public int compare(Item itemFirst, Item itemSecond) {
        return itemFirst.getName().compareTo(itemSecond.getName());
    }
}

