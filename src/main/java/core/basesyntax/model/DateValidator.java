package core.basesyntax.model;

import core.basesyntax.model.exception.DateIsNaN;
import core.basesyntax.model.exception.IncorrectDateFormat;
import core.basesyntax.model.exception.IncorrectDateLength;
import core.basesyntax.model.exception.IncorrectDatePattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс DateValidator. Обрабатывает строку даты и проверяет ее формат.
 * Инициализировать поле dateFormatDefault.
 * Создать строковый шаблон даты (dd.MM.yyyy).
 * Реализовать методы:
 * - isCorrectDateFormat() проверяет формат даты (dd.mm.yyyy)
 * - isCorrectDateLength() длина даты должна быть "dd.MM.yyyy".length()
 * - isDateNumber() дата может быть только числом
 */

public class DateValidator {
    private String dateFormatDefault = "dd.MM.yyyy";
    private Pattern regex = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");

    public String getDateFormatDefault() {
        return dateFormatDefault;
    }

    public boolean isCorrectDateFormat(String date) throws IncorrectDateFormat {
        Matcher matcher = regex.matcher(date);
        if (!matcher.matches()) {
            throw new IncorrectDatePattern("The date should be: [" + dateFormatDefault + "]");
        }
        return true;
    }

    public boolean isCorrectDateLength(String date) throws IncorrectDateFormat {
        if (dateFormatDefault.length() != date.length()) {
            throw new IncorrectDateLength("The length of the date should be: ["
                    + dateFormatDefault.length() + "]");
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
