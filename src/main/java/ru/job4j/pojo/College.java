package ru.job4j.pojo;

import java.util.Date;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Erika Worley");
        student.setGroup(105);
        student.setEnrollDate(new Date());
        System.out.println("New student " + student.getName() + " is placed into group #" + student.getGroup() + " on " + student.getEnrollDate());
    }
}
