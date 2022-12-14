package ru.job4j.ex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactTest {
    @Test
    void whenException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Fact().calc(-1);
                });
        assertThat(exception.getMessage()).isEqualTo("N can't be less than 0");
        }

    @Test
    public void whenNumber5Factorial120() {
        int number = 5;
        int expected = 120;
        int result = new Fact().calc(number);
        assertThat(result).isEqualTo(expected);
    }
}