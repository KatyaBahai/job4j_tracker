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
        if (hasSubstring(password)) {
            throw new IllegalArgumentException(
                    "A password shouldn't contain substrings: qwerty, 12345, password, admin, user."
            );
        }
        if (!hasCorrectChars(characters)) {
            throw new IllegalArgumentException(
                    "You must have a least one uppercase letter, " +
                            "one lowercase letter, one special character " +
                            "and one digit."
            );
        }
        return password;
    }

    public static boolean hasSubstring(String password) {
        String[] substrings = {"qwerty", "12345", "password", "admin", "user"};
        for (String substring : substrings) {
            if (password.toLowerCase().contains(substring)) {
                return true;
            }
        }  return false;
    }

    public static boolean hasCorrectChars(char[] characters) {
        int hasUpperCase = 0;
        int hasLowerCase = 0;
        int hasDigit = 0;
        int hasSpecialChar = 0;

        for (char character : characters) {
            if (Character.isUpperCase(character)) {
                hasUpperCase++;
            }
            if (Character.isLowerCase(character)) {
                hasLowerCase++;
            }
            if (Character.isDigit(character)) {
                hasDigit++;
            }
            if (!Character.isLetterOrDigit(character)) {
                hasSpecialChar++;
            }
            if (hasUpperCase > 0 && hasLowerCase > 0 && hasDigit > 0 && hasSpecialChar >0) {
                return true;
            }
        }
        if (hasUpperCase == 0) {
            throw new IllegalArgumentException(
                    "A password should contain at least one uppercase letter."
            );
        }
        if (hasLowerCase == 0) {
            throw new IllegalArgumentException(
                    "A password should contain at least one lowercase letter."
            );
        }
        if (hasDigit == 0) {
            throw new IllegalArgumentException(
                    "A password should contain at least one digit."
            );
        }
        if (hasSpecialChar == 0) {
            throw new IllegalArgumentException(
                    "A password should contain at least one special character."
            );
        }
        return false;
    }
}
