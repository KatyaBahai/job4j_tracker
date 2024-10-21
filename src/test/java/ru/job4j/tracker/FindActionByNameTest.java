package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindActionByNameTest {

    @Test
    void whenFindByNameThenFound() {
        Output output = new StubOutput();
        FindActionByName findByNameAction = new FindActionByName(output);

        Store tracker = new MemTracker();
        Item item = new Item("ItemName");
        tracker.add(item);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo("=== Find items by name ===" + ln
                + item + ln);
    }

    @Test
    void whenFindNonExistingNameThenNotFound() {
        Output output = new StubOutput();
        FindActionByName findByNameAction = new FindActionByName(output);

        Store tracker = new MemTracker();
        Item item = new Item("ItemName");
        tracker.add(item);

        Input input = mock(Input.class);
        String randomName = "random";
        when(input.askStr(any(String.class))).thenReturn(randomName);

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: " + randomName + " не найдены." + ln);
    }
}