package ru.job4j.ex;

public class FindEl {
    public static int indexOf (String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int index = 0; index < value.length; index++) {
            if (key.equals(value[index])) {
                rsl = index;
                break;
            }
        }
        if (rsl < 0) {
            throw new ElementNotFoundException("The element was not found");
        }
        return rsl;
    }

    public static void main(String[] args) {
       int index = -1;
        try {
            index = indexOf(new String[] {"pepper", "carrot", "cinnamon"}, "cucumber");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(index);
    }
}
