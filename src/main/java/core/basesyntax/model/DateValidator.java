package core.basesyntax.model;

import core.basesyntax.model.exception.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс DateValidator. Обрабатывает строку даты и проверяет ее формат.
 * Инициализировать поле DATE_FORMAT_DEFAULT.
 * Создать строковый шаблон даты (dd.MM.yyyy).
 * Реализовать методы:
 * - isCorrectDateFormat() проверяет формат даты (dd.mm.yyyy)
 * - isCorrectDateLength() длина даты должна быть "dd.MM.yyyy".length()
 * - isDateNumber() дата может быть только числом
 */

public class DateValidator {
    private final String DATE_FORMAT_DEFAULT = "dd.MM.yyyy";
    private final Pattern PATTERN = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");

    public String getDATE_FORMAT_DEFAULT() {
        return DATE_FORMAT_DEFAULT;
    }

    public boolean isCorrectDateFormat(String date) throws IncorrectDateFormat {
        Matcher matcher = PATTERN.matcher(date);
        if (!matcher.matches()) {
            throw new IncorrectDatePattern("The date should be: [" + DATE_FORMAT_DEFAULT + "]");
        }
        return true;
    }

    public boolean isCorrectDateLength(String date) throws IncorrectDateFormat {
        if (DATE_FORMAT_DEFAULT.length() != date.length()) {
            throw new IncorrectDateLength("The length of the date should be: ["
                    + DATE_FORMAT_DEFAULT.length() + "]");
        }
        return true;
    }

    public boolean isDateNumber(String date) throws IncorrectDateFormat {
        String current = date;
        date = date.replace(".", "");
        if ((int) date.chars().filter(i -> Character.isDigit(i)).count() != date.length()) {
            throw new DateIsNaN("The date can only be a number but was: [" + current + "]");
        }
        return true;
    }
}
