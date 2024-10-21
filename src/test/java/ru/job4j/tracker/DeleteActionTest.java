package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteActionTest {

    @Test
    void whenDeleteThenDeleted() {
        Output output = new StubOutput();
        DeleteAction deleteAction = new DeleteAction(output);

        Store tracker = new MemTracker();
        Item itemToDelete = new Item("itemToDelete");
        tracker.add(itemToDelete);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo("=== Delete item ===" + ln
        + "Заявка удалена успешно." + ln);
    }

    @Test
    void whenDeleteNonExistingThenNotDeleted() {
        Output output = new StubOutput();
        DeleteAction deleteAction = new DeleteAction(output);

        Store tracker = new MemTracker();
        Item itemToDelete = new Item("itemToDelete");
        tracker.add(itemToDelete);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(4);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                + "Ошибка удаления заявки." + ln);
    }
}