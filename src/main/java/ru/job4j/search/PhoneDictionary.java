package ru.job4j.search;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            String data = person.getName() + person.getSurname()
                    + person.getPhone() + person.getAddress();
            if (data.contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
