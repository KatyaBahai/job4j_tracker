package ru.job4j.tracker;

import java.util.Comparator;

public class ItemDescByName implements Comparator<Item> {

    @Override
    public int compare(Item itemFirst, Item itemSecond) {
        return itemSecond.getName().compareTo(itemFirst.getName());
    }
}
