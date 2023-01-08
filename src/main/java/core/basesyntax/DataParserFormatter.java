package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataParserFormatter {
    private final DateTimeFormatter dateParserFormatter;

    public DataParserFormatter(String formatString) {
        dateParserFormatter = DateTimeFormatter.ofPattern(formatString);
    }

    public LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, dateParserFormatter);
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable!%n", date);
            throw new DateTimeParseException("Illegal data format!", date, 1);
        }
    }
    public int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.printf("%s is not parsable!%n", number);
            throw new NumberFormatException(number + " is not parsable!");
        }
    }

    public String formatDate(LocalDate localDate) {
        try {
            return localDate.format(dateParserFormatter);
        } catch (DateTimeParseException e) {
            System.out.printf("%s can't be formatted!%n", localDate);
            throw new DateTimeParseException(localDate + " can't be formatted!", "", 1);
        }
    }
}
