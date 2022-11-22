package ru.job4j.early;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordValidatorTest {
    @Test
    void whenPasswordIsValid() {
        String password = "Ln2$mrTY12";
        String expected = "Ln2$mrTY12";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenPasswordIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(null)
        );
        String expected = "A password can't be null.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordLengthLess8() {
        String password = "Ln2$mrT";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password should be [8-32] symbols long.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordLengthMore32() {
        String password = "Ln2$mrTY3245nMdsdfdfPPPg$#dg124531";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password should be [8-32] symbols long.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordNotContainUpperCaseLetter() {
        String password = "ln2$mrty12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password should contain at least one uppercase letter.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordNotContainLowerCaseLetter() {
        String password = "LN2$MRTY12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password should contain at least one lowercase letter.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordNotContainFigure() {
        String password = "LnI$mrTYUo";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password should contain at least one digit.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordNotContainSpecialSymbol() {
        String password = "Ln2pmrTY12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password should contain at least one special character.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordContainSubstringQWERTY() {
        String password = "Ln2$mrQWerTY12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password shouldn't contain substrings: qwerty, 12345, password, admin, user.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordContainSubstring12345() {
        String password = "Ln2$mrTY12345";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password shouldn't contain substrings: qwerty, 12345, password, admin, user.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordContainSubstringPassword() {
        String password = "LnPaSsWoRd2$mrTY12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password shouldn't contain substrings: qwerty, 12345, password, admin, user.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordContainSubstringAdmin() {
        String password = "Ln2$aDmiNrTY12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password shouldn't contain substrings: qwerty, 12345, password, admin, user.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenPasswordContainSubstringUser() {
        String password = "Ln2$mUSerTY12";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PasswordValidator.validate(password)
        );
        String expected = "A password shouldn't contain substrings: qwerty, 12345, password, admin, user.";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }
}