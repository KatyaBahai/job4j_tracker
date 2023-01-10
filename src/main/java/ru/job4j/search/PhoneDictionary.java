package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> nameKey = (person) -> person.getName().contains(key);
        Predicate<Person> surnameKey = (person) -> person.getSurname().contains(key);
        Predicate<Person> phoneKey = (person) -> person.getPhone().contains(key);
        Predicate<Person> addressKey = (person) -> person.getAddress().contains(key);
        Predicate<Person> combine = nameKey.or(surnameKey).or(phoneKey).or(addressKey);
        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}