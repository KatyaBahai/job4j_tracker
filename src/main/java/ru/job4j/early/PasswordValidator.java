package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("A password can't be null.");
        }
        char[] characters = password.toCharArray();
        if (characters.length < 8 || characters.length > 32) {
            throw new IllegalArgumentException(
                    "A password should be [8-32] symbols long."
            );
        }
        if (!hasUpperCase(characters)) {
            throw new IllegalArgumentException(
                    "A password should contain at least one uppercase letter."
            );
        }
        if (!hasLowerCase(characters)) {
            throw new IllegalArgumentException(
                    "A password should contain at least one lowercase letter."
            );
        }
        if (!hasSpecialCharacter(characters)) {
            throw new IllegalArgumentException(
                    "A password should contain at least one special character."
            );
        }
        if (!hasDigit(characters)) {
            throw new IllegalArgumentException(
                    "A password should contain at least one digit."
            );
        }
        if (hasSubstring(password)) {
            throw new IllegalArgumentException(
                    "A password shouldn't contain substrings: qwerty, 12345, password, admin, user."
            );
        }
        return password;
    }

    public static boolean hasUpperCase(char[] characters) {
        for (char character : characters) {
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasLowerCase(char[] characters) {
        for (char character : characters) {
            if (Character.isLowerCase(character)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpecialCharacter(char[] characters) {
        for (char character : characters) {
            if (!Character.isLetterOrDigit(character)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDigit(char[] characters) {
        for (char character : characters) {
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSubstring(String password) {
        String[] substrings = {"qwerty", "12345", "password", "admin", "user"};
        for (String substring : substrings) {
            if (password.toLowerCase().contains(substring)) {
                return true;
            }
        }  return false;
    }

}
