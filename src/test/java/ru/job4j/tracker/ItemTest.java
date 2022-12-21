package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void whenAscendSortItems() {
        Item first = new Item(1, "first");
        Item second = new Item(2, "second");
        Item third = new Item(3, "third");
        List<Item> items = Arrays.asList(second, third, first);
        items.sort(new ItemAscByName());
        List<Item> expected = Arrays.asList(first, second, third);
        assertThat(items).isEqualTo(expected);
    }

    @Test
    void whenDescendSortItems() {
        Item first = new Item(1, "first");
        Item second = new Item(2, "second");
        Item third = new Item(3, "third");
        List<Item> items = Arrays.asList(second, third, first);
        items.sort(new ItemDescByName());
        List<Item> expected = Arrays.asList(third, second, first);
        assertThat(items).isEqualTo(expected);
    }
}