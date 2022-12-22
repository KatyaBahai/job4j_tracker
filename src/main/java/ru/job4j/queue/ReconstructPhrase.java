package ru.job4j.queue;

import java.util.*;

public class ReconstructPhrase {

    private final Queue<Character> descendingElements;

    private final Queue<Character> evenElements;

    public ReconstructPhrase(Queue<Character> descendingElements, Queue<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = evenElements.size();
        for (int index = 0; index < size; index++) {
            if (index % 2 == 0) {
             stringBuilder.append(evenElements.poll());
            } else {
                evenElements.poll();
            }
        }
        return stringBuilder.toString();
    }

    private String getDescendingElements() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = descendingElements.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(descendingElements.poll());
        }
        return stringBuilder.reverse().toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}