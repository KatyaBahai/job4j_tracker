package ru.job4j.stream;

import java.util.Comparator;

public class TupleComparator implements Comparator<Tuple> {
    @Override
    public int compare(Tuple o1, Tuple o2) {
        return Double.compare(o1.score(), o2.score());
    }
}
