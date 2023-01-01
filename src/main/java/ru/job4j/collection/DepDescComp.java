package ru.job4j.collection;

import java.util.*;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        List<String> deptFirst = Arrays.asList(s1.split("/", 2));
        List<String> deptSecond = Arrays.asList(s2.split("/", 2));
        int tmp = deptSecond.get(0).compareTo(deptFirst.get(0));
        if (tmp == 0) {
            tmp = s1.compareTo(s2);
        }
        return tmp;
    }
}
