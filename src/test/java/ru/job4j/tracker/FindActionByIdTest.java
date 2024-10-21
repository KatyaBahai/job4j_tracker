package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindActionByIdTest {

    @Test
    void whenFindByIdThenFound() {
        Output output = new StubOutput();
        FindActionById findByIdAction = new FindActionById(output);

        Store tracker = new MemTracker();
        Item item = new Item("ItemName");
        tracker.add(item);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo("=== Find item by id ===" + ln
                + item + ln);
    }

    @Test
    void whenFindNonExistingIdThenNotFound() {
        Output output = new StubOutput();
        FindActionById findByIdAction = new FindActionById(output);

        Store tracker = new MemTracker();

        Input input = mock(Input.class);
        int id = 1;
        when(input.askInt(any(String.class))).thenReturn(id);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + id + " не найдена." + ln);
    }
}